package uk.ac.cardiff.nsa.security.secure.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApiUserDetailsService implements UserDetailsService {

	/** class logger */
	private static final Logger log = LoggerFactory.getLogger(ApiUserDetailsService.class);



	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		log.info("Looking up userdetails for [{}]", username);



		throw new UsernameNotFoundException(
				"Username does not exist in properties file, hence none found in the ApiUsersRepository");

	}

}
