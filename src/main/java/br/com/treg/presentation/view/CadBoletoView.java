/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view;

import br.com.treg.business.model.Boleto;
import br.com.treg.business.model.NotaFiscal;
import java.util.Collection;

/**
 *
 * @author Gustavo
 */
public interface CadBoletoView {
    
    interface CadBoletoViewListener{
        CadBoletoView getView();
        void salvar(Boleto boleto);
        void excluir(Boleto boleto);
        Collection<Boleto> atualizaListaBoletosPendentes();
        Collection<Boleto> atualizaListaBoletosPagos();
    }
    
    void addListener(CadBoletoViewListener listener);
    
    void falha(String msg);
    
    void sucesso(String msg);
    
    void limparForm();
    
    void populaComboNF(Collection<NotaFiscal> lista);
    
    void populaComboParcelas(Integer n);
    
    void populaListaBoletos(Collection<Boleto> lista);
    
    
    
}
