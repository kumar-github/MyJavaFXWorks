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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "order_instruction", catalog = "DEV_CS25_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderInstruction.findAll", query = "SELECT o FROM OrderInstruction o"),
    @NamedQuery(name = "OrderInstruction.findByOrderInstrCode", query = "SELECT o FROM OrderInstruction o WHERE o.orderInstrCode = :orderInstrCode"),
    @NamedQuery(name = "OrderInstruction.findByOrderInstrDesc", query = "SELECT o FROM OrderInstruction o WHERE o.orderInstrDesc = :orderInstrDesc"),
    @NamedQuery(name = "OrderInstruction.findByTransId", query = "SELECT o FROM OrderInstruction o WHERE o.transId = :transId")})
public class OrderInstruction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "order_instr_code", columnDefinition="CHAR")
    private String orderInstrCode;
    
    @Basic(optional = false)
    @Column(name = "order_instr_desc")
    private String orderInstrDesc;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;

    public OrderInstruction() {
    }

    public OrderInstruction(String orderInstrCode) {
        this.orderInstrCode = orderInstrCode;
    }

    public OrderInstruction(String orderInstrCode, String orderInstrDesc, int transId) {
        this.orderInstrCode = orderInstrCode;
        this.orderInstrDesc = orderInstrDesc;
        this.transId = transId;
    }

    public String getOrderInstrCode() {
        return orderInstrCode;
    }

    public void setOrderInstrCode(String orderInstrCode) {
        this.orderInstrCode = orderInstrCode;
    }

    public String getOrderInstrDesc() {
        return orderInstrDesc;
    }

    public void setOrderInstrDesc(String orderInstrDesc) {
        this.orderInstrDesc = orderInstrDesc;
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
        hash += (orderInstrCode != null ? orderInstrCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderInstruction)) {
            return false;
        }
        OrderInstruction other = (OrderInstruction) object;
        if ((this.orderInstrCode == null && other.orderInstrCode != null) || (this.orderInstrCode != null && !this.orderInstrCode.equals(other.orderInstrCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrderInstruction[ orderInstrCode=" + orderInstrCode + " ]";
    }
    
}
