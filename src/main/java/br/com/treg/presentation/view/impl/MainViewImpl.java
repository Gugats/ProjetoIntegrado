/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view.impl;

import br.com.treg.presentation.presenter.CadClientePresenter;
import br.com.treg.presentation.presenter.CadFornecedorPresenter;
import br.com.treg.presentation.presenter.CadFuncionarioPresenter;
import br.com.treg.presentation.presenter.CadNotaFiscalPresenter;
import br.com.treg.presentation.presenter.CadObraPresenter;
import br.com.treg.presentation.presenter.CadOrcamentoPresenter;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import org.controlsfx.control.TaskProgressView;

/**
 *
 * @author Gustavo
 */
public class MainViewImpl extends BorderPane {
    
    //Presenters
    private CadFornecedorPresenter cadFornecedorPresenter;
    private CadClientePresenter cadClientePresenter;
    private CadOrcamentoPresenter cadOrcamentoPresenter;
    private CadFuncionarioPresenter cadFuncionarioPresenter;
    private CadObraPresenter cadObraPresenter;
    private CadNotaFiscalPresenter cadNotaFiscalPresenter;
    
    private ColorPicker colorPicker;
    
    private HBox menuLayout;
    private MenuBar menu;
    private Menu menuFile, menuEdit, menuView;
    private MenuItem itemCadFornecedor, itemCadCliente, itemCadOrcamento,
            itemCadFuncionario, itemCadObra, itemCadNotaFiscal;
    private Text pick;
    
    public MainViewImpl(){
        
        menuLayout = new HBox();
        menuLayout.setSpacing(10);
        
        menu = new MenuBar();
        //menu.setStyle("-fx-background-color: #F0E68C;");
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        menu.setMinWidth(primaryScreenBounds.getWidth());
        
        
        
        menuFile = new Menu("Cadastro");
        itemCadFornecedor = new MenuItem("Fornecedor");
        itemCadCliente = new MenuItem("Cliente");
        itemCadOrcamento = new MenuItem("Orçamento");
        itemCadFuncionario = new MenuItem("Funcionario");
        itemCadObra = new MenuItem("Obra");
        itemCadNotaFiscal = new MenuItem("Nota Fiscal");
        menuFile.getItems().addAll(itemCadFornecedor, itemCadCliente, itemCadOrcamento,
                itemCadFuncionario, itemCadObra, itemCadNotaFiscal);
        
        
        menuEdit = new Menu("Edit");
        menuView = new Menu("View");
        
        menu.getMenus().addAll(menuFile, menuEdit, menuView);
        menuLayout.getChildren().add(menu);
        
        
        itemCadFornecedor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                cadFornecedorPresenter = new CadFornecedorPresenter();
                setCenter((Parent)cadFornecedorPresenter.getView());
            }
        });
        
        itemCadCliente.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                cadClientePresenter = new CadClientePresenter();
                setCenter((Parent)cadClientePresenter.getView());
            }
        });
        
        itemCadOrcamento.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cadOrcamentoPresenter = new CadOrcamentoPresenter();
                setCenter((Parent)cadOrcamentoPresenter.getView());
            }
        });
        
        itemCadFuncionario.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cadFuncionarioPresenter = new CadFuncionarioPresenter();
                setCenter((Parent)cadFuncionarioPresenter.getView());
            }
        });
        
        itemCadObra.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cadObraPresenter = new CadObraPresenter();
                setCenter((Parent) cadObraPresenter.getView());
            }
        });
        
        itemCadNotaFiscal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cadNotaFiscalPresenter = new CadNotaFiscalPresenter();
                setCenter((Parent) cadNotaFiscalPresenter.getView());
            }
        });
   
        VBox leftLayout = new VBox();
        colorPicker = new ColorPicker();
        colorPicker.setValue(Color.CORAL);
        
        pick = new Text("Escolha a cor");
        
        leftLayout.getChildren().addAll(pick, colorPicker);
        
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Paint fill = colorPicker.getValue();
                BackgroundFill backgroundFill = 
                    new BackgroundFill(fill, 
                            CornerRadii.EMPTY, 
                            Insets.EMPTY);
                Background background = new Background(backgroundFill);
                setBackground(background);
            }
        });
        
        this.setLeft(leftLayout);
        this.setTop(menuLayout);
        
    }

    
}
