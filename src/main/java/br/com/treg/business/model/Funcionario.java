/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.business.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "funcionario")
@NamedQueries({
    @NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM Funcionario f")})
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private IntegerProperty id;
    private StringProperty cpf;
    private StringProperty nome;
    private StringProperty endereco;
    private StringProperty funcao;
    private BooleanProperty ativo;
    private DoubleProperty diaria;
    private List<ObraFuncionario> obrasFuncionarios;
    private StringProperty telefone;

    public Funcionario() {
        id = new SimpleIntegerProperty();
        cpf = new SimpleStringProperty();
        nome = new SimpleStringProperty();
        endereco = new SimpleStringProperty();
        funcao = new SimpleStringProperty();
        ativo = new SimpleBooleanProperty();
        telefone = new SimpleStringProperty();
        diaria = new SimpleDoubleProperty();
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
    
    @Size(max = 15)
    @Column(name = "cpf")
    public final String getCpf() {
        return cpfProperty().get();
    }
    public void setCpf(String cpf) {
        cpfProperty().set(cpf);
    }
    public StringProperty cpfProperty(){
        return this.cpf;
    }
    
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nome")
    public String getNome() {
        return nomeProperty().get();
    }
    public void setNome(String nome) {
        nomeProperty().set(nome);
    }
    public StringProperty nomeProperty(){
        return this.nome;
    }
    
    @Size(max = 150)
    @Column(name = "endereco")
    public String getEndereco() {
        return enderecoProperty().get();
    }
    public void setEndereco(String endereco) {
        enderecoProperty().set(endereco);
    }
    public StringProperty enderecoProperty(){
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
    
    @Column(name = "valor_diaria")
    public final Double getDiaria(){
        return diariaProperty().get();
    }
    public void setDiaria(Double diaria){
        diariaProperty().set(diaria);
    }
    public DoubleProperty diariaProperty(){
        return this.diaria;
    }
    
    @Size(max = 75)
    @Column(name = "funcao")
    public String getFuncao() {
        return funcaoProperty().get();
    }
    public void setFuncao(String funcao) {
        funcaoProperty().set(funcao);
    }
    public StringProperty funcaoProperty(){
        return this.funcao;
    }
    
    @Column(name = "ativo")
    public Boolean getAtivo() {
        return ativoProperty().get();
    }
    public void setAtivo(Boolean ativo) {
        ativoProperty().set(ativo);
    }
    public BooleanProperty ativoProperty(){
        return this.ativo;
    }
    

    @OneToMany(mappedBy="funcionario")
    public List<ObraFuncionario> getObrasFuncionarios() {
        return obrasFuncionarios;
    }

    public void setObrasFuncionarios(List<ObraFuncionario> obrasFuncionarios) {
        this.obrasFuncionarios = obrasFuncionarios;
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
        if (!(object instanceof Funcionario)) {
            return false;
        }
        Funcionario other = (Funcionario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome.get();
    }

}
