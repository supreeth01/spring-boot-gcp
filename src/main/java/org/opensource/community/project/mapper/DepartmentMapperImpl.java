package org.opensource.community.project.mapper;

import java.util.Date;

import org.opensource.community.project.dto.DepartmentDTO;
import org.opensource.community.project.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.stereotype.Component;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
/**
 * The Class DepartmentMapperImpl.
 */
@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    /** The mapper. */
    MapperFacade mapper;

    /**
	 * Instantiates a new department mapper impl.
	 */
    public DepartmentMapperImpl() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapper = mapperFactory.getMapperFacade();
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.mapper.DepartmentMapper#map(org.opensource.community.project.dto.DepartmentDTO)
	 */
    @Override
    public Department map(DepartmentDTO departmentDto) {
        return mapper.map(departmentDto, Department.class);
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.mapper.DepartmentMapper#map(org.opensource.community.project.model.Department)
	 */
    @Override
    public DepartmentDTO map(Department department) {
        return mapper.map(department, DepartmentDTO.class);
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.mapper.DepartmentMapper#mapList(java.util.List)
	 */
    @Override
    public List<Department> mapList(List<DepartmentDTO> departmentDtos) {
        return mapper.mapAsList(departmentDtos, Department.class);
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.mapper.DepartmentMapper#map(java.util.List)
	 */
    @Override
    public List<DepartmentDTO> map(List<Department> departments) {
        return mapper.mapAsList(departments, DepartmentDTO.class);
    }
}