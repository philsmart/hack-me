package uk.ac.cardiff.nsa.security.controller;

import com.sun.deploy.net.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.ac.cardiff.nsa.security.dao.AccountRepository;
import uk.ac.cardiff.nsa.security.jsf.ConfigureJSFContextParameters;

import javax.inject.Inject;
import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value="/api/v1")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Inject
    private AccountRepository repository;

    @RequestMapping(value = "/users", method= RequestMethod.GET, produces ="application/json" )
    public List<Map<String,Object>> findUser(@RequestParam("username") String username){
        log.debug("Finding user [{}]",username);

        return  repository.findUserAccount(username);


    }

    @RequestMapping(value = "/users", method= RequestMethod.POST, produces ="application/json" )
    public ResponseEntity<Void> addUser(@RequestBody String body){
        log.debug("Adding user [{}]",body);

        return  new ResponseEntity<Void>(HttpStatus.OK);


    }
}
