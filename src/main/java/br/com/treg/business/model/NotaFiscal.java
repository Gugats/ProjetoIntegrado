/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.business.model;

import java.io.Serializable;
import java.util.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
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
@Table(name = "nota_fiscal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotaFiscal.findAll", query = "SELECT n FROM NotaFiscal n"),
    @NamedQuery(name = "NotaFiscal.findById", query = "SELECT n FROM NotaFiscal n WHERE n.id = :id"),
    @NamedQuery(name = "NotaFiscal.findByDiscriminacao", query = "SELECT n FROM NotaFiscal n WHERE n.discriminacao = :discriminacao"),
    @NamedQuery(name = "NotaFiscal.findByDataEmissao", query = "SELECT n FROM NotaFiscal n WHERE n.dataEmissao = :dataEmissao"),
    @NamedQuery(name = "NotaFiscal.findByFormaPagamento", query = "SELECT n FROM NotaFiscal n WHERE n.formaPagamento = :formaPagamento"),
    @NamedQuery(name = "NotaFiscal.findByTipoNota", query = "SELECT n FROM NotaFiscal n WHERE n.tipoNota = :tipoNota")})
public class NotaFiscal implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private IntegerProperty id;
    private StringProperty discriminacao;
    private Date dataEmissao;
    private StringProperty formaPagamento;
    private StringProperty tipoNota;
    private DoubleProperty valor;
    private Fornecedor fornecedor;
    private Obra obra;
    private IntegerProperty parcelas;

    public NotaFiscal() {
        id = new SimpleIntegerProperty();
        discriminacao = new SimpleStringProperty();
        formaPagamento = new SimpleStringProperty();
        tipoNota = new SimpleStringProperty();
        valor = new SimpleDoubleProperty();
        parcelas = new SimpleIntegerProperty();
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

    @Size(max = 350)
    @Column(name = "discriminacao")
    public String getDiscriminacao() {
        return discriminacaoProperty().get();
    }
    public void setDiscriminacao(String discriminacao) {
        discriminacaoProperty().set(discriminacao);
    }
    public StringProperty discriminacaoProperty(){
        return this.discriminacao;
    }

    @Column(name = "data_emissao")
    @Temporal(TemporalType.DATE)
    public Date getDataEmissao() {
        return dataEmissao;
    }
    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    @Size(max = 50)
    @Column(name = "forma_pagamento")
    public String getFormaPagamento() {
        return formaPagamentoProperty().get();
    }
    public void setFormaPagamento(String formaPagamento) {
        formaPagamentoProperty().set(formaPagamento);
    }
    public StringProperty formaPagamentoProperty(){
        return this.formaPagamento;
    }

    @Size(max = 50)
    @Column(name = "tipo_nota")
    public String getTipoNota() {
        return tipoNotaProperty().get();
    }
    public void setTipoNota(String tipoNota) {
        tipoNotaProperty().set(tipoNota);
    }
    public StringProperty tipoNotaProperty(){
        return this.tipoNota;
    }
    
    @Column(name = "valor")
    public Double getValor(){
        return valorProperty().get();
    }
    public void setValor(Double valor){
        valorProperty().set(valor);
    }
    public DoubleProperty valorProperty(){
        return this.valor;
    }

    @ManyToOne
    @JoinColumn(name = "fornecedor")
    public Fornecedor getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @ManyToOne
    @JoinColumn(name = "obra")
    public Obra getObra() {
        return obra;
    }
    public void setObra(Obra obra) {
        this.obra = obra;
    }
    
    @Column(name = "parcelas")
    public final Integer getParcelas() {
        return parcelasProperty().get();
    }
    public void setParcelas(Integer parcelas) {
        parcelasProperty().set(parcelas);
    }
    public IntegerProperty parcelasProperty(){
        return this.parcelas;
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
        if (!(object instanceof NotaFiscal)) {
            return false;
        }
        NotaFiscal other = (NotaFiscal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return obra +" - "+ fornecedor;
    }
    
}
