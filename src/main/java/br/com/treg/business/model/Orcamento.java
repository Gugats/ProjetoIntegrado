/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.business.model;

import java.io.Serializable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "orcamento")
public class Orcamento implements Serializable {
    private static final long serialVersionUID = 1L;

    private IntegerProperty id;
    private Obra obra;
    
    public Orcamento() {
        id = new SimpleIntegerProperty();
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public final Integer getId() {
        return idProperty().get();
    }
    public void setId(Integer id) {
        idProperty().set(id);
    }
    public IntegerProperty idProperty(){
        return this.id;
    }

    @ManyToOne
    @JoinColumn(name = "obra")
    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }
    
    
}
