/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.integration.dao;

import java.util.Set;
import javafx.collections.ObservableList;

import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author Gustavo
 */
public interface GenericDAO<T> {
    
    /**
     * Método responsável pela persistência de uma instância da classe
     * persistente.
     *
     * @param t T
     * @return Boolean
     * @throws EntityExistsException
     * @throws IllegalArgumentException
     * @throws TransactionRequiredException
     * @throws PersistenceException
     */
    public abstract boolean persist(T t) throws EntityExistsException,
            IllegalArgumentException, TransactionRequiredException,
            PersistenceException;

    /**
     * Método responsável pela atualização de uma instância da classe
     * persistente.
     *
     * @param t T
     * @return Boolean
     * @throws IllegalArgumentException
     * @throws TransactionRequiredException
     */
    public abstract boolean merge(T t) throws IllegalArgumentException,
            TransactionRequiredException;

    /**
     * Método responsável pela remoção de uma instância da classe persistente.
     *
     * @param t T
     * @return Boolean
     * @throws IllegalArgumentException
     * @throws TransactionRequiredException
     */
    public abstract boolean remove(T t) throws IllegalArgumentException,
            TransactionRequiredException;

    /**
     * Método responsável pela busca de uma instância da classe persistente pela
     * chave-primária.
     *
     * @param pk Integer
     * @return T
     * @throws IllegalArgumentException
     */
    public abstract T find(int pk) throws IllegalArgumentException;

    /**
     * Método responsável pela busca de uma instância da classe persistente pela
     * chave-primária.
     *
     * @param pk String
     * @return T
     * @throws IllegalArgumentException
     */
    public abstract T find(String pk) throws IllegalArgumentException;

    /**
     * Método responsável pela listagem de instâncias da classe persistente.
     *
     * @return List<T>
     * @throws IllegalArgumentException
     */
    public abstract Set<T> list() throws IllegalArgumentException;
    
}
