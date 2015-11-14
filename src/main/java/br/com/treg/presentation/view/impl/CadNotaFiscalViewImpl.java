/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view.impl;

import br.com.treg.business.model.Fornecedor;
import br.com.treg.business.model.Funcionario;
import br.com.treg.business.model.NotaFiscal;
import br.com.treg.business.model.Obra;
import br.com.treg.presentation.view.CadFuncionarioView;
import br.com.treg.presentation.view.CadNotaFiscalView;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javafx.beans.property.DoubleProperty;
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
import javafx.scene.control.DatePicker;
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
import org.controlsfx.control.Notifications;

/**
 *
 * @author Gustavo
 */
public class CadNotaFiscalViewImpl extends VBox implements CadNotaFiscalView{

    List<CadNotaFiscalViewListener> listeners = new ArrayList<CadNotaFiscalViewListener>();
    
    private GridPane formLayout;
    private VBox tabelaLayout;
    private ComboBox<String> comboTipoNota, comboFormaPag;
    private DatePicker dataEmissao;
    private TextArea discriminacao;
    private TextField tfValor, tfQtdParcelas;
    private ComboBox<Obra> comboObra;
    private ComboBox<Fornecedor> comboFornecedor;
    private ObservableList<Obra> listaObras;
    private ObservableList<Fornecedor> listaFornecedores;
    private ObservableList<NotaFiscal> listaNF;
    private Button cancelar, excluir, salvar;
    private TableView<NotaFiscal> tabela;
    private NotaFiscal nf = new NotaFiscal();
    private Label lParcelas;
    
