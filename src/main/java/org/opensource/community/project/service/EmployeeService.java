package org.opensource.community.project.service;

import java.util.Date;

import org.opensource.community.project.dto.EmployeeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
/**
 * The Interface EmployeeService.
 */
@Validated
public interface EmployeeService {

    /**
	 * Creates the.
	 *
	 * @param employeeDTOs the employee DT os
	 * @return the list
	 */
    public List<EmployeeDTO> create(@NotNull @Valid List<EmployeeDTO> employeeDTOs);

    /**
	 * Creates the.
	 *
	 * @param employeeDTO the employee DTO
	 * @return the employee DTO
	 */
    public EmployeeDTO create(@NotNull @Valid EmployeeDTO employeeDTO);

    /**
     * Gets the.
     *
     * @param criteria the criteria
     * @return the list
     */
    public List<EmployeeDTO> get(String criteria);

    /**
     * Update.
     *
     * @param employeeDTOs the employee DT os
     * @return the list
     */
    public List<EmployeeDTO> update(@NotNull @Valid List<EmployeeDTO> employeeDTOs);

    /**
     * Update.
     *
     * @param employeeDTO the employee DTO
     * @return the employee DTO
     */
    public EmployeeDTO update(@NotNull @Valid EmployeeDTO employeeDTO);

    /**
     * Delete.
     *
     * @param id the id
     * @return the employee DTO
     */
    //  public EmployeeDTO delete(@NotNull @Valid Integer id);
    public EmployeeDTO delete(@NotNull @Valid Object id);
}