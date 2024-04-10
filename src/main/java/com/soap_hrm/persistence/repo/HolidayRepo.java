package com.soap_hrm.persistence.repo;

import com.soap_hrm.persistence.entities.Holiday;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class HolidayRepo extends CrudRepo<Holiday,Integer> {
    public HolidayRepo(EntityManager entityManager) {
        super(entityManager);
    }

    public List<Holiday> findHolidaysForEmployee(int id) {
        String jpql = "SELECT p FROM Holiday p WHERE p.employee.id = :id";
        TypedQuery<Holiday> findQuery = entityManager.createQuery(jpql, Holiday.class);
        findQuery.setParameter("id", id);
        try {
            return findQuery.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Holiday> findHolidaysInDay(LocalDate date) {
        String jpql = "SELECT p FROM Holiday p WHERE p.holidayDate = :date";
        TypedQuery<Holiday> findQuery = entityManager.createQuery(jpql, Holiday.class);
        findQuery.setParameter("date", date);
        try {
            return findQuery.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
