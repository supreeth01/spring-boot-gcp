package org.opensource.community.project.mapper;

import java.util.Date;

import org.opensource.community.project.dto.DepartmentDTO;
import org.opensource.community.project.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
/**
 * The Interface DepartmentMapper.
 */
public interface DepartmentMapper {

    /**
	 * Map.
	 *
	 * @param departmentDto the department dto
	 * @return the department
	 */
    Department map(DepartmentDTO departmentDto);

    /**
	 * Map.
	 *
	 * @param department the department
	 * @return the department DTO
	 */
    DepartmentDTO map(Department department);

    /**
	 * Map list.
	 *
	 * @param departmentDtos the department dtos
	 * @return the list
	 */
    List<Department> mapList(List<DepartmentDTO> departmentDtos);

    /**
	 * Map.
	 *
	 * @param departments the departments
	 * @return the list
	 */
    List<DepartmentDTO> map(List<Department> departments);
}