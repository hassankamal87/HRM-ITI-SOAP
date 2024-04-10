package com.soap_hrm.persistence.repo;

import com.soap_hrm.persistence.entities.TimeEntry;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TimeEntryRepo extends CrudRepo<TimeEntry,Integer> {
    public TimeEntryRepo(EntityManager entityManager) {
        super(entityManager);
    }

    public List<TimeEntry> findTimeEntriesForEmployee(int id) {
        String jpql = "SELECT p FROM TimeEntry p WHERE p.employee.id = :id";
        TypedQuery<TimeEntry> findQuery = entityManager.createQuery(jpql, TimeEntry.class);
        findQuery.setParameter("id", id);
        try {
            return findQuery.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<TimeEntry> findTimeEntriesForEmployeeInMonth(int id, int year, int month) {
        String jpql = "SELECT p FROM TimeEntry p WHERE p.employee.id = :id AND FUNCTION('YEAR', p.entryDate) = :year AND FUNCTION('MONTH', p.entryDate) = :month";
        TypedQuery<TimeEntry> findQuery = entityManager.createQuery(jpql, TimeEntry.class);
        findQuery.setParameter("id", id);
        findQuery.setParameter("year", year);
        findQuery.setParameter("month", month);
        try {
            return findQuery.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
    public Long findTotalHoursForEmployeeInMonth(int id, int year, int month) {
        String jpql = "SELECT SUM(FUNCTION('HOUR', p.timeOut) - FUNCTION('HOUR', p.timeIn)) FROM TimeEntry p WHERE p.employee.id = :id AND FUNCTION('YEAR', p.entryDate) = :year AND FUNCTION('MONTH', p.entryDate) = :month";
        TypedQuery<Long> findQuery = entityManager.createQuery(jpql, Long.class);
        findQuery.setParameter("id", id);
        findQuery.setParameter("year", year);
        findQuery.setParameter("month", month);
        try {
            return findQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
