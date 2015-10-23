/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view.impl;

import br.com.treg.presentation.view.CadNotaFiscalView;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Gustavo
 */
public class CadNotaFiscalViewImpl extends VBox implements CadNotaFiscalView{

    List<CadNotaFiscalViewListener> listeners = new ArrayList<CadNotaFiscalViewListener>();
    
    public CadNotaFiscalViewImpl() {
        this.setSpacing(10);
    }
    
    @Override
    public void addListener(CadNotaFiscalViewListener listener) {
        listeners.add(listener);
    }

    @Override
    public void limparForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void falha(String msg) {
        Notifications.create().title("Falha").position(Pos.CENTER).text(msg).showError();
    }

    @Override
    public void sucesso(String msg) {
        Notifications.create().title("Sucesso").position(Pos.CENTER).text(msg).showInformation();
    }
    
}
