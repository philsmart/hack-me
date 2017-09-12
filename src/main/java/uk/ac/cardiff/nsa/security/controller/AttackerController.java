package uk.ac.cardiff.nsa.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import uk.ac.cardiff.nsa.security.dao.AccountRepository;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value="/")
public class AttackerController {

    private static final Logger log = LoggerFactory.getLogger(AttackerController.class);

    @Inject
    private AccountRepository repository;


    @RequestMapping(value = "/sendme", method= RequestMethod.GET )
    public  ResponseEntity<Void> findUser(@RequestParam("cookie") String cookie, HttpServletRequest request, HttpServletResponse response){
        log.debug("Found cookie [{}]",cookie);

        return  new ResponseEntity<Void>(HttpStatus.OK);


    }


}
