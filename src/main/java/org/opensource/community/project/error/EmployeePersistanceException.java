package org.opensource.community.project.error;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class EmployeePersistanceException extends BaseEmployeeUncheckedException {

    private static final Logger logger = LoggerFactory.getLogger(EmployeePersistanceException.class);

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
	 * Instantiates a new employee persist exception.
	 *
	 * @param message the message
	 */
    public EmployeePersistanceException(String message) {
        super(EMPLOYEE_ERROR_CODES.EMPLOYEE_PERSIST_ERROR, message);
        logger.trace("-> EmployeePersistanceException: {}", message);
    }

    /**
	 * Instantiates a new employee persist exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
    public EmployeePersistanceException(String message, Exception e) {
        super(EMPLOYEE_ERROR_CODES.EMPLOYEE_PERSIST_ERROR, message, e);
        logger.trace("-> EmployeePersistanceException: {} {}", message, e);
    }
}