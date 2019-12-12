package org.opensource.community.project.controller;

import java.util.Date;

import org.opensource.community.project.AddressDoc;
import org.opensource.community.project.common.BaseAddressController;
import org.opensource.community.project.dto.AddressDTO;
import org.opensource.community.project.error.AddressNotFoundException;
import org.opensource.community.project.service.ext.AddressServiceExt;
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
 * The Class AddressController.
 */
public class AddressController extends BaseAddressController {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    /** The address service. */
    @Autowired
    private AddressServiceExt addressService;

    public void setAddressServiceExt(AddressServiceExt addressServiceExt) {
        this.addressService = addressServiceExt;
    }

    /**
	 * Creates Address.
	 * 
	 * @param addressDTO
	 *            the address DTO
	 * @return the address DTO
	 */
    @RequestMapping(value = "/v1", method = RequestMethod.POST)
    @ApiOperation(value = "Create Address", notes = AddressDoc.ADDRESS_CREATE_APIDOC)
    public AddressDTO create(@RequestBody(required = true) @ApiParam(value = AddressDoc.ADDRESS_CREATE_PARAM_ADDRESS_DTO_APIDOC) @NotNull @Valid AddressDTO addressDTO) {
        logger.trace("-> create: {}", addressDTO);
        try {
            return addressService.create(addressDTO);
        } catch (Exception e) {
            throw new AddressNotFoundException(e.getMessage());
        }
    }

    @RequestMapping(value = "/v1/list", method = RequestMethod.POST)
    @ApiOperation(value = "Create array of Address", notes = AddressDoc.ADDRESS_CREATE_APIDOC)
    public List<AddressDTO> create(@RequestBody(required = true) @ApiParam(value = AddressDoc.ADDRESS_CREATE_PARAM_ADDRESS_DTO_APIDOC) List<AddressDTO> addressDTOs) {
        logger.trace("-> create: {}", addressDTOs);
        try {
            return addressService.create(addressDTOs);
        } catch (Exception e) {
            throw new AddressNotFoundException(e.getMessage());
        }
    }

    /**
	 * Gets Address(s) as specified in criteria.
	 *
	 * @param criteria
	 *            criteria in SQL format to get addresss
	 * @return the list
	 */
    @RequestMapping(value = "/v1", method = RequestMethod.GET)
    @ApiOperation(value = "Get Address", notes = AddressDoc.ADDRESS_GET_APIDOC)
    public List<AddressDTO> get(@RequestParam(required = false) @ApiParam(value = AddressDoc.ADDRESS_GET_PARAM_CRITERIA_APIDOC) String criteria) {
        logger.trace("-> get: {}", criteria);
        return addressService.get(criteria);
    }

    /**
	 * Update Address.
	 *
	 * @param addressDTO
	 *            the address DTO
	 * @return the address DTO
	 */
    @RequestMapping(value = "/v1", method = RequestMethod.PUT)
    @ApiOperation(value = "Update Address", notes = AddressDoc.ADDRESS_UPDATE_APIDOC)
    public AddressDTO update(@RequestBody(required = true) @ApiParam(value = AddressDoc.ADDRESS_UODATE_PARAM_ADDRESS_DTO_APIDOC) @NotNull @Valid AddressDTO addressDTO) {
        logger.trace("-> update: {}", addressDTO);
        return addressService.update(addressDTO);
    }

    /**
	 * Delete Address.
	 *
	 * @param id
	 *            Address id
	 * @return the address DTO
	 */
    @RequestMapping(value = "/v1", method = RequestMethod.DELETE)
    @ApiOperation(value = "Delete Address", notes = AddressDoc.ADDRESS_DELETE_APIDOC)
    public AddressDTO delete(@RequestParam(required = true) @ApiParam(value = AddressDoc.ADDRESS_DELETE_PARAM_ID_APIDOC) @Valid Object id) {
        logger.info("-> delete: {}", id);
        return addressService.delete(id);
    }
}