package org.opensource.community.project.error;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * The Interface DEPARTMENT_ERROR_CODES.
 */
public interface DEPARTMENT_ERROR_CODES {

    /** The gen invalid input. */
    public String GEN_INVALID_INPUT = "GEN_INVALID_INPUT";

    /** The department not found. */
    public String DEPARTMENT_NOT_FOUND = "DEPARTMENT_NOT_FOUND";

    /** Issue with primary key */
    public String PRIMARY_KEY_INAVLID = "PRIMARY_KEY_INAVLID";

    /** Issue with Unique key */
    public String DEPARTMENT_PERSIST_ERROR = "DEPARTMENT_PERSIST_ERROR";
}