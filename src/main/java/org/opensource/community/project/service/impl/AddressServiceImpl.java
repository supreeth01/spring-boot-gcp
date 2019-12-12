package org.opensource.community.project.service.impl;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.text.WordUtils;
import org.opensource.community.project.dao.ext.AddressDaoExt;
import org.opensource.community.project.dto.AddressDTO;
import org.opensource.community.project.error.AddressNotFoundException;
import org.opensource.community.project.error.InvalidPrimaryKeyException;
import org.opensource.community.project.mapper.AddressMapper;
import org.opensource.community.project.model.Address;
import org.opensource.community.project.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class AddressServiceImpl implements AddressService {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

    /** The address dao. */
    @Autowired
    private AddressDaoExt addressDao;

    /** The mapper. */
    @Autowired
    AddressMapper mapper;

    private static Method getmethod;

    private static Method setmethod;

    private static String idPropertyName;

    private static Class<?> idPropertyType;

    private static Class<?> cls;

    static {
        Address address = new Address();
        staticInit(address);
    }

    public static void staticInit(Address address) {
        try {
            cls = Class.forName(address.getClass().getCanonicalName());
            Class<?>[] noparams = {};
            idPropertyName = address.getIdPropertyName();
            idPropertyType = address.getIdPropertyType();
            if (idPropertyName != null) {
                getmethod = cls.getDeclaredMethod("get" + WordUtils.capitalize(idPropertyName), noparams);
                setmethod = cls.getDeclaredMethod("set" + WordUtils.capitalize(idPropertyName), idPropertyType);
            }
        } catch (Exception e) {
            throw new InvalidPrimaryKeyException(e.getMessage());
        }
    }

    public AddressDaoExt getAddressDao() {
        return addressDao;
    }

    public void setAddressDao(AddressDaoExt addressDao) {
        this.addressDao = addressDao;
    }

    public AddressMapper getMapper() {
        return mapper;
    }

    public void setMapper(AddressMapper mapper) {
        this.mapper = mapper;
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.service.AddressService#create(java.util.List)
	 */
    @Override
    public List<AddressDTO> create(List<AddressDTO> addressDTOs) {
        logger.trace("-> create: {}", addressDTOs);
        List<AddressDTO> addressDTOList = new ArrayList<AddressDTO>();
        if (addressDTOs != null) {
            for (AddressDTO addressDTO : addressDTOs) {
                AddressDTO addressDTOCreated = create(addressDTO);
                addressDTOList.add(addressDTOCreated);
            }
        }
        logger.trace("<- create: {}", addressDTOList);
        return addressDTOList;
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.service.AddressService#create(org.opensource.community.project.dto.AddressDTO)
	 */
    @Override
    public AddressDTO create(AddressDTO addressDTO) {
        logger.trace("-> create: {}", addressDTO);
        addressDTO = createPreHook(addressDTO);
        Address address = mapper.map(addressDTO);
        try {
            if (idPropertyName != null) {
                setmethod.invoke(address, new Object[] { null });
            }
        } catch (Exception e) {
            throw new InvalidPrimaryKeyException(e.getMessage());
        }
        address = addressDao.create(address);
        addressDTO = mapper.map(address);
        addressDTO = createPostHook(addressDTO);
        logger.trace("<- create: {}", addressDTO);
        return addressDTO;
    }

    /* (non-Javadoc)
     * @see org.opensource.community.project.service.AddressService#get(java.lang.String)
     */
    @Override
    public List<AddressDTO> get(String criteria) {
        logger.trace("-> get: {}", criteria);
        criteria = getPreHook(criteria);
        List<Address> addresss = addressDao.get(criteria);
        List<AddressDTO> addressDTOs = mapper.map(addresss);
        addressDTOs = getPostHook(addressDTOs);
        logger.trace("<- get: {}", addressDTOs);
        return addressDTOs;
    }

    /* (non-Javadoc)
     * @see org.opensource.community.project.service.AddressService#update(java.util.List)
     */
    @Override
    public List<AddressDTO> update(List<AddressDTO> addressDTOs) {
        logger.trace("-> update: {}", addressDTOs);
        List<AddressDTO> addressDTOsUpdated = new ArrayList<AddressDTO>();
        if (addressDTOs != null) {
            for (AddressDTO addressDTO : addressDTOs) {
                AddressDTO addressDTOCreated = update(addressDTO);
                addressDTOsUpdated.add(addressDTOCreated);
            }
        }
        logger.trace("<- update: {}", addressDTOsUpdated);
        return addressDTOsUpdated;
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.service.AddressService#update(org.opensource.community.project.dto.AddressDTO)
	 */
    @Override
    public AddressDTO update(AddressDTO addressDTO) {
        logger.trace("-> update: {}", addressDTO);
        addressDTO = updatePreHook(addressDTO);
        Address address = mapper.map(addressDTO);
        Object idVal;
        try {
            // Since id attribute name is not fixed in generated code, we are invoking a
            // dynamic getter method
            idVal = getmethod.invoke(address, null);
        } catch (Exception e) {
            throw new InvalidPrimaryKeyException(e.getMessage());
        }
        List<Address> addresss = addressDao.get(idPropertyName + "=" + idVal);
        if (addresss == null || addresss.size() != 1) {
            throw new AddressNotFoundException("Address id " + idVal + " not found for update");
        }
        address = addressDao.update(address);
        addressDTO = mapper.map(address);
        addressDTO = updatePostHook(addressDTO);
        logger.trace("<- update: {}", addressDTO);
        return addressDTO;
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.service.AddressService#delete(java.lang.Integer)
	 */
    @Override
    public AddressDTO delete(Object id) {
        logger.trace("-> delete: {}", id);
        id = deletePreHook(id);
        //List<Address> addresss = addressDao.get("id=" + id);
        List<Address> addresss = addressDao.get(idPropertyName + "=" + id.toString());
        if (addresss == null || addresss.size() != 1) {
            throw new AddressNotFoundException("Address id " + id + " not found for delete");
        }
        addressDao.delete(addresss.get(0));
        AddressDTO addressDTO = mapper.map(addresss.get(0));
        addressDTO = deletePostHook(addressDTO);
        logger.trace("<- delete: {}", addressDTO);
        return addressDTO;
    }

    /**
	 * Creates the pre hook.
	 *
	 * @param addressDTO the address DTO
	 * @return the address DTO
	 */
    protected AddressDTO createPreHook(AddressDTO addressDTO) {
        return addressDTO;
    }

    /**
	 * Creates the post hook.
	 *
	 * @param addressDTO the address DTO
	 * @return the address DTO
	 */
    protected AddressDTO createPostHook(AddressDTO addressDTO) {
        return addressDTO;
    }

    /**
	 * Gets the pre hook.
	 *
	 * @param criterie the criterie
	 * @return the pre hook
	 */
    protected String getPreHook(String criterie) {
        return criterie;
    }

    /**
	 * Gets the post hook.
	 *
	 * @param addressDTOs the address DT os
	 * @return the post hook
	 */
    protected List<AddressDTO> getPostHook(List<AddressDTO> addressDTOs) {
        return addressDTOs;
    }

    /**
	 * Update pre hook.
	 *
	 * @param addressDTO the address DTO
	 * @return the address DTO
	 */
    protected AddressDTO updatePreHook(AddressDTO addressDTO) {
        return addressDTO;
    }

    /**
	 * Update post hook.
	 *
	 * @param addressDTO the address DTO
	 * @return the address DTO
	 */
    protected AddressDTO updatePostHook(AddressDTO addressDTO) {
        return addressDTO;
    }

    /**
	 * Delete pre hook.
	 *
	 * @param id the id
	 * @return the integer
	 */
    protected Object deletePreHook(Object id) {
        return id;
    }

    /**
	 * Delete post hook.
	 *
	 * @param addressDTO the address DTO
	 * @return the address DTO
	 */
    protected AddressDTO deletePostHook(AddressDTO addressDTO) {
        return addressDTO;
    }
}