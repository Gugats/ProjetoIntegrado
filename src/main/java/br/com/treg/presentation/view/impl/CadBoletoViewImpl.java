/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view.impl;

import br.com.treg.business.model.Boleto;
import br.com.treg.business.model.NotaFiscal;
import br.com.treg.presentation.view.CadBoletoView;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Gustavo
 */
public class CadBoletoViewImpl extends VBox implements CadBoletoView{

    List<CadBoletoViewListener> listeners = new ArrayList<CadBoletoViewListener>();
    
    private VBox formLayout, tabelaLayout;
    private ComboBox<NotaFiscal> comboNF;
    private ComboBox<Integer> comboParcelas;
    private ObservableList<NotaFiscal> listaNF;
    private ObservableList<Integer> listaParcelas;
    private ObservableList<Boleto> listaBoleto;
    private DatePicker dataEmissao;
    private DatePicker dataVencimento, dataPagamento;
    private CheckBox pago;
    private TableView<Boleto> tabela;
    private Button salvar, cancelar, excluir;
    private Boleto boleto = new Boleto();

    public CadBoletoViewImpl() {
        this.setSpacing(10);
        
        formLayout = new VBox();
        formLayout.setSpacing(10);

        HBox nfLayout = new HBox();
        nfLayout.setSpacing(7);
        Label lNF = new Label("Nota Fiscal: ");
        comboNF = new ComboBox<>();
        nfLayout.getChildren().addAll(lNF, comboNF);
        nfLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.getChildren().add(nfLayout);
        
        HBox parcelaLayout = new HBox();
        parcelaLayout.setSpacing(7);
        Label lParcela = new Label("Parcela: ");
        comboParcelas = new ComboBox<>();
        parcelaLayout.getChildren().addAll(lParcela, comboParcelas);
        parcelaLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.getChildren().add(parcelaLayout);
        
        HBox dataEmissaoLayout = new HBox();
        dataEmissaoLayout.setSpacing(7);
        Label lDataEmissao = new Label("Data de Emissão: ");
        dataEmissao = new DatePicker();
        dataEmissaoLayout.getChildren().addAll(lDataEmissao, dataEmissao);
        dataEmissaoLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.getChildren().add(dataEmissaoLayout);
        
        HBox dataVencimentoLayout = new HBox();
        dataVencimentoLayout.setSpacing(7);
        Label lDataVencimento = new Label("Data de Vencimento: ");
        dataVencimento = new DatePicker();
        dataVencimentoLayout.getChildren().addAll(lDataVencimento, dataVencimento);
        dataVencimentoLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.getChildren().add(dataVencimentoLayout);
        
        pago = new CheckBox("Pago?");
        pago.setSelected(false);
        formLayout.getChildren().add(pago);
        
        final HBox dataPagamentoLayout = new HBox();
        dataPagamentoLayout.setVisible(false);
        dataPagamentoLayout.setSpacing(7);
        Label lDataPagamento = new Label("Data de Pagamento: ");
        dataPagamento = new DatePicker();
        dataPagamentoLayout.getChildren().addAll(lDataPagamento, dataPagamento);
        dataPagamentoLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.getChildren().add(dataPagamentoLayout);
        
        pago.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(pago.isSelected()){
                    dataPagamentoLayout.setVisible(true);
                }else{
                    dataPagamentoLayout.setVisible(false);
                }
            }
        });
        
        comboNF.valueProperty().addListener(new ChangeListener<NotaFiscal>() {
            @Override
            public void changed(ObservableValue<? extends NotaFiscal> observable, NotaFiscal oldValue, NotaFiscal newValue) {
                
                if(comboNF.getValue() == null){
                    comboParcelas.getItems().clear();
                }else{
                    populaComboParcelas(comboNF.getValue().getParcelas());
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
        tabela.setMaxWidth(380);
        
        TableColumn nota = new TableColumn("Nota Fiscal");
        nota.setMinWidth(100);
        nota.setCellValueFactory(
                new PropertyValueFactory<Boleto, StringProperty>("notaFiscal")
        );
        
        TableColumn dataEmi = new TableColumn("Data Emissão");
        dataEmi.setMinWidth(100);
        dataEmi.setCellValueFactory(
                new PropertyValueFactory<Boleto, StringProperty>("dataEmissao")
        );
        
        TableColumn dataVen = new TableColumn("Data Vencimento");
        dataVen.setMinWidth(100);
        dataVen.setCellValueFactory(
                new PropertyValueFactory<Boleto, StringProperty>("dataVencimento")
        );
        
        TableColumn parcela = new TableColumn("Parcela");
        parcela.setMinWidth(40);
        parcela.setCellValueFactory(
                new PropertyValueFactory<Boleto, StringProperty>("parcela")
        );
        
        TableColumn pagoCol = new TableColumn("Pago");
        pagoCol.setMinWidth(40);
        pagoCol.setCellValueFactory(
                new PropertyValueFactory<Boleto, StringProperty>("pago")
        );
        
        tabela.getColumns().addAll(nota, dataEmi, dataVen, parcela, pagoCol);
        
        tabelaLayout = new VBox();
        tabelaLayout.setSpacing(10);
        tabelaLayout.getChildren().add(tabela);
        tabelaLayout.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(tabelaLayout);
        
        tabela.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                boleto = tabela.getSelectionModel().getSelectedItem();
                comboNF.setValue(boleto.getNotaFiscal());
                comboParcelas.setValue(boleto.getParcela());
                Date input = boleto.getDataEmissao();
                dataEmissao.setValue(Instant.ofEpochMilli(input.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
                Date input2 = boleto.getDataVencimento();
                dataVencimento.setValue(Instant.ofEpochMilli(input2.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
                pago.setSelected(boleto.getPago());
                excluir.setDisable(false);
            }
        });
        
        salvar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boleto.setDataEmissao(Date.from(dataEmissao.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                boleto.setDataVencimento(Date.from(dataVencimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                boleto.setNotaFiscal(comboNF.getValue());
                boleto.setPago(pago.isSelected());
                boleto.setParcela(comboParcelas.getValue());
                
                for(CadBoletoViewListener l : listeners){
                    l.salvar(boleto);
                }
                limparForm();
//                BorderPane parent = (BorderPane) getParent();
                
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
                confirmacao.setContentText("Realmente deseja excluir o Boleto selecionado?");
                
                ButtonType sim = new ButtonType("OK");
                ButtonType nao = new ButtonType("Cancelar");
                confirmacao.getButtonTypes().setAll(sim, nao);
                
                Optional<ButtonType> resultado = confirmacao.showAndWait();
                if(resultado.get() == sim){
                    for(CadBoletoViewListener l : listeners)
                        l.excluir(boleto);
                
                    limparForm();
                }
            }
        });
        
    }
    
    @Override
    public void addListener(CadBoletoViewListener listener) {
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
        tabela.getSelectionModel().select(null);
        boleto = new Boleto();
        excluir.setDisable(true);
        comboNF.setValue(null);
        pago.setSelected(false);
        dataEmissao.setValue(null);
        dataPagamento.setValue(null);
        dataVencimento.setValue(null);
        
    }

    @Override
    public void populaComboNF(Collection<NotaFiscal> lista) {
        listaNF = FXCollections.observableArrayList(lista);
        comboNF.setItems(listaNF);
    }

    @Override
    public void populaComboParcelas(Integer n) {
        List<Integer> lista = new ArrayList<>();
        int i=1;
        while(i <= n){
            lista.add(i);
            i++;
        }
        listaParcelas = FXCollections.observableArrayList(lista);
        comboParcelas.setItems(listaParcelas);
    }

    @Override
    public void populaListaBoletos(Collection<Boleto> lista) {
        listaBoleto = FXCollections.observableArrayList(lista);
        tabela.setItems(listaBoleto);
    }
    
}
