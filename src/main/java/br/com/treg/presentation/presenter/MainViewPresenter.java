/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.presenter;

import br.com.treg.business.bo.BoletoBO;
import br.com.treg.presentation.view.MainView;
import br.com.treg.presentation.view.impl.MainViewImpl;

/**
 *
 * @author Gustavo
 */
public class MainViewPresenter implements MainView.MainViewListener{

    public MainView view;
    
    BoletoBO boletoBO;
    
    public MainViewPresenter() {
        view = new MainViewImpl();
        this.view.addListener(this);
        
        boletoBO = new BoletoBO();
        this.view.constroiBoletosPendentesLayout(boletoBO.listaPendentes());
        this.view.costroiBoletosPagosLayout(boletoBO.listaPagos());
    }

    @Override
    public MainView getView() {
        return this.view;
    }
    
}
