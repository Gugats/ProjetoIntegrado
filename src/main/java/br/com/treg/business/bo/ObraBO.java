/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.business.bo;

import br.com.treg.business.model.Obra;
import br.com.treg.integration.jpa.ObraDAOImpl;

/**
 *
 * @author Gustavo
 */
public class ObraBO extends GenericBO<Obra>{

    public ObraBO() {
        dao = new ObraDAOImpl();
    }
    
}
