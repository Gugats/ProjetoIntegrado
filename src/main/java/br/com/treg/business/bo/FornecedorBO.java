/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.business.bo;

import br.com.treg.business.model.Fornecedor;
import br.com.treg.integration.jpa.FornecedorDAOImpl;

/**
 *
 * @author Gustavo
 */
public class FornecedorBO extends GenericBO<Fornecedor> {
    
    public FornecedorBO(){
        dao = new FornecedorDAOImpl();
    }
    
}
