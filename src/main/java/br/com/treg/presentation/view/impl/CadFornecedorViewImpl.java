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
import javafx.scene.layout.GridPane;
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
    private GridPane formLayout;
    private VBox tabelaLayout;
    private Label lNome, lCnpj, lEndereco, lTelefone, lEmail;
    private TextField tfNome, tfCnpj, tfEndereco, tfTelefone, tfEmail;
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
        
        formLayout = new GridPane();
        formLayout.setVgap(7);
        formLayout.setHgap(5);
        
        HBox tituloLayout = new HBox();
        Text titulo = new Text("Cadastro de Fornecedor");
        titulo.setId("titulo");
        tituloLayout.getChildren().add(titulo);
        tituloLayout.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(tituloLayout);
        
        lNome = new Label("Razão Social: ");
        tfNome = new TextField();
        formLayout.add(lNome, 0, 1);
        formLayout.add(tfNome, 1, 1);
        
        lCnpj = new Label("CNPJ: ");
        tfCnpj = new TextField();
        formLayout.add(lCnpj, 0, 2);
        formLayout.add(tfCnpj, 1, 2);
        
        lEndereco = new Label("Endereço: ");
        tfEndereco = new TextField();
        formLayout.add(lEndereco, 0, 3);
        formLayout.add(tfEndereco, 1, 3);
        
        lTelefone = new Label("Telefone: ");
        tfTelefone = new TextField();
        formLayout.add(lTelefone, 0, 4);
        formLayout.add(tfTelefone, 1, 4);
        
        lEmail = new Label("E-mail: ");
        tfEmail = new TextField();
        formLayout.add(lEmail, 0, 5);
        formLayout.add(tfEmail, 1, 5);
        
        HBox botoesLayout = new HBox();
        botoesLayout.setSpacing(7);
        salvar = new Button("Salvar");
        cancelar = new Button("Cancelar");
        excluir = new Button("Excluir");
        excluir.setDisable(true);
        botoesLayout.getChildren().addAll(salvar, cancelar, excluir);
        botoesLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.add(botoesLayout, 1, 6);
        
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
        
        formLayout.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(formLayout);
        this.getChildren().add(tabelaLayout);
        
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
