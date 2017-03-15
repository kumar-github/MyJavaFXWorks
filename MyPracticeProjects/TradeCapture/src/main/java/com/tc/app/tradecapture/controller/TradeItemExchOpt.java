/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.tradecapture.controller;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "trade_item_exch_opt", catalog = "DEV_31_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TradeItemExchOpt.findAll", query = "SELECT t FROM TradeItemExchOpt t"),
    @NamedQuery(name = "TradeItemExchOpt.findByTradeNum", query = "SELECT t FROM TradeItemExchOpt t WHERE t.tradeItemExchOptPK.tradeNum = :tradeNum"),
    @NamedQuery(name = "TradeItemExchOpt.findByOrderNum", query = "SELECT t FROM TradeItemExchOpt t WHERE t.tradeItemExchOptPK.orderNum = :orderNum"),
    @NamedQuery(name = "TradeItemExchOpt.findByItemNum", query = "SELECT t FROM TradeItemExchOpt t WHERE t.tradeItemExchOptPK.itemNum = :itemNum"),
    @NamedQuery(name = "TradeItemExchOpt.findByPutCallInd", query = "SELECT t FROM TradeItemExchOpt t WHERE t.putCallInd = :putCallInd"),
    @NamedQuery(name = "TradeItemExchOpt.findByOptType", query = "SELECT t FROM TradeItemExchOpt t WHERE t.optType = :optType"),
    @NamedQuery(name = "TradeItemExchOpt.findBySettlementType", query = "SELECT t FROM TradeItemExchOpt t WHERE t.settlementType = :settlementType"),
    @NamedQuery(name = "TradeItemExchOpt.findByPremium", query = "SELECT t FROM TradeItemExchOpt t WHERE t.premium = :premium"),
    @NamedQuery(name = "TradeItemExchOpt.findByPremiumPayDate", query = "SELECT t FROM TradeItemExchOpt t WHERE t.premiumPayDate = :premiumPayDate"),
    @NamedQuery(name = "TradeItemExchOpt.findByStrikePrice", query = "SELECT t FROM TradeItemExchOpt t WHERE t.strikePrice = :strikePrice"),
    @NamedQuery(name = "TradeItemExchOpt.findByExpDate", query = "SELECT t FROM TradeItemExchOpt t WHERE t.expDate = :expDate"),
    @NamedQuery(name = "TradeItemExchOpt.findByExpZoneCode", query = "SELECT t FROM TradeItemExchOpt t WHERE t.expZoneCode = :expZoneCode"),
    @NamedQuery(name = "TradeItemExchOpt.findByTotalFillQty", query = "SELECT t FROM TradeItemExchOpt t WHERE t.totalFillQty = :totalFillQty"),
    @NamedQuery(name = "TradeItemExchOpt.findByAvgFillPrice", query = "SELECT t FROM TradeItemExchOpt t WHERE t.avgFillPrice = :avgFillPrice"),
    @NamedQuery(name = "TradeItemExchOpt.findByStrikeExcerDate", query = "SELECT t FROM TradeItemExchOpt t WHERE t.strikeExcerDate = :strikeExcerDate"),
    @NamedQuery(name = "TradeItemExchOpt.findByClrBrkrCommAmt", query = "SELECT t FROM TradeItemExchOpt t WHERE t.clrBrkrCommAmt = :clrBrkrCommAmt"),
    @NamedQuery(name = "TradeItemExchOpt.findByClrBrkrRefNum", query = "SELECT t FROM TradeItemExchOpt t WHERE t.clrBrkrRefNum = :clrBrkrRefNum"),
    @NamedQuery(name = "TradeItemExchOpt.findBySurrenderQty", query = "SELECT t FROM TradeItemExchOpt t WHERE t.surrenderQty = :surrenderQty"),
    @NamedQuery(name = "TradeItemExchOpt.findByTransId", query = "SELECT t FROM TradeItemExchOpt t WHERE t.transId = :transId"),
    @NamedQuery(name = "TradeItemExchOpt.findByUseInFifoInd", query = "SELECT t FROM TradeItemExchOpt t WHERE t.useInFifoInd = :useInFifoInd")})
