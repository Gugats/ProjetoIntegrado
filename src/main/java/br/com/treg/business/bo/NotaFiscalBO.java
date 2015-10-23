/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.business.bo;

import br.com.treg.business.model.NotaFiscal;
import br.com.treg.integration.jpa.NotaFiscalDAOImpl;

/**
 *
 * @author Gustavo
 */
public class NotaFiscalBO extends GenericBO<NotaFiscal>{

    public NotaFiscalBO() {
        dao = new NotaFiscalDAOImpl();
    }
    
    
    
}
