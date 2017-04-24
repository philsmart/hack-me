package uk.ac.cardiff.nsa.security;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;

@SpringBootApplication
@ComponentScan(basePackages = { "uk.ac.cardiff" })
public class StartHackMe  implements EmbeddedServletContainerCustomizer {

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
      Authentication a;
        return servletRegistrationBean;
    }



    /**
     * Set the {@link Context#setUseHttpOnly(boolean)} method to false in Tomcat. This allows the capture of cookies in Javascript.
     * @param container
     */
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        ((TomcatEmbeddedServletContainerFactory) container).addContextCustomizers(new TomcatContextCustomizer()
        {
            @Override
            public void customize(Context context)
            {
                log.info("Setting http only to false");

                context.setUseHttpOnly(false);

            }
        });
    }
}
