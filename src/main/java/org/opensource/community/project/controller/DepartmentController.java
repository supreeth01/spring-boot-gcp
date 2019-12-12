package org.opensource.community.project.controller;

import java.util.Date;

import org.opensource.community.project.DepartmentDoc;
import org.opensource.community.project.common.BaseDepartmentController;
import org.opensource.community.project.dto.DepartmentDTO;
import org.opensource.community.project.error.DepartmentNotFoundException;
import org.opensource.community.project.service.ext.DepartmentServiceExt;
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
 * The Class DepartmentController.
 */
public class DepartmentController extends BaseDepartmentController {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    /** The department service. */
    @Autowired
    private DepartmentServiceExt departmentService;

    public void setDepartmentServiceExt(DepartmentServiceExt departmentServiceExt) {
        this.departmentService = departmentServiceExt;
    }

    /**
	 * Creates Department.
	 * 
	 * @param departmentDTO
	 *            the department DTO
	 * @return the department DTO
	 */
    @RequestMapping(value = "/v1", method = RequestMethod.POST)
    @ApiOperation(value = "Create Department", notes = DepartmentDoc.DEPARTMENT_CREATE_APIDOC)
    public DepartmentDTO create(@RequestBody(required = true) @ApiParam(value = DepartmentDoc.DEPARTMENT_CREATE_PARAM_DEPARTMENT_DTO_APIDOC) @NotNull @Valid DepartmentDTO departmentDTO) {
        logger.trace("-> create: {}", departmentDTO);
        try {
            return departmentService.create(departmentDTO);
        } catch (Exception e) {
            throw new DepartmentNotFoundException(e.getMessage());
        }
    }

    @RequestMapping(value = "/v1/list", method = RequestMethod.POST)
    @ApiOperation(value = "Create array of Department", notes = DepartmentDoc.DEPARTMENT_CREATE_APIDOC)
    public List<DepartmentDTO> create(@RequestBody(required = true) @ApiParam(value = DepartmentDoc.DEPARTMENT_CREATE_PARAM_DEPARTMENT_DTO_APIDOC) List<DepartmentDTO> departmentDTOs) {
        logger.trace("-> create: {}", departmentDTOs);
        try {
            return departmentService.create(departmentDTOs);
        } catch (Exception e) {
            throw new DepartmentNotFoundException(e.getMessage());
        }
    }

    /**
	 * Gets Department(s) as specified in criteria.
	 *
	 * @param criteria
	 *            criteria in SQL format to get departments
	 * @return the list
	 */
    @RequestMapping(value = "/v1", method = RequestMethod.GET)
    @ApiOperation(value = "Get Department", notes = DepartmentDoc.DEPARTMENT_GET_APIDOC)
    public List<DepartmentDTO> get(@RequestParam(required = false) @ApiParam(value = DepartmentDoc.DEPARTMENT_GET_PARAM_CRITERIA_APIDOC) String criteria) {
        logger.trace("-> get: {}", criteria);
        return departmentService.get(criteria);
    }

    /**
	 * Update Department.
	 *
	 * @param departmentDTO
	 *            the department DTO
	 * @return the department DTO
	 */
    @RequestMapping(value = "/v1", method = RequestMethod.PUT)
    @ApiOperation(value = "Update Department", notes = DepartmentDoc.DEPARTMENT_UPDATE_APIDOC)
    public DepartmentDTO update(@RequestBody(required = true) @ApiParam(value = DepartmentDoc.DEPARTMENT_UODATE_PARAM_DEPARTMENT_DTO_APIDOC) @NotNull @Valid DepartmentDTO departmentDTO) {
        logger.trace("-> update: {}", departmentDTO);
        return departmentService.update(departmentDTO);
    }

    /**
	 * Delete Department.
	 *
	 * @param id
	 *            Department id
	 * @return the department DTO
	 */
    @RequestMapping(value = "/v1", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete Department", notes = DepartmentDoc.DEPARTMENT_DELETE_APIDOC)
    public DepartmentDTO delete(@RequestParam(required = true) @ApiParam(value = DepartmentDoc.DEPARTMENT_DELETE_PARAM_ID_APIDOC) @Valid Object id) {
        logger.info("-> delete: {}", id);
        return departmentService.delete(id);
    }
}