    public CadNotaFiscalViewImpl() {
        this.setSpacing(10);
        
        HBox tituloLayout = new HBox();
        Text titulo = new Text("Cadastro de Nota Fiscal");
        titulo.setId("titulo");
        tituloLayout.getChildren().add(titulo);
        tituloLayout.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(tituloLayout);
        
        formLayout = new GridPane();
        formLayout.setVgap(7);
        formLayout.setHgap(5);        
                
        Label lFornecedor = new Label("Fornecedor/Prestador de Serviços: ");
        comboFornecedor = new ComboBox<>();
        formLayout.add(lFornecedor, 0, 1);
        formLayout.add(comboFornecedor, 1, 1);
        
        Label lObra = new Label("Obra: ");
        comboObra = new ComboBox<>();
        formLayout.add(lObra, 0, 2);
        formLayout.add(comboObra, 1, 2);
        
        Label lData = new Label("Data de Emissão: ");
        dataEmissao = new DatePicker();
        formLayout.add(lData, 0, 3);
        formLayout.add(dataEmissao, 1, 3);
        
        Label lDesc = new Label("Discriminação de Serviços/Produtos: ");
        discriminacao = new TextArea();
        discriminacao.setMaxWidth(250);
        formLayout.add(lDesc, 0, 4);
        formLayout.add(discriminacao, 1, 4);
        
        Label lValor = new Label("Valor Total: ");
        tfValor = new TextField();
        formLayout.add(lValor, 0, 5);
        formLayout.add(tfValor, 1, 5);
        
        Label lFormPag = new Label("Forma de Pagamento: ");
        comboFormaPag = new ComboBox<>();
        comboFormaPag.getItems().add("A Vista");
        comboFormaPag.getItems().add("A Prazo");
        formLayout.add(lFormPag, 0, 6);
        formLayout.add(comboFormaPag, 1, 6);
        
        lParcelas = new Label("Nº de parcelas: ");
        tfQtdParcelas = new TextField();
        formLayout.add(lParcelas, 0, 7);
        formLayout.add(tfQtdParcelas, 1, 7);
        lParcelas.setVisible(false);
        tfQtdParcelas.setVisible(false);
        
        comboFormaPag.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(comboFormaPag.getValue().equals("A Prazo")){
                    lParcelas.setVisible(true);
                    tfQtdParcelas.setVisible(true);
                }else{
                    lParcelas.setVisible(false);
                    tfQtdParcelas.setVisible(false);
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
        formLayout.add(botoesLayout, 0, 8);
        
        formLayout.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(formLayout);
        
        tabela = new TableView<>();
        tabela.setMaxWidth(500);
        
        TableColumn fornecedor = new TableColumn("Fornecedor");
        fornecedor.setMinWidth(100);
        fornecedor.setCellValueFactory(
                new PropertyValueFactory<NotaFiscal, StringProperty>("fornecedor")
        );
        
        TableColumn disc = new TableColumn("Discriminação");
        disc.setMinWidth(100);
        disc.setCellValueFactory(
                new PropertyValueFactory<NotaFiscal, StringProperty>("discriminacao")
        );
        
        TableColumn valor = new TableColumn("Valor Total");
        valor.setMinWidth(100);
        valor.setCellValueFactory(
                new PropertyValueFactory<NotaFiscal, DoubleProperty>("valor")
        );
        
        TableColumn dataEmissaoCol = new TableColumn("Data de Emissão");
        dataEmissaoCol.setMinWidth(100);
        dataEmissaoCol.setCellValueFactory(
                new PropertyValueFactory<NotaFiscal, Date>("dataEmissao")
        );
        
        TableColumn formaPagamento = new TableColumn("Forma de Pagamento");
        formaPagamento.setMinWidth(100);
        formaPagamento.setCellValueFactory(
                new PropertyValueFactory<NotaFiscal, StringProperty>("formaPagamento")
        );
        
        tabela.getColumns().addAll(fornecedor, disc, valor, dataEmissaoCol, formaPagamento);
        
        tabelaLayout = new VBox();
        tabelaLayout.setSpacing(10);
        tabelaLayout.getChildren().add(tabela);
        tabelaLayout.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(tabelaLayout);
        
        tabela.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                nf = tabela.getSelectionModel().getSelectedItem();
                if(nf != null){
                    comboFornecedor.setValue(nf.getFornecedor());
                    comboObra.setValue(nf.getObra());
                    comboFormaPag.setValue(nf.getFormaPagamento());
                    Date input = nf.getDataEmissao();
                    dataEmissao.setValue(Instant.ofEpochMilli(input.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
                    discriminacao.setText(nf.getDiscriminacao());
                    tfValor.setText(nf.getValor().toString());
                    tfQtdParcelas.setText(nf.getParcelas().toString());
                    excluir.setDisable(false);
                }
            }
        });
        
        salvar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                nf.setDataEmissao(Date.from(dataEmissao.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                nf.setDiscriminacao(discriminacao.getText());
                nf.setFormaPagamento(comboFormaPag.getValue());
                nf.setFornecedor(comboFornecedor.getValue());
                nf.setObra(comboObra.getValue());
                if(!tfQtdParcelas.getText().isEmpty()){
                    nf.setParcelas(Integer.parseInt(tfQtdParcelas.getText()));
                }
                nf.setValor(Double.parseDouble(tfValor.getText()));
                
                for(CadNotaFiscalViewListener l : listeners){
                    l.salvar(nf);
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
                confirmacao.setContentText("Realmente deseja excluir a Nota Fiscal selecionada?");
                
                ButtonType sim = new ButtonType("OK");
                ButtonType nao = new ButtonType("Cancelar");
                confirmacao.getButtonTypes().setAll(sim, nao);
                
                Optional<ButtonType> resultado = confirmacao.showAndWait();
                if(resultado.get() == sim){
                    for(CadNotaFiscalView.CadNotaFiscalViewListener l : listeners)
                        l.excluir(nf);
                
                    limparForm();
                }
            }
        });
    }
    
    @Override
    public void addListener(CadNotaFiscalViewListener listener) {
        listeners.add(listener);
    }

    @Override
    public void limparForm() {
        comboFormaPag.setValue("A Vista");
        dataEmissao.setValue(null);
        discriminacao.setText("");
        tfValor.setText("");
        tfQtdParcelas.setText("");
        comboFornecedor.setValue(null);
        comboObra.setValue(null);
        lParcelas.setVisible(false);
        tfQtdParcelas.setVisible(false);
        excluir.setDisable(true);
        tabela.getSelectionModel().select(null);
        nf = new NotaFiscal();
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
    public void populaComboObras(Collection<Obra> lista) {
        listaObras = FXCollections.observableArrayList(lista);
        comboObra.setItems(listaObras);
    }

    @Override
    public void populaComboFornecedores(Collection<Fornecedor> lista) {
        listaFornecedores = FXCollections.observableArrayList(lista);
        comboFornecedor.setItems(listaFornecedores);
    }

    @Override
    public void populaListaNF(Collection<NotaFiscal> lista) {
        listaNF = FXCollections.observableArrayList(lista);
        tabela.setItems(listaNF);
    }
    
}
