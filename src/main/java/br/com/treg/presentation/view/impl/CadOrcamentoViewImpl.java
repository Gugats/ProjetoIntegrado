/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.presentation.view.impl;

import br.com.treg.business.model.Item;
import br.com.treg.presentation.view.CadOrcamentoView;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.controlsfx.control.spreadsheet.GridBase;
import org.controlsfx.control.spreadsheet.SpreadsheetCell;
import org.controlsfx.control.spreadsheet.SpreadsheetCellType;
import org.controlsfx.control.spreadsheet.SpreadsheetView;

/**
 *
 * @author Gustavo
 */
public class CadOrcamentoViewImpl extends VBox implements CadOrcamentoView {

    List<CadOrcamentoViewListener> listeners = new ArrayList<CadOrcamentoViewListener>();

    Item itemQtd, itemMao, itemMaterial;
    
    private TreeTableView<Item> tabela;
    private List<Item> listaItem = new ArrayList<>();

    final TreeItem<Item> root = new TreeItem<>(new Item());

    @Override
    public void addListener(CadOrcamentoViewListener listener) {
        listeners.add(listener);
    }

    public CadOrcamentoViewImpl() {

        this.setSpacing(10);

        tabela = new TreeTableView<>(root);

        TreeTableColumn<Item, String> itemCol = new TreeTableColumn<>("Item");
        itemCol.setPrefWidth(50);
        itemCol.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Item, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getItem())
        );
        itemCol.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        itemCol.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<Item, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<Item, String> event) {
                final Item item = event.getRowValue().getValue();
                item.setItem(event.getNewValue());
            }
        });

        TreeTableColumn<Item, String> descCol = new TreeTableColumn<>("Discriminação");
        descCol.setPrefWidth(150);
        descCol.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Item, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getDiscriminacao())
        );
        descCol.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        descCol.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<Item, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<Item, String> event) {
                final Item item = event.getRowValue().getValue();
                item.setDiscriminacao(event.getNewValue());
            }
        });

        TreeTableColumn<Item, String> qtdCol = new TreeTableColumn<>("Quantidade");
        qtdCol.setPrefWidth(150);
        qtdCol.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Item, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getQtd())
        );
        qtdCol.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        qtdCol.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<Item, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<Item, String> event) {
                itemQtd = event.getRowValue().getValue();
                itemQtd.setQtd(event.getNewValue());
            }
        });

        TreeTableColumn<Item, String> uniCol = new TreeTableColumn<>("Unidade de Medida");
        uniCol.setPrefWidth(150);
        uniCol.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Item, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getUnidadeMedida())
        );
        uniCol.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        uniCol.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<Item, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<Item, String> event) {
                final Item item = event.getRowValue().getValue();
                item.setUnidadeMedida(item.getQtd());
                
            }
        });

        TreeTableColumn<Item, String> materialCol = new TreeTableColumn<>("Material");
        materialCol.setPrefWidth(150);
        materialCol.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Item, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getMaterial())
        );
        materialCol.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        materialCol.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<Item, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<Item, String> event) {
                itemMaterial = event.getRowValue().getValue();
                itemMaterial.setMaterial(event.getNewValue());
            }
        });

        TreeTableColumn<Item, String> maoCol = new TreeTableColumn<>("Mão de Obra");
        maoCol.setPrefWidth(150);
        maoCol.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Item, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getMaoObra())
        );
        maoCol.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        maoCol.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<Item, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<Item, String> event) {
                itemMao = event.getRowValue().getValue();
                itemMao.setMaoObra(event.getNewValue());
            }
        });

        TreeTableColumn<Item, String> totalCol = new TreeTableColumn<>("Total");
        totalCol.setPrefWidth(150);
        totalCol.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Item, String> param)
                -> new ReadOnlyStringWrapper(param.getValue().getValue().getTotal())
        );
        totalCol.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        totalCol.setOnEditCommit(new EventHandler<TreeTableColumn.CellEditEvent<Item, String>>() {
            @Override
            public void handle(TreeTableColumn.CellEditEvent<Item, String> event) {
                final Item item = event.getRowValue().getValue();
                Double valorMao = Double.parseDouble(itemMao.getMaoObra());
                Double valorMat = Double.parseDouble(itemMaterial.getMaterial());
                Double total = valorMao + valorMat;
                item.setTotal(total.toString());
                
            }
        });

        tabela.getColumns().setAll(itemCol, descCol, qtdCol, uniCol, materialCol, maoCol, totalCol);
        tabela.setTableMenuButtonVisible(true);

        tabela.setShowRoot(false);
        tabela.setEditable(true);

        tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tabela.getSelectionModel().setCellSelectionEnabled(true);
        tabela.setMaxWidth(950);

        ContextMenu addItem = new ContextMenu();
        tabela.setContextMenu(addItem);

        MenuItem addMenuItem = new MenuItem("Novo Item");
        addItem.getItems().add(addMenuItem);
        addMenuItem.setOnAction(new EventHandler() {
            @Override
            public void handle(Event t) {
                Item item = new Item("awef", "awfr");

                TreeItem<Item> novoItem = new TreeItem(item);
                tabela.getRoot().getChildren().add(novoItem);
                int j = 1;
                if (novoItem.getParent().equals(tabela.getRoot())) {
                    novoItem.setExpanded(true);

                    ObservableList<TreeItem<Item>> lista = FXCollections.observableArrayList(tabela.getRoot().getChildren());

                    if (lista.size() > 0) {
                        j = 0;
                        for (TreeItem<Item> i : lista) {
                            if (i.isExpanded()) {
                                j++;
                            }
                        }
                    }
                } else {
                    novoItem.setExpanded(false);
                }

                item.setItem(j + ".0");
                item.setDiscriminacao("Novo Item");
                novoItem.setValue(item);

            }
        });

        MenuItem addSubMenuItem = new MenuItem("Novo sub item");
        addItem.getItems().add(addSubMenuItem);
        addSubMenuItem.setVisible(false);

        MenuItem excluirItem = new MenuItem("Excluir");
        addItem.getItems().add(excluirItem);
        excluirItem.setVisible(false);

        tabela.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Integer index = tabela.getSelectionModel().getSelectedIndex();

                if (index == -1) {
                    addSubMenuItem.setVisible(false);
                } else {
                    excluirItem.setVisible(true);
                    if (tabela.getSelectionModel().getModelItem(index).isExpanded()) {
                        addSubMenuItem.setVisible(true);
                    } else {
                        addSubMenuItem.setVisible(false);
                    }
                }

                addSubMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Item item = new Item("awef", "awfr");
                        TreeItem<Item> novoItem = new TreeItem(item);
                        tabela.getTreeItem(index).getChildren().add(novoItem);

                        if (novoItem.getParent().equals(tabela.getRoot())) {
                            novoItem.setExpanded(true);
                        } else {
                            String n = novoItem.getParent().getValue().getItem().substring(0, 1);

                            ObservableList<TreeItem<Item>> lista = FXCollections.observableArrayList(novoItem.getParent().getChildren());

                            item.setItem(n + "." + lista.size());
                            item.setDiscriminacao("Novo Sub-Item");
                            novoItem.setValue(item);
                            novoItem.setExpanded(false);
                        }
                    }
                });

                excluirItem.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        
                        if(tabela.getSelectionModel().getModelItem(index).isExpanded()){
                            tabela.getRoot().getChildren().remove(tabela.getSelectionModel().getModelItem(index));
                        }else{
                            tabela.getSelectionModel().getModelItem(index).getParent().getChildren().remove(tabela.getSelectionModel().getModelItem(index));
                        }
                        
                        ordenaItensTabela(tabela.getRoot().getChildren());
                    }
                });
            }
        });

        Button salvar = new Button("Salvar");

        salvar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ObservableList<TreeItem<Item>> lista = FXCollections.observableArrayList(tabela.getRoot().getChildren());
                List<Item> listaNova = new ArrayList<>();

                for (TreeItem<Item> i : lista) {
                    listaNova.add(i.getValue());
                    ObservableList<TreeItem<Item>> listaT = FXCollections.observableArrayList(i.getChildren());
                    for (TreeItem<Item> j : listaT) {
                        listaNova.add(j.getValue());
                    }
                }
                System.out.println(listaNova);
            }
        });

        this.getChildren().addAll(tabela, salvar);
    }

    @Override
    public void ordenaItensTabela(ObservableList<TreeItem<Item>> lista) {
        
        int i = 1;
        
        for(TreeItem<Item> ti : lista){
            if(ti.isExpanded()){
                ti.getValue().setItem(i+".0");
                int j=1;
                for(TreeItem<Item> h : ti.getChildren()){
                    h.getValue().setItem(i+"."+j);
                    j++;
                }
            }
            i++;
        }
        
    }
}
