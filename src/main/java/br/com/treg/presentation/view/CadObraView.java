/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view;

import br.com.treg.business.model.Cliente;
import br.com.treg.business.model.Funcionario;
import br.com.treg.business.model.Obra;
import java.util.Collection;

/**
 *
 * @author Gustavo
 */
public interface CadObraView {
    
    interface CadObraViewListener{
        CadObraView getView();
         public void salvar(Obra obra);
         public void excluir(Obra obra);
         public void getListaFuncionarios();
    }
    
    void addListener(CadObraViewListener listener);
    
    void falha(String msg);
    
    void sucesso(String msg);
    
    void limparForm();
    
    void populaListaObras(Collection<Obra> lista);
    
    void populaComboClientes(Collection<Cliente> lista);
    
    void populaListaFuncionarios(Collection<Funcionario> lista);
    
}
