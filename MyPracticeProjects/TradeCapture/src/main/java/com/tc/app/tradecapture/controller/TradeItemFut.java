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
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "trade_item_fut", catalog = "DEV_CS25_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TradeItemFut.findAll", query = "SELECT t FROM TradeItemFut t"),
    @NamedQuery(name = "TradeItemFut.findByTradeNum", query = "SELECT t FROM TradeItemFut t WHERE t.tradeItemFutPK.tradeNum = :tradeNum"),
    @NamedQuery(name = "TradeItemFut.findByOrderNum", query = "SELECT t FROM TradeItemFut t WHERE t.tradeItemFutPK.orderNum = :orderNum"),
    @NamedQuery(name = "TradeItemFut.findByItemNum", query = "SELECT t FROM TradeItemFut t WHERE t.tradeItemFutPK.itemNum = :itemNum"),
    @NamedQuery(name = "TradeItemFut.findBySettlementType", query = "SELECT t FROM TradeItemFut t WHERE t.settlementType = :settlementType"),
    @NamedQuery(name = "TradeItemFut.findByFutPrice", query = "SELECT t FROM TradeItemFut t WHERE t.futPrice = :futPrice"),
    @NamedQuery(name = "TradeItemFut.findByTotalFillQty", query = "SELECT t FROM TradeItemFut t WHERE t.totalFillQty = :totalFillQty"),
    @NamedQuery(name = "TradeItemFut.findByAvgFillPrice", query = "SELECT t FROM TradeItemFut t WHERE t.avgFillPrice = :avgFillPrice"),
    @NamedQuery(name = "TradeItemFut.findByClrBrkrCommAmt", query = "SELECT t FROM TradeItemFut t WHERE t.clrBrkrCommAmt = :clrBrkrCommAmt"),
    @NamedQuery(name = "TradeItemFut.findByClrBrkrRefNum", query = "SELECT t FROM TradeItemFut t WHERE t.clrBrkrRefNum = :clrBrkrRefNum"),
    @NamedQuery(name = "TradeItemFut.findByExerciseNum", query = "SELECT t FROM TradeItemFut t WHERE t.exerciseNum = :exerciseNum"),
    @NamedQuery(name = "TradeItemFut.findByTransId", query = "SELECT t FROM TradeItemFut t WHERE t.transId = :transId"),
    @NamedQuery(name = "TradeItemFut.findByUseInFifoInd", query = "SELECT t FROM TradeItemFut t WHERE t.useInFifoInd = :useInFifoInd"),
    @NamedQuery(name = "TradeItemFut.findByEfpTriggerNum", query = "SELECT t FROM TradeItemFut t WHERE t.efpTriggerNum = :efpTriggerNum")})
