package org.opensource.community.project.test;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.opensource.community.project.dao.ext.impl.DepartmentDaoExtImpl;
import org.opensource.community.project.dao.impl.DepartmentDaoImpl;
import org.opensource.community.project.error.DepartmentPersistanceException;
import org.opensource.community.project.error.InvalidPrimaryKeyException;
import org.opensource.community.project.model.Department;
/** 
 * This class contains junit test cases for DAO methods.
 * EntityManager and Query are mocked.
 * @author M1007163
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DepartmentDaoTest {

    private static EntityManager em;

    private static Query query;

    private static DepartmentDaoExtImpl departmentDao;

    private static Department department = new Department();

    @BeforeClass
    public static void beforeClass() {
        departmentDao = new DepartmentDaoExtImpl();
        em = Mockito.mock(EntityManager.class);
        query = Mockito.mock(Query.class);
        ((DepartmentDaoExtImpl) departmentDao).setEntityManager(em);
        ((DepartmentDaoExtImpl) departmentDao).setMockQuery(query);
        List<Department> departmentList = new ArrayList<Department>();
        departmentList.add(department);
        Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
        Mockito.doNothing().when(em).remove(Mockito.any());
        Mockito.doNothing().when(em).flush();
        Mockito.when(query.getResultList()).thenReturn(departmentList);
    }

    @Test
    public void testGetDepartment() {
        List<Department> departmentList = departmentDao.get("10");
        Assert.assertEquals(departmentList.size(), 1);
    //Assert.assertSame(null, (departmentDao.get("")).get(0).getId());
    }

    @Test
    public void testCreateDepartment() {
        Mockito.when(em.merge(Mockito.any())).thenReturn(department);
        Department department = new Department();
        Department createdDepartment = departmentDao.create(department);
        // Perform the actual test
        Assert.assertEquals(department.toString(), createdDepartment.toString());
    }

    @Test
    public void testUpdateDepartment() {
        Mockito.when(em.merge(Mockito.any())).thenReturn(department);
        Department department = new Department();
        Department updatedDepartment = departmentDao.update(department);
        Assert.assertEquals(department, updatedDepartment);
    }

    @Test
    public void testDeleteDepartment() {
        Department department = new Department();
        // Perform the actual test
        Assert.assertSame(true, (departmentDao.delete(department)));
    }

    @Test
    public void testGetAllDepartment() {
        List<Department> departmentList = departmentDao.get(null);
        Assert.assertEquals(departmentList.size(), 1);
    }

    @Test
    public void testGetEntityManager() {
        assertTrue(((DepartmentDaoImpl) departmentDao).getEntityManager() == em);
    }

    @Test
    public void testGetMockQuery() {
        assertTrue(((DepartmentDaoImpl) departmentDao).getMockQuery() == query);
    }

    @Test(expected = DepartmentPersistanceException.class)
    public void testCreateForException() throws Exception {
        Mockito.when(em.merge(Mockito.any(Department.class))).thenThrow(new InvalidPrimaryKeyException("Error occurred"));
        departmentDao.create(new Department());
    }
}