package org.opensource.community.project.error;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * The Interface EMPLOYEE_ERROR_CODES.
 */
public interface EMPLOYEE_ERROR_CODES {

    /** The gen invalid input. */
    public String GEN_INVALID_INPUT = "GEN_INVALID_INPUT";

    /** The employee not found. */
    public String EMPLOYEE_NOT_FOUND = "EMPLOYEE_NOT_FOUND";

    /** Issue with primary key */
    public String PRIMARY_KEY_INAVLID = "PRIMARY_KEY_INAVLID";

    /** Issue with Unique key */
    public String EMPLOYEE_PERSIST_ERROR = "EMPLOYEE_PERSIST_ERROR";
}