public class TradeItemFut implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TradeItemFutPK tradeItemFutPK;
    @Column(name = "settlement_type")
    private Character settlementType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "fut_price")
    private Double futPrice;
    @Column(name = "total_fill_qty")
    private Double totalFillQty;
    @Column(name = "avg_fill_price")
    private Double avgFillPrice;
    @Column(name = "clr_brkr_comm_amt")
    private Double clrBrkrCommAmt;
    @Column(name = "clr_brkr_ref_num")
    private String clrBrkrRefNum;
    @Column(name = "exercise_num")
    private Short exerciseNum;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @Column(name = "use_in_fifo_ind")
    private Character useInFifoInd;
    @Column(name = "efp_trigger_num")
    private Short efpTriggerNum;
    @JoinColumn(name = "clr_brkr_num", referencedColumnName = "acct_num")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account clrBrkrNum;
    @JoinColumns({
        @JoinColumn(name = "clr_brkr_num", referencedColumnName = "acct_num"),
        @JoinColumn(name = "clr_brkr_cont_num", referencedColumnName = "acct_cont_num")})
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountContact accountContact;
    @JoinColumn(name = "fut_price_curr_code", referencedColumnName = "cmdty_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Commodity futPriceCurrCode;
    @JoinColumn(name = "clr_brkr_comm_curr_code", referencedColumnName = "cmdty_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Commodity clrBrkrCommCurrCode;
    @JoinColumn(name = "exec_type_code", referencedColumnName = "exec_type_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private ExecutionType execTypeCode;
    @JoinColumn(name = "price_source_code", referencedColumnName = "price_source_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private PriceSource priceSourceCode;
    @JoinColumn(name = "fill_qty_uom_code", referencedColumnName = "uom_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Uom fillQtyUomCode;
    @JoinColumn(name = "clr_brkr_comm_uom_code", referencedColumnName = "uom_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Uom clrBrkrCommUomCode;

    public TradeItemFut() {
    }

    public TradeItemFut(TradeItemFutPK tradeItemFutPK) {
        this.tradeItemFutPK = tradeItemFutPK;
    }

    public TradeItemFut(TradeItemFutPK tradeItemFutPK, int transId) {
        this.tradeItemFutPK = tradeItemFutPK;
        this.transId = transId;
    }

    public TradeItemFut(int tradeNum, short orderNum, short itemNum) {
        this.tradeItemFutPK = new TradeItemFutPK(tradeNum, orderNum, itemNum);
    }

    public TradeItemFutPK getTradeItemFutPK() {
        return tradeItemFutPK;
    }

    public void setTradeItemFutPK(TradeItemFutPK tradeItemFutPK) {
        this.tradeItemFutPK = tradeItemFutPK;
    }

    public Character getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(Character settlementType) {
        this.settlementType = settlementType;
    }

    public Double getFutPrice() {
        return futPrice;
    }

    public void setFutPrice(Double futPrice) {
        this.futPrice = futPrice;
    }

    public Double getTotalFillQty() {
        return totalFillQty;
    }

    public void setTotalFillQty(Double totalFillQty) {
        this.totalFillQty = totalFillQty;
    }

    public Double getAvgFillPrice() {
        return avgFillPrice;
    }

    public void setAvgFillPrice(Double avgFillPrice) {
        this.avgFillPrice = avgFillPrice;
    }

    public Double getClrBrkrCommAmt() {
        return clrBrkrCommAmt;
    }

    public void setClrBrkrCommAmt(Double clrBrkrCommAmt) {
        this.clrBrkrCommAmt = clrBrkrCommAmt;
    }

    public String getClrBrkrRefNum() {
        return clrBrkrRefNum;
    }

    public void setClrBrkrRefNum(String clrBrkrRefNum) {
        this.clrBrkrRefNum = clrBrkrRefNum;
    }

    public Short getExerciseNum() {
        return exerciseNum;
    }

    public void setExerciseNum(Short exerciseNum) {
        this.exerciseNum = exerciseNum;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public Character getUseInFifoInd() {
        return useInFifoInd;
    }

    public void setUseInFifoInd(Character useInFifoInd) {
        this.useInFifoInd = useInFifoInd;
    }

    public Short getEfpTriggerNum() {
        return efpTriggerNum;
    }

    public void setEfpTriggerNum(Short efpTriggerNum) {
        this.efpTriggerNum = efpTriggerNum;
    }

    public Account getClrBrkrNum() {
        return clrBrkrNum;
    }

    public void setClrBrkrNum(Account clrBrkrNum) {
        this.clrBrkrNum = clrBrkrNum;
    }

    public AccountContact getAccountContact() {
        return accountContact;
    }

    public void setAccountContact(AccountContact accountContact) {
        this.accountContact = accountContact;
    }

    public Commodity getFutPriceCurrCode() {
        return futPriceCurrCode;
    }

    public void setFutPriceCurrCode(Commodity futPriceCurrCode) {
        this.futPriceCurrCode = futPriceCurrCode;
    }

    public Commodity getClrBrkrCommCurrCode() {
        return clrBrkrCommCurrCode;
    }

    public void setClrBrkrCommCurrCode(Commodity clrBrkrCommCurrCode) {
        this.clrBrkrCommCurrCode = clrBrkrCommCurrCode;
    }

    public ExecutionType getExecTypeCode() {
        return execTypeCode;
    }

    public void setExecTypeCode(ExecutionType execTypeCode) {
        this.execTypeCode = execTypeCode;
    }

    public PriceSource getPriceSourceCode() {
        return priceSourceCode;
    }

    public void setPriceSourceCode(PriceSource priceSourceCode) {
        this.priceSourceCode = priceSourceCode;
    }

    public Uom getFillQtyUomCode() {
        return fillQtyUomCode;
    }

    public void setFillQtyUomCode(Uom fillQtyUomCode) {
        this.fillQtyUomCode = fillQtyUomCode;
    }

    public Uom getClrBrkrCommUomCode() {
        return clrBrkrCommUomCode;
    }

    public void setClrBrkrCommUomCode(Uom clrBrkrCommUomCode) {
        this.clrBrkrCommUomCode = clrBrkrCommUomCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tradeItemFutPK != null ? tradeItemFutPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TradeItemFut)) {
            return false;
        }
        TradeItemFut other = (TradeItemFut) object;
        if ((this.tradeItemFutPK == null && other.tradeItemFutPK != null) || (this.tradeItemFutPK != null && !this.tradeItemFutPK.equals(other.tradeItemFutPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TradeItemFut[ tradeItemFutPK=" + tradeItemFutPK + " ]";
    }
    
}
