/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view.impl;

import br.com.treg.business.model.Fornecedor;
import br.com.treg.presentation.view.CadFornecedorView;
import com.sun.javafx.scene.text.TextLayout;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Gustavo
 */
public class CadFornecedorViewImpl extends VBox implements CadFornecedorView{
    
    List<CadFornecedorViewListener> listeners = new ArrayList<CadFornecedorView.CadFornecedorViewListener>();
    
    //Elementos do form
    private VBox formLayout;
    private Label lNome, lCnpj, lEndereco;
    private TextField tfNome, tfCnpj, tfEndereco;
    private Text titulo;
    private Button salvar, cancelar, excluir;
    private Fornecedor fornecedor = new Fornecedor();
    
    @Override
    public void addListener(CadFornecedorViewListener listener) {
        listeners.add(listener);
    }
    
    public CadFornecedorViewImpl(){
        
        this.setSpacing(10);
        
        formLayout = new VBox();
        formLayout.setSpacing(10);
        
        titulo = new Text("Cadastro de Fornecedores");
        HBox nomeLayout = new HBox();
        nomeLayout.setSpacing(7);
        lNome = new Label("Nome: ");
        tfNome = new TextField();
        nomeLayout.getChildren().addAll(lNome, tfNome);
        nomeLayout.setAlignment(Pos.TOP_CENTER);
        
        HBox cnpjLayout = new HBox();
        cnpjLayout.setSpacing(7);
        lCnpj = new Label("CNPJ: ");
        tfCnpj = new TextField();
        cnpjLayout.getChildren().addAll(lCnpj, tfCnpj);
        cnpjLayout.setAlignment(Pos.TOP_CENTER);
        
        HBox enderecoLayout = new HBox();
        enderecoLayout.setSpacing(7);
        lEndereco = new Label("Endereço: ");
        tfEndereco = new TextField();
        enderecoLayout.getChildren().addAll(lEndereco, tfEndereco);
        enderecoLayout.setAlignment(Pos.TOP_CENTER);
        
        HBox botoesLayout = new HBox();
        botoesLayout.setSpacing(7);
        salvar = new Button("Salvar");
        cancelar = new Button("Cancelar");
        excluir = new Button("Excluir");
        botoesLayout.getChildren().addAll(salvar, cancelar, excluir);
        
        salvar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                fornecedor.setNome(tfNome.getText());
                fornecedor.setCnpj(tfCnpj.getText());
                fornecedor.setEndereco(tfEndereco.getText());
                
                for (CadFornecedorViewListener l : listeners) {
                    l.salvar(fornecedor);
                }
            }
        });
        
        formLayout.getChildren().addAll(titulo, nomeLayout, cnpjLayout, enderecoLayout, botoesLayout);
        formLayout.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(formLayout);
        
    }

    @Override
    public void sucesso(String msg) {
        Notifications.create().title("Sucesso").position(Pos.CENTER).text(msg).showInformation();
    }
}
