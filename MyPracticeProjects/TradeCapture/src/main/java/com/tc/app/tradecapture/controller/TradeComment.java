/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.controller;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "trade_comment", catalog = "DEV_CS25_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TradeComment.findAll", query = "SELECT t FROM TradeComment t"),
    @NamedQuery(name = "TradeComment.findByTradeNum", query = "SELECT t FROM TradeComment t WHERE t.tradeCommentPK.tradeNum = :tradeNum"),
    @NamedQuery(name = "TradeComment.findByCmntNum", query = "SELECT t FROM TradeComment t WHERE t.tradeCommentPK.cmntNum = :cmntNum"),
    @NamedQuery(name = "TradeComment.findByTradeCmntType", query = "SELECT t FROM TradeComment t WHERE t.tradeCmntType = :tradeCmntType"),
    @NamedQuery(name = "TradeComment.findByTransId", query = "SELECT t FROM TradeComment t WHERE t.transId = :transId")})
public class TradeComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TradeCommentPK tradeCommentPK;
    @Column(name = "trade_cmnt_type")
    private Character tradeCmntType;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;

    public TradeComment() {
    }

    public TradeComment(TradeCommentPK tradeCommentPK) {
        this.tradeCommentPK = tradeCommentPK;
    }

    public TradeComment(TradeCommentPK tradeCommentPK, int transId) {
        this.tradeCommentPK = tradeCommentPK;
        this.transId = transId;
    }

    public TradeComment(int tradeNum, int cmntNum) {
        this.tradeCommentPK = new TradeCommentPK(tradeNum, cmntNum);
    }

    public TradeCommentPK getTradeCommentPK() {
        return tradeCommentPK;
    }

    public void setTradeCommentPK(TradeCommentPK tradeCommentPK) {
        this.tradeCommentPK = tradeCommentPK;
    }

    public Character getTradeCmntType() {
        return tradeCmntType;
    }

    public void setTradeCmntType(Character tradeCmntType) {
        this.tradeCmntType = tradeCmntType;
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
        hash += (tradeCommentPK != null ? tradeCommentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TradeComment)) {
            return false;
        }
        TradeComment other = (TradeComment) object;
        if ((this.tradeCommentPK == null && other.tradeCommentPK != null) || (this.tradeCommentPK != null && !this.tradeCommentPK.equals(other.tradeCommentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TradeComment[ tradeCommentPK=" + tradeCommentPK + " ]";
    }
    
}
