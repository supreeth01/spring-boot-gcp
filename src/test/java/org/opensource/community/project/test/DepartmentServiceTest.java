package org.opensource.community.project.test;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.opensource.community.project.dao.ext.DepartmentDaoExt;
import org.opensource.community.project.dto.DepartmentDTO;
import org.opensource.community.project.error.DepartmentNotFoundException;
import org.opensource.community.project.error.InvalidPrimaryKeyException;
import org.opensource.community.project.mapper.DepartmentMapper;
import org.opensource.community.project.model.Department;
import org.opensource.community.project.service.ext.DepartmentServiceExt;
import org.opensource.community.project.service.ext.impl.DepartmentServiceExtImpl;
import org.opensource.community.project.service.impl.DepartmentServiceImpl;
public class DepartmentServiceTest {

    private static DepartmentDaoExt departmentDao;

    private static DepartmentDaoExt custDao;

    private static DepartmentMapper departmentMapper;

    private static DepartmentMapper custMapper;

    private static DepartmentServiceExt departmentService;

    private static DepartmentServiceExt custService;

    private static Department department;

    private static DepartmentDTO departmentDto;

    @BeforeClass
    public static void beforeClass() {
        departmentService = new DepartmentServiceExtImpl();
        departmentDao = Mockito.mock(DepartmentDaoExt.class);
        custService = new DepartmentServiceExtImpl();
        custDao = Mockito.mock(DepartmentDaoExt.class);
        departmentMapper = Mockito.mock(DepartmentMapper.class);
        custMapper = Mockito.mock(DepartmentMapper.class);
        ((DepartmentServiceImpl) departmentService).setDepartmentDao(departmentDao);
        ((DepartmentServiceImpl) custService).setDepartmentDao(custDao);
        ((DepartmentServiceImpl) departmentService).setMapper(departmentMapper);
        ((DepartmentServiceImpl) custService).setMapper(custMapper);
        department = new Department();
        List<Department> departmentList = new ArrayList<Department>();
        departmentList.add(department);
        departmentDto = new DepartmentDTO();
        List<DepartmentDTO> departmentDtoList = new ArrayList<DepartmentDTO>();
        departmentDtoList.add(departmentDto);
        Mockito.when(departmentMapper.map(departmentDto)).thenReturn(department);
        Mockito.when(custMapper.map(departmentDto)).thenReturn(department);
        Mockito.when(departmentDao.get(Mockito.anyString())).thenReturn(departmentList);
        Mockito.when(departmentDao.delete(department)).thenReturn(true);
        Mockito.when(departmentMapper.map(department)).thenReturn(departmentDto);
        Mockito.when(custMapper.map(department)).thenReturn(departmentDto);
        Mockito.when(departmentMapper.map(departmentList)).thenReturn(departmentDtoList);
        Mockito.when(custMapper.map(departmentList)).thenReturn(departmentDtoList);
        Mockito.when(departmentMapper.mapList(departmentDtoList)).thenReturn(departmentList);
        Mockito.when(custMapper.mapList(departmentDtoList)).thenReturn(departmentList);
        Mockito.when(departmentDao.create((Department) Mockito.any())).thenReturn(department);
        Mockito.doAnswer(new Answer<Object>() {

            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Department department = (Department) args[0];
                return department;
            }
        }).when(departmentDao).update(Mockito.any());
    }

    @Test
    public void testFind() {
        List<DepartmentDTO> departmentList = departmentService.get("10");
        Mockito.verify(departmentDao).get("10");
        assertEquals(departmentList.size(), 1);
    }

    @Test
    public void testInsert() {
        List<DepartmentDTO> departmentDtoList = departmentService.get("20");
        departmentDtoList = departmentService.create(departmentDtoList);
        Department department = departmentMapper.map(departmentDtoList.get(0));
        Mockito.verify(departmentDao).create(department);
        assertEquals(1, departmentDtoList.size());
    }

    @Test
    public void testUpdate() {
        List<DepartmentDTO> departmentDtoList = departmentService.get("20");
        departmentDtoList = departmentService.update(departmentDtoList);
        assertEquals(1, departmentDtoList.size());
    }

    @Test
    public void testRemove() {
        DepartmentDTO deletedDTO = departmentService.delete(2);
        Assert.assertEquals(departmentDto, deletedDTO);
    }

    @Test(expected = InvalidPrimaryKeyException.class)
    public void testCreateForException() {
        List<DepartmentDTO> departmentDtoList = departmentService.get("20");
        DepartmentDTO departmentDTO = departmentDtoList.get(0);
        departmentDTO = null;
        departmentService.create(departmentDTO);
    }

    @Test(expected = DepartmentNotFoundException.class)
    public void testExceptionUpdate() {
        Mockito.when(custDao.get(Mockito.anyString())).thenReturn(null);
        custService.update(departmentDto);
    }

    @Test(expected = InvalidPrimaryKeyException.class)
    public void testExceptionUpdate1() {
        Mockito.when(custDao.get(Mockito.anyString())).thenReturn(null);
        Mockito.when(custMapper.map(departmentDto)).thenReturn(null);
        custService.update(departmentDto);
    }

    @Test(expected = DepartmentNotFoundException.class)
    public void testExceptionDelete() {
        Mockito.when(custDao.get(Mockito.anyString())).thenReturn(null);
        custService.delete(2);
    }

    @Test
    public void testGetSurveyDao() {
        assertTrue(((DepartmentServiceImpl) departmentService).getDepartmentDao() == departmentDao);
    }

    @Test
    public void testGetMapper() {
        assertTrue(((DepartmentServiceImpl) departmentService).getMapper() == departmentMapper);
    }

    @Test(expected = InvalidPrimaryKeyException.class)
    public void testInvalidPrimaryKey() {
        Department Department = Mockito.mock(Department.class);
        DepartmentServiceImpl.staticInit(null);
    }
}