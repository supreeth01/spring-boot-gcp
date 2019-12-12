package org.opensource.community.project.common;

import java.util.Date;

import org.opensource.community.project.dto.AddressErrorResponseDTO;
import org.opensource.community.project.error.AddressHttpErrorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * This is the base class for all Address controllers. 
 */
public class BaseAddressController {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(BaseAddressController.class);

    /**
	 * Common exception handler.
	 *
	 * @param t the t
	 * @param response the response
	 * @return the address error response DTO
	 */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public AddressErrorResponseDTO exceptionHandler(Throwable t, HttpServletResponse response) {
        return AddressHttpErrorMapper.mapException(t, response);
    }
}