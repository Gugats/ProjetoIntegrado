/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.treg.business.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 350)
    @Column(name = "discriminacao")
    private String discriminacao;
    @Column(name = "data_emissao")
    @Temporal(TemporalType.DATE)
    private Date dataEmissao;
    @Size(max = 50)
    @Column(name = "forma_pagamento")
    private String formaPagamento;
    @Size(max = 50)
    @Column(name = "tipo_nota")
    private String tipoNota;

    public NotaFiscal() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiscriminacao() {
        return discriminacao;
    }

    public void setDiscriminacao(String discriminacao) {
        this.discriminacao = discriminacao;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(String tipoNota) {
        this.tipoNota = tipoNota;
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
        return "br.com.treg.business.model.NotaFiscal[ id=" + id + " ]";
    }
    
}
