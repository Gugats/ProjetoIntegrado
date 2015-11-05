/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view.impl;

import br.com.treg.business.model.Boleto;
import br.com.treg.presentation.presenter.CadBoletoPresenter;
import br.com.treg.presentation.presenter.CadClientePresenter;
import br.com.treg.presentation.presenter.CadFornecedorPresenter;
import br.com.treg.presentation.presenter.CadFuncionarioPresenter;
import br.com.treg.presentation.presenter.CadNotaFiscalPresenter;
import br.com.treg.presentation.presenter.CadObraPresenter;
import br.com.treg.presentation.presenter.CadOrcamentoPresenter;
import br.com.treg.presentation.presenter.CadReciboPresenter;
import br.com.treg.presentation.view.MainView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
public class MainViewImpl extends BorderPane implements MainView{
    
    List<MainViewListener> listeners = new ArrayList<MainViewListener>();
    
    //Presenters
    private CadFornecedorPresenter cadFornecedorPresenter;
    private CadClientePresenter cadClientePresenter;
    private CadOrcamentoPresenter cadOrcamentoPresenter;
    private CadFuncionarioPresenter cadFuncionarioPresenter;
    private CadObraPresenter cadObraPresenter;
    private CadNotaFiscalPresenter cadNotaFiscalPresenter;
    private CadBoletoPresenter cadBoletoPresenter;
    private CadReciboPresenter cadReciboPresenter;
    
    private ColorPicker colorPicker;
    
    private VBox boletosPendentesLayout, boletosPagosLayout;
    private HBox menuLayout;
    private MenuBar menu;
    private Menu menuFile, menuEdit, menuView;
    private MenuItem itemCadFornecedor, itemCadCliente, itemCadOrcamento,
            itemCadFuncionario, itemCadObra, itemCadNotaFiscal, itemCadBoleto,
            itemCadRecibo;
    private Text pick, textoPagos, textoPendentes;
    
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
        itemCadBoleto = new MenuItem("Boleto");
        itemCadRecibo = new MenuItem("Recibo");
        menuFile.getItems().addAll(itemCadFornecedor, itemCadCliente, itemCadOrcamento,
                itemCadFuncionario, itemCadObra, itemCadNotaFiscal, itemCadBoleto, itemCadRecibo);
        
        
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
        
        itemCadBoleto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cadBoletoPresenter = new CadBoletoPresenter();
                setCenter((Parent) cadBoletoPresenter.getView());
            }
        });
        
        itemCadRecibo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cadReciboPresenter = new CadReciboPresenter();
                setCenter((Parent) cadReciboPresenter.getView());
            }
        });
   
        VBox leftLayout = new VBox();
        
        boletosPagosLayout = new VBox();
        boletosPagosLayout.setSpacing(7);
        textoPagos = new Text("Últimos 5 boletos pagos");
        
        
        
        boletosPendentesLayout = new VBox();
        boletosPendentesLayout.setSpacing(7);
        textoPendentes = new Text("Próximos 5 boletos a vencer");
        
        
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
        
//        leftLayout.getChildren().add(boletosPendentesLayout);
        
        this.setLeft(leftLayout);
        this.setTop(menuLayout);
        
    }

    @Override
    public void costroiBoletosPagosLayout(Collection<Boleto> lista) {
        boletosPagosLayout.getChildren().clear();
        boletosPagosLayout.getChildren().add(textoPagos);
        if(lista.size() == 0){
            Text listaVaziaText = new Text("Não há Boletos pagos disponíveis!");
        }else{
            
        }
    }

    @Override
    public void constroiBoletosPendentesLayout(Collection<Boleto> lista) {
        boletosPendentesLayout.getChildren().clear();
        boletosPendentesLayout.getChildren().add(textoPendentes);
        if(lista.size() == 0){
            Text listaVaziaText = new Text("Não há Boleto a ser pago");
            boletosPendentesLayout.getChildren().add(listaVaziaText);
        }else{
            for(Boleto b : lista){
                Button botao = new Button(b.toString());
                botao.setUserData(b);
                boletosPendentesLayout.getChildren().add(botao);
            }
        }
    }

    @Override
    public void addListener(MainViewListener listener) {
        listeners.add(listener);
    }

    
}
