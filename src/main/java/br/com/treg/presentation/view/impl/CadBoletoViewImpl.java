/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view.impl;

import br.com.treg.TReG;
import br.com.treg.business.model.Boleto;
import br.com.treg.business.model.NotaFiscal;
import br.com.treg.presentation.view.CadBoletoView;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javafx.beans.property.BooleanProperty;
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
import javafx.scene.control.TableCell;
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
public class CadBoletoViewImpl extends VBox implements CadBoletoView{

    List<CadBoletoViewListener> listeners = new ArrayList<CadBoletoViewListener>();
    
    private GridPane formLayout;
    private VBox tabelaLayout;
    private ComboBox<NotaFiscal> comboNF;
    private ComboBox<Integer> comboParcelas;
    private ObservableList<NotaFiscal> listaNF;
    private ObservableList<Integer> listaParcelas;
    private ObservableList<Boleto> listaBoleto;
    private ObservableList<Boleto> listaBoletosPendentes;
    private ObservableList<Boleto> listaBoletosPagos;
    private DatePicker dataEmissao;
    private DatePicker dataVencimento, dataPagamento;
    private CheckBox pago;
    private TableView<Boleto> tabela;
    private TextField tfValor;
    private Button salvar, cancelar, excluir;
    private Boleto boleto = new Boleto();

