/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view;

import br.com.treg.business.model.Obra;
import br.com.treg.business.model.ObraFuncionario;
import br.com.treg.business.model.Recibo;
import java.util.Collection;

/**
 *
 * @author Gustavo
 */
public interface CadReciboView {
    
    interface CadReciboViewListener{
        CadReciboView getView();
        void salvar(Recibo recibo);
        void excluir(Recibo recibo);
    }
    void addListener(CadReciboViewListener listener);
    
    void falha(String msg);
    
    void sucesso(String msg);
    
    void limparForm();
    
    void populaComboObra(Collection<Obra> lista);
    
    void populaComboFuncionarios(Collection<ObraFuncionario> lista);
    
    void concatenaString();
    
}
