package org.opensource.community.project;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * The Class DepartmentDoc. All Department application documentation are in this class. 
 */
public class DepartmentDoc {

    /** The Constant DEPARTMENT_CREATE_APIDOC. */
    // API Documentation
    public static final String DEPARTMENT_CREATE_APIDOC = "Create Department";

    /** The Constant DEPARTMENT_CREATE_PARAM_DEPARTMENT_DTO_APIDOC. */
    public static final String DEPARTMENT_CREATE_PARAM_DEPARTMENT_DTO_APIDOC = "Department to create";

    /** The Constant DEPARTMENT_GET_APIDOC. */
    public static final String DEPARTMENT_GET_APIDOC = "Get Department(s)";

    /** The Constant DEPARTMENT_GET_PARAM_CRITERIA_APIDOC. */
    public static final String DEPARTMENT_GET_PARAM_CRITERIA_APIDOC = "Criteria to get Department(s)";

    /** The Constant DEPARTMENT_UPDATE_APIDOC. */
    public static final String DEPARTMENT_UPDATE_APIDOC = "Update Department";

    /** The Constant DEPARTMENT_UODATE_PARAM_DEPARTMENT_DTO_APIDOC. */
    public static final String DEPARTMENT_UODATE_PARAM_DEPARTMENT_DTO_APIDOC = "Department to update";

    /** The Constant DEPARTMENT_DELETE_APIDOC. */
    public static final String DEPARTMENT_DELETE_APIDOC = "Delete Department";

    /** The Constant DEPARTMENT_DELETE_PARAM_ID_APIDOC. */
    public static final String DEPARTMENT_DELETE_PARAM_ID_APIDOC = "ID of Department to delete";

    /** The Constant DEPARTMENT_ID_APIDOC. */
    // DTO Documentation
    public static final String DEPARTMENT_ID_APIDOC = "For POST, id should be set to 0 and is ignored. For other calls, id should be valid value";

    /** The Constant DEPARTMENT_NAME_APIDOC. */
    public static final String DEPARTMENT_NAME_APIDOC = "Department#name size should be greater than 3 and less than 250";
}