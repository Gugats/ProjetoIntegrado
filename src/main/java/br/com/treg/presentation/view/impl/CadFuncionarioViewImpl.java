/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view.impl;

import br.com.treg.business.model.Fornecedor;
import br.com.treg.business.model.Funcionario;
import br.com.treg.presentation.view.CadFuncionarioView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Gustavo
 */
public class CadFuncionarioViewImpl extends VBox implements CadFuncionarioView{

    List<CadFuncionarioViewListener> listeners = new ArrayList<CadFuncionarioViewListener>();
    
    //Elementos do form
    private GridPane formLayout;
    private VBox tabelaLayout;
    private Label lNome, lEndereco, lCPF, lFuncao, lTelefone, lDiaria;
    private TextField tfNome, tfEndereco, tfCPF, tfFuncao, tfTelefone, tfDiaria;
    private CheckBox checkAtivo;
    private Button cancelar, excluir, salvar;
    private TableView<Funcionario> tabela;
    private Funcionario funcionario = new Funcionario();
    private ObservableList<Funcionario> listaFuncionarios;
    
    public CadFuncionarioViewImpl() {
        
        this.setSpacing(10);
        
        formLayout = new GridPane();
        formLayout.setVgap(7);
        formLayout.setHgap(5);
        
        HBox tituloLayout = new HBox();
        Text titulo = new Text("Cadastro de Funcionário");
        titulo.setId("titulo");
        tituloLayout.getChildren().add(titulo);
        tituloLayout.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(tituloLayout);

        lNome = new Label("Nome: ");
        tfNome = new TextField();
        formLayout.add(lNome, 0, 1);
        formLayout.add(tfNome, 1, 1);
        
        lEndereco = new Label("Endereço: ");
        tfEndereco = new TextField();
        formLayout.add(lEndereco, 0, 2);
        formLayout.add(tfEndereco, 1, 2);

        lTelefone = new Label("Telefone: ");
        tfTelefone = new TextField();
        formLayout.add(lTelefone, 0, 3);
        formLayout.add(tfTelefone, 1, 3);
        
        lCPF = new Label("CPF: ");
        tfCPF = new TextField();
        formLayout.add(lCPF, 0, 4);
        formLayout.add(tfCPF, 1, 4);
        
        lFuncao = new Label("Função: ");
        tfFuncao = new TextField();
        formLayout.add(lFuncao, 0, 5);
        formLayout.add(tfFuncao, 1, 5);
        
        lDiaria = new Label("Valor da Diária: ");
        tfDiaria = new TextField();
        formLayout.add(lDiaria, 0, 6);
        formLayout.add(tfDiaria, 1, 6);

        checkAtivo = new CheckBox("Ativo");
        checkAtivo.setSelected(true);
        formLayout.add(checkAtivo, 0, 7);
        
        HBox botoesLayout = new HBox();
        botoesLayout.setSpacing(7);
        salvar = new Button("Salvar");
        cancelar = new Button("Cancelar");
        excluir = new Button("Excluir");
        excluir.setDisable(true);
        botoesLayout.getChildren().addAll(salvar, cancelar, excluir);
        botoesLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.add(botoesLayout, 1, 8);
        
        formLayout.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(formLayout);
        
        tabela = new TableView<>();
        tabela.setMaxWidth(400);
        
        TableColumn nome = new TableColumn("Nome");
        nome.setMinWidth(100);
        nome.setCellValueFactory(
                new PropertyValueFactory<Funcionario, StringProperty>("nome")
        );
        
        TableColumn fone = new TableColumn("Telefone");
        fone.setMinWidth(100);
        fone.setCellValueFactory(
                new PropertyValueFactory<Funcionario, StringProperty>("telefone")
        );
        
        TableColumn funcao = new TableColumn("Função");
        funcao.setMinWidth(100);
        funcao.setCellValueFactory(
                new PropertyValueFactory<Funcionario, StringProperty>("funcao")
        );
        
        TableColumn diaria = new TableColumn("Diaria");
        diaria.setMinWidth(50);
        diaria.setCellValueFactory(
                new PropertyValueFactory<Funcionario, DoubleProperty>("diaria")
        );
        
        TableColumn ativo = new TableColumn("Ativo");
        ativo.setMinWidth(50);
        ativo.setCellValueFactory(new PropertyValueFactory<Funcionario, BooleanProperty>("ativo"));
        
        ativo.setCellFactory(column -> {
            return new TableCell<Funcionario, Boolean>() {
                @Override
                protected void updateItem(Boolean item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    }else{
                        if(item.booleanValue() == true){
                            setText("Sim");
                        }else{
                            setText("Não");
                        }
                    }
                }
            };
        
        });
        
        
        tabela.getColumns().addAll(nome, fone, funcao, diaria, ativo);
        
        tabelaLayout = new VBox();
        tabelaLayout.setSpacing(10);
        tabelaLayout.getChildren().add(tabela);
        tabelaLayout.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(tabelaLayout);
        
        tabela.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                funcionario = tabela.getSelectionModel().getSelectedItem();
                tfNome.setText(funcionario.getNome());
                tfCPF.setText(funcionario.getCpf());
                tfTelefone.setText(funcionario.getTelefone());
                tfEndereco.setText(funcionario.getEndereco());
                tfFuncao.setText(funcionario.getFuncao());
                tfDiaria.setText(funcionario.getDiaria().toString());
                checkAtivo.setSelected(funcionario.getAtivo());
                excluir.setDisable(false);
            }
        });
        
        salvar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                funcionario.setNome(tfNome.getText());
                funcionario.setEndereco(tfEndereco.getText());
                funcionario.setTelefone(tfTelefone.getText());
                funcionario.setAtivo(checkAtivo.isSelected());
                funcionario.setFuncao(tfFuncao.getText());
                funcionario.setDiaria(Double.parseDouble(tfDiaria.getText()));
                funcionario.setCpf(tfCPF.getText());
                
                for(CadFuncionarioViewListener l : listeners){
                    l.salvar(funcionario);
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
                confirmacao.setContentText("Realmente deseja excluir o funcionário "+funcionario+"?");
                
                ButtonType sim = new ButtonType("OK");
                ButtonType nao = new ButtonType("Cancelar");
                confirmacao.getButtonTypes().setAll(sim, nao);
                
                Optional<ButtonType> resultado = confirmacao.showAndWait();
                if(resultado.get() == sim){
                    for(CadFuncionarioViewListener l : listeners)
                        l.excluir(funcionario);
                
                    limparForm();
                }
            }
        });
    }
    
    @Override
    public void addListener(CadFuncionarioViewListener listener) {
        listeners.add(listener);
    }

    @Override
    public void limparForm() {
        tfNome.setText("");
        tfCPF.setText("");
        tfTelefone.setText("");
        tfEndereco.setText("");
        tfFuncao.setText("");
        tfDiaria.setText("");
        checkAtivo.setSelected(true);
        tabela.getSelectionModel().select(null);
        excluir.setDisable(true);
        funcionario = new Funcionario();
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
    public void populaListaFuncionarios(Collection<Funcionario> lista) {
        listaFuncionarios = FXCollections.observableArrayList(lista);
        tabela.setItems(listaFuncionarios);
    }
    
}
