/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.business.model;

import java.io.Serializable;
import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "recibo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recibo.findAll", query = "SELECT r FROM Recibo r"),
    @NamedQuery(name = "Recibo.findById", query = "SELECT r FROM Recibo r WHERE r.id = :id"),
    @NamedQuery(name = "Recibo.findByDescricao", query = "SELECT r FROM Recibo r WHERE r.descricao = :descricao"),
    @NamedQuery(name = "Recibo.findByDataEmissao", query = "SELECT r FROM Recibo r WHERE r.dataEmissao = :dataEmissao")})
public class Recibo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private IntegerProperty id;
    private StringProperty descricao;
    private Date dataEmissao;
    private ObraFuncionario obraFuncionario;

    public Recibo() {
        id = new SimpleIntegerProperty();
        descricao = new SimpleStringProperty();
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
    
    
    @Column(name = "descricao")
    public String getDescricao(){
        return descricaoProperty().get();
    }
    public void setDescricao(String descricao){
        descricaoProperty().set(descricao);
    }
    public StringProperty descricaoProperty(){
        return this.descricao;
    }
    
    @Column(name = "data_emissao")
    @Temporal(TemporalType.DATE)
    public Date getDataEmissao() {
        return dataEmissao;
    }
    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    @ManyToOne
    @JoinColumn(name = "obra_funcionario")
    public ObraFuncionario getObraFuncionario() {
        return obraFuncionario;
    }
    public void setObraFuncionario(ObraFuncionario obraFuncionario) {
        this.obraFuncionario = obraFuncionario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recibo)) {
            return false;
        }
        Recibo other = (Recibo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.treg.business.model.Recibo[ id=" + id + " ]";
    }
    
}
