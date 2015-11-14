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
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Tooltip;
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
import org.controlsfx.dialog.ProgressDialog;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;

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
    
    private GlyphFont font = GlyphFontRegistry.font("FontAwesome");
    
    private ColorPicker colorPicker;
    
    private VBox boletosPendentesLayout, boletosPagosLayout;
    private HBox menuLayout;
    private MenuBar menu;
    private Menu menuFile, menuConfig, menuView;
    private MenuItem itemCadFornecedor, itemCadCliente, itemCadOrcamento,
            itemCadFuncionario, itemCadObra, itemCadNotaFiscal, itemCadBoleto,
            itemCadRecibo, itemConfigColor;
    private Text pick, textoPagos, textoPendentes;
    
    private ProgressDialog progressDialog;
    
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
        
        
        menuConfig = new Menu("Configuração");
        itemConfigColor = new MenuItem("Cor de Background");
        
        
        menuView = new Menu("View");
        
        menu.getMenus().addAll(menuFile, menuConfig, menuView);
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
        leftLayout.setSpacing(10);
        
        boletosPagosLayout = new VBox();
        boletosPagosLayout.setId("borderLayout");
        boletosPagosLayout.setSpacing(7);
        textoPagos = new Text("Últimos boletos pagos");
        
        
        boletosPendentesLayout = new VBox();
        boletosPendentesLayout.setId("borderLayout");
        boletosPendentesLayout.setSpacing(7);
        textoPendentes = new Text("Próximos boletos a vencer");
        
        
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
        
        leftLayout.getChildren().add(boletosPendentesLayout);
        leftLayout.getChildren().add(boletosPagosLayout);
        
        this.setLeft(leftLayout);
        this.setTop(menuLayout);
        
    }

    @Override
    public void costroiBoletosPagosLayout(Collection<Boleto> lista) {
        boletosPagosLayout.getChildren().clear();
        boletosPagosLayout.getChildren().add(textoPagos);
        if(lista.size() == 0){
            Text listaVaziaText = new Text("Não há Boletos pagos disponíveis!");
            boletosPagosLayout.getChildren().add(listaVaziaText);
        }else{
            for(Boleto b : lista){
                
                String newstring = new SimpleDateFormat("dd-MM-yyyy").format(b.getDataPagamento());
                
                Button botao = new Button("Valor: R$ "+b.getValor() + " - Data Pagamento: " + newstring, font.create(FontAwesome.Glyph.MONEY));
                botao.setUserData(b);
                Tooltip tooltip = new Tooltip("Clique para visualizar detalhes!");
                botao.setTooltip(tooltip);
//                botao.setId("glass-grey");
                boletosPagosLayout.getChildren().add(botao);
                botao.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                       
                        cadBoletoPresenter = new CadBoletoPresenter((Boleto)botao.getUserData());
//                        new Thread(cadBoletoPresenter).start();
//                        progressDialog = new ProgressDialog(cadBoletoPresenter);
                        setCenter((Parent) cadBoletoPresenter.getView());
                        
//                        progressDialog.setTitle("Executando...");
//                        progressDialog.setHeaderText(null);
//                        progressDialog.setContentText("Aguarde...");
//                        
//                        progressDialog.show();
                        
                    }
                });
            }
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
                String newstring = new SimpleDateFormat("dd-MM-yyyy").format(b.getDataVencimento());
                
                Button botao = new Button("Valor: R$ "+b.getValor() + " - Data Vencimento: " + newstring, font.create(FontAwesome.Glyph.MONEY));
                botao.setUserData(b);
                Tooltip tooltip = new Tooltip("Clique para visualizar detalhes!");
                botao.setTooltip(tooltip);
//                botao.setId("glass-grey");
                boletosPendentesLayout.getChildren().add(botao);
                botao.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        
                        cadBoletoPresenter = new CadBoletoPresenter((Boleto)botao.getUserData());
                        
                        setCenter((Parent) cadBoletoPresenter.getView());
                    }
                });
            }
        }
    }

    @Override
    public void addListener(MainViewListener listener) {
        listeners.add(listener);
    }

    
}
