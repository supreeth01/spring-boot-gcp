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
import org.opensource.community.project.controller.DepartmentController;
import org.opensource.community.project.controller.ext.DepartmentControllerExt;
import org.opensource.community.project.dto.DepartmentDTO;
import org.opensource.community.project.error.DepartmentNotFoundException;
import org.opensource.community.project.error.InvalidPrimaryKeyException;
import org.opensource.community.project.service.ext.DepartmentServiceExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
@RunWith(MockitoJUnitRunner.class)
public class DepartmentControllerTest {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(DepartmentControllerTest.class);

    @InjectMocks
    private static DepartmentControllerExt controllerUT = new DepartmentControllerExt();

    @MockBean
    private static DepartmentServiceExt departmentService;

    @Mock
    private static DepartmentDTO departmentDTO;

    private static List<DepartmentDTO> departmentDTOList;

    @BeforeClass
    public static void beforeClass() {
        departmentService = Mockito.mock(DepartmentServiceExt.class);
        ((DepartmentControllerExt) controllerUT).setDepartmentServiceExt(departmentService);
        departmentDTO = new DepartmentDTO();
        departmentDTOList = new ArrayList<DepartmentDTO>();
        departmentDTOList.add(departmentDTO);
    }

    @Test
    public void retrieveDetailsForDepartment() throws Exception {
        Mockito.when(departmentService.get(Mockito.anyString())).thenReturn(departmentDTOList);
        List<DepartmentDTO> departmentDtoList = controllerUT.get("10");
        assertEquals(1, departmentDtoList.size());
    }

    @Test
    public void createDepartment() throws Exception {
        Mockito.when(departmentService.create(Mockito.any(DepartmentDTO.class))).thenReturn(departmentDTO);
        DepartmentDTO departmentDTORes = controllerUT.create(departmentDTO);
        assertEquals(departmentDTO, departmentDTORes);
    }

    @Test
    public void createDepartments() throws Exception {
        Mockito.when(departmentService.create(Mockito.any(List.class))).thenReturn(departmentDTOList);
        List<DepartmentDTO> departmentDTORes = controllerUT.create(departmentDTOList);
        assertEquals(departmentDTOList, departmentDTORes);
    }

    @Test
    public void updateDepartment() throws Exception {
        // DepartmentService.create to respond back with mockDepartment
        Mockito.when(departmentService.update(Mockito.any(DepartmentDTO.class))).thenReturn(departmentDTO);
        DepartmentDTO departmentDTORes = controllerUT.update(departmentDTO);
        assertEquals(departmentDTO, departmentDTORes);
    }

    @Test
    public void deleteDepartment() throws Exception {
        Mockito.when(departmentService.delete(Mockito.any())).thenReturn(departmentDTO);
        DepartmentDTO departmentDTO = controllerUT.delete("20");
        assertEquals(departmentDTO, departmentDTO);
    }

    @Test(expected = DepartmentNotFoundException.class)
    public void testCreateForException() throws Exception {
        Mockito.when(departmentService.create(Mockito.any(DepartmentDTO.class))).thenThrow(new InvalidPrimaryKeyException("Error occurred"));
        controllerUT.create(departmentDTO);
    }

    @Test(expected = DepartmentNotFoundException.class)
    public void testCreateDepartmentsForException() throws Exception {
        Mockito.when(departmentService.create(Mockito.any(List.class))).thenThrow(new InvalidPrimaryKeyException("Error occurred"));
        controllerUT.create(departmentDTOList);
    }
}