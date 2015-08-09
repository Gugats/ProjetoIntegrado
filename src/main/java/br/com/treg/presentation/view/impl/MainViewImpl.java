/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view.impl;

import br.com.treg.presentation.presenter.CadFornecedorPresenter;
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

/**
 *
 * @author Gustavo
 */
public class MainViewImpl extends BorderPane {
    
    //Presenters
    private CadFornecedorPresenter cadFornecedorPresenter;
    
    private ColorPicker colorPicker;
    
    private HBox menuLayout;
    private MenuBar menu;
    private Menu menuFile, menuEdit, menuView;
    private MenuItem itemCadFornecedor;
    private Text pick;
    
    public MainViewImpl(){
        
        menuLayout = new HBox();
        menuLayout.setSpacing(10);
        
        menu = new MenuBar();
        menu.setStyle("-fx-background-color: #F0E68C;");
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        menu.setMinWidth(primaryScreenBounds.getWidth());
        menuFile = new Menu("Cadastro");
        itemCadFornecedor = new MenuItem("Fornecedor");
        menuFile.getItems().add(itemCadFornecedor);
        
        menuEdit = new Menu("Edit");
        menuView = new Menu("View");
        
        menu.getMenus().addAll(menuFile, menuEdit, menuView);
        menuLayout.getChildren().add(menu);
        
        cadFornecedorPresenter = new CadFornecedorPresenter();
        
        itemCadFornecedor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                setCenter((Parent)cadFornecedorPresenter.getView());
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
