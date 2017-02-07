package uk.ac.cardiff.nsa.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uk.ac.cardiff.nsa.security.StartHackMe;
import uk.ac.cardiff.nsa.security.dao.AccountRepository;
import uk.ac.cardiff.nsa.security.model.AccountInfo;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Simple AccountService to perform basic CRUD methods over a suitable DAO.
 *
 * @Service over @Component - more correct semantically, functionally equivelant currently.
 */
@Service
public class AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    @Inject
    private AccountRepository repository;





    @PostConstruct
    public void init(){
        Objects.requireNonNull(repository);

    }

    public void findUserAccount(final AccountInfo user){


        List<Map<String,Object>> userInfo= repository.findUserAccount(user.getUsername());

        user.setAccountInformation(userInfo);




    }
}
