package com.soap_hrm.business.mappers;

import com.soap_hrm.business.dto.AddressDto;
import com.soap_hrm.persistence.entities.Address;

public class AddressMapper extends GenericMapping<Address, AddressDto> {

    private static AddressMapper instance;

    private AddressMapper(){
        if (instance!=null){
            throw new RuntimeException("wld 3eeb");
        }
    }

    public static synchronized AddressMapper getInstance(){
        if (instance == null){
            instance = new AddressMapper();
        }
        return instance;
    }}
