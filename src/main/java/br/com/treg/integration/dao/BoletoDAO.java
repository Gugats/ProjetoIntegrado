/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.integration.dao;

import br.com.treg.business.model.Boleto;
import java.util.Collection;

/**
 *
 * @author Gustavo
 */
public interface BoletoDAO extends GenericDAO<Boleto>{

    public Collection<Boleto> listaPendentes();

    public Collection<Boleto> listaPagos();
    
}
