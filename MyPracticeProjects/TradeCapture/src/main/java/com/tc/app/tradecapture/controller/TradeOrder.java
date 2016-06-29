/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.exchangemonitor.controller;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "trade_order", catalog = "DEV_CS25_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TradeOrder.findAll", query = "SELECT t FROM TradeOrder t"),
    @NamedQuery(name = "TradeOrder.findByTradeNum", query = "SELECT t FROM TradeOrder t WHERE t.tradeOrderPK.tradeNum = :tradeNum"),
    @NamedQuery(name = "TradeOrder.findByOrderNum", query = "SELECT t FROM TradeOrder t WHERE t.tradeOrderPK.orderNum = :orderNum"),
    @NamedQuery(name = "TradeOrder.findByParentOrderInd", query = "SELECT t FROM TradeOrder t WHERE t.parentOrderInd = :parentOrderInd"),
    @NamedQuery(name = "TradeOrder.findByParentOrderNum", query = "SELECT t FROM TradeOrder t WHERE t.parentOrderNum = :parentOrderNum"),
    @NamedQuery(name = "TradeOrder.findByOrderStrategyNum", query = "SELECT t FROM TradeOrder t WHERE t.orderStrategyNum = :orderStrategyNum"),
    @NamedQuery(name = "TradeOrder.findByOrderStrategyName", query = "SELECT t FROM TradeOrder t WHERE t.orderStrategyName = :orderStrategyName"),
    @NamedQuery(name = "TradeOrder.findByOrderStripNum", query = "SELECT t FROM TradeOrder t WHERE t.orderStripNum = :orderStripNum"),
    @NamedQuery(name = "TradeOrder.findByStripSummaryInd", query = "SELECT t FROM TradeOrder t WHERE t.stripSummaryInd = :stripSummaryInd"),
    @NamedQuery(name = "TradeOrder.findByStripDetailOrderCount", query = "SELECT t FROM TradeOrder t WHERE t.stripDetailOrderCount = :stripDetailOrderCount"),
    @NamedQuery(name = "TradeOrder.findByStripPeriodicity", query = "SELECT t FROM TradeOrder t WHERE t.stripPeriodicity = :stripPeriodicity"),
    @NamedQuery(name = "TradeOrder.findByStripOrderStatus", query = "SELECT t FROM TradeOrder t WHERE t.stripOrderStatus = :stripOrderStatus"),
    @NamedQuery(name = "TradeOrder.findByTermEvergreenInd", query = "SELECT t FROM TradeOrder t WHERE t.termEvergreenInd = :termEvergreenInd"),
    @NamedQuery(name = "TradeOrder.findByBalInd", query = "SELECT t FROM TradeOrder t WHERE t.balInd = :balInd"),
    @NamedQuery(name = "TradeOrder.findByMarginAmt", query = "SELECT t FROM TradeOrder t WHERE t.marginAmt = :marginAmt"),
    @NamedQuery(name = "TradeOrder.findByCmntNum", query = "SELECT t FROM TradeOrder t WHERE t.cmntNum = :cmntNum"),
    @NamedQuery(name = "TradeOrder.findByEfpLastPostDate", query = "SELECT t FROM TradeOrder t WHERE t.efpLastPostDate = :efpLastPostDate"),
    @NamedQuery(name = "TradeOrder.findByCashSettleType", query = "SELECT t FROM TradeOrder t WHERE t.cashSettleType = :cashSettleType"),
    @NamedQuery(name = "TradeOrder.findByCashSettleSaturdays", query = "SELECT t FROM TradeOrder t WHERE t.cashSettleSaturdays = :cashSettleSaturdays"),
    @NamedQuery(name = "TradeOrder.findByCashSettleSundays", query = "SELECT t FROM TradeOrder t WHERE t.cashSettleSundays = :cashSettleSundays"),
    @NamedQuery(name = "TradeOrder.findByCashSettleHolidays", query = "SELECT t FROM TradeOrder t WHERE t.cashSettleHolidays = :cashSettleHolidays"),
    @NamedQuery(name = "TradeOrder.findByCashSettlePrdFreq", query = "SELECT t FROM TradeOrder t WHERE t.cashSettlePrdFreq = :cashSettlePrdFreq"),
    @NamedQuery(name = "TradeOrder.findByCashSettlePrdStartDate", query = "SELECT t FROM TradeOrder t WHERE t.cashSettlePrdStartDate = :cashSettlePrdStartDate"),
    @NamedQuery(name = "TradeOrder.findByCommitmentInd", query = "SELECT t FROM TradeOrder t WHERE t.commitmentInd = :commitmentInd"),
    @NamedQuery(name = "TradeOrder.findByMaxItemNum", query = "SELECT t FROM TradeOrder t WHERE t.maxItemNum = :maxItemNum"),
    @NamedQuery(name = "TradeOrder.findByTransId", query = "SELECT t FROM TradeOrder t WHERE t.transId = :transId"),
    @NamedQuery(name = "TradeOrder.findByInternalParentTradeNum", query = "SELECT t FROM TradeOrder t WHERE t.internalParentTradeNum = :internalParentTradeNum"),
    @NamedQuery(name = "TradeOrder.findByInternalParentOrderNum", query = "SELECT t FROM TradeOrder t WHERE t.internalParentOrderNum = :internalParentOrderNum")})
