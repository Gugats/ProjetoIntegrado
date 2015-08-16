/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view;

import br.com.treg.business.model.Cliente;
import java.util.Collection;

/**
 *
 * @author Gustavo
 */
public interface CadClienteView {
    
    interface CadClienteViewListener{
        
        public void salvar(Cliente cliente);
        
        public void excluir(Cliente cliente);
        
    }
    
    void addListener(CadClienteViewListener listener);
    
    void sucesso(String msg);
    
    void falha(String msg);
    
    void populaListaClientes(Collection<Cliente> lista);
    
}
