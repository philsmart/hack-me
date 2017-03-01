package uk.ac.cardiff.nsa.security.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;
import java.util.Map;

/**
 * Created by philsmart on 03/02/2017. Example only.
 */
@Component
@RequestScope
public class Account {


    private String username = "phil";

    /**
     * Store information about the account releating to the username {@code username}.
     */
    private List<Map<String,Object>> accountInformation;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Map<String, Object>> getAccountInformation() {
        return accountInformation;
    }

    public void setAccountInformation(List<Map<String, Object>> accountInformation) {
        this.accountInformation = accountInformation;
    }


}
