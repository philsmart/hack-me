package uk.ac.cardiff.nsa.security.secure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import uk.ac.cardiff.nsa.security.dao.AccountRepository;

import javax.inject.Inject;
import javax.sql.DataSource;

/**
 * Created by philsmart on 06/02/2017.
 */

@EnableWebSecurity
public class SecurityConfiguration   {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Inject
    private DataSource ds;



    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        log.debug("Setting up auth manager");

        auth.jdbcAuthentication().dataSource(getDs()).usersByUsernameQuery(
                "select username,password, enabled from users where username=?")
                .authoritiesByUsernameQuery(
                        "select username, role from user_roles where username=?");;

    }

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Autowired
         OAuth2AuthenticationFilter oauthFilter;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                   .antMatcher("/api/v1/*").authorizeRequests().anyRequest().authenticated().and().httpBasic();

//            log.info("OAuth filter is {}",oauthFilter);
//            http.addFilterBefore(oauthFilter,WebAsyncManagerIntegrationFilter.class)
//                    .antMatcher("/facebook/**").authorizeRequests().anyRequest().authenticated();
        }
    }

//    @Configuration
//    public static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .authorizeRequests()
//                    .anyRequest().authenticated()
//                    .and()
//                    .formLogin();
//        }
//    }




    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }
}
