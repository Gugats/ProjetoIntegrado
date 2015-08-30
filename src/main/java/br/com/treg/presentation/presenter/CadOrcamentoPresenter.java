/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.presenter;

import br.com.treg.presentation.view.CadOrcamentoView;
import br.com.treg.presentation.view.impl.CadOrcamentoViewImpl;

/**
 *
 * @author Gustavo
 */
public class CadOrcamentoPresenter implements CadOrcamentoView.CadOrcamentoViewListener{

    CadOrcamentoView view = new CadOrcamentoViewImpl();
    
    public CadOrcamentoPresenter() {
        this.view.addListener(this);
    }
    
    public CadOrcamentoView getView(){
        return this.view;
    }
    
}
