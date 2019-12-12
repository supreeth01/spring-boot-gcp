package org.opensource.community.project.dao.impl;

import java.util.Date;

import org.opensource.community.project.dao.DepartmentDao;
import org.opensource.community.project.error.DepartmentPersistanceException;
import org.opensource.community.project.model.Department;
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
 * The Class DepartmentDaoImpl.
 */
@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(DepartmentDaoImpl.class);

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
     * @see org.opensource.community.project.dao.DepartmentDao#create(org.opensource.community.project.model.Department)
     */
    @Override
    public Department create(Department department) {
        try {
            Department createdDepartment = entityManager.merge(department);
            entityManager.flush();
            return createdDepartment;
        } catch (Exception e) {
            logger.error("Exception Occured" + e);
            throw new DepartmentPersistanceException(e.getMessage() + ". Failed to persist department " + department.toString());
        }
    }

    /* (non-Javadoc)
     * @see org.opensource.community.project.dao.DepartmentDao#get(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Department> get(String criteria) {
        logger.info("-> get: {}", criteria);
        String queryStr = null;
        String alias = getAlias();
        if (criteria == null) {
            queryStr = "SELECT DISTINCT " + alias + " FROM " + "Department" + " " + alias;
        } else {
            if (!criteria.startsWith("WHERE")) {
                criteria = "WHERE " + criteria;
            }
            logger.info("criteria: {}", criteria);
            queryStr = "SELECT DISTINCT " + alias + " FROM " + "Department" + " " + alias + " " + criteria;
        }
        Query query = entityManager.createQuery(queryStr);
        return query.getResultList();
    }

    /* (non-Javadoc)
     * @see org.opensource.community.project.dao.DepartmentDao#update(org.opensource.community.project.model.Department)
     */
    @Override
    public Department update(Department department) {
        entityManager.merge(department);
        entityManager.flush();
        return department;
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.dao.DepartmentDao#delete(org.opensource.community.project.model.Department)
	 */
    @Override
    public Boolean delete(Department department) {
        entityManager.remove(department);
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