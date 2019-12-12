package org.opensource.community.project.error;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * The Class DepartmentNotFoundException.
 */
public class DepartmentNotFoundException extends BaseDepartmentUncheckedException {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(DepartmentNotFoundException.class);

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
	 * Instantiates a new department not found exception.
	 *
	 * @param message the message
	 */
    public DepartmentNotFoundException(String message) {
        super(DEPARTMENT_ERROR_CODES.DEPARTMENT_NOT_FOUND, message);
        logger.trace("-> DepartmentNotFoundException: {}", message);
    }

    /**
	 * Instantiates a new department not found exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
    public DepartmentNotFoundException(String message, Exception e) {
        super(DEPARTMENT_ERROR_CODES.DEPARTMENT_NOT_FOUND, message, e);
        logger.trace("-> DepartmentNotFoundException: {} {}", message, e);
    }
}