/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.business.model;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "obra")
@NamedQueries({
    @NamedQuery(name = "Obra.findAll", query = "SELECT o FROM Obra o")})
public class Obra implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private IntegerProperty id;
    private StringProperty endereco;
    private StringProperty descricao;
    private DoubleProperty custoFinal;
    private DoubleProperty custoInicial;
    private BooleanProperty ativo;
    private StringProperty status;
    private List<Orcamento> orcamentos;
    private List<ObraFuncionario> obrasFuncionarios;
    private Cliente cliente;

    public Obra() {
        id = new SimpleIntegerProperty();
        endereco = new SimpleStringProperty();
        custoFinal = new SimpleDoubleProperty();
        custoInicial = new SimpleDoubleProperty();
        ativo = new SimpleBooleanProperty();
        status = new SimpleStringProperty();
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
    
    @Column(name = "custo_final")
    public Double getCustoFinal(){
        return custoFinalProperty().get();
    }
    public void setCustoFinal(Double custoFinal) {
        custoFinalProperty().set(custoFinal);
    }
    public DoubleProperty custoFinalProperty(){
        return this.custoFinal;
    }
    
    @Column(name = "custo_inicial")
    public Double getCustoInicial() {
        return custoInicialProperty().get();
    }
    public void setCustoInicial(Double custoInicial) {
        custoInicialProperty().set(custoInicial);
    }
    public DoubleProperty custoInicialProperty(){
        return this.custoInicial;
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
    
    @Column(name = "status")
    public String getStatus() {
        return statusProperty().get();
    }
    public void setStatus(String status) {
        statusProperty().set(status);
    }
    public StringProperty statusProperty(){
        return this.status;
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

    @OneToMany(mappedBy="obra", targetEntity = Orcamento.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Orcamento> getOrcamentos() {
        return orcamentos;
    }
    public void setOrcamentos(List<Orcamento> orcamentos) {
        this.orcamentos = orcamentos;
    }
    public void addOrcamento(Orcamento o){
        if(this.orcamentos==null){
            this.orcamentos = new ArrayList<>();
        }
        this.orcamentos.add(o);
    }
    public void removeOrcamento(Orcamento o){
        if(!this.orcamentos.isEmpty())
            this.orcamentos.remove(o);
    }

    @OneToMany(mappedBy="obra", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    public List<ObraFuncionario> getObrasFuncionarios() {
        return obrasFuncionarios;
    }
    public void setObrasFuncionarios(List<ObraFuncionario> obrasFuncionarios) {
        this.obrasFuncionarios = obrasFuncionarios;
    }
    public void addObraFuncionario(ObraFuncionario o){
        if(this.obrasFuncionarios==null){
            this.obrasFuncionarios = new ArrayList<>();
        }
        this.obrasFuncionarios.add(o);
    }

    @ManyToOne
    @JoinColumn(name = "cliente")
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
        if (!(object instanceof Obra)) {
            return false;
        }
        Obra other = (Obra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getDescricao();
    }
    
}
