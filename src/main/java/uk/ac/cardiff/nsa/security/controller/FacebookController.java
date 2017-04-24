package uk.ac.cardiff.nsa.security.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Optional;


@Controller
@RequestMapping(value = "/fb")
public class FacebookController {

    private static final Logger log = LoggerFactory.getLogger(FacebookController.class);

    private final String client_id = "1982088195406872";

    private final String client_secret = "edfb4e270c477ec25b9f40e1d2bda7cc";


    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json")
    public String getToken(@RequestParam("code") String code, HttpServletResponse response) {
        log.info("Retrieved facebook code [{}]", code);

        Optional<String> token = getAccessToken(code);
        log.info("Has Access Token [{}]",token.orElse("None"));

        Optional<JSONObject> me = Optional.empty();
        if (token.isPresent()){
            me = getMe(token.get());

        }




        return "redirect:../facebook-logged-in.xhtml?person="+me.get().getString("name")+"&email="+me.get().getString("email");


    }

    private Optional<JSONObject> getMe(String accessToken){

        URL fbGraphURL;
        try {
            fbGraphURL = new URL("https://graph.facebook.com/me?fields=id,name,email&" + accessToken);
        } catch (MalformedURLException e) {
            log.error("Could not form me graph call",e);
            return Optional.empty();
        }
        Optional<String> data =getData(fbGraphURL);

        if (data.isPresent()==false){
            return Optional.empty();
        }

        JSONObject meJson = new JSONObject(data.get());

        log.info("Has me data {}",meJson);

        return Optional.of(meJson);
    }

    private Optional<String> getAccessToken(String code) {
        String accessToken = null;
        URL fbGraphURL;
        try {

            String graphUrl = "https://graph.facebook.com/oauth/access_token?"
                    + "client_id=" + client_id
                    + "&client_secret=" + client_secret + "&code=" + code+"&redirect_uri=http://localhost:8080/fb/login";

            fbGraphURL = new URL(graphUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid code received " + e);
        }


        Optional<String> data =getData(fbGraphURL);

        if (data.isPresent()==false){
            return Optional.empty();
        }

        accessToken = data.get();
        if (accessToken.startsWith("{")) {
            log.debug("Access token is not value");
            return Optional.empty();
        }

        return Optional.of(accessToken);
    }


    private Optional<String> getData(URL graphUrl){
        StringBuffer b = null;
        URLConnection fbConnection;
        try {
            fbConnection = graphUrl.openConnection();
            BufferedReader in;
            in = new BufferedReader(new InputStreamReader(
                    fbConnection.getInputStream()));
            String inputLine;
            b = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                b.append(inputLine + "\n");
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(b.toString());
    }

}
