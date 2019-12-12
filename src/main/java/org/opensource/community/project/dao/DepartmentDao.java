package org.opensource.community.project.dao;

import java.util.Date;

import org.opensource.community.project.dto.DepartmentDTO;
import org.opensource.community.project.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
/**
 * The Interface DepartmentDao.
 */
public interface DepartmentDao {

    /**
	 * Creates the.
	 *
	 * @param department the api request
	 * @return the department
	 */
    public Department create(Department department);

    /**
     * Gets the.
     *
     * @param criteria the criteria
     * @return the list
     */
    public List<Department> get(String criteria);

    /**
	 * Update.
	 *
	 * @param apiRequest the api request
	 * @return the department
	 */
    public Department update(Department apiRequest);

    /**
     * Delete.
     *
     * @param department the department
     * @return the boolean
     */
    public Boolean delete(Department department);
}