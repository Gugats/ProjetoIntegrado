/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.business.bo;

import br.com.treg.business.model.Boleto;
import br.com.treg.integration.jpa.BoletoDAOImpl;

/**
 *
 * @author Gustavo
 */
public class BoletoBO extends GenericBO<Boleto>{

    
    
    public BoletoBO() {
        dao = new BoletoDAOImpl();
    }
    
}
