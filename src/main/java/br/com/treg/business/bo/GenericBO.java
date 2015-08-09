/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.business.bo;

import br.com.treg.integration.dao.GenericDAO;
import java.util.Set;

/**
 *
 * @author Gustavo
 */
public class GenericBO<T> {

    protected GenericDAO dao;

    @SuppressWarnings("unchecked")
    public T find(Integer id) {
        return (T) this.dao.find(id);
    }

    @SuppressWarnings("unchecked")
    public Set<T> listAll() {
        return this.dao.list();
    }

    @SuppressWarnings("unchecked")
    public void saveOrUpdate(T p) {
                //if(p.getId()==null) {
        //        this.dao.persist(p);
        //}else {
        this.dao.merge(p);
        //}
    }

    @SuppressWarnings("unchecked")
    public boolean delete(T p) {
        return this.dao.remove(p);
    }
}
