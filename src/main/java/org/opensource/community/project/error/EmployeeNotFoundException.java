package org.opensource.community.project.error;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * The Class EmployeeNotFoundException.
 */
public class EmployeeNotFoundException extends BaseEmployeeUncheckedException {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(EmployeeNotFoundException.class);

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
	 * Instantiates a new employee not found exception.
	 *
	 * @param message the message
	 */
    public EmployeeNotFoundException(String message) {
        super(EMPLOYEE_ERROR_CODES.EMPLOYEE_NOT_FOUND, message);
        logger.trace("-> EmployeeNotFoundException: {}", message);
    }

    /**
	 * Instantiates a new employee not found exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
    public EmployeeNotFoundException(String message, Exception e) {
        super(EMPLOYEE_ERROR_CODES.EMPLOYEE_NOT_FOUND, message, e);
        logger.trace("-> EmployeeNotFoundException: {} {}", message, e);
    }
}