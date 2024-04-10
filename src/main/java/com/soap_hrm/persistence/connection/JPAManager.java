package com.soap_hrm.persistence.connection;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public enum JPAManager {
    INSTANCE;

    private final EntityManagerFactory entityManagerFactory;

    JPAManager(){

        this.entityManagerFactory = Persistence.createEntityManagerFactory("hrm");
    }
    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

}
