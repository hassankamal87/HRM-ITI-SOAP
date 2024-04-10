package com.soap_hrm.persistence.repo;

import com.soap_hrm.persistence.entities.Department;
import com.soap_hrm.persistence.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DepartmentRepo extends CrudRepo<Department, Integer> {
    public DepartmentRepo(EntityManager entityManager) {
        super(entityManager);
    }

    public List<Department> findDepartmentsByName(String name) {
        String jpql = "SELECT p FROM Department p WHERE p.depName LIKE :name";
        TypedQuery<Department> findQuery = entityManager.createQuery(jpql, Department.class);
        findQuery.setParameter("name", name + "%");
        try {
            return findQuery.getResultList();
        } catch (NoResultException e) {
            return List.of();
        }
    }

    public Department findDepartmentByManagerID(int id) {
        String jpql = "SELECT p FROM Department p WHERE p.depMGR.id = :id";
        TypedQuery<Department> findQuery = entityManager.createQuery(jpql, Department.class);
        findQuery.setParameter("id", id);
        try {
            return findQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Department> findDepartmentsByManagerName(String name) {
        String jpql = "SELECT p FROM Department p WHERE p.depMGR.empName like :name";
        TypedQuery<Department> findQuery = entityManager.createQuery(jpql, Department.class);
        findQuery.setParameter("name", name + "%");
        try {
            return findQuery.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void deleteAll() {
        String jpql = "DELETE FROM Department ";
        entityManager.createQuery(jpql).executeUpdate();
    }

    public Employee getDepartmentManager(int deptId) {
        String jpql = "SELECT p FROM Department p WHERE p.id = :id";
        TypedQuery<Department> findQuery = entityManager.createQuery(jpql, Department.class);
        findQuery.setParameter("id", deptId);
        try {
            return findQuery.getSingleResult().getDepMGR();
        } catch (NoResultException e) {
            return null;
        }
    }
}
