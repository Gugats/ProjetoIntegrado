/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.business.bo;

import br.com.treg.business.model.Recibo;
import br.com.treg.integration.jpa.ReciboDAOImpl;

/**
 *
 * @author Gustavo
 */
public class ReciboBO extends GenericBO<Recibo>{

    public ReciboBO() {
        dao = new ReciboDAOImpl();
    }
    
    
    
}
