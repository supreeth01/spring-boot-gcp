package org.opensource.community.project.controller;

import java.util.Date;

import org.opensource.community.project.EmployeeDoc;
import org.opensource.community.project.common.BaseEmployeeController;
import org.opensource.community.project.dto.EmployeeDTO;
import org.opensource.community.project.error.EmployeeNotFoundException;
import org.opensource.community.project.service.ext.EmployeeServiceExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * The Class EmployeeController.
 */
public class EmployeeController extends BaseEmployeeController {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    /** The employee service. */
    @Autowired
    private EmployeeServiceExt employeeService;

    public void setEmployeeServiceExt(EmployeeServiceExt employeeServiceExt) {
        this.employeeService = employeeServiceExt;
    }

    /**
	 * Creates Employee.
	 * 
	 * @param employeeDTO
	 *            the employee DTO
	 * @return the employee DTO
	 */
    @RequestMapping(value = "/v1", method = RequestMethod.POST)
    @ApiOperation(value = "Create Employee", notes = EmployeeDoc.EMPLOYEE_CREATE_APIDOC)
    public EmployeeDTO create(@RequestBody(required = true) @ApiParam(value = EmployeeDoc.EMPLOYEE_CREATE_PARAM_EMPLOYEE_DTO_APIDOC) @NotNull @Valid EmployeeDTO employeeDTO) {
        logger.trace("-> create: {}", employeeDTO);
        try {
            return employeeService.create(employeeDTO);
        } catch (Exception e) {
            throw new EmployeeNotFoundException(e.getMessage());
        }
    }

    @RequestMapping(value = "/v1/list", method = RequestMethod.POST)
    @ApiOperation(value = "Create array of Employee", notes = EmployeeDoc.EMPLOYEE_CREATE_APIDOC)
    public List<EmployeeDTO> create(@RequestBody(required = true) @ApiParam(value = EmployeeDoc.EMPLOYEE_CREATE_PARAM_EMPLOYEE_DTO_APIDOC) List<EmployeeDTO> employeeDTOs) {
        logger.trace("-> create: {}", employeeDTOs);
        try {
            return employeeService.create(employeeDTOs);
        } catch (Exception e) {
            throw new EmployeeNotFoundException(e.getMessage());
        }
    }

    /**
	 * Gets Employee(s) as specified in criteria.
	 *
	 * @param criteria
	 *            criteria in SQL format to get employees
	 * @return the list
	 */
    @RequestMapping(value = "/v1", method = RequestMethod.GET)
    @ApiOperation(value = "Get Employee", notes = EmployeeDoc.EMPLOYEE_GET_APIDOC)
    public List<EmployeeDTO> get(@RequestParam(required = false) @ApiParam(value = EmployeeDoc.EMPLOYEE_GET_PARAM_CRITERIA_APIDOC) String criteria) {
        logger.trace("-> get: {}", criteria);
        return employeeService.get(criteria);
    }

    /**
	 * Update Employee.
	 *
	 * @param employeeDTO
	 *            the employee DTO
	 * @return the employee DTO
	 */
    @RequestMapping(value = "/v1", method = RequestMethod.PUT)
    @ApiOperation(value = "Update Employee", notes = EmployeeDoc.EMPLOYEE_UPDATE_APIDOC)
    public EmployeeDTO update(@RequestBody(required = true) @ApiParam(value = EmployeeDoc.EMPLOYEE_UODATE_PARAM_EMPLOYEE_DTO_APIDOC) @NotNull @Valid EmployeeDTO employeeDTO) {
        logger.trace("-> update: {}", employeeDTO);
        return employeeService.update(employeeDTO);
    }

    /**
	 * Delete Employee.
	 *
	 * @param id
	 *            Employee id
	 * @return the employee DTO
	 */
    @RequestMapping(value = "/v1", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete Employee", notes = EmployeeDoc.EMPLOYEE_DELETE_APIDOC)
    public EmployeeDTO delete(@RequestParam(required = true) @ApiParam(value = EmployeeDoc.EMPLOYEE_DELETE_PARAM_ID_APIDOC) @Valid Object id) {
        logger.info("-> delete: {}", id);
        return employeeService.delete(id);
    }
}