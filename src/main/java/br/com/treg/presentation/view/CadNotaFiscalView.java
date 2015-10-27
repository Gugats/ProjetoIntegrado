/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view;

import br.com.treg.business.model.Fornecedor;
import br.com.treg.business.model.NotaFiscal;
import br.com.treg.business.model.Obra;
import java.util.Collection;

/**
 *
 * @author Gustavo
 */
public interface CadNotaFiscalView {
    
    interface CadNotaFiscalViewListener{
        CadNotaFiscalView getView();
        public void salvar(NotaFiscal nf);
        public void excluir(NotaFiscal nf);
    }
    
    void addListener(CadNotaFiscalViewListener listener);
    
    void limparForm();
    
    void falha(String msg);
    
    void sucesso(String msg);
    
    void populaComboFornecedores(Collection<Fornecedor> lista);
    
    void populaComboObras(Collection<Obra> lista);
    
    void populaListaNF(Collection<NotaFiscal> lista);
    
}
