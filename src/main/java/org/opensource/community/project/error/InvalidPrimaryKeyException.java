package org.opensource.community.project.error;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class InvalidPrimaryKeyException extends BaseEmployeeUncheckedException {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(EmployeeNotFoundException.class);

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
	 * Instantiates a new invalid primary key exception.
	 *
	 * @param message the message
	 */
    public InvalidPrimaryKeyException(String message) {
        super(EMPLOYEE_ERROR_CODES.PRIMARY_KEY_INAVLID, message);
        logger.trace("-> InvalidPrimaryKeyException: {}", message);
    }

    /**
	 * Instantiates a new invalid primary key exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
    public InvalidPrimaryKeyException(String message, Exception e) {
        super(EMPLOYEE_ERROR_CODES.PRIMARY_KEY_INAVLID, message, e);
        logger.trace("-> InvalidPrimaryKeyException: {} {}", message, e);
    }
}