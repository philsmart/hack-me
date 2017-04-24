package uk.ac.cardiff.nsa.security.secure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import uk.ac.cardiff.nsa.security.StartHackMe;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by philsmart on 06/03/2017.
 */
@Component
public class OAuth2AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger log = LoggerFactory.getLogger(OAuth2AuthenticationFilter.class);

    public  OAuth2AuthenticationFilter(String defaultFilterProcessesUrl){
        super(defaultFilterProcessesUrl);
        setAuthenticationManager(new NoopAuthenticationManager());


    }

    public  OAuth2AuthenticationFilter(){
        super("/facebook/**");
        setAuthenticationManager(new NoopAuthenticationManager());

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.info("Attempting simple OAuth Authentication Filter");

        //authorities should be loaded here...
        PreAuthenticatedAuthenticationToken authN = new PreAuthenticatedAuthenticationToken("philsm","phils");




        authN.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authN);

//        BadCredentialsException bad = new BadCredentialsException("Could not obtain user details from token");
//        throw bad;

        return authN;

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("Successfull authentication");
        super.successfulAuthentication(request, response, chain, authResult);
        // Nearly a no-op, but if there is a ClientTokenServices then the token will now be stored

    }

    private static class NoopAuthenticationManager implements AuthenticationManager {

        @Override
        public Authentication authenticate(Authentication authentication)
                throws AuthenticationException {
            throw new UnsupportedOperationException("No authentication should be done with this AuthenticationManager");
        }

    }
}
