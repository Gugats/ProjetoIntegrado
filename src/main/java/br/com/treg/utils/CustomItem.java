/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.utils;

import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author Gustavo
 */
public class CustomItem extends HBox{
    
    private Label label;
    private ColorPicker colorPicker;

    public CustomItem(Label txt) {
        super();

        this.label = txt;
        this.label.setId("labelTree");
        this.getChildren().add(label);
        this.setAlignment(Pos.CENTER_LEFT);
    }
    
    public CustomItem(Label txt, ColorPicker cp) {
        super(5);

        this.label = txt;
        this.label.setId("labelTree");
        this.colorPicker = cp;

        this.getChildren().addAll(label, colorPicker);
        this.setAlignment(Pos.CENTER_LEFT);
    }
    
}
