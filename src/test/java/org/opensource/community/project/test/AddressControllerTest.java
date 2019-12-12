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
import org.opensource.community.project.controller.AddressController;
import org.opensource.community.project.controller.ext.AddressControllerExt;
import org.opensource.community.project.dto.AddressDTO;
import org.opensource.community.project.error.AddressNotFoundException;
import org.opensource.community.project.error.InvalidPrimaryKeyException;
import org.opensource.community.project.service.ext.AddressServiceExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
@RunWith(MockitoJUnitRunner.class)
public class AddressControllerTest {

    /** The Constant logger. */
    private static final Logger logger = LoggerFactory.getLogger(AddressControllerTest.class);

    @InjectMocks
    private static AddressControllerExt controllerUT = new AddressControllerExt();

    @MockBean
    private static AddressServiceExt addressService;

    @Mock
    private static AddressDTO addressDTO;

    private static List<AddressDTO> addressDTOList;

    @BeforeClass
    public static void beforeClass() {
        addressService = Mockito.mock(AddressServiceExt.class);
        ((AddressControllerExt) controllerUT).setAddressServiceExt(addressService);
        addressDTO = new AddressDTO();
        addressDTOList = new ArrayList<AddressDTO>();
        addressDTOList.add(addressDTO);
    }

    @Test
    public void retrieveDetailsForAddress() throws Exception {
        Mockito.when(addressService.get(Mockito.anyString())).thenReturn(addressDTOList);
        List<AddressDTO> addressDtoList = controllerUT.get("10");
        assertEquals(1, addressDtoList.size());
    }

    @Test
    public void createAddress() throws Exception {
        Mockito.when(addressService.create(Mockito.any(AddressDTO.class))).thenReturn(addressDTO);
        AddressDTO addressDTORes = controllerUT.create(addressDTO);
        assertEquals(addressDTO, addressDTORes);
    }

    @Test
    public void createAddresss() throws Exception {
        Mockito.when(addressService.create(Mockito.any(List.class))).thenReturn(addressDTOList);
        List<AddressDTO> addressDTORes = controllerUT.create(addressDTOList);
        assertEquals(addressDTOList, addressDTORes);
    }

    @Test
    public void updateAddress() throws Exception {
        // AddressService.create to respond back with mockAddress
        Mockito.when(addressService.update(Mockito.any(AddressDTO.class))).thenReturn(addressDTO);
        AddressDTO addressDTORes = controllerUT.update(addressDTO);
        assertEquals(addressDTO, addressDTORes);
    }

    @Test
    public void deleteAddress() throws Exception {
        Mockito.when(addressService.delete(Mockito.any())).thenReturn(addressDTO);
        AddressDTO addressDTO = controllerUT.delete("20");
        assertEquals(addressDTO, addressDTO);
    }

    @Test(expected = AddressNotFoundException.class)
    public void testCreateForException() throws Exception {
        Mockito.when(addressService.create(Mockito.any(AddressDTO.class))).thenThrow(new InvalidPrimaryKeyException("Error occurred"));
        controllerUT.create(addressDTO);
    }

    @Test(expected = AddressNotFoundException.class)
    public void testCreateAddresssForException() throws Exception {
        Mockito.when(addressService.create(Mockito.any(List.class))).thenThrow(new InvalidPrimaryKeyException("Error occurred"));
        controllerUT.create(addressDTOList);
    }
}