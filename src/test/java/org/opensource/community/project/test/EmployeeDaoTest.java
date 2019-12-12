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
import org.opensource.community.project.dao.ext.impl.EmployeeDaoExtImpl;
import org.opensource.community.project.dao.impl.EmployeeDaoImpl;
import org.opensource.community.project.error.EmployeePersistanceException;
import org.opensource.community.project.error.InvalidPrimaryKeyException;
import org.opensource.community.project.model.Employee;
/** 
 * This class contains junit test cases for DAO methods.
 * EntityManager and Query are mocked.
 * @author M1007163
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class EmployeeDaoTest {

    private static EntityManager em;

    private static Query query;

    private static EmployeeDaoExtImpl employeeDao;

    private static Employee employee = new Employee();

    @BeforeClass
    public static void beforeClass() {
        employeeDao = new EmployeeDaoExtImpl();
        em = Mockito.mock(EntityManager.class);
        query = Mockito.mock(Query.class);
        ((EmployeeDaoExtImpl) employeeDao).setEntityManager(em);
        ((EmployeeDaoExtImpl) employeeDao).setMockQuery(query);
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(employee);
        Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
        Mockito.doNothing().when(em).remove(Mockito.any());
        Mockito.doNothing().when(em).flush();
        Mockito.when(query.getResultList()).thenReturn(employeeList);
    }

    @Test
    public void testGetEmployee() {
        List<Employee> employeeList = employeeDao.get("10");
        Assert.assertEquals(employeeList.size(), 1);
    //Assert.assertSame(null, (employeeDao.get("")).get(0).getId());
    }

    @Test
    public void testCreateEmployee() {
        Mockito.when(em.merge(Mockito.any())).thenReturn(employee);
        Employee employee = new Employee();
        Employee createdEmployee = employeeDao.create(employee);
        // Perform the actual test
        Assert.assertEquals(employee.toString(), createdEmployee.toString());
    }

    @Test
    public void testUpdateEmployee() {
        Mockito.when(em.merge(Mockito.any())).thenReturn(employee);
        Employee employee = new Employee();
        Employee updatedEmployee = employeeDao.update(employee);
        Assert.assertEquals(employee, updatedEmployee);
    }

    @Test
    public void testDeleteEmployee() {
        Employee employee = new Employee();
        // Perform the actual test
        Assert.assertSame(true, (employeeDao.delete(employee)));
    }

    @Test
    public void testGetAllEmployee() {
        List<Employee> employeeList = employeeDao.get(null);
        Assert.assertEquals(employeeList.size(), 1);
    }

    @Test
    public void testGetEntityManager() {
        assertTrue(((EmployeeDaoImpl) employeeDao).getEntityManager() == em);
    }

    @Test
    public void testGetMockQuery() {
        assertTrue(((EmployeeDaoImpl) employeeDao).getMockQuery() == query);
    }

    @Test(expected = EmployeePersistanceException.class)
    public void testCreateForException() throws Exception {
        Mockito.when(em.merge(Mockito.any(Employee.class))).thenThrow(new InvalidPrimaryKeyException("Error occurred"));
        employeeDao.create(new Employee());
    }
}