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
import org.opensource.community.project.dao.ext.impl.AddressDaoExtImpl;
import org.opensource.community.project.dao.impl.AddressDaoImpl;
import org.opensource.community.project.error.AddressPersistanceException;
import org.opensource.community.project.error.InvalidPrimaryKeyException;
import org.opensource.community.project.model.Address;
/** 
 * This class contains junit test cases for DAO methods.
 * EntityManager and Query are mocked.
 * @author M1007163
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AddressDaoTest {

    private static EntityManager em;

    private static Query query;

    private static AddressDaoExtImpl addressDao;

    private static Address address = new Address();

    @BeforeClass
    public static void beforeClass() {
        addressDao = new AddressDaoExtImpl();
        em = Mockito.mock(EntityManager.class);
        query = Mockito.mock(Query.class);
        ((AddressDaoExtImpl) addressDao).setEntityManager(em);
        ((AddressDaoExtImpl) addressDao).setMockQuery(query);
        List<Address> addressList = new ArrayList<Address>();
        addressList.add(address);
        Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
        Mockito.doNothing().when(em).remove(Mockito.any());
        Mockito.doNothing().when(em).flush();
        Mockito.when(query.getResultList()).thenReturn(addressList);
    }

    @Test
    public void testGetAddress() {
        List<Address> addressList = addressDao.get("10");
        Assert.assertEquals(addressList.size(), 1);
    //Assert.assertSame(null, (addressDao.get("")).get(0).getId());
    }

    @Test
    public void testCreateAddress() {
        Mockito.when(em.merge(Mockito.any())).thenReturn(address);
        Address address = new Address();
        Address createdAddress = addressDao.create(address);
        // Perform the actual test
        Assert.assertEquals(address.toString(), createdAddress.toString());
    }

    @Test
    public void testUpdateAddress() {
        Mockito.when(em.merge(Mockito.any())).thenReturn(address);
        Address address = new Address();
        Address updatedAddress = addressDao.update(address);
        Assert.assertEquals(address, updatedAddress);
    }

    @Test
    public void testDeleteAddress() {
        Address address = new Address();
        // Perform the actual test
        Assert.assertSame(true, (addressDao.delete(address)));
    }

    @Test
    public void testGetAllAddress() {
        List<Address> addressList = addressDao.get(null);
        Assert.assertEquals(addressList.size(), 1);
    }

    @Test
    public void testGetEntityManager() {
        assertTrue(((AddressDaoImpl) addressDao).getEntityManager() == em);
    }

    @Test
    public void testGetMockQuery() {
        assertTrue(((AddressDaoImpl) addressDao).getMockQuery() == query);
    }

    @Test(expected = AddressPersistanceException.class)
    public void testCreateForException() throws Exception {
        Mockito.when(em.merge(Mockito.any(Address.class))).thenThrow(new InvalidPrimaryKeyException("Error occurred"));
        addressDao.create(new Address());
    }
}