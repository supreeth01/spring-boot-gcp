package org.opensource.community.project.test;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.opensource.community.project.controller.EmployeeController;
import org.opensource.community.project.controller.ext.EmployeeControllerExt;
import org.opensource.community.project.dto.EmployeeDTO;
import org.opensource.community.project.error.EmployeeNotFoundException;
import org.opensource.community.project.error.InvalidPrimaryKeyException;
import org.opensource.community.project.service.ext.EmployeeServiceExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(EmployeeControllerTest.class);

    @InjectMocks
    private static EmployeeControllerExt controllerUT = new EmployeeControllerExt();

    @MockBean
    private static EmployeeServiceExt employeeService;

    @Mock
    private static EmployeeDTO employeeDTO;

    private static List<EmployeeDTO> employeeDTOList;

    @BeforeClass
    public static void beforeClass() {
        employeeService = Mockito.mock(EmployeeServiceExt.class);
        ((EmployeeControllerExt) controllerUT).setEmployeeServiceExt(employeeService);
        employeeDTO = new EmployeeDTO();
        employeeDTOList = new ArrayList<EmployeeDTO>();
        employeeDTOList.add(employeeDTO);
    }

    @Test
    public void retrieveDetailsForEmployee() throws Exception {
        Mockito.when(employeeService.get(Mockito.anyString())).thenReturn(employeeDTOList);
        List<EmployeeDTO> employeeDtoList = controllerUT.get("10");
        assertEquals(1, employeeDtoList.size());
    }

    @Test
    public void createEmployee() throws Exception {
        Mockito.when(employeeService.create(Mockito.any(EmployeeDTO.class))).thenReturn(employeeDTO);
        EmployeeDTO employeeDTORes = controllerUT.create(employeeDTO);
        assertEquals(employeeDTO, employeeDTORes);
    }

    @Test
    public void createEmployees() throws Exception {
        Mockito.when(employeeService.create(Mockito.any(List.class))).thenReturn(employeeDTOList);
        List<EmployeeDTO> employeeDTORes = controllerUT.create(employeeDTOList);
        assertEquals(employeeDTOList, employeeDTORes);
    }

    @Test
    public void updateEmployee() throws Exception {
        // EmployeeService.create to respond back with mockEmployee
        Mockito.when(employeeService.update(Mockito.any(EmployeeDTO.class))).thenReturn(employeeDTO);
        EmployeeDTO employeeDTORes = controllerUT.update(employeeDTO);
        assertEquals(employeeDTO, employeeDTORes);
    }

    @Test
    public void deleteEmployee() throws Exception {
        Mockito.when(employeeService.delete(Mockito.any())).thenReturn(employeeDTO);
        EmployeeDTO employeeDTO = controllerUT.delete("20");
        assertEquals(employeeDTO, employeeDTO);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void testCreateForException() throws Exception {
        Mockito.when(employeeService.create(Mockito.any(EmployeeDTO.class))).thenThrow(new InvalidPrimaryKeyException("Error occurred"));
        controllerUT.create(employeeDTO);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void testCreateEmployeesForException() throws Exception {
        Mockito.when(employeeService.create(Mockito.any(List.class))).thenThrow(new InvalidPrimaryKeyException("Error occurred"));
        controllerUT.create(employeeDTOList);
    }
}