
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tc.app.exchangemonitor.controller;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author smurugabushanam
 */
@Entity
@Table(name = "external_trade", catalog = "QA_30_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExternalTrade.findAll", query = "SELECT e FROM ExternalTrade e"),
    @NamedQuery(name = "ExternalTrade.findByOid", query = "SELECT e FROM ExternalTrade e WHERE e.oid = :oid"),
    @NamedQuery(name = "ExternalTrade.findByEntryDate", query = "SELECT e FROM ExternalTrade e WHERE e.entryDate = :entryDate"),
    @NamedQuery(name = "ExternalTrade.findByPortNum", query = "SELECT e FROM ExternalTrade e WHERE e.portNum = :portNum"),
    @NamedQuery(name = "ExternalTrade.findByTradeNum", query = "SELECT e FROM ExternalTrade e WHERE e.tradeNum = :tradeNum"),
    @NamedQuery(name = "ExternalTrade.findByTransId", query = "SELECT e FROM ExternalTrade e WHERE e.transId = :transId"),
    @NamedQuery(name = "ExternalTrade.findBySequence", query = "SELECT e FROM ExternalTrade e WHERE e.sequence = :sequence"),
    @NamedQuery(name = "ExternalTrade.findByExternalCommentOid", query = "SELECT e FROM ExternalTrade e WHERE e.externalCommentOid = :externalCommentOid"),
    @NamedQuery(name = "ExternalTrade.findByInhousePortNum", query = "SELECT e FROM ExternalTrade e WHERE e.inhousePortNum = :inhousePortNum"),
    @NamedQuery(name = "ExternalTrade.findByOrderNum", query = "SELECT e FROM ExternalTrade e WHERE e.orderNum = :orderNum"),
    @NamedQuery(name = "ExternalTrade.findByItemNum", query = "SELECT e FROM ExternalTrade e WHERE e.itemNum = :itemNum")})
public class ExternalTrade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "oid")
    private Integer oid;
    @Basic(optional = false)
    @Column(name = "entry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;
    @Column(name = "port_num")
    private Integer portNum;
    @Column(name = "trade_num")
    private Integer tradeNum;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    
    @Basic(optional = false)
    @Column(name = "sequence", columnDefinition="big_decimal")
    /*@Column(name = "sequence", columnDefinition="bigint")*/
    //private BigInteger sequence;
    private BigDecimal sequence;
    
    @Column(name = "external_comment_oid")
    private Integer externalCommentOid;
    @Column(name = "inhouse_port_num")
    private Integer inhousePortNum;
    @Column(name = "order_num")
    private Short orderNum;
    @Column(name = "item_num")
    private Short itemNum;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "externalTrade")
    private ExchToolsTrade exchToolsTrade;
    
    @JoinColumn(name = "ext_pos_num", referencedColumnName = "ext_pos_num")
    @ManyToOne
    private ExternalPosition extPosNum;
    @JoinColumn(name = "external_trade_source_oid", referencedColumnName = "oid")
    @ManyToOne(optional = false)
    private ExternalTradeSource externalTradeSourceOid;
    @JoinColumn(name = "external_trade_state_oid", referencedColumnName = "oid")
    @ManyToOne
    private ExternalTradeState externalTradeStateOid;
    @JoinColumn(name = "external_trade_status_oid", referencedColumnName = "oid")
    @ManyToOne(optional = false)
    private ExternalTradeStatus externalTradeStatusOid;
    
    @JoinColumn(name = "external_trade_system_oid", referencedColumnName = "oid")
    @ManyToOne(optional = false)
    private ExternalTradeSystem externalTradeSystemOid;

    public ExternalTrade() {
    }

    public ExternalTrade(Integer oid) {
        this.oid = oid;
    }

    public ExternalTrade(Integer oid, Date entryDate, int transId, BigDecimal sequence) {
        this.oid = oid;
        this.entryDate = entryDate;
        this.transId = transId;
        this.sequence = sequence;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Integer getPortNum() {
        return portNum;
    }

    public void setPortNum(Integer portNum) {
        this.portNum = portNum;
    }

    public Integer getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(Integer tradeNum) {
        this.tradeNum = tradeNum;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public BigDecimal getSequence() {
        return sequence;
    }

    public void setSequence(BigDecimal sequence) {
        this.sequence = sequence;
    }

    public Integer getExternalCommentOid() {
        return externalCommentOid;
    }

    public void setExternalCommentOid(Integer externalCommentOid) {
        this.externalCommentOid = externalCommentOid;
    }

    public Integer getInhousePortNum() {
        return inhousePortNum;
    }

    public void setInhousePortNum(Integer inhousePortNum) {
        this.inhousePortNum = inhousePortNum;
    }

    public Short getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Short orderNum) {
        this.orderNum = orderNum;
    }

    public Short getItemNum() {
        return itemNum;
    }

    public void setItemNum(Short itemNum) {
        this.itemNum = itemNum;
    }

    public ExchToolsTrade getExchToolsTrade() {
        return exchToolsTrade;
    }

    public void setExchToolsTrade(ExchToolsTrade exchToolsTrade) {
        this.exchToolsTrade = exchToolsTrade;
    }

    public ExternalPosition getExtPosNum() {
        return extPosNum;
    }

    public void setExtPosNum(ExternalPosition extPosNum) {
        this.extPosNum = extPosNum;
    }

    public ExternalTradeSource getExternalTradeSourceOid() {
        return externalTradeSourceOid;
    }

    public void setExternalTradeSourceOid(ExternalTradeSource externalTradeSourceOid) {
        this.externalTradeSourceOid = externalTradeSourceOid;
    }

    public ExternalTradeState getExternalTradeStateOid() {
        return externalTradeStateOid;
    }

    public void setExternalTradeStateOid(ExternalTradeState externalTradeStateOid) {
        this.externalTradeStateOid = externalTradeStateOid;
    }

    public ExternalTradeStatus getExternalTradeStatusOid() {
        return externalTradeStatusOid;
    }

    public void setExternalTradeStatusOid(ExternalTradeStatus externalTradeStatusOid) {
        this.externalTradeStatusOid = externalTradeStatusOid;
    }

    public ExternalTradeSystem getExternalTradeSystemOid() {
        return externalTradeSystemOid;
    }

    public void setExternalTradeSystemOid(ExternalTradeSystem externalTradeSystemOid) {
        this.externalTradeSystemOid = externalTradeSystemOid;
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
        if (!(object instanceof ExternalTrade)) {
            return false;
        }
        ExternalTrade other = (ExternalTrade) object;
        if ((this.oid == null && other.oid != null) || (this.oid != null && !this.oid.equals(other.oid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generated3.ExternalTrade[ oid=" + oid + " ]";
    }
    
}
