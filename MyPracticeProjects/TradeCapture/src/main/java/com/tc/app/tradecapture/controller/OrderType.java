/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.tradecapture.controller;

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
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "order_type", catalog = "DEV_31_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderType.findAll", query = "SELECT o FROM OrderType o"),
    @NamedQuery(name = "OrderType.findByOrderTypeCode", query = "SELECT o FROM OrderType o WHERE o.orderTypeCode = :orderTypeCode"),
    @NamedQuery(name = "OrderType.findByOrderTypeDesc", query = "SELECT o FROM OrderType o WHERE o.orderTypeDesc = :orderTypeDesc"),
    @NamedQuery(name = "OrderType.findByTransId", query = "SELECT o FROM OrderType o WHERE o.transId = :transId")})
public class OrderType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "order_type_code")
    private String orderTypeCode;
    @Basic(optional = false)
    @Column(name = "order_type_desc")
    private String orderTypeDesc;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @OneToMany(mappedBy = "orderTypeCode", fetch = FetchType.LAZY)
    private Collection<TradeOrder> tradeOrderCollection;

    public OrderType() {
    }

    public OrderType(String orderTypeCode) {
        this.orderTypeCode = orderTypeCode;
    }

    public OrderType(String orderTypeCode, String orderTypeDesc, int transId) {
        this.orderTypeCode = orderTypeCode;
        this.orderTypeDesc = orderTypeDesc;
        this.transId = transId;
    }

    public String getOrderTypeCode() {
        return orderTypeCode;
    }

    public void setOrderTypeCode(String orderTypeCode) {
        this.orderTypeCode = orderTypeCode;
    }

    public String getOrderTypeDesc() {
        return orderTypeDesc;
    }

    public void setOrderTypeDesc(String orderTypeDesc) {
        this.orderTypeDesc = orderTypeDesc;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    @XmlTransient
    public Collection<TradeOrder> getTradeOrderCollection() {
        return tradeOrderCollection;
    }

    public void setTradeOrderCollection(Collection<TradeOrder> tradeOrderCollection) {
        this.tradeOrderCollection = tradeOrderCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderTypeCode != null ? orderTypeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderType)) {
            return false;
        }
        OrderType other = (OrderType) object;
        if ((this.orderTypeCode == null && other.orderTypeCode != null) || (this.orderTypeCode != null && !this.orderTypeCode.equals(other.orderTypeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrderType[ orderTypeCode=" + orderTypeCode + " ]";
    }
    
}
