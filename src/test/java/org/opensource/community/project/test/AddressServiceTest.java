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
import org.opensource.community.project.dao.ext.AddressDaoExt;
import org.opensource.community.project.dto.AddressDTO;
import org.opensource.community.project.error.AddressNotFoundException;
import org.opensource.community.project.error.InvalidPrimaryKeyException;
import org.opensource.community.project.mapper.AddressMapper;
import org.opensource.community.project.model.Address;
import org.opensource.community.project.service.ext.AddressServiceExt;
import org.opensource.community.project.service.ext.impl.AddressServiceExtImpl;
import org.opensource.community.project.service.impl.AddressServiceImpl;
public class AddressServiceTest {

    private static AddressDaoExt addressDao;

    private static AddressDaoExt custDao;

    private static AddressMapper addressMapper;

    private static AddressMapper custMapper;

    private static AddressServiceExt addressService;

    private static AddressServiceExt custService;

    private static Address address;

    private static AddressDTO addressDto;

    @BeforeClass
    public static void beforeClass() {
        addressService = new AddressServiceExtImpl();
        addressDao = Mockito.mock(AddressDaoExt.class);
        custService = new AddressServiceExtImpl();
        custDao = Mockito.mock(AddressDaoExt.class);
        addressMapper = Mockito.mock(AddressMapper.class);
        custMapper = Mockito.mock(AddressMapper.class);
        ((AddressServiceImpl) addressService).setAddressDao(addressDao);
        ((AddressServiceImpl) custService).setAddressDao(custDao);
        ((AddressServiceImpl) addressService).setMapper(addressMapper);
        ((AddressServiceImpl) custService).setMapper(custMapper);
        address = new Address();
        List<Address> addressList = new ArrayList<Address>();
        addressList.add(address);
        addressDto = new AddressDTO();
        List<AddressDTO> addressDtoList = new ArrayList<AddressDTO>();
        addressDtoList.add(addressDto);
        Mockito.when(addressMapper.map(addressDto)).thenReturn(address);
        Mockito.when(custMapper.map(addressDto)).thenReturn(address);
        Mockito.when(addressDao.get(Mockito.anyString())).thenReturn(addressList);
        Mockito.when(addressDao.delete(address)).thenReturn(true);
        Mockito.when(addressMapper.map(address)).thenReturn(addressDto);
        Mockito.when(custMapper.map(address)).thenReturn(addressDto);
        Mockito.when(addressMapper.map(addressList)).thenReturn(addressDtoList);
        Mockito.when(custMapper.map(addressList)).thenReturn(addressDtoList);
        Mockito.when(addressMapper.mapList(addressDtoList)).thenReturn(addressList);
        Mockito.when(custMapper.mapList(addressDtoList)).thenReturn(addressList);
        Mockito.when(addressDao.create((Address) Mockito.any())).thenReturn(address);
        Mockito.doAnswer(new Answer<Object>() {

            public Object answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Address address = (Address) args[0];
                return address;
            }
        }).when(addressDao).update(Mockito.any());
    }

    @Test
    public void testFind() {
        List<AddressDTO> addressList = addressService.get("10");
        Mockito.verify(addressDao).get("10");
        assertEquals(addressList.size(), 1);
    }

    @Test
    public void testInsert() {
        List<AddressDTO> addressDtoList = addressService.get("20");
        addressDtoList = addressService.create(addressDtoList);
        Address address = addressMapper.map(addressDtoList.get(0));
        Mockito.verify(addressDao).create(address);
        assertEquals(1, addressDtoList.size());
    }

    @Test
    public void testUpdate() {
        List<AddressDTO> addressDtoList = addressService.get("20");
        addressDtoList = addressService.update(addressDtoList);
        assertEquals(1, addressDtoList.size());
    }

    @Test
    public void testRemove() {
        AddressDTO deletedDTO = addressService.delete(2);
        Assert.assertEquals(addressDto, deletedDTO);
    }

    @Test(expected = InvalidPrimaryKeyException.class)
    public void testCreateForException() {
        List<AddressDTO> addressDtoList = addressService.get("20");
        AddressDTO addressDTO = addressDtoList.get(0);
        addressDTO = null;
        addressService.create(addressDTO);
    }

    @Test(expected = AddressNotFoundException.class)
    public void testExceptionUpdate() {
        Mockito.when(custDao.get(Mockito.anyString())).thenReturn(null);
        custService.update(addressDto);
    }

    @Test(expected = InvalidPrimaryKeyException.class)
    public void testExceptionUpdate1() {
        Mockito.when(custDao.get(Mockito.anyString())).thenReturn(null);
        Mockito.when(custMapper.map(addressDto)).thenReturn(null);
        custService.update(addressDto);
    }

    @Test(expected = AddressNotFoundException.class)
    public void testExceptionDelete() {
        Mockito.when(custDao.get(Mockito.anyString())).thenReturn(null);
        custService.delete(2);
    }

    @Test
    public void testGetSurveyDao() {
        assertTrue(((AddressServiceImpl) addressService).getAddressDao() == addressDao);
    }

    @Test
    public void testGetMapper() {
        assertTrue(((AddressServiceImpl) addressService).getMapper() == addressMapper);
    }

    @Test(expected = InvalidPrimaryKeyException.class)
    public void testInvalidPrimaryKey() {
        Address Address = Mockito.mock(Address.class);
        AddressServiceImpl.staticInit(null);
    }
}