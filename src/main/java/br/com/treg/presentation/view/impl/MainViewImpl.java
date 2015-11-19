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
import br.com.treg.utils.CustomItem;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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
    
    //private GlyphFont font = GlyphFontRegistry.font("FontAwesome");
    private ColorPicker colorPicker;
    
    private TreeView treeView;
    
    private VBox boletosPendentesLayout, boletosPagosLayout;
    private Text pick, textoPagos, textoPendentes;
    
    private ProgressDialog progressDialog;
    
    public MainViewImpl(){
        
        this.setId("mainPanel");
        
        VBox leftLayout = new VBox();
        leftLayout.setSpacing(10);
        
        TreeItem<String> cadastrosItem = new TreeItem<>("Cadastros");
        cadastrosItem.setExpanded(true);
        
        TreeItem<String> cadFornecedorItem = new TreeItem<>("Fornecedor");
        TreeItem<String> cadClienteItem = new TreeItem<>("Cliente");
        TreeItem<String> cadFuncionarioItem = new TreeItem<>("Funcionário");
        TreeItem<String> cadObraItem = new TreeItem<>("Obra");
        TreeItem<String> cadNFItem = new TreeItem<>("Nota Fiscal");
        TreeItem<String> cadBoletoItem = new TreeItem<>("Boleto");
        TreeItem<String> cadReciboItem = new TreeItem<>("Recibo");
        TreeItem<String> cadOrcamentoItem = new TreeItem<>("Orçamento");
        
        cadastrosItem.getChildren().addAll(cadClienteItem, cadFornecedorItem, cadFuncionarioItem,
                cadObraItem, cadNFItem, cadBoletoItem, cadReciboItem, cadOrcamentoItem);
        
        treeView = new TreeView(new TreeItem<Object>());
        treeView.setShowRoot(false);
        
        colorPicker = new ColorPicker();
        colorPicker.setValue(Color.CORAL);
        Label label = new Label("Configurações");
        
        CustomItem customItem = new CustomItem(label);
        CustomItem customItem2 = new CustomItem(new Label("Cor de Background: "), colorPicker);
        
        TreeItem<CustomItem> configItem = new TreeItem<>(customItem);
        TreeItem<CustomItem> colorItem = new TreeItem<>(customItem2);
        configItem.getChildren().add(colorItem);
        
        //TreeItem<ColorPicker> colorItem = new TreeItem<>(colorPicker, font.create(FontAwesome.Glyph.PAINT_BRUSH));
        //colorItem.setGraphic(label);
        
        treeView.getRoot().getChildren().add(cadastrosItem);
        treeView.getRoot().getChildren().add(configItem);
        
        treeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> handle(newValue));
        
        leftLayout.getChildren().add(treeView);
        
        boletosPagosLayout = new VBox();
        boletosPagosLayout.setId("borderLayout");
        boletosPagosLayout.setSpacing(7);
        textoPagos = new Text("Últimos boletos pagos");
        
        boletosPendentesLayout = new VBox();
        boletosPendentesLayout.setId("borderLayout");
        boletosPendentesLayout.setSpacing(7);
        textoPendentes = new Text("Próximos boletos a vencer");
        
        //pick = new Text("Escolha a cor");
        
        //leftLayout.getChildren().addAll(pick, colorPicker);
        
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
                
                Button botao = new Button("Valor: R$ "+b.getValor() + " - Data Pagamento: " + newstring);
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
                
                Button botao = new Button("Valor: R$ "+b.getValor() + " - Data Vencimento: " + newstring);
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
    
    private void handle(Object newValue) {      
        TreeItem<String> selectedItem = (TreeItem<String>) newValue;
        
        if(selectedItem.getValue().equals("Fornecedor")){
            cadFornecedorPresenter = new CadFornecedorPresenter();
            setCenter((Parent) cadFornecedorPresenter.getView());
        }else if(selectedItem.getValue().equals("Cliente")){
            cadClientePresenter = new CadClientePresenter();
            setCenter((Parent) cadClientePresenter.getView());
        }else if(selectedItem.getValue().equals("Obra")){
            cadObraPresenter = new CadObraPresenter();
            setCenter((Parent) cadObraPresenter.getView());
        }else if(selectedItem.getValue().equals("Funcionário")){
            cadFuncionarioPresenter = new CadFuncionarioPresenter();
            setCenter((Parent) cadFuncionarioPresenter.getView());
        }else if(selectedItem.getValue().equals("Nota Fiscal")){
            cadNotaFiscalPresenter = new CadNotaFiscalPresenter();
            setCenter((Parent) cadNotaFiscalPresenter.getView());
        }else if(selectedItem.getValue().equals("Boleto")){
//            ProgressBar bar = new ProgressBar();
//            Task task = new Task<Void>() {
//                @Override
//                protected Void call() throws Exception {
                    cadBoletoPresenter = new CadBoletoPresenter();
                    setCenter((Parent) cadBoletoPresenter.getView());
                    
//                    bar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
//                    return null;
//                }
//                
//            };
//            bar.progressProperty().bind(task.progressProperty());
//            new Thread(task).start();
            
        }else if(selectedItem.getValue().equals("Recibo")){
            cadReciboPresenter = new CadReciboPresenter();
            setCenter((Parent) cadReciboPresenter.getView());
        }else if(selectedItem.getValue().equals("Orçamento")){
            cadOrcamentoPresenter = new CadOrcamentoPresenter();
            setCenter((Parent) cadOrcamentoPresenter.getView());
        }
    }
    
}
