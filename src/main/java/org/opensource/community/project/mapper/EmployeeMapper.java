package org.opensource.community.project.mapper;

import java.util.Date;

import org.opensource.community.project.dto.EmployeeDTO;
import org.opensource.community.project.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
/**
 * The Interface EmployeeMapper.
 */
public interface EmployeeMapper {

    /**
	 * Map.
	 *
	 * @param employeeDto the employee dto
	 * @return the employee
	 */
    Employee map(EmployeeDTO employeeDto);

    /**
	 * Map.
	 *
	 * @param employee the employee
	 * @return the employee DTO
	 */
    EmployeeDTO map(Employee employee);

    /**
	 * Map list.
	 *
	 * @param employeeDtos the employee dtos
	 * @return the list
	 */
    List<Employee> mapList(List<EmployeeDTO> employeeDtos);

    /**
	 * Map.
	 *
	 * @param employees the employees
	 * @return the list
	 */
    List<EmployeeDTO> map(List<Employee> employees);
}