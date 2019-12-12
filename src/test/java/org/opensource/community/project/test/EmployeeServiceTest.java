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
import org.opensource.community.project.dao.ext.EmployeeDaoExt;
import org.opensource.community.project.dto.EmployeeDTO;
import org.opensource.community.project.error.EmployeeNotFoundException;
import org.opensource.community.project.error.InvalidPrimaryKeyException;
import org.opensource.community.project.mapper.EmployeeMapper;
import org.opensource.community.project.model.Employee;
import org.opensource.community.project.service.ext.EmployeeServiceExt;
import org.opensource.community.project.service.ext.impl.EmployeeServiceExtImpl;
import org.opensource.community.project.service.impl.EmployeeServiceImpl;
public class EmployeeServiceTest {

    private static EmployeeDaoExt employeeDao;

    private static EmployeeDaoExt custDao;

    private static EmployeeMapper employeeMapper;

    private static EmployeeMapper custMapper;

    private static EmployeeServiceExt employeeService;

    private static EmployeeServiceExt custService;

    private static Employee employee;

    private static EmployeeDTO employeeDto;

    @BeforeClass
    public static void beforeClass() {
        employeeService = new EmployeeServiceExtImpl();
        employeeDao = Mockito.mock(EmployeeDaoExt.class);
        custService = new EmployeeServiceExtImpl();
        custDao = Mockito.mock(EmployeeDaoExt.class);
        employeeMapper = Mockito.mock(EmployeeMapper.class);
        custMapper = Mockito.mock(EmployeeMapper.class);
        ((EmployeeServiceImpl) employeeService).setEmployeeDao(employeeDao);
        ((EmployeeServiceImpl) custService).setEmployeeDao(custDao);
        ((EmployeeServiceImpl) employeeService).setMapper(employeeMapper);
        ((EmployeeServiceImpl) custService).setMapper(custMapper);
        employee = new Employee();
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(employee);
        employeeDto = new EmployeeDTO();
        List<EmployeeDTO> employeeDtoList = new ArrayList<EmployeeDTO>();
        employeeDtoList.add(employeeDto);
        Mockito.when(employeeMapper.map(employeeDto)).thenReturn(employee);
        Mockito.when(custMapper.map(employeeDto)).thenReturn(employee);
        Mockito.when(employeeDao.get(Mockito.anyString())).thenReturn(employeeList);
        Mockito.when(employeeDao.delete(employee)).thenReturn(true);
        Mockito.when(employeeMapper.map(employee)).thenReturn(employeeDto);
        Mockito.when(custMapper.map(employee)).thenReturn(employeeDto);
        Mockito.when(employeeMapper.map(employeeList)).thenReturn(employeeDtoList);
        Mockito.when(custMapper.map(employeeList)).thenReturn(employeeDtoList);
        Mockito.when(employeeMapper.mapList(employeeDtoList)).thenReturn(employeeList);
        Mockito.when(custMapper.mapList(employeeDtoList)).thenReturn(employeeList);
        Mockito.when(employeeDao.create((Employee) Mockito.any())).thenReturn(employee);
        Mockito.doAnswer(new Answer<Object>() {

            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Employee employee = (Employee) args[0];
                return employee;
            }
        }).when(employeeDao).update(Mockito.any());
    }

    @Test
    public void testFind() {
        List<EmployeeDTO> employeeList = employeeService.get("10");
        Mockito.verify(employeeDao).get("10");
        assertEquals(employeeList.size(), 1);
    }

    @Test
    public void testInsert() {
        List<EmployeeDTO> employeeDtoList = employeeService.get("20");
        employeeDtoList = employeeService.create(employeeDtoList);
        Employee employee = employeeMapper.map(employeeDtoList.get(0));
        Mockito.verify(employeeDao).create(employee);
        assertEquals(1, employeeDtoList.size());
    }

    @Test
    public void testUpdate() {
        List<EmployeeDTO> employeeDtoList = employeeService.get("20");
        employeeDtoList = employeeService.update(employeeDtoList);
        assertEquals(1, employeeDtoList.size());
    }

    @Test
    public void testRemove() {
        EmployeeDTO deletedDTO = employeeService.delete(2);
        Assert.assertEquals(employeeDto, deletedDTO);
    }

    @Test(expected = InvalidPrimaryKeyException.class)
    public void testCreateForException() {
        List<EmployeeDTO> employeeDtoList = employeeService.get("20");
        EmployeeDTO employeeDTO = employeeDtoList.get(0);
        employeeDTO = null;
        employeeService.create(employeeDTO);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void testExceptionUpdate() {
        Mockito.when(custDao.get(Mockito.anyString())).thenReturn(null);
        custService.update(employeeDto);
    }

    @Test(expected = InvalidPrimaryKeyException.class)
    public void testExceptionUpdate1() {
        Mockito.when(custDao.get(Mockito.anyString())).thenReturn(null);
        Mockito.when(custMapper.map(employeeDto)).thenReturn(null);
        custService.update(employeeDto);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void testExceptionDelete() {
        Mockito.when(custDao.get(Mockito.anyString())).thenReturn(null);
        custService.delete(2);
    }

    @Test
    public void testGetSurveyDao() {
        assertTrue(((EmployeeServiceImpl) employeeService).getEmployeeDao() == employeeDao);
    }

    @Test
    public void testGetMapper() {
        assertTrue(((EmployeeServiceImpl) employeeService).getMapper() == employeeMapper);
    }

    @Test(expected = InvalidPrimaryKeyException.class)
    public void testInvalidPrimaryKey() {
        Employee Employee = Mockito.mock(Employee.class);
        EmployeeServiceImpl.staticInit(null);
    }
}