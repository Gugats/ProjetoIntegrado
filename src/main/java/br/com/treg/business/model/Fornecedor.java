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
@NamedQueries({
    @NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f"),
    @NamedQuery(name = "Fornecedor.findById", query = "SELECT f FROM Fornecedor f WHERE f.id = :id"),
    @NamedQuery(name = "Fornecedor.findByCnpj", query = "SELECT f FROM Fornecedor f WHERE f.cnpj = :cnpj"),
    @NamedQuery(name = "Fornecedor.findByNome", query = "SELECT f FROM Fornecedor f WHERE f.nome = :nome"),
    @NamedQuery(name = "Fornecedor.findByEndereco", query = "SELECT f FROM Fornecedor f WHERE f.endereco = :endereco")})
public class Fornecedor implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private IntegerProperty id;
    private StringProperty cnpj;
    private StringProperty nome;
    private StringProperty endereco;
    private StringProperty email;
    private StringProperty telefone;

    public Fornecedor() {
        id = new SimpleIntegerProperty();
        cnpj = new SimpleStringProperty();
        nome = new SimpleStringProperty();
        endereco = new SimpleStringProperty();
        email = new SimpleStringProperty();
        telefone = new SimpleStringProperty();
    }

    //Getters and Setters for ID
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
    
    //Getters and Setters for CNPJ
    @Column(name = "cnpj")
    public final String getCnpj() {
        return cnpjProperty().get();
    }
    public void setCnpj(String cnpj) {
        cnpjProperty().set(cnpj);
    }
    public StringProperty cnpjProperty() {
        return this.cnpj;
    }

    //Getters and Setters for NOME
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

    //Getters and Setters for ENDEREÇO
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
    
    @Column(name = "telefone")
    public final String getTelefone() {
        return telefoneProperty().get();
    }
    public void setTelefone(String telefone) {
        telefoneProperty().set(telefone);
    }
    public StringProperty telefoneProperty() {
        return this.telefone;
    }
    
    @Column(name = "email")
    public final String getEmail() {
        return emailProperty().get();
    }
    public void setEmail(String email) {
        emailProperty().set(email);
    }
    public StringProperty emailProperty() {
        return this.email;
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
