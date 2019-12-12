package org.opensource.community.project.mapper;

import java.util.Date;

import org.opensource.community.project.dto.AddressDTO;
import org.opensource.community.project.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
/**
 * The Interface AddressMapper.
 */
public interface AddressMapper {

    /**
	 * Map.
	 *
	 * @param addressDto the address dto
	 * @return the address
	 */
    Address map(AddressDTO addressDto);

    /**
	 * Map.
	 *
	 * @param address the address
	 * @return the address DTO
	 */
    AddressDTO map(Address address);

    /**
	 * Map list.
	 *
	 * @param addressDtos the address dtos
	 * @return the list
	 */
    List<Address> mapList(List<AddressDTO> addressDtos);

    /**
	 * Map.
	 *
	 * @param addresss the addresss
	 * @return the list
	 */
    List<AddressDTO> map(List<Address> addresss);
}