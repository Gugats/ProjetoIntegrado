/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view;

import br.com.treg.business.model.Boleto;
import java.util.Collection;

/**
 *
 * @author Gustavo
 */
public interface MainView {
    
    interface MainViewListener{
        MainView getView();
    }
    
    void addListener(MainViewListener listener);
    
    void costroiBoletosPagosLayout(Collection<Boleto> lista);
    
    void constroiBoletosPendentesLayout(Collection<Boleto> lista);
    
}
