/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view.impl;

import br.com.treg.business.model.Cliente;
import br.com.treg.business.model.Funcionario;
import br.com.treg.business.model.Obra;
import br.com.treg.business.model.ObraFuncionario;
import br.com.treg.presentation.view.CadObraView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.ListSelectionView;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Gustavo
 */
public class CadObraViewImpl extends VBox implements CadObraView{
    
    List<CadObraViewListener> listeners = new ArrayList<CadObraViewListener>();
    
    private GridPane formLayout;
    private VBox tabelaLayout;
    private Text titulo;
    private ComboBox<Cliente> comboCliente;
    private ComboBox<String> comboStatus;
    private Label lCliente, lEndereco, lStatus;
    private TextField tfEndereco;
    private CheckBox checkAtivo;
    private Button cancelar, excluir, salvar;
    private TableView<Obra> tabela;
    private Obra obra = new Obra();
    private ObservableList<Obra> listaObras;
    private ObservableList<Cliente> listaClientes;
    private ObservableList<Funcionario> listaFuncionarios;
    private ListSelectionView<Funcionario> twinCol;
    private TextArea descricao;
    
    public CadObraViewImpl() {
        
        this.setSpacing(10);
        
        HBox tituloLayout = new HBox();
        titulo = new Text("Cadastro de Obra");
        titulo.setId("titulo");
        tituloLayout.getChildren().add(titulo);
        tituloLayout.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(tituloLayout);
        
        formLayout = new GridPane();
        formLayout.setVgap(7);
        formLayout.setHgap(5);
        
        lCliente = new Label("Cliente: ");
        comboCliente = new ComboBox<>();
        formLayout.add(lCliente, 0, 1);
        formLayout.add(comboCliente, 1, 1);
        
        lEndereco = new Label("Local: ");
        tfEndereco = new TextField();
        formLayout.add(lEndereco, 0, 2);
        formLayout.add(tfEndereco, 1, 2);
        
        Label lDesc = new Label("Descrição: ");
        descricao = new TextArea();
        descricao.setMinWidth(250);
        descricao.setMinHeight(100);
        formLayout.add(lDesc, 0, 3);
        formLayout.add(descricao, 1, 3);
        
        lStatus = new Label("Status: ");
        comboStatus = new ComboBox<>();
        comboStatus.getItems().add("Não iniciada");
        comboStatus.getItems().add("Em andamento");
        comboStatus.getItems().add("Finalizada");
        formLayout.add(lStatus, 0, 4);
        formLayout.add(comboStatus, 1, 4);
        
        checkAtivo = new CheckBox("Ativo");
        checkAtivo.setSelected(true);
        formLayout.add(checkAtivo, 0, 5);
        
        Label lFun = new Label("Funcionários: ");
        twinCol = new ListSelectionView<>();
        twinCol.setMaxWidth(420);
        formLayout.add(lFun, 0, 6);
        formLayout.add(twinCol, 1, 6);
        
        HBox botoesLayout = new HBox();
        botoesLayout.setSpacing(7);
        salvar = new Button("Salvar");
        cancelar = new Button("Cancelar");
        excluir = new Button("Excluir");
        excluir.setDisable(true);
        botoesLayout.getChildren().addAll(salvar, cancelar, excluir);
        botoesLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.add(botoesLayout, 1, 7);
        
        formLayout.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(formLayout);
        
        tabela = new TableView<>();
        tabela.setMaxWidth(400);
        
        TableColumn cliente = new TableColumn("Cliente");
        cliente.setMinWidth(100);
        cliente.setCellValueFactory(
                new PropertyValueFactory<Funcionario, StringProperty>("cliente")
        );
        
        TableColumn endereco = new TableColumn("Endereço");
        endereco.setMinWidth(200);
        endereco.setCellValueFactory(
                new PropertyValueFactory<Funcionario, StringProperty>("endereco")
        );
        
        TableColumn status = new TableColumn("Status");
        status.setMinWidth(100);
        status.setCellValueFactory(
                new PropertyValueFactory<Funcionario, BooleanProperty>("status")
        );
        
        tabela.getColumns().addAll(cliente, endereco, status);
        
        tabelaLayout = new VBox();
        tabelaLayout.setSpacing(10);
        tabelaLayout.getChildren().add(tabela);
        tabelaLayout.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(tabelaLayout);
        
        tabela.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                obra = tabela.getSelectionModel().getSelectedItem();
                if(obra != null){
                    for(CadObraViewListener l : listeners)
                        l.getListaFuncionarios();
                    tfEndereco.setText(obra.getEndereco());
                    comboCliente.setValue(obra.getCliente());
                    comboStatus.setValue(obra.getStatus());
                    checkAtivo.setSelected(obra.getAtivo());
                    descricao.setText(obra.getDescricao());
                    excluir.setDisable(false);
                    List<Funcionario> listaF = new ArrayList<>();
                    
                    if(obra.getObrasFuncionarios().size()!=0){
                    
                        for(ObraFuncionario of : obra.getObrasFuncionarios()){
                            listaF.add(of.getFuncionario());
                        }
                        for(Funcionario f : listaF){
                            twinCol.getTargetItems().add(f);
                            twinCol.getSourceItems().remove(f);
                        }
                    
                    }
                }
            }
        });
        
        salvar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                obra.setAtivo(checkAtivo.isSelected());
                obra.setEndereco(tfEndereco.getText());
                obra.setCliente(comboCliente.getValue());
                obra.setStatus(comboStatus.getValue());
                obra.setDescricao(descricao.getText());
                
                for(Funcionario f : twinCol.getTargetItems()){
                    ObraFuncionario of = new ObraFuncionario();
                    of.setFuncionario(f);
                    of.setObra(obra);
                    obra.addObraFuncionario(of);
                }
                
                for(CadObraViewListener l : listeners){
                    l.salvar(obra);
                }
                
                limparForm();
            }
        });
        
        cancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                limparForm();
            }
        });
        
        excluir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacao.setTitle("Confirmação");
                confirmacao.setHeaderText("Confirmação de exclusão");
                confirmacao.setContentText("Realmente deseja excluir a obra selecionada?");
                
                ButtonType sim = new ButtonType("OK");
                ButtonType nao = new ButtonType("Cancelar");
                confirmacao.getButtonTypes().setAll(sim, nao);
                
                Optional<ButtonType> resultado = confirmacao.showAndWait();
                if(resultado.get() == sim){
                    for(CadObraView.CadObraViewListener l : listeners)
                        l.excluir(obra);
                
                    limparForm();
                }
            }
        });
    }
    
    @Override
    public void addListener(CadObraViewListener listener) {
        listeners.add(listener);
    }

    @Override
    public void falha(String msg) {
        Notifications.create().title("Falha").position(Pos.CENTER).text(msg).showError();
    }

    @Override
    public void sucesso(String msg) {
        Notifications.create().title("Sucesso").position(Pos.CENTER).text(msg).showInformation();
    }

    @Override
    public void limparForm() {
        tfEndereco.setText("");
        comboCliente.setValue(null);
        comboStatus.setValue(null);
        checkAtivo.setSelected(true);
        tabela.getSelectionModel().select(null);
        excluir.setDisable(true);
        descricao.setText("");
        twinCol.getTargetItems().clear();
        for(CadObraViewListener l : listeners)
            l.getListaFuncionarios();
        obra = new Obra();
    }

    @Override
    public void populaListaObras(Collection<Obra> lista) {
        listaObras = FXCollections.observableArrayList(lista);
        tabela.setItems(listaObras);
    }

    @Override
    public void populaComboClientes(Collection<Cliente> lista) {
        listaClientes = FXCollections.observableArrayList(lista);
        comboCliente.setItems(listaClientes);
    }

    @Override
    public void populaListaFuncionarios(Collection<Funcionario> lista) {
        listaFuncionarios = FXCollections.observableArrayList(lista);
        twinCol.getSourceItems().clear();
        twinCol.getSourceItems().addAll(listaFuncionarios);
    }
    
}
