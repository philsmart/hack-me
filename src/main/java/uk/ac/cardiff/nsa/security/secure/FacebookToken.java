package uk.ac.cardiff.nsa.security.secure;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by philsmart on 06/03/2017.
 */
public class FacebookToken extends AbstractAuthenticationToken {


    public FacebookToken(Collection<? extends GrantedAuthority> authorities){
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
