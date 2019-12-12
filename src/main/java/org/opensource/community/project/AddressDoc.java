package org.opensource.community.project;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * The Class AddressDoc. All Address application documentation are in this class. 
 */
public class AddressDoc {

    /** The Constant ADDRESS_CREATE_APIDOC. */
    // API Documentation
    public static final String ADDRESS_CREATE_APIDOC = "Create Address";

    /** The Constant ADDRESS_CREATE_PARAM_ADDRESS_DTO_APIDOC. */
    public static final String ADDRESS_CREATE_PARAM_ADDRESS_DTO_APIDOC = "Address to create";

    /** The Constant ADDRESS_GET_APIDOC. */
    public static final String ADDRESS_GET_APIDOC = "Get Address(s)";

    /** The Constant ADDRESS_GET_PARAM_CRITERIA_APIDOC. */
    public static final String ADDRESS_GET_PARAM_CRITERIA_APIDOC = "Criteria to get Address(s)";

    /** The Constant ADDRESS_UPDATE_APIDOC. */
    public static final String ADDRESS_UPDATE_APIDOC = "Update Address";

    /** The Constant ADDRESS_UODATE_PARAM_ADDRESS_DTO_APIDOC. */
    public static final String ADDRESS_UODATE_PARAM_ADDRESS_DTO_APIDOC = "Address to update";

    /** The Constant ADDRESS_DELETE_APIDOC. */
    public static final String ADDRESS_DELETE_APIDOC = "Delete Address";

    /** The Constant ADDRESS_DELETE_PARAM_ID_APIDOC. */
    public static final String ADDRESS_DELETE_PARAM_ID_APIDOC = "ID of Address to delete";

    /** The Constant ADDRESS_ID_APIDOC. */
    // DTO Documentation
    public static final String ADDRESS_ID_APIDOC = "For POST, id should be set to 0 and is ignored. For other calls, id should be valid value";

    /** The Constant ADDRESS_NAME_APIDOC. */
    public static final String ADDRESS_NAME_APIDOC = "Address#name size should be greater than 3 and less than 250";
}