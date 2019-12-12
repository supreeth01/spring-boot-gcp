package org.opensource.community.project.error;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class DepartmentPersistanceException extends BaseDepartmentUncheckedException {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentPersistanceException.class);

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
	 * Instantiates a new department persist exception.
	 *
	 * @param message the message
	 */
    public DepartmentPersistanceException(String message) {
        super(DEPARTMENT_ERROR_CODES.DEPARTMENT_PERSIST_ERROR, message);
        logger.trace("-> DepartmentPersistanceException: {}", message);
    }

    /**
	 * Instantiates a new department persist exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
    public DepartmentPersistanceException(String message, Exception e) {
        super(DEPARTMENT_ERROR_CODES.DEPARTMENT_PERSIST_ERROR, message, e);
        logger.trace("-> DepartmentPersistanceException: {} {}", message, e);
    }
}