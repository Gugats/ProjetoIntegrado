/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.integration.jpa;

import br.com.treg.integration.dao.GenericDAO;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author Gustavo
 */
public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    private Class<T> persistentClass;
    private EntityManager em;

    /**
     * Método responsável pela instanciação e extração da classe persistente.
     */
    @SuppressWarnings("unchecked")
    public GenericDAOImpl() {
        init();
    }

    private void init() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.em = Persistence.createEntityManagerFactory("TReGPU").createEntityManager();
    }

    @Override
    public boolean persist(T t) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException, PersistenceException {

        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            return true;
        } catch (EntityExistsException e) {
            em.getTransaction().rollback();
            throw e;
        } catch (IllegalArgumentException e) {
            em.getTransaction().rollback();
            throw e;
        } catch (TransactionRequiredException e) {
            em.getTransaction().rollback();
            throw e;
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            //em.close();
        }
    }

    @Override
    public boolean merge(T t) throws IllegalArgumentException, TransactionRequiredException {
        try {
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
            return true;
        } catch (IllegalArgumentException e) {
            em.getTransaction().rollback();
            throw e;
        } catch (TransactionRequiredException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            //em.close();
        }
    }

    @Override
    public boolean remove(T t) throws IllegalArgumentException, TransactionRequiredException {
        try {
            em.getTransaction().begin();
            em.remove(em.contains(t) ? t : em.merge(t));
            em.getTransaction().commit();
            return true;
        } catch (IllegalArgumentException e) {
            em.getTransaction().rollback();
            throw e;
        } catch (TransactionRequiredException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            //em.close();
        }
    }

    @Override
    public T find(int pk) throws IllegalArgumentException {
        try {
            return em.find(this.persistentClass, pk);
        } finally {
            //em.close();
        }
    }

    @Override
    public T find(String pk) throws IllegalArgumentException {
        try {
            return em.find(this.persistentClass, pk);
        } finally {
            //em.close();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<T> list() throws IllegalArgumentException {
        try {
            return new HashSet<T>(em.createQuery("from " + this.persistentClass.getSimpleName()).getResultList());
        } finally {
            //em.close();
        }
    }
}
