package org.opensource.community.project.service;

import java.util.Date;

import org.opensource.community.project.dto.DepartmentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
/**
 * The Interface DepartmentService.
 */
@Validated
public interface DepartmentService {

    /**
	 * Creates the.
	 *
	 * @param departmentDTOs the department DT os
	 * @return the list
	 */
    public List<DepartmentDTO> create(@NotNull @Valid List<DepartmentDTO> departmentDTOs);

    /**
	 * Creates the.
	 *
	 * @param departmentDTO the department DTO
	 * @return the department DTO
	 */
    public DepartmentDTO create(@NotNull @Valid DepartmentDTO departmentDTO);

    /**
     * Gets the.
     *
     * @param criteria the criteria
     * @return the list
     */
    public List<DepartmentDTO> get(String criteria);

    /**
     * Update.
     *
     * @param departmentDTOs the department DT os
     * @return the list
     */
    public List<DepartmentDTO> update(@NotNull @Valid List<DepartmentDTO> departmentDTOs);

    /**
     * Update.
     *
     * @param departmentDTO the department DTO
     * @return the department DTO
     */
    public DepartmentDTO update(@NotNull @Valid DepartmentDTO departmentDTO);

    /**
     * Delete.
     *
     * @param id the id
     * @return the department DTO
     */
    //  public DepartmentDTO delete(@NotNull @Valid Integer id);
    public DepartmentDTO delete(@NotNull @Valid Object id);
}