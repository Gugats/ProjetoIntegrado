/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view;

import br.com.treg.business.model.Funcionario;
import java.util.Collection;

/**
 *
 * @author Gustavo
 */
public interface CadFuncionarioView {
    
    interface CadFuncionarioViewListener{
        CadFuncionarioView getView();

        public void salvar(Funcionario funcionario);

        public void excluir(Funcionario funcionario);
    }
    
    void addListener(CadFuncionarioViewListener listener);
    
    void limparForm();
    
    void falha(String msg);
    
    void sucesso(String msg);
    
    void populaListaFuncionarios(Collection<Funcionario> lista);
    
}
