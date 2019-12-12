package org.opensource.community.project;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * The Class EmployeeDoc. All Employee application documentation are in this class. 
 */
public class EmployeeDoc {

    /** The Constant EMPLOYEE_CREATE_APIDOC. */
    // API Documentation
    public static final String EMPLOYEE_CREATE_APIDOC = "Create Employee";

    /** The Constant EMPLOYEE_CREATE_PARAM_EMPLOYEE_DTO_APIDOC. */
    public static final String EMPLOYEE_CREATE_PARAM_EMPLOYEE_DTO_APIDOC = "Employee to create";

    /** The Constant EMPLOYEE_GET_APIDOC. */
    public static final String EMPLOYEE_GET_APIDOC = "Get Employee(s)";

    /** The Constant EMPLOYEE_GET_PARAM_CRITERIA_APIDOC. */
    public static final String EMPLOYEE_GET_PARAM_CRITERIA_APIDOC = "Criteria to get Employee(s)";

    /** The Constant EMPLOYEE_UPDATE_APIDOC. */
    public static final String EMPLOYEE_UPDATE_APIDOC = "Update Employee";

    /** The Constant EMPLOYEE_UODATE_PARAM_EMPLOYEE_DTO_APIDOC. */
    public static final String EMPLOYEE_UODATE_PARAM_EMPLOYEE_DTO_APIDOC = "Employee to update";

    /** The Constant EMPLOYEE_DELETE_APIDOC. */
    public static final String EMPLOYEE_DELETE_APIDOC = "Delete Employee";

    /** The Constant EMPLOYEE_DELETE_PARAM_ID_APIDOC. */
    public static final String EMPLOYEE_DELETE_PARAM_ID_APIDOC = "ID of Employee to delete";

    /** The Constant EMPLOYEE_ID_APIDOC. */
    // DTO Documentation
    public static final String EMPLOYEE_ID_APIDOC = "For POST, id should be set to 0 and is ignored. For other calls, id should be valid value";

    /** The Constant EMPLOYEE_NAME_APIDOC. */
    public static final String EMPLOYEE_NAME_APIDOC = "Employee#name size should be greater than 3 and less than 250";
}