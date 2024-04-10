package com.soap_hrm.persistence.repo;

import com.soap_hrm.persistence.entities.Address;
import jakarta.persistence.EntityManager;

public class AddressRepo extends CrudRepo<Address, Integer> {
    public AddressRepo(EntityManager entityManager) {
        super(entityManager);
    }
}
