/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view.impl;

import br.com.treg.business.model.Cliente;
import br.com.treg.presentation.view.CadClienteView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
public class CadClienteViewImpl extends VBox implements CadClienteView {

    List<CadClienteViewListener> listeners = new ArrayList<CadClienteViewListener>();
    
    //Elementos do Form
    private VBox formLayout, tabelaLayout;
    private Text titulo;
    private Label lNome, lNumDoc, lEndereco, lTipoCliente;
    private ComboBox<String> comboTipoCliente;
    private Button salvar, cancelar, excluir;
    private TextField tfNome, tfNumDoc, tfEndereco;
    private Cliente cliente = new Cliente();
    private TableView<Cliente> tabela;
    private ObservableList<Cliente> listaClientes;
    
    @Override
    public void addListener(CadClienteViewListener listener) {
        listeners.add(listener);
    }
    
    public CadClienteViewImpl(){
        this.setSpacing(10);
        
        formLayout = new VBox();
        formLayout.setSpacing(10);
        
        titulo = new Text("Cadastro de Clientes");
        
        HBox nomeLayout = new HBox();
        nomeLayout.setSpacing(7);
        lNome = new Label("Nome: ");
        tfNome = new TextField();
        nomeLayout.getChildren().addAll(lNome, tfNome);
        nomeLayout.setAlignment(Pos.TOP_CENTER);
        
        HBox enderecoLayout = new HBox();
        enderecoLayout.setSpacing(7);
        lEndereco = new Label("Endereço: ");
        tfEndereco = new TextField();
        enderecoLayout.getChildren().addAll(lEndereco, tfEndereco);
        enderecoLayout.setAlignment(Pos.TOP_CENTER);
        
        HBox tipoClienteLayout = new HBox();
        tipoClienteLayout.setSpacing(7);
        lTipoCliente = new Label("Tipo de Cliente: ");
        comboTipoCliente = new ComboBox();
        comboTipoCliente.getItems().add("Pessoa Física");
        comboTipoCliente.getItems().add("Pessoa Jurídica");
        tipoClienteLayout.getChildren().addAll(lTipoCliente, comboTipoCliente);
        tipoClienteLayout.setAlignment(Pos.TOP_CENTER);
        
        HBox numDocLayout = new HBox();
        numDocLayout.setSpacing(7);
        lNumDoc = new Label();
        tfNumDoc = new TextField();
        numDocLayout.getChildren().addAll(lNumDoc, tfNumDoc);
        numDocLayout.setAlignment(Pos.TOP_CENTER);
        numDocLayout.setVisible(false);
        
        comboTipoCliente.valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(comboTipoCliente.getValue().equals("Pessoa Física")){
                    lNumDoc.setText("CPF: ");
                    numDocLayout.setVisible(true);
                }else if(comboTipoCliente.getValue().equals("Pessoa Jurídica")){
                    lNumDoc.setText("CNPJ: ");
                    numDocLayout.setVisible(true);
                }else{
                    numDocLayout.setVisible(false);
                }
            }
        });
        
        HBox botoesLayout = new HBox();
        botoesLayout.setSpacing(7);
        salvar = new Button("Salvar");
        cancelar = new Button("Cancelar");
        excluir = new Button("Excluir");
        excluir.setDisable(true);
        botoesLayout.getChildren().addAll(salvar, cancelar, excluir);
        botoesLayout.setAlignment(Pos.TOP_CENTER);
        
        tabela = new TableView();
        tabela.setMaxWidth(500);
        
        TableColumn nome = new TableColumn("Nome");
        nome.setMinWidth(100);
        nome.setCellValueFactory(
                new PropertyValueFactory<Cliente, StringProperty>("nome")
        );
        
        TableColumn numCli = new TableColumn("CPF/CNPJ");
        numCli.setMinWidth(100);
        numCli.setCellValueFactory(
                new PropertyValueFactory<Cliente, StringProperty>("numDoc")
        );
        
        TableColumn tipoCli = new TableColumn("Tipo Cliente");
        tipoCli.setMinWidth(100);
        tipoCli.setCellValueFactory(
                new PropertyValueFactory<Cliente, StringProperty>("tipoCliente")
        );
        
        TableColumn endereco = new TableColumn("Endereço");
        endereco.setMinWidth(200);
        endereco.setCellValueFactory(
                new PropertyValueFactory<Cliente, StringProperty>("endereco")
        );
        
        tabela.getColumns().addAll(nome, endereco, tipoCli, numCli);
        
        tabelaLayout = new VBox();
        tabelaLayout.setSpacing(10);
        tabelaLayout.getChildren().add(tabela);
        tabelaLayout.setAlignment(Pos.TOP_CENTER);
        
        tabela.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cliente = tabela.getSelectionModel().getSelectedItem();
                tfNome.setText(cliente.getNome());
                tfNumDoc.setText(cliente.getNumdoc());
                tfEndereco.setText(cliente.getEndereco());
                comboTipoCliente.setValue(cliente.getTipoCliente());
                excluir.setDisable(false);
            }
        });
        
        salvar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                cliente.setNome(tfNome.getText());
                cliente.setTipoCliente(comboTipoCliente.getValue());
                cliente.setNumdoc(tfNumDoc.getText());
                cliente.setEndereco(tfEndereco.getText());
                
                for(CadClienteView.CadClienteViewListener l : listeners) {
                    l.salvar(cliente);
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
                tfNumDoc.setText("");
                tfEndereco.setText("");
                comboTipoCliente.setValue(null);
                excluir.setDisable(true);
                cliente = new Cliente();
            }
        });
        
        excluir.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
              
                Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacao.setTitle("Confirmação");
                confirmacao.setHeaderText("Confirmação de exclusão");
                confirmacao.setContentText("Realmente deseja excluir o cliente "+cliente+"?");
                
                ButtonType sim = new ButtonType("OK");
                ButtonType nao = new ButtonType("Cancelar");
                confirmacao.getButtonTypes().setAll(sim, nao);
                
                Optional<ButtonType> resultado = confirmacao.showAndWait();
                if(resultado.get() == sim){
                    for(CadClienteView.CadClienteViewListener l : listeners)
                        l.excluir(cliente);
                
                    tabela.getSelectionModel().select(null);
                    tfNome.setText("");
                    tfNumDoc.setText("");
                    tfEndereco.setText("");
                    comboTipoCliente.setValue(null);
                    excluir.setDisable(true);
                    cliente = new Cliente();
                }else if(resultado.get() == nao){
                    
                }
                
            }
        });
        
        formLayout.getChildren().addAll(titulo, nomeLayout, enderecoLayout, tipoClienteLayout, numDocLayout, botoesLayout, tabelaLayout);
        formLayout.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(formLayout);
    }

    @Override
    public void sucesso(String msg) {
        Notifications.create().title("Sucesso").position(Pos.CENTER).text(msg).showInformation();
    }

    @Override
    public void populaListaClientes(Collection<Cliente> lista) {
        listaClientes = FXCollections.observableArrayList(lista);
        tabela.setItems(listaClientes);
    }

    @Override
    public void falha(String msg) {
        Notifications.create().title("Falha").position(Pos.CENTER).text(msg).showError();
    }
    
}
