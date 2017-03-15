/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.tradecapture.controller;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Saravana Kumar M
 */
@Embeddable
public class TradeCommentPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "trade_num")
    private int tradeNum;
    @Basic(optional = false)
    @Column(name = "cmnt_num")
    private int cmntNum;

    public TradeCommentPK() {
    }

    public TradeCommentPK(int tradeNum, int cmntNum) {
        this.tradeNum = tradeNum;
        this.cmntNum = cmntNum;
    }

    public int getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(int tradeNum) {
        this.tradeNum = tradeNum;
    }

    public int getCmntNum() {
        return cmntNum;
    }

    public void setCmntNum(int cmntNum) {
        this.cmntNum = cmntNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tradeNum;
        hash += (int) cmntNum;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TradeCommentPK)) {
            return false;
        }
        TradeCommentPK other = (TradeCommentPK) object;
        if (this.tradeNum != other.tradeNum) {
            return false;
        }
        if (this.cmntNum != other.cmntNum) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TradeCommentPK[ tradeNum=" + tradeNum + ", cmntNum=" + cmntNum + " ]";
    }
    
}
