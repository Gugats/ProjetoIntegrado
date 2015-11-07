/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.integration.jpa;

import br.com.treg.business.model.Boleto;
import br.com.treg.integration.dao.BoletoDAO;
import java.util.Collection;
import javax.persistence.Query;

/**
 *
 * @author Gustavo
 */
public class BoletoDAOImpl extends GenericDAOImpl<Boleto> implements BoletoDAO{

    @Override
    public Collection<Boleto> listaPendentes() {
        Query q = em.createQuery("FROM Boleto b WHERE b.pago = FALSE ORDER BY b.dataVencimento");
        q.setMaxResults(5);
        return (Collection<Boleto>) q.getResultList();
    }

    @Override
    public Collection<Boleto> listaPagos() {
        Query q = em.createQuery("FROM Boleto b WHERE b.pago = TRUE ORDER BY b.dataPagamento");
        q.setMaxResults(5);
        return (Collection<Boleto>) q.getResultList();
    }
    
}
