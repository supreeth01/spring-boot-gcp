package org.opensource.community.project.dao.impl;

import java.util.Date;

import org.opensource.community.project.dao.AddressDao;
import org.opensource.community.project.error.AddressPersistanceException;
import org.opensource.community.project.model.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
/**
 * The Class AddressDaoImpl.
 */
@Repository
public class AddressDaoImpl implements AddressDao {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(AddressDaoImpl.class);

    /** The entity manager. */
    @PersistenceContext
    private EntityManager entityManager;

    private Query mockQuery;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Query getMockQuery() {
        return mockQuery;
    }

    public void setMockQuery(Query mockQuery) {
        this.mockQuery = mockQuery;
    }

    /* (non-Javadoc)
     * @see org.opensource.community.project.dao.AddressDao#create(org.opensource.community.project.model.Address)
     */
    @Override
    public Address create(Address address) {
        try {
            Address createdAddress = entityManager.merge(address);
            entityManager.flush();
            return createdAddress;
        } catch (Exception e) {
            logger.error("Exception Occured" + e);
            throw new AddressPersistanceException(e.getMessage() + ". Failed to persist address " + address.toString());
        }
    }

    /* (non-Javadoc)
     * @see org.opensource.community.project.dao.AddressDao#get(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Address> get(String criteria) {
        logger.info("-> get: {}", criteria);
        String queryStr = null;
        String alias = getAlias();
        if (criteria == null) {
            queryStr = "SELECT DISTINCT " + alias + " FROM " + "Address" + " " + alias;
        } else {
            if (!criteria.startsWith("WHERE")) {
                criteria = "WHERE " + criteria;
            }
            logger.info("criteria: {}", criteria);
            queryStr = "SELECT DISTINCT " + alias + " FROM " + "Address" + " " + alias + " " + criteria;
        }
        Query query = entityManager.createQuery(queryStr);
        return query.getResultList();
    }

    /* (non-Javadoc)
     * @see org.opensource.community.project.dao.AddressDao#update(org.opensource.community.project.model.Address)
     */
    @Override
    public Address update(Address address) {
        entityManager.merge(address);
        entityManager.flush();
        return address;
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.dao.AddressDao#delete(org.opensource.community.project.model.Address)
	 */
    @Override
    public Boolean delete(Address address) {
        entityManager.remove(address);
        return true;
    }

    /**
	 * Gets the alias.
	 *
	 * @return the alias
	 */
    private String getAlias() {
        String alias = this.getClass().getSimpleName().substring(0, 1);
        alias = alias.toLowerCase(Locale.ENGLISH);
        return alias;
    }
}