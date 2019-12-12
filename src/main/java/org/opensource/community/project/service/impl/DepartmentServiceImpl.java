package org.opensource.community.project.service.impl;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.text.WordUtils;
import org.opensource.community.project.dao.ext.DepartmentDaoExt;
import org.opensource.community.project.dto.DepartmentDTO;
import org.opensource.community.project.error.DepartmentNotFoundException;
import org.opensource.community.project.error.InvalidPrimaryKeyException;
import org.opensource.community.project.mapper.DepartmentMapper;
import org.opensource.community.project.model.Department;
import org.opensource.community.project.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    /** The department dao. */
    @Autowired
    private DepartmentDaoExt departmentDao;

    /** The mapper. */
    @Autowired
    DepartmentMapper mapper;

    private static Method getmethod;

    private static Method setmethod;

    private static String idPropertyName;

    private static Class<?> idPropertyType;

    private static Class<?> cls;

    static {
        Department department = new Department();
        staticInit(department);
    }

    public static void staticInit(Department department) {
        try {
            cls = Class.forName(department.getClass().getCanonicalName());
            Class<?>[] noparams = {};
            idPropertyName = department.getIdPropertyName();
            idPropertyType = department.getIdPropertyType();
            if (idPropertyName != null) {
                getmethod = cls.getDeclaredMethod("get" + WordUtils.capitalize(idPropertyName), noparams);
                setmethod = cls.getDeclaredMethod("set" + WordUtils.capitalize(idPropertyName), idPropertyType);
            }
        } catch (Exception e) {
            throw new InvalidPrimaryKeyException(e.getMessage());
        }
    }

    public DepartmentDaoExt getDepartmentDao() {
        return departmentDao;
    }

    public void setDepartmentDao(DepartmentDaoExt departmentDao) {
        this.departmentDao = departmentDao;
    }

    public DepartmentMapper getMapper() {
        return mapper;
    }

    public void setMapper(DepartmentMapper mapper) {
        this.mapper = mapper;
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.service.DepartmentService#create(java.util.List)
	 */
    @Override
    public List<DepartmentDTO> create(List<DepartmentDTO> departmentDTOs) {
        logger.trace("-> create: {}", departmentDTOs);
        List<DepartmentDTO> departmentDTOList = new ArrayList<DepartmentDTO>();
        if (departmentDTOs != null) {
            for (DepartmentDTO departmentDTO : departmentDTOs) {
                DepartmentDTO departmentDTOCreated = create(departmentDTO);
                departmentDTOList.add(departmentDTOCreated);
            }
        }
        logger.trace("<- create: {}", departmentDTOList);
        return departmentDTOList;
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.service.DepartmentService#create(org.opensource.community.project.dto.DepartmentDTO)
	 */
    @Override
    public DepartmentDTO create(DepartmentDTO departmentDTO) {
        logger.trace("-> create: {}", departmentDTO);
        departmentDTO = createPreHook(departmentDTO);
        Department department = mapper.map(departmentDTO);
        try {
            if (idPropertyName != null) {
                setmethod.invoke(department, new Object[] { null });
            }
        } catch (Exception e) {
            throw new InvalidPrimaryKeyException(e.getMessage());
        }
        department = departmentDao.create(department);
        departmentDTO = mapper.map(department);
        departmentDTO = createPostHook(departmentDTO);
        logger.trace("<- create: {}", departmentDTO);
        return departmentDTO;
    }

    /* (non-Javadoc)
     * @see org.opensource.community.project.service.DepartmentService#get(java.lang.String)
     */
    @Override
    public List<DepartmentDTO> get(String criteria) {
        logger.trace("-> get: {}", criteria);
        criteria = getPreHook(criteria);
        List<Department> departments = departmentDao.get(criteria);
        List<DepartmentDTO> departmentDTOs = mapper.map(departments);
        departmentDTOs = getPostHook(departmentDTOs);
        logger.trace("<- get: {}", departmentDTOs);
        return departmentDTOs;
    }

    /* (non-Javadoc)
     * @see org.opensource.community.project.service.DepartmentService#update(java.util.List)
     */
    @Override
    public List<DepartmentDTO> update(List<DepartmentDTO> departmentDTOs) {
        logger.trace("-> update: {}", departmentDTOs);
        List<DepartmentDTO> departmentDTOsUpdated = new ArrayList<DepartmentDTO>();
        if (departmentDTOs != null) {
            for (DepartmentDTO departmentDTO : departmentDTOs) {
                DepartmentDTO departmentDTOCreated = update(departmentDTO);
                departmentDTOsUpdated.add(departmentDTOCreated);
            }
        }
        logger.trace("<- update: {}", departmentDTOsUpdated);
        return departmentDTOsUpdated;
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.service.DepartmentService#update(org.opensource.community.project.dto.DepartmentDTO)
	 */
    @Override
    public DepartmentDTO update(DepartmentDTO departmentDTO) {
        logger.trace("-> update: {}", departmentDTO);
        departmentDTO = updatePreHook(departmentDTO);
        Department department = mapper.map(departmentDTO);
        Object idVal;
        try {
            // Since id attribute name is not fixed in generated code, we are invoking a
            // dynamic getter method
            idVal = getmethod.invoke(department, null);
        } catch (Exception e) {
            throw new InvalidPrimaryKeyException(e.getMessage());
        }
        List<Department> departments = departmentDao.get(idPropertyName + "=" + idVal);
        if (departments == null || departments.size() != 1) {
            throw new DepartmentNotFoundException("Department id " + idVal + " not found for update");
        }
        department = departmentDao.update(department);
        departmentDTO = mapper.map(department);
        departmentDTO = updatePostHook(departmentDTO);
        logger.trace("<- update: {}", departmentDTO);
        return departmentDTO;
    }

    /* (non-Javadoc)
	 * @see org.opensource.community.project.service.DepartmentService#delete(java.lang.Integer)
	 */
    @Override
    public DepartmentDTO delete(Object id) {
        logger.trace("-> delete: {}", id);
        id = deletePreHook(id);
        //List<Department> departments = departmentDao.get("id=" + id);
        List<Department> departments = departmentDao.get(idPropertyName + "=" + id.toString());
        if (departments == null || departments.size() != 1) {
            throw new DepartmentNotFoundException("Department id " + id + " not found for delete");
        }
        departmentDao.delete(departments.get(0));
        DepartmentDTO departmentDTO = mapper.map(departments.get(0));
        departmentDTO = deletePostHook(departmentDTO);
        logger.trace("<- delete: {}", departmentDTO);
        return departmentDTO;
    }

    /**
	 * Creates the pre hook.
	 *
	 * @param departmentDTO the department DTO
	 * @return the department DTO
	 */
    protected DepartmentDTO createPreHook(DepartmentDTO departmentDTO) {
        return departmentDTO;
    }

    /**
	 * Creates the post hook.
	 *
	 * @param departmentDTO the department DTO
	 * @return the department DTO
	 */
    protected DepartmentDTO createPostHook(DepartmentDTO departmentDTO) {
        return departmentDTO;
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
	 * @param departmentDTOs the department DT os
	 * @return the post hook
	 */
    protected List<DepartmentDTO> getPostHook(List<DepartmentDTO> departmentDTOs) {
        return departmentDTOs;
    }

    /**
	 * Update pre hook.
	 *
	 * @param departmentDTO the department DTO
	 * @return the department DTO
	 */
    protected DepartmentDTO updatePreHook(DepartmentDTO departmentDTO) {
        return departmentDTO;
    }

    /**
	 * Update post hook.
	 *
	 * @param departmentDTO the department DTO
	 * @return the department DTO
	 */
    protected DepartmentDTO updatePostHook(DepartmentDTO departmentDTO) {
        return departmentDTO;
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
	 * @param departmentDTO the department DTO
	 * @return the department DTO
	 */
    protected DepartmentDTO deletePostHook(DepartmentDTO departmentDTO) {
        return departmentDTO;
    }
}