/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.tradecapture.controller;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "trade_sync", catalog = "DEV_31_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TradeSync.findAll", query = "SELECT t FROM TradeSync t"),
    @NamedQuery(name = "TradeSync.findByTradeNum", query = "SELECT t FROM TradeSync t WHERE t.tradeNum = :tradeNum"),
    @NamedQuery(name = "TradeSync.findByTradeSyncInds", query = "SELECT t FROM TradeSync t WHERE t.tradeSyncInds = :tradeSyncInds"),
    @NamedQuery(name = "TradeSync.findByTransId", query = "SELECT t FROM TradeSync t WHERE t.transId = :transId")})
public class TradeSync implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "trade_num")
    private Integer tradeNum;
    @Basic(optional = false)
    @Column(name = "trade_sync_inds")
    private String tradeSyncInds;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;

    public TradeSync() {
    }

    public TradeSync(Integer tradeNum) {
        this.tradeNum = tradeNum;
    }

    public TradeSync(Integer tradeNum, String tradeSyncInds, int transId) {
        this.tradeNum = tradeNum;
        this.tradeSyncInds = tradeSyncInds;
        this.transId = transId;
    }

    public Integer getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(Integer tradeNum) {
        this.tradeNum = tradeNum;
    }

    public String getTradeSyncInds() {
        return tradeSyncInds;
    }

    public void setTradeSyncInds(String tradeSyncInds) {
        this.tradeSyncInds = tradeSyncInds;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tradeNum != null ? tradeNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TradeSync)) {
            return false;
        }
        TradeSync other = (TradeSync) object;
        if ((this.tradeNum == null && other.tradeNum != null) || (this.tradeNum != null && !this.tradeNum.equals(other.tradeNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TradeSync[ tradeNum=" + tradeNum + " ]";
    }
    
}
