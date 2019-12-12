package org.opensource.community.project;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * The Class SwaggerConfig. Swagger is configured in application context init
 * in this class.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

    /**
	 * Instantiates a new swagger config.
	 */
    public SwaggerConfig() {
        System.out.println("-> SwaggerConfig");
    }

    /**
	 * Custom implementation.
	 *
	 * @return the docket
	 */
    @Bean
    public Docket customImplementation() {
        logger.trace("-> customImplementation");
        List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
        responseMessages.add(new ResponseMessage(200, "Success", null));
        responseMessages.add(new ResponseMessage(400, "Bad Request", null));
        responseMessages.add(new ResponseMessage(401, "Unauthorized (Not logged in or invalid session or resource cannot be accessed)", null));
        responseMessages.add(new ResponseMessage(404, "Requested resource is not found", null));
        responseMessages.add(new ResponseMessage(500, "Internal Server Error", null));
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().build().globalResponseMessage(RequestMethod.GET, responseMessages).globalResponseMessage(RequestMethod.POST, responseMessages).globalResponseMessage(RequestMethod.PUT, responseMessages).globalResponseMessage(RequestMethod.DELETE, responseMessages);
    }

    /**
	 * Api info.
	 *
	 * @return the api info
	 */
    private ApiInfo apiInfo() {
        logger.trace("-> apiInfo");
        ApiInfo apiInfo = new ApiInfo(null, null, null, null, null, null, null);
        return apiInfo;
    }
}