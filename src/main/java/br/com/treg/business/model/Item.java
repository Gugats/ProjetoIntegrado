/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.business.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Gustavo
 */
public class Item {
    
    StringProperty item, discriminacao, qtd, unidadeMedida, material, maoObra, total;

    public Item(String dsc) {
        
        discriminacao = new SimpleStringProperty(dsc);
        item = new SimpleStringProperty();
        qtd = new SimpleStringProperty();
        unidadeMedida = new SimpleStringProperty();
        material = new SimpleStringProperty();
        maoObra = new SimpleStringProperty();
        total = new SimpleStringProperty();
        
    }

    public String getItem() {
        return item.get();
    }
    public void setItem(String item) {
        this.item.set(item);
    }
    public StringProperty itemProperty(){
        return this.item;
    }

    public String getDiscriminacao() {
        return discriminacao.get();
    }
    public void setDiscriminacao(String discriminacao) {
        this.discriminacao.set(discriminacao);
    }
    public StringProperty discriminacaoProperty(){
        return this.discriminacao;
    }

    public String getQtd() {
        return qtd.get();
    }
    public void setQtd(String qtd) {
        this.qtd.set(qtd);
    }
    public StringProperty qtdProperty(){
        return this.qtd;
    }

    public String getUnidadeMedida() {
        return unidadeMedida.get();
    }
    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida.set(unidadeMedida);
    }
    public StringProperty unidadeMedidaProperty(){
        return this.unidadeMedida;
    }

    public String getMaterial() {
        return material.get();
    }
    public void setMaterial(String material) {
        this.material.set(material);
    }
    public StringProperty materialProperty(){
        return this.material;
    }

    public String getMaoObra() {
        return maoObra.get();
    }
    public void setMaoObra(String maoObra) {
        this.maoObra.set(maoObra);
    }
    public StringProperty maoObraProperty(){
        return this.maoObra;
    }

    public String getTotal() {
        return total.get();
    }
    public void setTotal(String total) {
        this.total.set(total);
    }
    public StringProperty totalProperty(){
        return this.total;
    }

    @Override
    public String toString() {
        return "Item: "+item+", Descrição: "+discriminacao;
    }
    
    
    
}

