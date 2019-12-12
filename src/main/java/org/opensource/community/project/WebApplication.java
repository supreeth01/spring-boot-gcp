package org.opensource.community.project;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * The Class WebApplication. Employee application starts from this Spring Boot
 * Main class.
 */
@SpringBootApplication
public class WebApplication extends WebMvcConfigurerAdapter {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(WebApplication.class);

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/apidoc", "/apidoc/index.html");
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        logger.trace("-> main {}", (Object[]) args);
        SpringApplication.run(WebApplication.class, args);
    }
}