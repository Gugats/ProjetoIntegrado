/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view;

import br.com.treg.business.model.Item;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

/**
 *
 * @author Gustavo
 */
public interface CadOrcamentoView {
    
    interface CadOrcamentoViewListener{
        
    }
    
    void addListener(CadOrcamentoViewListener listener);
    
    void ordenaItensTabela(ObservableList<TreeItem<Item>> lista);
    
}
