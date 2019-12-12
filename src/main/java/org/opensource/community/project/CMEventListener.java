package org.opensource.community.project;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
@Component
public class CMEventListener implements ApplicationListener<ApplicationReadyEvent> {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(CMEventListener.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            int port = applicationContext.getBean(Environment.class).getProperty("server.port", Integer.class, 8080);
            System.out.printf("**Java Live Development Server is listening on %s:%d,open your browser on http://%s:%d/apidoc**", ip, port, ip, port);
        } catch (UnknownHostException e) {
            logger.error("Exception occurred while fetching server ip and port " + e.getMessage());
        }
    }
}