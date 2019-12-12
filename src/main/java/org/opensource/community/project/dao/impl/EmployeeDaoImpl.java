package org.opensource.community.project.dao.impl;

import java.util.Date;

import org.opensource.community.project.dao.EmployeeDao;
import org.opensource.community.project.error.EmployeePersistanceException;
import org.opensource.community.project.model.Employee;
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
 * The Class EmployeeDaoImpl.
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);

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
     * @see org.opensource.community.project.dao.EmployeeDao#create(org.opensource.community.project.model.Employee)
     */
    @Override
    public Employee create(Employee employee) {
        try {
            Employee createdEmployee = entityManager.merge(employee);
            entityManager.flush();
            return createdEmployee;
        } catch (Exception e) {
            logger.error("Exception Occured" + e);
            throw new EmployeePersistanceException(e.getMessage() + ". Failed to persist employee " + employee.toString());
        }
    }

    /* (non-Javadoc)
     * @see org.opensource.community.project.dao.EmployeeDao#get(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Employee> get(String criteria) {
        logger.info("-> get: {}", criteria);
        String queryStr = null;
        String alias = getAlias();
        if (criteria == null) {
            queryStr = "SELECT DISTINCT " + alias + " FROM " + "Employee" + " " + alias;
        } else {
            if (!criteria.startsWith("WHERE")) {
                criteria = "WHERE " + criteria;
            }
            logger.info("criteria: {}", criteria);
            queryStr = "SELECT DISTINCT " + alias + " FROM " + "Employee" + " " + alias + " " + criteria;
        }
        Query query = entityManager.createQuery(queryStr);
        return query.getResultList();
    }

    /* (non-Javadoc)
     * @see org.opensource.community.project.dao.EmployeeDao#update(org.opensource.community.project.model.Employee)
     */
    @Override
    public Employee update(Employee employee) {
        entityManager.merge(employee);
        entityManager.flush();
        return employee;
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.dao.EmployeeDao#delete(org.opensource.community.project.model.Employee)
	 */
    @Override
    public Boolean delete(Employee employee) {
        entityManager.remove(employee);
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