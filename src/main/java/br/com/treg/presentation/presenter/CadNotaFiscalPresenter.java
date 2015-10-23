/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.presenter;

import br.com.treg.business.bo.NotaFiscalBO;
import br.com.treg.business.model.NotaFiscal;
import br.com.treg.presentation.view.CadNotaFiscalView;
import br.com.treg.presentation.view.impl.CadNotaFiscalViewImpl;

/**
 *
 * @author Gustavo
 */
public class CadNotaFiscalPresenter implements CadNotaFiscalView.CadNotaFiscalViewListener{

    CadNotaFiscalView view = new CadNotaFiscalViewImpl();
    
    NotaFiscalBO bo;
    
    public CadNotaFiscalPresenter() {
        this.view.addListener(this);
        
        bo = new NotaFiscalBO();
    }

    @Override
    public CadNotaFiscalView getView() {
        return this.view;
    }

    @Override
    public void salvar(NotaFiscal nf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(NotaFiscal nf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
