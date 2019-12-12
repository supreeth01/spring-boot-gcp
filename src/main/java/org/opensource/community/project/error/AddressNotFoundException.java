package org.opensource.community.project.error;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * The Class AddressNotFoundException.
 */
public class AddressNotFoundException extends BaseAddressUncheckedException {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(AddressNotFoundException.class);

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
	 * Instantiates a new address not found exception.
	 *
	 * @param message the message
	 */
    public AddressNotFoundException(String message) {
        super(ADDRESS_ERROR_CODES.ADDRESS_NOT_FOUND, message);
        logger.trace("-> AddressNotFoundException: {}", message);
    }

    /**
	 * Instantiates a new address not found exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
    public AddressNotFoundException(String message, Exception e) {
        super(ADDRESS_ERROR_CODES.ADDRESS_NOT_FOUND, message, e);
        logger.trace("-> AddressNotFoundException: {} {}", message, e);
    }
}