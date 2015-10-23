/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.business.model;

import java.io.Serializable;
import java.util.Collection;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findById", query = "SELECT c FROM Cliente c WHERE c.id = :id"),
    @NamedQuery(name = "Cliente.findByNumdoc", query = "SELECT c FROM Cliente c WHERE c.numdoc = :numdoc"),
    @NamedQuery(name = "Cliente.findByNome", query = "SELECT c FROM Cliente c WHERE c.nome = :nome"),
    @NamedQuery(name = "Cliente.findByEndereco", query = "SELECT c FROM Cliente c WHERE c.endereco = :endereco"),
    @NamedQuery(name = "Cliente.findByTipocliente", query = "SELECT c FROM Cliente c WHERE c.tipoCliente = :tipoCliente")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private IntegerProperty id;
    private StringProperty numDoc;
    private StringProperty nome;
    private StringProperty endereco;
    private StringProperty tipoCliente;

    public Cliente() {
        id = new SimpleIntegerProperty();
        numDoc = new SimpleStringProperty();
        nome = new SimpleStringProperty();
        endereco = new SimpleStringProperty();
        tipoCliente = new SimpleStringProperty();
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
    
    @NotNull
    @Column(name = "numdoc")
    public String getNumdoc() {
        return numDocProperty().get();
    }
    public void setNumdoc(String numdoc) {
        numDocProperty().set(numdoc);
    }
    public StringProperty numDocProperty(){
        return this.numDoc;
    }

    @Column(name = "nome")
    public final String getNome() {
        return nomeProperty().get();
    }
    public void setNome(String nome) {
        nomeProperty().set(nome);
    }
    public StringProperty nomeProperty() {
        return this.nome;
    }
    
    @Column(name = "endereco")
    public final String getEndereco() {
        return enderecoProperty().get();
    }
    public void setEndereco(String endereco) {
        enderecoProperty().set(endereco);
    }
    public StringProperty enderecoProperty() {
        return this.endereco;
    }
    
    @Column(name = "tipocliente")
    public final String getTipoCliente() {
        return tipoClienteProperty().get();
    }
    public void setTipoCliente(String tipoCliente) {
        tipoClienteProperty().set(tipoCliente);
    }
    public StringProperty tipoClienteProperty() {
        return this.tipoCliente;
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
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getNome();
    }

    
}
