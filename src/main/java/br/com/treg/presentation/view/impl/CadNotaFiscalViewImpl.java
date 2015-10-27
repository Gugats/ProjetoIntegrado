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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Gustavo
 */
public class CadNotaFiscalViewImpl extends VBox implements CadNotaFiscalView{

    List<CadNotaFiscalViewListener> listeners = new ArrayList<CadNotaFiscalViewListener>();
    
    private VBox formLayout, tabelaLayout;
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
    private HBox parcelasLayout;
    
    public CadNotaFiscalViewImpl() {
        this.setSpacing(10);
        
        formLayout = new VBox();
        formLayout.setSpacing(10);
        
        HBox fornecedorLayout = new HBox();
        fornecedorLayout.setSpacing(7);
        Label lFornecedor = new Label("Fornecedor/Prestador de Serviços: ");
        comboFornecedor = new ComboBox<>();
        fornecedorLayout.getChildren().addAll(lFornecedor, comboFornecedor);
        fornecedorLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.getChildren().add(fornecedorLayout);
        
        HBox obraLayout = new HBox();
        obraLayout.setSpacing(7);
        Label lObra = new Label("Obra: ");
        comboObra = new ComboBox<>();
        obraLayout.getChildren().addAll(lObra, comboObra);
        obraLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.getChildren().add(obraLayout);
        
        HBox dataLayout = new HBox();
        dataLayout.setSpacing(7);
        Label lData = new Label("Data de Emissão: ");
        dataEmissao = new DatePicker();
        dataLayout.getChildren().addAll(lData, dataEmissao);
        dataLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.getChildren().add(dataLayout);
        
        VBox descLayout = new VBox();
        descLayout.setSpacing(7);
        Label lDesc = new Label("Discriminação de Serviços/Produtos: ");
        discriminacao = new TextArea();
        discriminacao.setMaxWidth(250);
        descLayout.getChildren().addAll(lDesc, discriminacao);
        descLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.getChildren().add(descLayout);
        
        HBox valorLayout = new HBox();
        valorLayout.setSpacing(7);
        Label lValor = new Label("Valor Total: ");
        tfValor = new TextField();
        valorLayout.getChildren().addAll(lValor, tfValor);
        valorLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.getChildren().add(valorLayout);
        
        HBox formaPagLayout = new HBox();
        formaPagLayout.setSpacing(7);
        Label lFormPag = new Label("Forma de Pagamento: ");
        comboFormaPag = new ComboBox<>();
        comboFormaPag.getItems().add("A Vista");
        comboFormaPag.getItems().add("A Prazo");
        formaPagLayout.getChildren().addAll(lFormPag, comboFormaPag);
        formaPagLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.getChildren().add(formaPagLayout);
        
        parcelasLayout = new HBox();
        parcelasLayout.setSpacing(7);
        parcelasLayout.setVisible(false);
        Label lParcelas = new Label("Nº de parcelas: ");
        tfQtdParcelas = new TextField();
        parcelasLayout.getChildren().addAll(lParcelas, tfQtdParcelas);
        parcelasLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.getChildren().add(parcelasLayout);
        
        comboFormaPag.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(comboFormaPag.getValue().equals("A Prazo")){
                    parcelasLayout.setVisible(true);
                }else{
                    parcelasLayout.setVisible(false);
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
        formLayout.getChildren().add(botoesLayout);
        
        formLayout.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(formLayout);
        
        tabela = new TableView<>();
        tabela.setMaxWidth(250);
        
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
        parcelasLayout.setVisible(false);
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
