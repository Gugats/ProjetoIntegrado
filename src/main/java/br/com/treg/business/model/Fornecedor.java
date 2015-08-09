/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.business.model;

import java.io.Serializable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "fornecedor")
@XmlRootElement
@Access(AccessType.FIELD)
@NamedQueries({
    @NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f"),
    @NamedQuery(name = "Fornecedor.findById", query = "SELECT f FROM Fornecedor f WHERE f.id = :id"),
    @NamedQuery(name = "Fornecedor.findByCnpj", query = "SELECT f FROM Fornecedor f WHERE f.cnpj = :cnpj"),
    @NamedQuery(name = "Fornecedor.findByNome", query = "SELECT f FROM Fornecedor f WHERE f.nome = :nome"),
    @NamedQuery(name = "Fornecedor.findByEndereco", query = "SELECT f FROM Fornecedor f WHERE f.endereco = :endereco")})
public class Fornecedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private IntegerProperty id = new SimpleIntegerProperty();
    
    @Column(name = "cnpj")
    private StringProperty cnpj = new SimpleStringProperty();
    @Column(name = "nome")
    private StringProperty nome = new SimpleStringProperty();
    @Column(name = "endereco")
    private StringProperty endereco = new SimpleStringProperty();

    public Fornecedor() {
    }

    //Getters and Setters for ID
    @Access(AccessType.PROPERTY)
    public final Integer getId() {
        return this.id.get();
    }
    public void setId(Integer id) {
        this.id.set(id);
    }
    public IntegerProperty idProperty(){
        return this.id;
    }
    
    //Getters and Setters for CNPJ
    @Access(AccessType.PROPERTY)
    public final String getCnpj() {
        return this.cnpj.get();
    }
    public void setCnpj(String cnpj) {
        this.cnpj.set(cnpj);
    }
    public StringProperty cnpjProperty() {
        return this.cnpj;
    }

    //Getters and Setters for NOME
    @Access(AccessType.PROPERTY)
    public final String getNome() {
        return this.nome.get();
    }
    public void setNome(String nome) {
        this.nome.set(nome);
    }
    public StringProperty nomeProperty() {
        return this.nome;
    }

    //Getters and Setters for ENDEREÇO
    @Access(AccessType.PROPERTY)
    public final String getEndereco() {
        return this.nome.get();
    }
    public void setEndereco(String endereco) {
        this.endereco.set(endereco);
    }
    public StringProperty enderecoProperty() {
        return this.endereco;
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
        if (!(object instanceof Fornecedor)) {
            return false;
        }
        Fornecedor other = (Fornecedor) object;
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
