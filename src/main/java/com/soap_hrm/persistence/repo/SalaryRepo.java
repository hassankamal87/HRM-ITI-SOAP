package com.soap_hrm.persistence.repo;

import com.soap_hrm.persistence.entities.Salary;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.math.BigDecimal;

public class SalaryRepo extends CrudRepo<Salary, Integer> {
    public SalaryRepo(EntityManager entityManager) {
        super(entityManager);
    }

    public Salary findSalaryByAmount(BigDecimal amount){
        String jpql = "SELECT p FROM Salary p WHERE p.amount = :amount";
        TypedQuery<Salary> findQuery = entityManager.createQuery(jpql, Salary.class);
        findQuery.setParameter("amount", amount);
        try {
            return findQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
