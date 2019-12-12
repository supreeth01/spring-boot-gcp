package org.opensource.community.project.mapper;

import java.util.Date;

import org.opensource.community.project.dto.AddressDTO;
import org.opensource.community.project.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.stereotype.Component;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
/**
 * The Class AddressMapperImpl.
 */
@Component
public class AddressMapperImpl implements AddressMapper {

    /** The mapper. */
    MapperFacade mapper;

    /**
	 * Instantiates a new address mapper impl.
	 */
    public AddressMapperImpl() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapper = mapperFactory.getMapperFacade();
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.mapper.AddressMapper#map(org.opensource.community.project.dto.AddressDTO)
	 */
    @Override
    public Address map(AddressDTO addressDto) {
        return mapper.map(addressDto, Address.class);
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.mapper.AddressMapper#map(org.opensource.community.project.model.Address)
	 */
    @Override
    public AddressDTO map(Address address) {
        return mapper.map(address, AddressDTO.class);
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.mapper.AddressMapper#mapList(java.util.List)
	 */
    @Override
    public List<Address> mapList(List<AddressDTO> addressDtos) {
        return mapper.mapAsList(addressDtos, Address.class);
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.mapper.AddressMapper#map(java.util.List)
	 */
    @Override
    public List<AddressDTO> map(List<Address> addresss) {
        return mapper.mapAsList(addresss, AddressDTO.class);
    }
}