/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.business.bo;

import br.com.treg.business.model.Cliente;
import br.com.treg.integration.jpa.ClienteDAOImpl;

/**
 *
 * @author Gustavo
 */
public class ClienteBO extends GenericBO<Cliente>{

    public ClienteBO() {
        dao = new ClienteDAOImpl();
    }
}
