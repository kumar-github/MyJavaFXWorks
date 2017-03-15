/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tc.app.tradecapture.controller;

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
 * @author smurugabushanam
 */
@Entity
@Table(name = "external_trade_system", catalog = "DEV_31_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExternalTradeSystem.findAll", query = "SELECT e FROM ExternalTradeSystem e"),
    @NamedQuery(name = "ExternalTradeSystem.findByOid", query = "SELECT e FROM ExternalTradeSystem e WHERE e.oid = :oid"),
    @NamedQuery(name = "ExternalTradeSystem.findByExternalTradeSystemName", query = "SELECT e FROM ExternalTradeSystem e WHERE e.externalTradeSystemName = :externalTradeSystemName"),
    @NamedQuery(name = "ExternalTradeSystem.findByTransId", query = "SELECT e FROM ExternalTradeSystem e WHERE e.transId = :transId")})
public class ExternalTradeSystem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "oid")
    private Integer oid;
    @Basic(optional = false)
    @Column(name = "external_trade_system_name")
    private String externalTradeSystemName;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "externalTradeSystemOid")
    private Collection<ExternalTradeSource> externalTradeSourceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "externalTradeSystemOid")
    private Collection<ExternalTrade> externalTradeCollection;

    public ExternalTradeSystem() {
    }

    public ExternalTradeSystem(Integer oid) {
        this.oid = oid;
    }

    public ExternalTradeSystem(Integer oid, String externalTradeSystemName, int transId) {
        this.oid = oid;
        this.externalTradeSystemName = externalTradeSystemName;
        this.transId = transId;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public String getExternalTradeSystemName() {
        return externalTradeSystemName;
    }

    public void setExternalTradeSystemName(String externalTradeSystemName) {
        this.externalTradeSystemName = externalTradeSystemName;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<ExternalTradeSource> getExternalTradeSourceCollection() {
        return externalTradeSourceCollection;
    }

    public void setExternalTradeSourceCollection(Collection<ExternalTradeSource> externalTradeSourceCollection) {
        this.externalTradeSourceCollection = externalTradeSourceCollection;
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
        if (!(object instanceof ExternalTradeSystem)) {
            return false;
        }
        ExternalTradeSystem other = (ExternalTradeSystem) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generated3.ExternalTradeSystem[ oid=" + oid + " ]";
    }
    
}
