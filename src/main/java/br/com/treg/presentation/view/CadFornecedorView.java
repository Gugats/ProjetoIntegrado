/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view;

import br.com.treg.business.model.Fornecedor;

/**
 *
 * @author Gustavo
 */
public interface CadFornecedorView {
    
    interface CadFornecedorViewListener{

        public void salvar(Fornecedor fornecedor);
        
    }
    
    void addListener(CadFornecedorViewListener listener);
    
    void sucesso(String msg);
    
}
