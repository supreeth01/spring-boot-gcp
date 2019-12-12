package org.opensource.community.project.service;

import java.util.Date;

import org.opensource.community.project.dto.AddressDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
/**
 * The Interface AddressService.
 */
@Validated
public interface AddressService {

    /**
	 * Creates the.
	 *
	 * @param addressDTOs the address DT os
	 * @return the list
	 */
    public List<AddressDTO> create(@NotNull @Valid List<AddressDTO> addressDTOs);

    /**
	 * Creates the.
	 *
	 * @param addressDTO the address DTO
	 * @return the address DTO
	 */
    public AddressDTO create(@NotNull @Valid AddressDTO addressDTO);

    /**
     * Gets the.
     *
     * @param criteria the criteria
     * @return the list
     */
    public List<AddressDTO> get(String criteria);

    /**
     * Update.
     *
     * @param addressDTOs the address DT os
     * @return the list
     */
    public List<AddressDTO> update(@NotNull @Valid List<AddressDTO> addressDTOs);

    /**
     * Update.
     *
     * @param addressDTO the address DTO
     * @return the address DTO
     */
    public AddressDTO update(@NotNull @Valid AddressDTO addressDTO);

    /**
     * Delete.
     *
     * @param id the id
     * @return the address DTO
     */
    //  public AddressDTO delete(@NotNull @Valid Integer id);
    public AddressDTO delete(@NotNull @Valid Object id);
}