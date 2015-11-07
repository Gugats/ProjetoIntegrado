/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.business.model;

import java.io.Serializable;
import java.util.Date;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gustavo
 */
@Entity
@Table(name = "boleto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Boleto.findAll", query = "SELECT b FROM Boleto b"),
    @NamedQuery(name = "Boleto.findById", query = "SELECT b FROM Boleto b WHERE b.id = :id"),
    @NamedQuery(name = "Boleto.findByDataEmissao", query = "SELECT b FROM Boleto b WHERE b.dataEmissao = :dataEmissao"),
    @NamedQuery(name = "Boleto.findByDataVencimento", query = "SELECT b FROM Boleto b WHERE b.dataVencimento = :dataVencimento"),
    @NamedQuery(name = "Boleto.findByParcela", query = "SELECT b FROM Boleto b WHERE b.parcela = :parcela")})
public class Boleto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private IntegerProperty id;
    private NotaFiscal notaFiscal;
    private Date dataEmissao;
    private Date dataVencimento;
    private Date dataPagamento;
    private BooleanProperty pago;
    private IntegerProperty parcela;
    private DoubleProperty valor;

    public Boleto() {
        id = new SimpleIntegerProperty();
        pago = new SimpleBooleanProperty();
        parcela = new SimpleIntegerProperty();
        valor = new SimpleDoubleProperty();
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
    
    @Column(name = "data_emissao")
    @Temporal(TemporalType.DATE)
    public Date getDataEmissao() {
        return dataEmissao;
    }
    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    @Column(name = "data_vencimento")
    @Temporal(TemporalType.DATE)
    public Date getDataVencimento() {
        return dataVencimento;
    }
    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    @Column(name = "data_pagamento")
    @Temporal(TemporalType.DATE)
    public Date getDataPagamento() {
        return dataPagamento;
    }
    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    
    @Column(name = "parcela")
    public final Integer getParcela() {
        return parcelaProperty().get();
    }
    public void setParcela(Integer parcela) {
        parcelaProperty().set(parcela);
    }
    public IntegerProperty parcelaProperty(){
        return this.parcela;
    }
    
    @Column(name = "valor")
    public final Double getValor(){
        return valorProperty().get();
    }
    public void setValor(Double valor){
        valorProperty().set(valor);
    }
    public DoubleProperty valorProperty(){
        return this.valor;
    }
    
    @Column(name = "pago")
    public final Boolean getPago(){
        return pagoProperty().get();
    }
    public void setPago(Boolean pago){
        pagoProperty().set(pago);
    }
    public BooleanProperty pagoProperty(){
        return this.pago;
    }

    @ManyToOne
    @JoinColumn(name = "nota_fiscal")
    public NotaFiscal getNotaFiscal() {
        return notaFiscal;
    }
    public void setNotaFiscal(NotaFiscal notaFiscal) {
        this.notaFiscal = notaFiscal;
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
        if (!(object instanceof Boleto)) {
            return false;
        }
        Boleto other = (Boleto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Valor: R$ "+getValor() + " - Data Vencimento: " + getDataVencimento();
    }
    
}
