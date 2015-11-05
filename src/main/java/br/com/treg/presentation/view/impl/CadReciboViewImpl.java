/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view.impl;

import br.com.treg.business.model.Obra;
import br.com.treg.business.model.ObraFuncionario;
import br.com.treg.presentation.view.CadReciboView;
import br.com.treg.utils.GeneratorPDF;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author Gustavo
 */
public class CadReciboViewImpl extends VBox implements CadReciboView{

    List<CadReciboViewListener> listeners = new ArrayList<CadReciboViewListener>();
    
    private VBox formLayout;
    private Text titulo;
    private ComboBox<Obra> comboObra;
    private ComboBox<ObraFuncionario> comboFuncionario;
    private TextField tfDias;
    private ObservableList<Obra> listaObras;
    private ObservableList<ObraFuncionario> listaFuncionarios;
    private Button salvarGerar, cancelar, excluir;
    
    public CadReciboViewImpl() {
        
        this.setSpacing(10);
        
        formLayout = new VBox();
        formLayout.setSpacing(10);
        
        titulo = new Text("Gerar Recibo");
        titulo.setId("titulo");
        formLayout.getChildren().add(titulo);
        
        HBox obraLayout = new HBox();
        obraLayout.setSpacing(7);
        Label lObra = new Label("Obra: ");
        comboObra = new ComboBox<>();
        obraLayout.getChildren().addAll(lObra, comboObra);
        obraLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.getChildren().add(obraLayout);
        
        HBox funcionarioLayout = new HBox();
        funcionarioLayout.setSpacing(7);
        Label lFuncionario = new Label("Funcionario: ");
        comboFuncionario = new ComboBox<>();
        funcionarioLayout.getChildren().addAll(lFuncionario, comboFuncionario);
        funcionarioLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.getChildren().add(funcionarioLayout);
        
        HBox diasLayout = new HBox();
        diasLayout.setSpacing(7);
        Label lDias = new Label("Dias Trabalhados: ");
        tfDias = new TextField();
        diasLayout.getChildren().addAll(lDias, tfDias);
        diasLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.getChildren().add(diasLayout);
        
        HBox botoesLayout = new HBox();
        botoesLayout.setSpacing(7);
        salvarGerar = new Button("Gerar PDF");
        cancelar = new Button("Cancelar");
        excluir = new Button("Excluir");
        botoesLayout.getChildren().addAll(salvarGerar, cancelar, excluir);
        botoesLayout.setAlignment(Pos.TOP_CENTER);
        formLayout.getChildren().add(botoesLayout);
        
        comboObra.valueProperty().addListener(new ChangeListener<Obra>() {
            @Override
            public void changed(ObservableValue<? extends Obra> observable, Obra oldValue, Obra newValue) {
                if(comboObra.getValue() != null){
                    populaComboFuncionarios(comboObra.getValue().getObrasFuncionarios());
                }
            }
        });
        
        salvarGerar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ObraFuncionario of = comboFuncionario.getValue();
                String dias = tfDias.getText();
                int d = Integer.parseInt(dias);
                double valor = of.getFuncionario().getDiaria() * d;
                
                String texto = "";
                new GeneratorPDF(texto);
            }
        });
        
        this.getChildren().add(formLayout);
        
    }
    
    @Override
    public void addListener(CadReciboViewListener listener) {
        listeners.add(listener);
    }

    @Override
    public void falha(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sucesso(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void limparForm() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void populaComboObra(Collection<Obra> lista) {
        listaObras = FXCollections.observableArrayList(lista);
        comboObra.setItems(listaObras);
    }

    @Override
    public void populaComboFuncionarios(Collection<ObraFuncionario> lista) {
        listaFuncionarios = FXCollections.observableArrayList(lista);
        comboFuncionario.setItems(listaFuncionarios);
    }

    @Override
    public void concatenaString() {
        
    }
    
}
