package org.opensource.community.project.error;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class AddressPersistanceException extends BaseAddressUncheckedException {

    private static final Logger logger = LoggerFactory.getLogger(AddressPersistanceException.class);

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
	 * Instantiates a new address persist exception.
	 *
	 * @param message the message
	 */
    public AddressPersistanceException(String message) {
        super(ADDRESS_ERROR_CODES.ADDRESS_PERSIST_ERROR, message);
        logger.trace("-> AddressPersistanceException: {}", message);
    }

    /**
	 * Instantiates a new address persist exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
    public AddressPersistanceException(String message, Exception e) {
        super(ADDRESS_ERROR_CODES.ADDRESS_PERSIST_ERROR, message, e);
        logger.trace("-> AddressPersistanceException: {} {}", message, e);
    }
}