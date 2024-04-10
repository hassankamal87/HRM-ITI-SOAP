package com.soap_hrm.presentation.controller;

import com.soap_hrm.business.dto.AddressDto;
import com.soap_hrm.business.service.AddressService;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.soap.SOAPFactory;
import jakarta.xml.soap.SOAPFault;
import jakarta.xml.ws.soap.SOAPFaultException;

import java.util.List;
import java.util.Objects;

@WebService
public class AddressController {
    AddressService addressService;

    public AddressController(){
        addressService = new AddressService();
    }

    public List<AddressDto> getAllAddresses(){
        return addressService.getAllAddresses();
    }

    public AddressDto getAddressById(@WebParam(name = "addressId") int id){
        AddressDto addressDto = addressService.getAddressByID(id);
        if (addressDto != null)
            return addressDto;
        throwSoapFault("invalid address id");
        return null;
    }

    public AddressDto createAddress(AddressDto addressDto){
        if (addressDto == null){
            throwSoapFault("invalid address data");
            return null;
        }
        AddressDto createdDto = addressService.createAddress(addressDto);
        if (createdDto != null)
            return createdDto;
        throwSoapFault("invalid address data");
        return null;
    }

    public void deleteAddressById(@WebParam(name = "addressId") int id) {
        boolean result = addressService.deleteAddressById(id);
        if (!result)
            throwSoapFault("can't delete this address");

    }

    private void throwSoapFault(String message) {
        try {
            SOAPFault fault = SOAPFactory.newInstance().createFault();
            fault.setFaultString(message);
            throw new SOAPFaultException(fault);
        } catch (Exception e) {
            throw new RuntimeException(message);
        }
    }
}
