/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "external_trade_status", catalog = "QA_30_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExternalTradeStatus.findAll", query = "SELECT e FROM ExternalTradeStatus e"),
    @NamedQuery(name = "ExternalTradeStatus.findByOid", query = "SELECT e FROM ExternalTradeStatus e WHERE e.oid = :oid"),
    @NamedQuery(name = "ExternalTradeStatus.findByExternalTradeStatusName", query = "SELECT e FROM ExternalTradeStatus e WHERE e.externalTradeStatusName = :externalTradeStatusName"),
    @NamedQuery(name = "ExternalTradeStatus.findByTransId", query = "SELECT e FROM ExternalTradeStatus e WHERE e.transId = :transId")})
public class ExternalTradeStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "oid")
    private Integer oid;
    @Basic(optional = false)
    @Column(name = "external_trade_status_name")
    private String externalTradeStatusName;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "externalTradeStatusOid")
    private Collection<ExternalTrade> externalTradeCollection;

    public ExternalTradeStatus() {
    }

    public ExternalTradeStatus(Integer oid) {
        this.oid = oid;
    }

    public ExternalTradeStatus(Integer oid, String externalTradeStatusName, int transId) {
        this.oid = oid;
        this.externalTradeStatusName = externalTradeStatusName;
        this.transId = transId;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getExternalTradeStatusName() {
        return externalTradeStatusName;
    }

    public void setExternalTradeStatusName(String externalTradeStatusName) {
        this.externalTradeStatusName = externalTradeStatusName;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<ExternalTrade> getExternalTradeCollection() {
        return externalTradeCollection;
    }

    public void setExternalTradeCollection(Collection<ExternalTrade> externalTradeCollection) {
        this.externalTradeCollection = externalTradeCollection;
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
        if (!(object instanceof ExternalTradeStatus)) {
            return false;
        }
        ExternalTradeStatus other = (ExternalTradeStatus) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExternalTradeStatus[ oid=" + oid + " ]";
    }
    
}