    public CadBoletoViewImpl() {
        this.setSpacing(10);
        
        HBox tituloLayout = new HBox();
        Text titulo = new Text("Cadastro de Boleto");
        titulo.setId("titulo");
        tituloLayout.getChildren().add(titulo);
        tituloLayout.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(tituloLayout);
        
        formLayout = new GridPane();
        formLayout.setVgap(7);
        formLayout.setHgap(5); 
        
        Label lNF = new Label("Nota Fiscal: ");
        comboNF = new ComboBox<>();
        formLayout.add(lNF, 0, 1);
        formLayout.add(comboNF, 1, 1);
        
        Label lParcela = new Label("Parcela: ");
        comboParcelas = new ComboBox<>();
        formLayout.add(lParcela, 0, 2);
        formLayout.add(comboParcelas, 1, 2);
        
        Label lDataEmissao = new Label("Data de Emissão: ");
        dataEmissao = new DatePicker();
        formLayout.add(lDataEmissao, 0, 3);
        formLayout.add(dataEmissao, 1, 3);
        
        Label lDataVencimento = new Label("Data de Vencimento: ");
        dataVencimento = new DatePicker();
        formLayout.add(lDataVencimento, 0, 4);
        formLayout.add(dataVencimento, 1, 4);
        
        Label lValor = new Label("Valor: ");
        tfValor = new TextField();
        formLayout.add(lValor, 0, 5);
        formLayout.add(tfValor, 1, 5);
        
        pago = new CheckBox("Pago?");
        pago.setSelected(false);
        formLayout.add(pago, 0, 6);
        
        Label lDataPagamento = new Label("Data de Pagamento: ");
        dataPagamento = new DatePicker();
        formLayout.add(lDataPagamento, 0, 7);
        formLayout.add(dataPagamento, 1, 7);
        lDataPagamento.setVisible(false);
        dataPagamento.setVisible(false);
        
        pago.selectedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue == true){
                    lDataPagamento.setVisible(true);
                    dataPagamento.setVisible(true);
                }
                else{
                    lDataPagamento.setVisible(false);
                    dataPagamento.setVisible(false);
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
        formLayout.add(botoesLayout, 0, 8);
        
        formLayout.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(formLayout);
        
        tabela = new TableView<>();
        tabela.setMaxWidth(405);
        
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
        pagoCol.setMinWidth(50);
        pagoCol.setCellValueFactory(
                new PropertyValueFactory<Boleto, BooleanProperty>("pago")
        );
        
        pagoCol.setCellFactory(column -> {
            return new TableCell<Boleto, Boolean>() {
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
        
        tabela.getColumns().addAll(nota, dataEmi, dataVen, parcela, pagoCol);
        
        tabelaLayout = new VBox();
        tabelaLayout.setSpacing(10);
        tabelaLayout.getChildren().add(tabela);
        tabelaLayout.setAlignment(Pos.TOP_CENTER);
        this.getChildren().add(tabelaLayout);
        
        tabela.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            boleto = tabela.getSelectionModel().getSelectedItem();
            comboNF.setValue(boleto.getNotaFiscal());
            comboParcelas.setValue(boleto.getParcela());
            Date input = boleto.getDataEmissao();
            dataEmissao.setValue(Instant.ofEpochMilli(input.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            Date input2 = boleto.getDataVencimento();
            dataVencimento.setValue(Instant.ofEpochMilli(input2.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            pago.setSelected(boleto.getPago());
            tfValor.setText(boleto.getValor().toString());
            
            if(boleto.getPago()){
                Date input3 = boleto.getDataVencimento();
                dataPagamento.setValue(Instant.ofEpochMilli(input3.getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            }else{
                dataPagamento.setValue(null);
            }
            //dataPagamento.setValue(LocalDate.MIN);
            excluir.setDisable(false);
        });
        
//        tabela.selectionModelProperty().addListener(new ChangeListener<TableView.TableViewSelectionModel<Boleto>>() {
//            @Override
//            public void changed(ObservableValue<? extends TableView.TableViewSelectionModel<Boleto>> observable, TableView.TableViewSelectionModel<Boleto> oldValue, TableView.TableViewSelectionModel<Boleto> newValue) {
//                
//            }
//        });
        
//        tabela.setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                
//            }
//        });
        
        salvar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boleto.setDataEmissao(Date.from(dataEmissao.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                boleto.setDataVencimento(Date.from(dataVencimento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                boleto.setNotaFiscal(comboNF.getValue());
                boleto.setPago(pago.isSelected());
                boleto.setParcela(comboParcelas.getValue());
                boleto.setValor(Double.parseDouble(tfValor.getText()));
                
                if(pago.isSelected())
                    boleto.setDataPagamento(Date.from(dataPagamento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    
                for(CadBoletoViewListener l : listeners){
                    l.salvar(boleto);
                }
                limparForm();
                
                for(CadBoletoViewListener l : listeners){
                    listaBoletosPendentes = FXCollections.observableArrayList(l.atualizaListaBoletosPendentes());
                    listaBoletosPagos = FXCollections.observableArrayList(l.atualizaListaBoletosPagos());
                }
                
                TReG.viewPresenter.view.constroiBoletosPendentesLayout(listaBoletosPendentes);
                TReG.viewPresenter.view.costroiBoletosPagosLayout(listaBoletosPagos);
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
                    
                    for(CadBoletoViewListener l : listeners){
                        listaBoletosPendentes = FXCollections.observableArrayList(l.atualizaListaBoletosPendentes());
                        listaBoletosPagos = FXCollections.observableArrayList(l.atualizaListaBoletosPagos());
                    }

                    TReG.viewPresenter.view.constroiBoletosPendentesLayout(listaBoletosPendentes);
                    TReG.viewPresenter.view.costroiBoletosPagosLayout(listaBoletosPagos);
                    
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
        tfValor.setText("");
        
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
        
        int numParcelas = lista.size();
        Double valor = comboNF.getValue().getValor()/numParcelas;
        tfValor.setText(valor.toString());
        
        listaParcelas = FXCollections.observableArrayList(lista);
        comboParcelas.setItems(listaParcelas);
    }

    @Override
    public void populaListaBoletos(Collection<Boleto> lista) {
        listaBoleto = FXCollections.observableArrayList(lista);
        tabela.setItems(listaBoleto);
    }

    @Override
    public void boletoOnClick(Boleto b) {
        
        for(Boleto bol : tabela.getItems()){
            if(bol.getId().equals(b.getId())){
                tabela.getSelectionModel().select(bol);
                break;
            }
        }
        //tabela.getSelectionModel().select(b);
    }
    
}
