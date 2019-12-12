package org.opensource.community.project.mapper;

import java.util.Date;

import org.opensource.community.project.dto.EmployeeDTO;
import org.opensource.community.project.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.stereotype.Component;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
/**
 * The Class EmployeeMapperImpl.
 */
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    /** The mapper. */
    MapperFacade mapper;

    /**
	 * Instantiates a new employee mapper impl.
	 */
    public EmployeeMapperImpl() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapper = mapperFactory.getMapperFacade();
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.mapper.EmployeeMapper#map(org.opensource.community.project.dto.EmployeeDTO)
	 */
    @Override
    public Employee map(EmployeeDTO employeeDto) {
        return mapper.map(employeeDto, Employee.class);
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.mapper.EmployeeMapper#map(org.opensource.community.project.model.Employee)
	 */
    @Override
    public EmployeeDTO map(Employee employee) {
        return mapper.map(employee, EmployeeDTO.class);
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.mapper.EmployeeMapper#mapList(java.util.List)
	 */
    @Override
    public List<Employee> mapList(List<EmployeeDTO> employeeDtos) {
        return mapper.mapAsList(employeeDtos, Employee.class);
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.mapper.EmployeeMapper#map(java.util.List)
	 */
    @Override
    public List<EmployeeDTO> map(List<Employee> employees) {
        return mapper.mapAsList(employees, EmployeeDTO.class);
    }
}