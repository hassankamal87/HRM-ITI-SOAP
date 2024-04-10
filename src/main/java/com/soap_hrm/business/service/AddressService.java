package com.soap_hrm.business.service;

import com.soap_hrm.business.dto.AddressDto;
import com.soap_hrm.business.mappers.AddressMapper;
import com.soap_hrm.persistence.connection.JPAManager;
import com.soap_hrm.persistence.entities.Address;
import com.soap_hrm.persistence.repo.AddressRepo;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AddressService {
    EntityManager em;
    AddressRepo addressRepo;

    public AddressService() {
        em = JPAManager.INSTANCE.getEntityManagerFactory().createEntityManager();
        addressRepo = new AddressRepo(em);
    }

    public List<AddressDto> getAllAddresses() {
        List<Address> addresses = addressRepo.findAll(Address.class);

        return addresses.stream()
                .map(address -> AddressMapper.getInstance().mapEntityToDto(address, AddressDto.class))
                .toList();
    }

    public AddressDto getAddressByID(int id) {
        Address address = addressRepo.findById(Address.class, id);
        if (address != null)
            return AddressMapper.getInstance().mapEntityToDto(address, AddressDto.class);
        return null;
    }

    public AddressDto createAddress(AddressDto addressDto){
        String result = isAddressDtoValid(addressDto);
        if (!result.isEmpty())
            return null;

        Address newAddress = new Address();
        newAddress.setCity(addressDto.getCity());
        newAddress.setCountry(addressDto.getCountry());
        newAddress.setStreetName(addressDto.getStreetName());

        newAddress  = addressRepo.save(newAddress);
        addressDto.setId(newAddress.getId());
        return addressDto;
    }

    private String isAddressDtoValid(AddressDto addressDto) {
        if (addressDto.getCity().isEmpty())
            return "city is empty";
        if (addressDto.getCountry().isEmpty())
            return "country is empty";
        if (addressDto.getStreetName().isEmpty())
            return "street name is empty";
        return "";
    }

    public String deleteAddressById(int id){
        try {
            addressRepo.deleteById(Address.class, id);
            return "";
        }catch (RuntimeException exception){
            return "can't delete this address";
        }
    }
}
