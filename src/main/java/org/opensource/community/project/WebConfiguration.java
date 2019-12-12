package org.opensource.community.project;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * The Class WebConfiguration. Additional web configuration is done in this class.
 */
@Configuration
public class WebConfiguration {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(WebConfiguration.class);

    /**
     * H2 servlet registration.
     *
     * @return the servlet registration bean
     */
    @Bean
    ServletRegistrationBean h2servletRegistration() {
        logger.trace("-> h2servletRegistration");
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
        registrationBean.addUrlMappings("/console/*");
        return registrationBean;
    }
}