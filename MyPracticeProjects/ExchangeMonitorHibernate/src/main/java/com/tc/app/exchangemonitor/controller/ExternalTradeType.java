/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.controller;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "external_trade_type", catalog = "QA_30_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExternalTradeType.findAll", query = "SELECT e FROM ExternalTradeType e"),
    @NamedQuery(name = "ExternalTradeType.findByOid", query = "SELECT e FROM ExternalTradeType e WHERE e.oid = :oid"),
    @NamedQuery(name = "ExternalTradeType.findByTradeTypeCode", query = "SELECT e FROM ExternalTradeType e WHERE e.tradeTypeCode = :tradeTypeCode"),
    @NamedQuery(name = "ExternalTradeType.findByTradeTypeName", query = "SELECT e FROM ExternalTradeType e WHERE e.tradeTypeName = :tradeTypeName"),
    @NamedQuery(name = "ExternalTradeType.findByTransId", query = "SELECT e FROM ExternalTradeType e WHERE e.transId = :transId")})
public class ExternalTradeType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "oid")
    private Integer oid;
    @Basic(optional = false)
    @Column(name = "trade_type_code")
    private String tradeTypeCode;
    @Basic(optional = false)
    @Column(name = "trade_type_name")
    private String tradeTypeName;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @JoinColumn(name = "external_trade_source_oid", referencedColumnName = "oid")
    @ManyToOne(optional = false)
    private ExternalTradeSource externalTradeSourceOid;

    public ExternalTradeType() {
    }

    public ExternalTradeType(Integer oid) {
        this.oid = oid;
    }

    public ExternalTradeType(Integer oid, String tradeTypeCode, String tradeTypeName, int transId) {
        this.oid = oid;
        this.tradeTypeCode = tradeTypeCode;
        this.tradeTypeName = tradeTypeName;
        this.transId = transId;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getTradeTypeCode() {
        return tradeTypeCode;
    }

    public void setTradeTypeCode(String tradeTypeCode) {
        this.tradeTypeCode = tradeTypeCode;
    }

    public String getTradeTypeName() {
        return tradeTypeName;
    }

    public void setTradeTypeName(String tradeTypeName) {
        this.tradeTypeName = tradeTypeName;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public ExternalTradeSource getExternalTradeSourceOid() {
        return externalTradeSourceOid;
    }

    public void setExternalTradeSourceOid(ExternalTradeSource externalTradeSourceOid) {
        this.externalTradeSourceOid = externalTradeSourceOid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (oid != null ? oid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExternalTradeType)) {
            return false;
        }
        ExternalTradeType other = (ExternalTradeType) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExternalTradeType[ oid=" + oid + " ]";
    }
    
}