public class TradeItemExchOpt implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TradeItemExchOptPK tradeItemExchOptPK;
    @Column(name = "put_call_ind")
    private Character putCallInd;
    @Column(name = "opt_type")
    private Character optType;
    @Column(name = "settlement_type")
    private Character settlementType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "premium")
    private Double premium;
    @Column(name = "premium_pay_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date premiumPayDate;
    @Column(name = "strike_price")
    private Double strikePrice;
    @Column(name = "exp_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expDate;
    @Column(name = "exp_zone_code")
    private String expZoneCode;
    @Column(name = "total_fill_qty")
    private Double totalFillQty;
    @Column(name = "avg_fill_price")
    private Double avgFillPrice;
    @Column(name = "strike_excer_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date strikeExcerDate;
    @Column(name = "clr_brkr_comm_amt")
    private Double clrBrkrCommAmt;
    @Column(name = "clr_brkr_ref_num")
    private String clrBrkrRefNum;
    @Column(name = "surrender_qty")
    private Double surrenderQty;
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @Column(name = "use_in_fifo_ind")
    private Character useInFifoInd;
    @JoinColumn(name = "price_source_code", referencedColumnName = "price_source_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private PriceSource priceSourceCode;
    @JoinColumn(name = "clr_brkr_num", referencedColumnName = "acct_num")
    @ManyToOne(fetch = FetchType.LAZY)
    private Account clrBrkrNum;
    @JoinColumns({
        @JoinColumn(name = "clr_brkr_num", referencedColumnName = "acct_num"),
        @JoinColumn(name = "clr_brkr_cont_num", referencedColumnName = "acct_cont_num")})
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountContact accountContact;
    @JoinColumn(name = "premium_curr_code", referencedColumnName = "cmdty_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Commodity premiumCurrCode;
    @JoinColumn(name = "strike_price_curr_code", referencedColumnName = "cmdty_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Commodity strikePriceCurrCode;
    @JoinColumn(name = "clr_brkr_comm_curr_code", referencedColumnName = "cmdty_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Commodity clrBrkrCommCurrCode;
    @JoinColumn(name = "exec_type_code", referencedColumnName = "exec_type_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private ExecutionType execTypeCode;
    @JoinColumn(name = "strike_price_uom_code", referencedColumnName = "uom_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Uom strikePriceUomCode;
    @JoinColumn(name = "premium_uom_code", referencedColumnName = "uom_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Uom premiumUomCode;
    @JoinColumn(name = "fill_qty_uom_code", referencedColumnName = "uom_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Uom fillQtyUomCode;
    @JoinColumn(name = "clr_brkr_comm_uom_code", referencedColumnName = "uom_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Uom clrBrkrCommUomCode;

    public TradeItemExchOpt() {
    }

    public TradeItemExchOpt(TradeItemExchOptPK tradeItemExchOptPK) {
        this.tradeItemExchOptPK = tradeItemExchOptPK;
    }

    public TradeItemExchOpt(TradeItemExchOptPK tradeItemExchOptPK, int transId) {
        this.tradeItemExchOptPK = tradeItemExchOptPK;
        this.transId = transId;
    }

    public TradeItemExchOpt(int tradeNum, short orderNum, short itemNum) {
        this.tradeItemExchOptPK = new TradeItemExchOptPK(tradeNum, orderNum, itemNum);
    }

    public TradeItemExchOptPK getTradeItemExchOptPK() {
        return tradeItemExchOptPK;
    }

    public void setTradeItemExchOptPK(TradeItemExchOptPK tradeItemExchOptPK) {
        this.tradeItemExchOptPK = tradeItemExchOptPK;
    }

    public Character getPutCallInd() {
        return putCallInd;
    }

    public void setPutCallInd(Character putCallInd) {
        this.putCallInd = putCallInd;
    }

    public Character getOptType() {
        return optType;
    }

    public void setOptType(Character optType) {
        this.optType = optType;
    }

    public Character getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(Character settlementType) {
        this.settlementType = settlementType;
    }

    public Double getPremium() {
        return premium;
    }

    public void setPremium(Double premium) {
        this.premium = premium;
    }

    public Date getPremiumPayDate() {
        return premiumPayDate;
    }

    public void setPremiumPayDate(Date premiumPayDate) {
        this.premiumPayDate = premiumPayDate;
    }

    public Double getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public String getExpZoneCode() {
        return expZoneCode;
    }

    public void setExpZoneCode(String expZoneCode) {
        this.expZoneCode = expZoneCode;
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

    public Date getStrikeExcerDate() {
        return strikeExcerDate;
    }

    public void setStrikeExcerDate(Date strikeExcerDate) {
        this.strikeExcerDate = strikeExcerDate;
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

    public Double getSurrenderQty() {
        return surrenderQty;
    }

    public void setSurrenderQty(Double surrenderQty) {
        this.surrenderQty = surrenderQty;
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

    public PriceSource getPriceSourceCode() {
        return priceSourceCode;
    }

    public void setPriceSourceCode(PriceSource priceSourceCode) {
        this.priceSourceCode = priceSourceCode;
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

    public Commodity getPremiumCurrCode() {
        return premiumCurrCode;
    }

    public void setPremiumCurrCode(Commodity premiumCurrCode) {
        this.premiumCurrCode = premiumCurrCode;
    }

    public Commodity getStrikePriceCurrCode() {
        return strikePriceCurrCode;
    }

    public void setStrikePriceCurrCode(Commodity strikePriceCurrCode) {
        this.strikePriceCurrCode = strikePriceCurrCode;
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

    public Uom getStrikePriceUomCode() {
        return strikePriceUomCode;
    }

    public void setStrikePriceUomCode(Uom strikePriceUomCode) {
        this.strikePriceUomCode = strikePriceUomCode;
    }

    public Uom getPremiumUomCode() {
        return premiumUomCode;
    }

    public void setPremiumUomCode(Uom premiumUomCode) {
        this.premiumUomCode = premiumUomCode;
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
        hash += (tradeItemExchOptPK != null ? tradeItemExchOptPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TradeItemExchOpt)) {
            return false;
        }
        TradeItemExchOpt other = (TradeItemExchOpt) object;
        if ((this.tradeItemExchOptPK == null && other.tradeItemExchOptPK != null) || (this.tradeItemExchOptPK != null && !this.tradeItemExchOptPK.equals(other.tradeItemExchOptPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TradeItemExchOpt[ tradeItemExchOptPK=" + tradeItemExchOptPK + " ]";
    }
    
}
