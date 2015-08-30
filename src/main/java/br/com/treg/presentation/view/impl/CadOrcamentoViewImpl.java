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

    private TreeTableView<Item> tabela;
    private List<Item> listaItem = new ArrayList<>();

    final TreeItem<Item> root = new TreeItem<>(new Item("Lista de Itens"));

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
                final Item item = event.getRowValue().getValue();
                item.setQtd(event.getNewValue());
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
                item.setUnidadeMedida(event.getNewValue());
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
                final Item item = event.getRowValue().getValue();
                item.setMaterial(event.getNewValue());
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
                final Item item = event.getRowValue().getValue();
                item.setMaoObra(event.getNewValue());
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
                item.setTotal(event.getNewValue());
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
                TreeItem<Item> novoItem
                        = new TreeItem(new Item("Novo Item"));
                tabela.getRoot().getChildren().add(novoItem);
                if (novoItem.getParent().equals(tabela.getRoot())) {
                    novoItem.setExpanded(true);
                } else {
                    novoItem.setExpanded(false);
                }
            }
        });

        MenuItem addSubMenuItem = new MenuItem("Novo sub item");
        addItem.getItems().add(addSubMenuItem);
        addSubMenuItem.setVisible(false);

        tabela.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Integer index = tabela.getSelectionModel().getSelectedIndex();

                if (index == -1) {
                    addSubMenuItem.setVisible(false);
                } else {
                    if (tabela.getSelectionModel().getModelItem(index).isExpanded()) {
                        addSubMenuItem.setVisible(true);
                    } else {
                        addSubMenuItem.setVisible(false);
                    }
                }

                addSubMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        TreeItem<Item> novoItem
                                = new TreeItem(new Item("Novo Sub-Item"));
                        tabela.getTreeItem(index).getChildren().add(novoItem);

                        if (novoItem.getParent().equals(tabela.getRoot())) {
                            novoItem.setExpanded(true);
                        } else {
                            novoItem.setExpanded(false);
                        }
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
                    for(TreeItem<Item> j : listaT){
                        listaNova.add(j.getValue());
                    }
                }
                System.out.println(listaNova);
            }
        });

        this.getChildren().addAll(tabela, salvar);
    }
}