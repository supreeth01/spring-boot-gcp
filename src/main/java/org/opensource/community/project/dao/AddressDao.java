package org.opensource.community.project.dao;

import java.util.Date;

import org.opensource.community.project.dto.AddressDTO;
import org.opensource.community.project.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
/**
 * The Interface AddressDao.
 */
public interface AddressDao {

    /**
	 * Creates the.
	 *
	 * @param address the api request
	 * @return the address
	 */
    public Address create(Address address);

    /**
     * Gets the.
     *
     * @param criteria the criteria
     * @return the list
     */
    public List<Address> get(String criteria);

    /**
	 * Update.
	 *
	 * @param apiRequest the api request
	 * @return the address
	 */
    public Address update(Address apiRequest);

    /**
     * Delete.
     *
     * @param address the address
     * @return the boolean
     */
    public Boolean delete(Address address);
}