package org.opensource.community.project.dao;

import java.util.Date;

import org.opensource.community.project.dto.EmployeeDTO;
import org.opensource.community.project.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
/**
 * The Interface EmployeeDao.
 */
public interface EmployeeDao {

    /**
	 * Creates the.
	 *
	 * @param employee the api request
	 * @return the employee
	 */
    public Employee create(Employee employee);

    /**
     * Gets the.
     *
     * @param criteria the criteria
     * @return the list
     */
    public List<Employee> get(String criteria);

    /**
	 * Update.
	 *
	 * @param apiRequest the api request
	 * @return the employee
	 */
    public Employee update(Employee apiRequest);

    /**
     * Delete.
     *
     * @param employee the employee
     * @return the boolean
     */
    public Boolean delete(Employee employee);
}