package uk.ac.cardiff.nsa.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.ac.cardiff.nsa.security.dao.AccountRepository;
import uk.ac.cardiff.nsa.security.jsf.ConfigureJSFContextParameters;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;


@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Inject
    private AccountRepository repository;

    @RequestMapping(value = "/users", method= RequestMethod.GET, produces ="application/json" )
    public List<Map<String,Object>> findUser(@RequestParam("username") String username){
        log.debug("Finding user [{}}",username);
        return repository.findUserAccount(username);

    }
}
