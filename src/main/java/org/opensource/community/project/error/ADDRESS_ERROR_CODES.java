package org.opensource.community.project.error;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * The Interface ADDRESS_ERROR_CODES.
 */
public interface ADDRESS_ERROR_CODES {

    /** The gen invalid input. */
    public String GEN_INVALID_INPUT = "GEN_INVALID_INPUT";

    /** The address not found. */
    public String ADDRESS_NOT_FOUND = "ADDRESS_NOT_FOUND";

    /** Issue with primary key */
    public String PRIMARY_KEY_INAVLID = "PRIMARY_KEY_INAVLID";

    /** Issue with Unique key */
    public String ADDRESS_PERSIST_ERROR = "ADDRESS_PERSIST_ERROR";
}