public class TradeOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TradeOrderPK tradeOrderPK;
    @Column(name = "parent_order_ind")
    private Character parentOrderInd;
    @Column(name = "parent_order_num")
    private Short parentOrderNum;
    @Column(name = "order_strategy_num")
    private Short orderStrategyNum;
    @Column(name = "order_strategy_name")
    private String orderStrategyName;
    @Column(name = "order_strip_num")
    private Short orderStripNum;
    @Basic(optional = false)
    @Column(name = "strip_summary_ind")
    private Character stripSummaryInd;
    @Column(name = "strip_detail_order_count")
    private Short stripDetailOrderCount;
    @Column(name = "strip_periodicity")
    private Character stripPeriodicity;
    @Column(name = "strip_order_status")
    private Character stripOrderStatus;
    @Column(name = "term_evergreen_ind")
    private Character termEvergreenInd;
    @Column(name = "bal_ind")
    private Character balInd;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "margin_amt")
    private Double marginAmt;
    @Column(name = "cmnt_num")
    private Integer cmntNum;
    @Column(name = "efp_last_post_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date efpLastPostDate;
    @Column(name = "cash_settle_type")
    private Character cashSettleType;
    @Column(name = "cash_settle_saturdays")
    private Character cashSettleSaturdays;
    @Column(name = "cash_settle_sundays")
    private Character cashSettleSundays;
    @Column(name = "cash_settle_holidays")
    private Character cashSettleHolidays;
    @Column(name = "cash_settle_prd_freq")
    private Character cashSettlePrdFreq;
    @Column(name = "cash_settle_prd_start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cashSettlePrdStartDate;
    @Column(name = "commitment_ind")
    private Character commitmentInd;
    @Column(name = "max_item_num")
    private Short maxItemNum;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @Column(name = "internal_parent_trade_num")
    private Integer internalParentTradeNum;
    @Column(name = "internal_parent_order_num")
    private Short internalParentOrderNum;
    @JoinColumn(name = "margin_amt_curr_code", referencedColumnName = "cmdty_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Commodity marginAmtCurrCode;
    @JoinColumn(name = "order_type_code", referencedColumnName = "order_type_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private OrderType orderTypeCode;
    @JoinColumn(name = "order_status_code", referencedColumnName = "trade_status_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private TradeStatus orderStatusCode;

    public TradeOrder() {
    }

    public TradeOrder(TradeOrderPK tradeOrderPK) {
        this.tradeOrderPK = tradeOrderPK;
    }

    public TradeOrder(TradeOrderPK tradeOrderPK, Character stripSummaryInd, int transId) {
        this.tradeOrderPK = tradeOrderPK;
        this.stripSummaryInd = stripSummaryInd;
        this.transId = transId;
    }

    public TradeOrder(int tradeNum, short orderNum) {
        this.tradeOrderPK = new TradeOrderPK(tradeNum, orderNum);
    }

    public TradeOrderPK getTradeOrderPK() {
        return tradeOrderPK;
    }

    public void setTradeOrderPK(TradeOrderPK tradeOrderPK) {
        this.tradeOrderPK = tradeOrderPK;
    }

    public Character getParentOrderInd() {
        return parentOrderInd;
    }

    public void setParentOrderInd(Character parentOrderInd) {
        this.parentOrderInd = parentOrderInd;
    }

    public Short getParentOrderNum() {
        return parentOrderNum;
    }

    public void setParentOrderNum(Short parentOrderNum) {
        this.parentOrderNum = parentOrderNum;
    }

    public Short getOrderStrategyNum() {
        return orderStrategyNum;
    }

    public void setOrderStrategyNum(Short orderStrategyNum) {
        this.orderStrategyNum = orderStrategyNum;
    }

    public String getOrderStrategyName() {
        return orderStrategyName;
    }

    public void setOrderStrategyName(String orderStrategyName) {
        this.orderStrategyName = orderStrategyName;
    }

    public Short getOrderStripNum() {
        return orderStripNum;
    }

    public void setOrderStripNum(Short orderStripNum) {
        this.orderStripNum = orderStripNum;
    }

    public Character getStripSummaryInd() {
        return stripSummaryInd;
    }

    public void setStripSummaryInd(Character stripSummaryInd) {
        this.stripSummaryInd = stripSummaryInd;
    }

    public Short getStripDetailOrderCount() {
        return stripDetailOrderCount;
    }

    public void setStripDetailOrderCount(Short stripDetailOrderCount) {
        this.stripDetailOrderCount = stripDetailOrderCount;
    }

    public Character getStripPeriodicity() {
        return stripPeriodicity;
    }

    public void setStripPeriodicity(Character stripPeriodicity) {
        this.stripPeriodicity = stripPeriodicity;
    }

    public Character getStripOrderStatus() {
        return stripOrderStatus;
    }

    public void setStripOrderStatus(Character stripOrderStatus) {
        this.stripOrderStatus = stripOrderStatus;
    }

    public Character getTermEvergreenInd() {
        return termEvergreenInd;
    }

    public void setTermEvergreenInd(Character termEvergreenInd) {
        this.termEvergreenInd = termEvergreenInd;
    }

    public Character getBalInd() {
        return balInd;
    }

    public void setBalInd(Character balInd) {
        this.balInd = balInd;
    }

    public Double getMarginAmt() {
        return marginAmt;
    }

    public void setMarginAmt(Double marginAmt) {
        this.marginAmt = marginAmt;
    }

    public Integer getCmntNum() {
        return cmntNum;
    }

    public void setCmntNum(Integer cmntNum) {
        this.cmntNum = cmntNum;
    }

    public Date getEfpLastPostDate() {
        return efpLastPostDate;
    }

    public void setEfpLastPostDate(Date efpLastPostDate) {
        this.efpLastPostDate = efpLastPostDate;
    }

    public Character getCashSettleType() {
        return cashSettleType;
    }

    public void setCashSettleType(Character cashSettleType) {
        this.cashSettleType = cashSettleType;
    }

    public Character getCashSettleSaturdays() {
        return cashSettleSaturdays;
    }

    public void setCashSettleSaturdays(Character cashSettleSaturdays) {
        this.cashSettleSaturdays = cashSettleSaturdays;
    }

    public Character getCashSettleSundays() {
        return cashSettleSundays;
    }

    public void setCashSettleSundays(Character cashSettleSundays) {
        this.cashSettleSundays = cashSettleSundays;
    }

    public Character getCashSettleHolidays() {
        return cashSettleHolidays;
    }

    public void setCashSettleHolidays(Character cashSettleHolidays) {
        this.cashSettleHolidays = cashSettleHolidays;
    }

    public Character getCashSettlePrdFreq() {
        return cashSettlePrdFreq;
    }

    public void setCashSettlePrdFreq(Character cashSettlePrdFreq) {
        this.cashSettlePrdFreq = cashSettlePrdFreq;
    }

    public Date getCashSettlePrdStartDate() {
        return cashSettlePrdStartDate;
    }

    public void setCashSettlePrdStartDate(Date cashSettlePrdStartDate) {
        this.cashSettlePrdStartDate = cashSettlePrdStartDate;
    }

    public Character getCommitmentInd() {
        return commitmentInd;
    }

    public void setCommitmentInd(Character commitmentInd) {
        this.commitmentInd = commitmentInd;
    }

    public Short getMaxItemNum() {
        return maxItemNum;
    }

    public void setMaxItemNum(Short maxItemNum) {
        this.maxItemNum = maxItemNum;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public Integer getInternalParentTradeNum() {
        return internalParentTradeNum;
    }

    public void setInternalParentTradeNum(Integer internalParentTradeNum) {
        this.internalParentTradeNum = internalParentTradeNum;
    }

    public Short getInternalParentOrderNum() {
        return internalParentOrderNum;
    }

    public void setInternalParentOrderNum(Short internalParentOrderNum) {
        this.internalParentOrderNum = internalParentOrderNum;
    }

    public Commodity getMarginAmtCurrCode() {
        return marginAmtCurrCode;
    }

    public void setMarginAmtCurrCode(Commodity marginAmtCurrCode) {
        this.marginAmtCurrCode = marginAmtCurrCode;
    }

    public OrderType getOrderTypeCode() {
        return orderTypeCode;
    }

    public void setOrderTypeCode(OrderType orderTypeCode) {
        this.orderTypeCode = orderTypeCode;
    }

    public TradeStatus getOrderStatusCode() {
        return orderStatusCode;
    }

    public void setOrderStatusCode(TradeStatus orderStatusCode) {
        this.orderStatusCode = orderStatusCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tradeOrderPK != null ? tradeOrderPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TradeOrder)) {
            return false;
        }
        TradeOrder other = (TradeOrder) object;
        if ((this.tradeOrderPK == null && other.tradeOrderPK != null) || (this.tradeOrderPK != null && !this.tradeOrderPK.equals(other.tradeOrderPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TradeOrder[ tradeOrderPK=" + tradeOrderPK + " ]";
    }
    
}
