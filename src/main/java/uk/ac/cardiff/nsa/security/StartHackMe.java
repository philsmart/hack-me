package uk.ac.cardiff.nsa.security;

import javax.faces.webapp.FacesServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "uk.ac.cardiff" })
public class StartHackMe {

    private static final Logger log = LoggerFactory.getLogger(StartHackMe.class);

    public static void main(String[] args) {
        log.info("Starting the hack-me epic web application");

        SpringApplication.run(StartHackMe.class, args);
    }


    /**
     * Register the {@link FacesServlet} within the current {@link org.springframework.boot.web.servlet.ServletContextInitializer},
     * and hence with the v3 {@link javax.servlet.Servlet} provider. This maps all URL patterns *.xhtml to the {@link FacesServlet}.
     *
     * @return a {@link ServletRegistrationBean} with registered {@link FacesServlet}
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        final FacesServlet servlet = new FacesServlet();

        final ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, new String[]{"*.xhtml"});
        servletRegistrationBean.setName("FacesServlet");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }
}
