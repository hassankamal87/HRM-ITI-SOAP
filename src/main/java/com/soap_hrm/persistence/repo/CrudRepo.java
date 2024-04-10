package com.soap_hrm.persistence.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public abstract class CrudRepo<T,ID> {
    protected EntityManager entityManager;

    protected CrudRepo(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public T save(T entity){
        System.out.println("saving..");
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        entityManager.refresh(entity);
        return entity;
    }

    public List<T> findAll(Class<T> objClass){
        String jpql = "SELECT p FROM "+ objClass.getSimpleName()+ " p";
        TypedQuery<T> findQuery = entityManager.createQuery(jpql, objClass);
        return findQuery.getResultList();
    }

    public T findById(Class<T> objClass, ID id){
        return entityManager.find(objClass,id);
    }

    public T update(T entity){
        System.out.println("updating..");
        entityManager.getTransaction().begin();
        T updatedEntity = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return updatedEntity;
    }

    public void delete(T entity){
        entityManager.getTransaction().begin();
        if (!entityManager.contains(entity)) {
            entity = entityManager.merge(entity);
        }

        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    public T deleteById(Class<T> objClass, ID id) throws RuntimeException{
        try {

            entityManager.getTransaction().begin();

            T entity = entityManager.find(objClass, id);
            if (entity != null) {
                entityManager.remove(entity);
            }

            entityManager.getTransaction().commit();
            return entity;
        }catch (Exception e){
            throw new RuntimeException("cannot delete this item");
        }
    }
}
