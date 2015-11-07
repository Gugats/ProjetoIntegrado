/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.business.bo;

import br.com.treg.business.model.Boleto;
import br.com.treg.integration.dao.BoletoDAO;
import br.com.treg.integration.jpa.BoletoDAOImpl;
import java.util.Collection;

/**
 *
 * @author Gustavo
 */
public class BoletoBO extends GenericBO<Boleto>{

    private BoletoDAO boletoDao = new BoletoDAOImpl();
    
    public BoletoBO() {
        dao = new BoletoDAOImpl();
    }

    public Collection<Boleto> listaPendentes() {
        return boletoDao.listaPendentes();
    }

    public Collection<Boleto> listaPagos() {
        return boletoDao.listaPagos();
    }
    
}
