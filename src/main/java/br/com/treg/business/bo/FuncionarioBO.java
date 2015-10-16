/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.business.bo;

import br.com.treg.business.model.Funcionario;
import br.com.treg.integration.jpa.FuncionarioDAOImpl;

/**
 *
 * @author Gustavo
 */
public class FuncionarioBO extends GenericBO<Funcionario>{

    public FuncionarioBO() {
        dao = new FuncionarioDAOImpl();
    }
    
}
