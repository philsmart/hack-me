package uk.ac.cardiff.nsa.security.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by philsmart on 03/02/2017. Example only.
 */
@Component
public class Account {


    private String username = "phil";


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
