package org.opensource.community.project.service.impl;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.text.WordUtils;
import org.opensource.community.project.dao.ext.EmployeeDaoExt;
import org.opensource.community.project.dto.EmployeeDTO;
import org.opensource.community.project.error.EmployeeNotFoundException;
import org.opensource.community.project.error.InvalidPrimaryKeyException;
import org.opensource.community.project.mapper.EmployeeMapper;
import org.opensource.community.project.model.Employee;
import org.opensource.community.project.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    /** The employee dao. */
    @Autowired
    private EmployeeDaoExt employeeDao;

    /** The mapper. */
    @Autowired
    EmployeeMapper mapper;

    private static Method getmethod;

    private static Method setmethod;

    private static String idPropertyName;

    private static Class<?> idPropertyType;

    private static Class<?> cls;

    static {
        Employee employee = new Employee();
        staticInit(employee);
    }

    public static void staticInit(Employee employee) {
        try {
            cls = Class.forName(employee.getClass().getCanonicalName());
            Class<?>[] noparams = {};
            idPropertyName = employee.getIdPropertyName();
            idPropertyType = employee.getIdPropertyType();
            if (idPropertyName != null) {
                getmethod = cls.getDeclaredMethod("get" + WordUtils.capitalize(idPropertyName), noparams);
                setmethod = cls.getDeclaredMethod("set" + WordUtils.capitalize(idPropertyName), idPropertyType);
            }
        } catch (Exception e) {
            throw new InvalidPrimaryKeyException(e.getMessage());
        }
    }

    public EmployeeDaoExt getEmployeeDao() {
        return employeeDao;
    }

    public void setEmployeeDao(EmployeeDaoExt employeeDao) {
        this.employeeDao = employeeDao;
    }

    public EmployeeMapper getMapper() {
        return mapper;
    }

    public void setMapper(EmployeeMapper mapper) {
        this.mapper = mapper;
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.service.EmployeeService#create(java.util.List)
	 */
    @Override
    public List<EmployeeDTO> create(List<EmployeeDTO> employeeDTOs) {
        logger.trace("-> create: {}", employeeDTOs);
        List<EmployeeDTO> employeeDTOList = new ArrayList<EmployeeDTO>();
        if (employeeDTOs != null) {
            for (EmployeeDTO employeeDTO : employeeDTOs) {
                EmployeeDTO employeeDTOCreated = create(employeeDTO);
                employeeDTOList.add(employeeDTOCreated);
            }
        }
        logger.trace("<- create: {}", employeeDTOList);
        return employeeDTOList;
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.service.EmployeeService#create(org.opensource.community.project.dto.EmployeeDTO)
	 */
    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO) {
        logger.trace("-> create: {}", employeeDTO);
        employeeDTO = createPreHook(employeeDTO);
        Employee employee = mapper.map(employeeDTO);
        try {
            if (idPropertyName != null) {
                setmethod.invoke(employee, new Object[] { null });
            }
        } catch (Exception e) {
            throw new InvalidPrimaryKeyException(e.getMessage());
        }
        employee = employeeDao.create(employee);
        employeeDTO = mapper.map(employee);
        employeeDTO = createPostHook(employeeDTO);
        logger.trace("<- create: {}", employeeDTO);
        return employeeDTO;
    }

    /* (non-Javadoc)
     * @see org.opensource.community.project.service.EmployeeService#get(java.lang.String)
     */
    @Override
    public List<EmployeeDTO> get(String criteria) {
        logger.trace("-> get: {}", criteria);
        criteria = getPreHook(criteria);
        List<Employee> employees = employeeDao.get(criteria);
        List<EmployeeDTO> employeeDTOs = mapper.map(employees);
        employeeDTOs = getPostHook(employeeDTOs);
        logger.trace("<- get: {}", employeeDTOs);
        return employeeDTOs;
    }

    /* (non-Javadoc)
     * @see org.opensource.community.project.service.EmployeeService#update(java.util.List)
     */
    @Override
    public List<EmployeeDTO> update(List<EmployeeDTO> employeeDTOs) {
        logger.trace("-> update: {}", employeeDTOs);
        List<EmployeeDTO> employeeDTOsUpdated = new ArrayList<EmployeeDTO>();
        if (employeeDTOs != null) {
            for (EmployeeDTO employeeDTO : employeeDTOs) {
                EmployeeDTO employeeDTOCreated = update(employeeDTO);
                employeeDTOsUpdated.add(employeeDTOCreated);
            }
        }
        logger.trace("<- update: {}", employeeDTOsUpdated);
        return employeeDTOsUpdated;
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.service.EmployeeService#update(org.opensource.community.project.dto.EmployeeDTO)
	 */
    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO) {
        logger.trace("-> update: {}", employeeDTO);
        employeeDTO = updatePreHook(employeeDTO);
        Employee employee = mapper.map(employeeDTO);
        Object idVal;
        try {
            // Since id attribute name is not fixed in generated code, we are invoking a
            // dynamic getter method
            idVal = getmethod.invoke(employee, null);
        } catch (Exception e) {
            throw new InvalidPrimaryKeyException(e.getMessage());
        }
        List<Employee> employees = employeeDao.get(idPropertyName + "=" + idVal);
        if (employees == null || employees.size() != 1) {
            throw new EmployeeNotFoundException("Employee id " + idVal + " not found for update");
        }
        employee = employeeDao.update(employee);
        employeeDTO = mapper.map(employee);
        employeeDTO = updatePostHook(employeeDTO);
        logger.trace("<- update: {}", employeeDTO);
        return employeeDTO;
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.service.EmployeeService#delete(java.lang.Integer)
	 */
    @Override
    public EmployeeDTO delete(Object id) {
        logger.trace("-> delete: {}", id);
        id = deletePreHook(id);
        //List<Employee> employees = employeeDao.get("id=" + id);
        List<Employee> employees = employeeDao.get(idPropertyName + "=" + id.toString());
        if (employees == null || employees.size() != 1) {
            throw new EmployeeNotFoundException("Employee id " + id + " not found for delete");
        }
        employeeDao.delete(employees.get(0));
        EmployeeDTO employeeDTO = mapper.map(employees.get(0));
        employeeDTO = deletePostHook(employeeDTO);
        logger.trace("<- delete: {}", employeeDTO);
        return employeeDTO;
    }

    /**
	 * Creates the pre hook.
	 *
	 * @param employeeDTO the employee DTO
	 * @return the employee DTO
	 */
    protected EmployeeDTO createPreHook(EmployeeDTO employeeDTO) {
        return employeeDTO;
    }

    /**
	 * Creates the post hook.
	 *
	 * @param employeeDTO the employee DTO
	 * @return the employee DTO
	 */
    protected EmployeeDTO createPostHook(EmployeeDTO employeeDTO) {
        return employeeDTO;
    }

    /**
	 * Gets the pre hook.
	 *
	 * @param criterie the criterie
	 * @return the pre hook
	 */
    protected String getPreHook(String criterie) {
        return criterie;
    }

    /**
	 * Gets the post hook.
	 *
	 * @param employeeDTOs the employee DT os
	 * @return the post hook
	 */
    protected List<EmployeeDTO> getPostHook(List<EmployeeDTO> employeeDTOs) {
        return employeeDTOs;
    }

    /**
	 * Update pre hook.
	 *
	 * @param employeeDTO the employee DTO
	 * @return the employee DTO
	 */
    protected EmployeeDTO updatePreHook(EmployeeDTO employeeDTO) {
        return employeeDTO;
    }

    /**
	 * Update post hook.
	 *
	 * @param employeeDTO the employee DTO
	 * @return the employee DTO
	 */
    protected EmployeeDTO updatePostHook(EmployeeDTO employeeDTO) {
        return employeeDTO;
    }

    /**
	 * Delete pre hook.
	 *
	 * @param id the id
	 * @return the integer
	 */
    protected Object deletePreHook(Object id) {
        return id;
    }

    /**
	 * Delete post hook.
	 *
	 * @param employeeDTO the employee DTO
	 * @return the employee DTO
	 */
    protected EmployeeDTO deletePostHook(EmployeeDTO employeeDTO) {
        return employeeDTO;
    }
}