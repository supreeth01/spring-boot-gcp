package org.opensource.community.project.common;

import java.util.Date;

import org.opensource.community.project.dto.DepartmentErrorResponseDTO;
import org.opensource.community.project.error.DepartmentHttpErrorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * This is the base class for all Department controllers. 
 */
public class BaseDepartmentController {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(BaseDepartmentController.class);

    /**
	 * Common exception handler.
	 *
	 * @param t the t
	 * @param response the response
	 * @return the department error response DTO
	 */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public DepartmentErrorResponseDTO exceptionHandler(Throwable t, HttpServletResponse response) {
        return DepartmentHttpErrorMapper.mapException(t, response);
    }
}