/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view.impl;

import br.com.treg.business.model.Cliente;
import br.com.treg.business.model.Fornecedor;
import br.com.treg.presentation.view.CadFornecedorView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private VBox formLayout, tabelaLayout;
    private Label lNome, lCnpj, lEndereco, lTelefone, lEmail;
    private TextField tfNome, tfCnpj, tfEndereco, tfTelefone, tfEmail;
    private Text titulo;
    private Button salvar, cancelar, excluir;
    private Fornecedor fornecedor = new Fornecedor();
    private TableView<Fornecedor> tabela;
    private ObservableList<Fornecedor> listaFornecedores;
    
    @Override
    public void addListener(CadFornecedorViewListener listener) {
        listeners.add(listener);
    }
    
    public CadFornecedorViewImpl(){
        
        this.setSpacing(10);
        
        formLayout = new VBox();
        formLayout.setSpacing(10);
        
        titulo = new Text("Cadastro de Fornecedores");
        titulo.setId("titulo");
        
        HBox nomeLayout = new HBox();
        nomeLayout.setSpacing(7);
        lNome = new Label("Razão Social: ");
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
        
        HBox telefoneLayout = new HBox();
        telefoneLayout.setSpacing(7);
        lTelefone = new Label("Telefone: ");
        tfTelefone = new TextField();
        telefoneLayout.getChildren().addAll(lTelefone, tfTelefone);
        telefoneLayout.setAlignment(Pos.TOP_CENTER);
        
        HBox emailLayout = new HBox();
        emailLayout.setSpacing(7);
        lEmail = new Label("E-mail: ");
        tfEmail = new TextField();
        emailLayout.getChildren().addAll(lEmail, tfEmail);
        emailLayout.setAlignment(Pos.TOP_CENTER);
        
        HBox botoesLayout = new HBox();
        botoesLayout.setSpacing(7);
        salvar = new Button("Salvar");
        cancelar = new Button("Cancelar");
        excluir = new Button("Excluir");
        excluir.setDisable(true);
        botoesLayout.getChildren().addAll(salvar, cancelar, excluir);
        botoesLayout.setAlignment(Pos.TOP_CENTER);
        
        tabela = new TableView();
        tabela.setMaxWidth(600);
        
        TableColumn nome = new TableColumn("Razão Social");
        nome.setMinWidth(100);
        nome.setCellValueFactory(
                new PropertyValueFactory<Fornecedor, StringProperty>("nome")
        );
        
        TableColumn cnpj = new TableColumn("CNPJ");
        cnpj.setMinWidth(100);
        cnpj.setCellValueFactory(
                new PropertyValueFactory<Fornecedor, StringProperty>("cnpj")
        );
        
        TableColumn endereco = new TableColumn("Endereço");
        endereco.setMinWidth(200);
        endereco.setCellValueFactory(
                new PropertyValueFactory<Fornecedor, StringProperty>("endereco")
        );
        
        TableColumn fone = new TableColumn("Telefone");
        fone.setMinWidth(100);
        fone.setCellValueFactory(
                new PropertyValueFactory<Fornecedor, StringProperty>("telefone")
        );
        
        TableColumn email = new TableColumn("Email");
        email.setMinWidth(100);
        email.setCellValueFactory(
                new PropertyValueFactory<Fornecedor, StringProperty>("email")
        );
        
        tabela.getColumns().addAll(nome, cnpj, endereco, fone, email);
        
        tabelaLayout = new VBox();
        tabelaLayout.setSpacing(10);
        tabelaLayout.getChildren().add(tabela);
        tabelaLayout.setAlignment(Pos.TOP_CENTER);
        
//        tabela.getFocusModel().focusedItemProperty().addListener(new ChangeListener<Fornecedor>() {
//            @Override
//            public void changed(ObservableValue<? extends Fornecedor> observable, Fornecedor oldValue, Fornecedor newValue) {
//                if (oldValue != null) {
//                    tfNome.textProperty().unbindBidirectional(oldValue.nomeProperty());
//                    tfCnpj.textProperty().unbindBidirectional(oldValue.cnpjProperty());
//                    tfEndereco.textProperty().unbindBidirectional(oldValue.enderecoProperty());
//                }
//
//                if (newValue != null) {
//                    tfNome.textProperty().bindBidirectional(newValue.nomeProperty());
//                    tfCnpj.textProperty().bindBidirectional(newValue.cnpjProperty());
//                    tfEndereco.textProperty().bindBidirectional(newValue.enderecoProperty());
//                }
//            }
//        });
        
        tabela.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                fornecedor = tabela.getSelectionModel().getSelectedItem();
                tfNome.setText(fornecedor.getNome());
                tfCnpj.setText(fornecedor.getCnpj());
                tfEndereco.setText(fornecedor.getEndereco());
                tfTelefone.setText(fornecedor.getTelefone());
                tfEmail.setText(fornecedor.getEmail());
                excluir.setDisable(false);
            }
        });
        
        salvar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                fornecedor.setNome(tfNome.getText());
                fornecedor.setCnpj(tfCnpj.getText());
                fornecedor.setEndereco(tfEndereco.getText());
                fornecedor.setEmail(tfEmail.getText());
                fornecedor.setTelefone(tfTelefone.getText());
                
                for(CadFornecedorViewListener l : listeners) {
                    l.salvar(fornecedor);
                }
                tabela.getItems().removeAll();
                excluir.setDisable(true);
            }
        });
        
        cancelar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                tabela.getSelectionModel().select(null);
                tfNome.setText("");
                tfCnpj.setText("");
                tfEndereco.setText("");
                tfTelefone.setText("");
                tfEmail.setText("");
                excluir.setDisable(true);
                fornecedor = new Fornecedor();
            }
        });
        
        excluir.setOnAction(new EventHandler<ActionEvent>() {            
            
            @Override
            public void handle(ActionEvent event) {
                for(CadFornecedorViewListener l : listeners)
                    l.excluir(fornecedor);
                
                tfNome.setText("");
                tfCnpj.setText("");
                tfTelefone.setText("");
                tfEmail.setText("");
                tfEndereco.setText("");
                fornecedor = new Fornecedor();
                excluir.setDisable(true);
                tabela.getSelectionModel().select(null);
            }
        });
        
        formLayout.getChildren().addAll(titulo, nomeLayout, cnpjLayout, enderecoLayout,emailLayout, telefoneLayout, botoesLayout, tabelaLayout);
        formLayout.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(formLayout);
        
    }

    @Override
    public void sucesso(String msg) {
        Notifications.create().title("Sucesso").position(Pos.CENTER).text(msg).showInformation();
    }

    @Override
    public void populaListaFornecedores(Collection<Fornecedor> lista) {
        listaFornecedores = FXCollections.observableArrayList(lista);
        tabela.setItems(listaFornecedores);
    }

    @Override
    public void falha(String msg) {
        Notifications.create().title("Falha").position(Pos.CENTER).text(msg).showError();
    }
}
