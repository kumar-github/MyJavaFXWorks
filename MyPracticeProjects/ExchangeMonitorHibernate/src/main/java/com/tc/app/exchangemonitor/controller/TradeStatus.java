/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.controller;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "trade_status", catalog = "QA_30_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TradeStatus.findAll", query = "SELECT t FROM TradeStatus t"),
    @NamedQuery(name = "TradeStatus.findByTradeStatusCode", query = "SELECT t FROM TradeStatus t WHERE t.tradeStatusCode = :tradeStatusCode"),
    @NamedQuery(name = "TradeStatus.findByTradeStatusDesc", query = "SELECT t FROM TradeStatus t WHERE t.tradeStatusDesc = :tradeStatusDesc"),
    @NamedQuery(name = "TradeStatus.findByTransId", query = "SELECT t FROM TradeStatus t WHERE t.transId = :transId")})
public class TradeStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "trade_status_code", nullable = false, length = 8, columnDefinition="CHAR")
    private String tradeStatusCode;
    
    @Column(name = "trade_status_desc", length = 40)
    private String tradeStatusDesc;
    @Basic(optional = false)
    @Column(name = "trans_id", nullable = false)
    private int transId;
    
    @OneToMany(mappedBy = "tradeStatusCode", fetch = FetchType.LAZY)
    private Collection<Trade> tradeCollection;

    public TradeStatus() {
    }

    public TradeStatus(String tradeStatusCode) {
        this.tradeStatusCode = tradeStatusCode;
    }

    public TradeStatus(String tradeStatusCode, int transId) {
        this.tradeStatusCode = tradeStatusCode;
        this.transId = transId;
    }

    public String getTradeStatusCode() {
        return tradeStatusCode;
    }

    public void setTradeStatusCode(String tradeStatusCode) {
        this.tradeStatusCode = tradeStatusCode;
    }

    public String getTradeStatusDesc() {
        return tradeStatusDesc;
    }

    public void setTradeStatusDesc(String tradeStatusDesc) {
        this.tradeStatusDesc = tradeStatusDesc;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<Trade> getTradeCollection() {
        return tradeCollection;
    }

    public void setTradeCollection(Collection<Trade> tradeCollection) {
        this.tradeCollection = tradeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tradeStatusCode != null ? tradeStatusCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TradeStatus)) {
            return false;
        }
        TradeStatus other = (TradeStatus) object;
        if ((this.tradeStatusCode == null && other.tradeStatusCode != null) || (this.tradeStatusCode != null && !this.tradeStatusCode.equals(other.tradeStatusCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generated6.TradeStatus[ tradeStatusCode=" + tradeStatusCode + " ]";
    }
    
}
