/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.controller;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Saravana Kumar M
 */
@Embeddable
public class TradeOrderPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "trade_num")
    private int tradeNum;
    @Basic(optional = false)
    @Column(name = "order_num")
    private short orderNum;

    public TradeOrderPK() {
    }

    public TradeOrderPK(int tradeNum, short orderNum) {
        this.tradeNum = tradeNum;
        this.orderNum = orderNum;
    }

    public int getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(int tradeNum) {
        this.tradeNum = tradeNum;
    }

    public short getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(short orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tradeNum;
        hash += (int) orderNum;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TradeOrderPK)) {
            return false;
        }
        TradeOrderPK other = (TradeOrderPK) object;
        if (this.tradeNum != other.tradeNum) {
            return false;
        }
        if (this.orderNum != other.orderNum) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TradeOrderPK[ tradeNum=" + tradeNum + ", orderNum=" + orderNum + " ]";
    }
    
}
