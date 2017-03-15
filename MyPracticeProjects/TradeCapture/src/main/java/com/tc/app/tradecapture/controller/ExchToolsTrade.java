/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tc.app.tradecapture.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
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
 * @author Saravana Kumar M
 */
@Entity
@Table(name = "exch_tools_trade", catalog = "DEV_31_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExchToolsTrade.findAll", query = "SELECT e FROM ExchToolsTrade e"),
    @NamedQuery(name = "ExchToolsTrade.findByExternalTradeOid", query = "SELECT e FROM ExchToolsTrade e WHERE e.externalTradeOid = :externalTradeOid"),
    @NamedQuery(name = "ExchToolsTrade.findByAcceptedAction", query = "SELECT e FROM ExchToolsTrade e WHERE e.acceptedAction = :acceptedAction"),
    @NamedQuery(name = "ExchToolsTrade.findByAcceptedBroker", query = "SELECT e FROM ExchToolsTrade e WHERE e.acceptedBroker = :acceptedBroker"),
    @NamedQuery(name = "ExchToolsTrade.findByAcceptedCompany", query = "SELECT e FROM ExchToolsTrade e WHERE e.acceptedCompany = :acceptedCompany"),
    @NamedQuery(name = "ExchToolsTrade.findByAcceptedTrader", query = "SELECT e FROM ExchToolsTrade e WHERE e.acceptedTrader = :acceptedTrader"),
    @NamedQuery(name = "ExchToolsTrade.findByBuyerAccount", query = "SELECT e FROM ExchToolsTrade e WHERE e.buyerAccount = :buyerAccount"),
    @NamedQuery(name = "ExchToolsTrade.findByCommodity", query = "SELECT e FROM ExchToolsTrade e WHERE e.commodity = :commodity"),
    @NamedQuery(name = "ExchToolsTrade.findByCreationDate", query = "SELECT e FROM ExchToolsTrade e WHERE e.creationDate = :creationDate"),
    @NamedQuery(name = "ExchToolsTrade.findByExchToolsTradeNum", query = "SELECT e FROM ExchToolsTrade e WHERE e.exchToolsTradeNum = :exchToolsTradeNum"),
    @NamedQuery(name = "ExchToolsTrade.findByInputAction", query = "SELECT e FROM ExchToolsTrade e WHERE e.inputAction = :inputAction"),
    @NamedQuery(name = "ExchToolsTrade.findByInputBroker", query = "SELECT e FROM ExchToolsTrade e WHERE e.inputBroker = :inputBroker"),
    @NamedQuery(name = "ExchToolsTrade.findByInputCompany", query = "SELECT e FROM ExchToolsTrade e WHERE e.inputCompany = :inputCompany"),
    @NamedQuery(name = "ExchToolsTrade.findByInputTrader", query = "SELECT e FROM ExchToolsTrade e WHERE e.inputTrader = :inputTrader"),
    @NamedQuery(name = "ExchToolsTrade.findByPrice", query = "SELECT e FROM ExchToolsTrade e WHERE e.price = :price"),
    @NamedQuery(name = "ExchToolsTrade.findByQuantity", query = "SELECT e FROM ExchToolsTrade e WHERE e.quantity = :quantity"),
    @NamedQuery(name = "ExchToolsTrade.findBySellerAccount", query = "SELECT e FROM ExchToolsTrade e WHERE e.sellerAccount = :sellerAccount"),
    @NamedQuery(name = "ExchToolsTrade.findByTradingPeriod", query = "SELECT e FROM ExchToolsTrade e WHERE e.tradingPeriod = :tradingPeriod"),
    @NamedQuery(name = "ExchToolsTrade.findByBeginDate", query = "SELECT e FROM ExchToolsTrade e WHERE e.beginDate = :beginDate"),
    @NamedQuery(name = "ExchToolsTrade.findByEndDate", query = "SELECT e FROM ExchToolsTrade e WHERE e.endDate = :endDate"),
    @NamedQuery(name = "ExchToolsTrade.findByCallPut", query = "SELECT e FROM ExchToolsTrade e WHERE e.callPut = :callPut"),
    @NamedQuery(name = "ExchToolsTrade.findByStrikePrice", query = "SELECT e FROM ExchToolsTrade e WHERE e.strikePrice = :strikePrice"),
    @NamedQuery(name = "ExchToolsTrade.findByBuyerCommCost", query = "SELECT e FROM ExchToolsTrade e WHERE e.buyerCommCost = :buyerCommCost"),
    @NamedQuery(name = "ExchToolsTrade.findByBuyerCommCurr", query = "SELECT e FROM ExchToolsTrade e WHERE e.buyerCommCurr = :buyerCommCurr"),
    @NamedQuery(name = "ExchToolsTrade.findBySellerCommCost", query = "SELECT e FROM ExchToolsTrade e WHERE e.sellerCommCost = :sellerCommCost"),
    @NamedQuery(name = "ExchToolsTrade.findBySellerCommCurr", query = "SELECT e FROM ExchToolsTrade e WHERE e.sellerCommCurr = :sellerCommCurr"),
    @NamedQuery(name = "ExchToolsTrade.findByTransId", query = "SELECT e FROM ExchToolsTrade e WHERE e.transId = :transId"),
    @NamedQuery(name = "ExchToolsTrade.findByBuyerClrngBroker", query = "SELECT e FROM ExchToolsTrade e WHERE e.buyerClrngBroker = :buyerClrngBroker"),
    @NamedQuery(name = "ExchToolsTrade.findBySellerClrngBroker", query = "SELECT e FROM ExchToolsTrade e WHERE e.sellerClrngBroker = :sellerClrngBroker"),
    @NamedQuery(name = "ExchToolsTrade.findByAcctContact", query = "SELECT e FROM ExchToolsTrade e WHERE e.acctContact = :acctContact"),
    @NamedQuery(name = "ExchToolsTrade.findByGtc", query = "SELECT e FROM ExchToolsTrade e WHERE e.gtc = :gtc"),
    @NamedQuery(name = "ExchToolsTrade.findByTradeType", query = "SELECT e FROM ExchToolsTrade e WHERE e.tradeType = :tradeType"),
    @NamedQuery(name = "ExchToolsTrade.findByRiskMarket", query = "SELECT e FROM ExchToolsTrade e WHERE e.riskMarket = :riskMarket"),
    @NamedQuery(name = "ExchToolsTrade.findByTitleMarket", query = "SELECT e FROM ExchToolsTrade e WHERE e.titleMarket = :titleMarket"),
    @NamedQuery(name = "ExchToolsTrade.findByQtyUom", query = "SELECT e FROM ExchToolsTrade e WHERE e.qtyUom = :qtyUom"),
    @NamedQuery(name = "ExchToolsTrade.findByDelDateFrom", query = "SELECT e FROM ExchToolsTrade e WHERE e.delDateFrom = :delDateFrom"),
    @NamedQuery(name = "ExchToolsTrade.findByDelDateTo", query = "SELECT e FROM ExchToolsTrade e WHERE e.delDateTo = :delDateTo"),
    @NamedQuery(name = "ExchToolsTrade.findByMot", query = "SELECT e FROM ExchToolsTrade e WHERE e.mot = :mot"),
    @NamedQuery(name = "ExchToolsTrade.findByTitleTransfer", query = "SELECT e FROM ExchToolsTrade e WHERE e.titleTransfer = :titleTransfer"),
    @NamedQuery(name = "ExchToolsTrade.findByPriceType", query = "SELECT e FROM ExchToolsTrade e WHERE e.priceType = :priceType"),
    @NamedQuery(name = "ExchToolsTrade.findByFormulaName", query = "SELECT e FROM ExchToolsTrade e WHERE e.formulaName = :formulaName"),
    @NamedQuery(name = "ExchToolsTrade.findByEventDeemedDate", query = "SELECT e FROM ExchToolsTrade e WHERE e.eventDeemedDate = :eventDeemedDate"),
    @NamedQuery(name = "ExchToolsTrade.findByPriceUom", query = "SELECT e FROM ExchToolsTrade e WHERE e.priceUom = :priceUom"),
    @NamedQuery(name = "ExchToolsTrade.findByPriceCurrency", query = "SELECT e FROM ExchToolsTrade e WHERE e.priceCurrency = :priceCurrency"),
    @NamedQuery(name = "ExchToolsTrade.findByTemplateTradeNum", query = "SELECT e FROM ExchToolsTrade e WHERE e.templateTradeNum = :templateTradeNum"),
    @NamedQuery(name = "ExchToolsTrade.findByFloatMarketQuote1", query = "SELECT e FROM ExchToolsTrade e WHERE e.floatMarketQuote1 = :floatMarketQuote1"),
    @NamedQuery(name = "ExchToolsTrade.findByFloatMarketQuote2", query = "SELECT e FROM ExchToolsTrade e WHERE e.floatMarketQuote2 = :floatMarketQuote2"),
    @NamedQuery(name = "ExchToolsTrade.findByFloatQty1", query = "SELECT e FROM ExchToolsTrade e WHERE e.floatQty1 = :floatQty1"),
    @NamedQuery(name = "ExchToolsTrade.findByFloatQty2", query = "SELECT e FROM ExchToolsTrade e WHERE e.floatQty2 = :floatQty2"),
    @NamedQuery(name = "ExchToolsTrade.findByPremiumDate", query = "SELECT e FROM ExchToolsTrade e WHERE e.premiumDate = :premiumDate"),
    @NamedQuery(name = "ExchToolsTrade.findByAutoExercInd", query = "SELECT e FROM ExchToolsTrade e WHERE e.autoExercInd = :autoExercInd"),
    @NamedQuery(name = "ExchToolsTrade.findByProductId", query = "SELECT e FROM ExchToolsTrade e WHERE e.productId = :productId")})
public class ExchToolsTrade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "external_trade_oid")
    private Integer externalTradeOid;
    
    @Basic(optional = false)
    @Column(name = "accepted_action", columnDefinition="CHAR")
    private String acceptedAction;
    
    @Column(name = "accepted_broker")
    private String acceptedBroker;
    @Basic(optional = false)
    @Column(name = "accepted_company")
    private String acceptedCompany;
    @Basic(optional = false)
    @Column(name = "accepted_trader")
    private String acceptedTrader;
    @Column(name = "buyer_account")
    private String buyerAccount;
    @Basic(optional = false)
    @Column(name = "commodity")
    private String commodity;
    @Basic(optional = false)
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Basic(optional = false)
    @Column(name = "exch_tools_trade_num")
    private String exchToolsTradeNum;
    
    @Basic(optional = false)
    @Column(name = "input_action", columnDefinition="CHAR")
    private String inputAction;
    
    @Column(name = "input_broker")
    private String inputBroker;
    @Basic(optional = false)
    @Column(name = "input_company")
    private String inputCompany;
    @Basic(optional = false)
    @Column(name = "input_trader")
    private String inputTrader;
    @Basic(optional = false)
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @Column(name = "quantity")
    private double quantity;
    @Column(name = "seller_account")
    private String sellerAccount;
    @Basic(optional = false)
    @Column(name = "trading_period")
    private String tradingPeriod;
    @Column(name = "begin_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date beginDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    
    @Column(name = "call_put", columnDefinition="CHAR")
    private String callPut;
    
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "strike_price")
    private Double strikePrice;
    @Column(name = "buyer_comm_cost")
    private Double buyerCommCost;
    
    @Column(name = "buyer_comm_curr", columnDefinition="CHAR")
    private String buyerCommCurr;
    
    @Column(name = "seller_comm_cost")
    private Double sellerCommCost;
    
    @Column(name = "seller_comm_curr", columnDefinition="CHAR")
    private String sellerCommCurr;
    
    @Basic(optional = false)
    @Column(name = "trans_id")
    private int transId;
    @Column(name = "buyer_clrng_broker")
    private String buyerClrngBroker;
    @Column(name = "seller_clrng_broker")
    private String sellerClrngBroker;
    @Column(name = "acct_contact")
    private String acctContact;
    @Column(name = "gtc")
    private String gtc;
    @Column(name = "trade_type")
    private String tradeType;
    
    @Column(name = "risk_market", columnDefinition="CHAR")
    private String riskMarket;
    
    @Column(name = "title_market", columnDefinition="CHAR")
    private String titleMarket;
    
    @Column(name = "qty_uom", columnDefinition="CHAR")
    private String qtyUom;
    
    @Column(name = "del_date_from")
    @Temporal(TemporalType.TIMESTAMP)
    private Date delDateFrom;
    @Column(name = "del_date_to")
    @Temporal(TemporalType.TIMESTAMP)
    private Date delDateTo;
    
    @Column(name = "mot", columnDefinition="CHAR")
    private String mot;
    
    @Column(name = "title_transfer")
    private String titleTransfer;
    @Column(name = "price_type")
    private Character priceType;
    @Column(name = "formula_name")
    private String formulaName;
    @Column(name = "event_deemed_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDeemedDate;
    
    @Column(name = "price_uom", columnDefinition="CHAR")
    private String priceUom;
    
    @Column(name = "price_currency", columnDefinition="CHAR")
    private String priceCurrency;
    
    @Column(name = "template_trade_num")
    private Integer templateTradeNum;
    @Column(name = "float_market_quote1")
    private String floatMarketQuote1;
    @Column(name = "float_market_quote2")
    private String floatMarketQuote2;
    @Column(name = "float_qty1")
    private BigDecimal floatQty1;
    @Column(name = "float_qty2")
    private BigDecimal floatQty2;
    @Column(name = "premium_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date premiumDate;
    @Column(name = "auto_exerc_ind")
    private Character autoExercInd;
    @Column(name = "product_id")
    private Integer productId;
    @JoinColumn(name = "external_comment_oid", referencedColumnName = "oid")
    @ManyToOne
    private ExternalComment externalCommentOid;
    @JoinColumn(name = "external_trade_oid", referencedColumnName = "oid", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private ExternalTrade externalTrade;

    public ExchToolsTrade() {
    }

    public ExchToolsTrade(Integer externalTradeOid) {
        this.externalTradeOid = externalTradeOid;
    }

    public ExchToolsTrade(Integer externalTradeOid, String acceptedAction, String acceptedCompany, String acceptedTrader, String commodity, Date creationDate, String exchToolsTradeNum, String inputAction, String inputCompany, String inputTrader, double price, double quantity, String tradingPeriod, int transId) {
        this.externalTradeOid = externalTradeOid;
        this.acceptedAction = acceptedAction;
        this.acceptedCompany = acceptedCompany;
        this.acceptedTrader = acceptedTrader;
        this.commodity = commodity;
        this.creationDate = creationDate;
        this.exchToolsTradeNum = exchToolsTradeNum;
        this.inputAction = inputAction;
        this.inputCompany = inputCompany;
        this.inputTrader = inputTrader;
        this.price = price;
        this.quantity = quantity;
        this.tradingPeriod = tradingPeriod;
        this.transId = transId;
    }

    public Integer getExternalTradeOid() {
        return externalTradeOid;
    }

    public void setExternalTradeOid(Integer externalTradeOid) {
        this.externalTradeOid = externalTradeOid;
    }

    public String getAcceptedAction() {
        return acceptedAction;
    }

    public void setAcceptedAction(String acceptedAction) {
        this.acceptedAction = acceptedAction;
    }

    public String getAcceptedBroker() {
        return acceptedBroker;
    }

    public void setAcceptedBroker(String acceptedBroker) {
        this.acceptedBroker = acceptedBroker;
    }

    public String getAcceptedCompany() {
        return acceptedCompany;
    }

    public void setAcceptedCompany(String acceptedCompany) {
        this.acceptedCompany = acceptedCompany;
    }

    public String getAcceptedTrader() {
        return acceptedTrader;
    }

    public void setAcceptedTrader(String acceptedTrader) {
        this.acceptedTrader = acceptedTrader;
    }

    public String getBuyerAccount() {
        return buyerAccount;
    }

    public void setBuyerAccount(String buyerAccount) {
        this.buyerAccount = buyerAccount;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getExchToolsTradeNum() {
        return exchToolsTradeNum;
    }

    public void setExchToolsTradeNum(String exchToolsTradeNum) {
        this.exchToolsTradeNum = exchToolsTradeNum;
    }

    public String getInputAction() {
        return inputAction;
    }

    public void setInputAction(String inputAction) {
        this.inputAction = inputAction;
    }

    public String getInputBroker() {
        return inputBroker;
    }

    public void setInputBroker(String inputBroker) {
        this.inputBroker = inputBroker;
    }

    public String getInputCompany() {
        return inputCompany;
    }

    public void setInputCompany(String inputCompany) {
        this.inputCompany = inputCompany;
    }

    public String getInputTrader() {
        return inputTrader;
    }

    public void setInputTrader(String inputTrader) {
        this.inputTrader = inputTrader;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getSellerAccount() {
        return sellerAccount;
    }

    public void setSellerAccount(String sellerAccount) {
        this.sellerAccount = sellerAccount;
    }

    public String getTradingPeriod() {
        return tradingPeriod;
    }

    public void setTradingPeriod(String tradingPeriod) {
        this.tradingPeriod = tradingPeriod;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCallPut() {
        return callPut;
    }

    public void setCallPut(String callPut) {
        this.callPut = callPut;
    }

    public Double getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
    }

    public Double getBuyerCommCost() {
        return buyerCommCost;
    }

    public void setBuyerCommCost(Double buyerCommCost) {
        this.buyerCommCost = buyerCommCost;
    }

    public String getBuyerCommCurr() {
        return buyerCommCurr;
    }

    public void setBuyerCommCurr(String buyerCommCurr) {
        this.buyerCommCurr = buyerCommCurr;
    }

    public Double getSellerCommCost() {
        return sellerCommCost;
    }

    public void setSellerCommCost(Double sellerCommCost) {
        this.sellerCommCost = sellerCommCost;
    }

    public String getSellerCommCurr() {
        return sellerCommCurr;
    }

    public void setSellerCommCurr(String sellerCommCurr) {
        this.sellerCommCurr = sellerCommCurr;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public String getBuyerClrngBroker() {
        return buyerClrngBroker;
    }

    public void setBuyerClrngBroker(String buyerClrngBroker) {
        this.buyerClrngBroker = buyerClrngBroker;
    }

    public String getSellerClrngBroker() {
        return sellerClrngBroker;
    }

    public void setSellerClrngBroker(String sellerClrngBroker) {
        this.sellerClrngBroker = sellerClrngBroker;
    }

    public String getAcctContact() {
        return acctContact;
    }

    public void setAcctContact(String acctContact) {
        this.acctContact = acctContact;
    }

    public String getGtc() {
        return gtc;
    }

    public void setGtc(String gtc) {
        this.gtc = gtc;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getRiskMarket() {
        return riskMarket;
    }

    public void setRiskMarket(String riskMarket) {
        this.riskMarket = riskMarket;
    }

    public String getTitleMarket() {
        return titleMarket;
    }

    public void setTitleMarket(String titleMarket) {
        this.titleMarket = titleMarket;
    }

    public String getQtyUom() {
        return qtyUom;
    }

    public void setQtyUom(String qtyUom) {
        this.qtyUom = qtyUom;
    }

    public Date getDelDateFrom() {
        return delDateFrom;
    }

    public void setDelDateFrom(Date delDateFrom) {
        this.delDateFrom = delDateFrom;
    }

    public Date getDelDateTo() {
        return delDateTo;
    }

    public void setDelDateTo(Date delDateTo) {
        this.delDateTo = delDateTo;
    }

    public String getMot() {
        return mot;
    }

    public void setMot(String mot) {
        this.mot = mot;
    }

    public String getTitleTransfer() {
        return titleTransfer;
    }

    public void setTitleTransfer(String titleTransfer) {
        this.titleTransfer = titleTransfer;
    }

    public Character getPriceType() {
        return priceType;
    }

    public void setPriceType(Character priceType) {
        this.priceType = priceType;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public Date getEventDeemedDate() {
        return eventDeemedDate;
    }

    public void setEventDeemedDate(Date eventDeemedDate) {
        this.eventDeemedDate = eventDeemedDate;
    }

    public String getPriceUom() {
        return priceUom;
    }

    public void setPriceUom(String priceUom) {
        this.priceUom = priceUom;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }

    public void setPriceCurrency(String priceCurrency) {
        this.priceCurrency = priceCurrency;
    }

    public Integer getTemplateTradeNum() {
        return templateTradeNum;
    }

    public void setTemplateTradeNum(Integer templateTradeNum) {
        this.templateTradeNum = templateTradeNum;
    }

    public String getFloatMarketQuote1() {
        return floatMarketQuote1;
    }

    public void setFloatMarketQuote1(String floatMarketQuote1) {
        this.floatMarketQuote1 = floatMarketQuote1;
    }

    public String getFloatMarketQuote2() {
        return floatMarketQuote2;
    }

    public void setFloatMarketQuote2(String floatMarketQuote2) {
        this.floatMarketQuote2 = floatMarketQuote2;
    }

    public BigDecimal getFloatQty1() {
        return floatQty1;
    }

    public void setFloatQty1(BigDecimal floatQty1) {
        this.floatQty1 = floatQty1;
    }

    public BigDecimal getFloatQty2() {
        return floatQty2;
    }

    public void setFloatQty2(BigDecimal floatQty2) {
        this.floatQty2 = floatQty2;
    }

    public Date getPremiumDate() {
        return premiumDate;
    }

    public void setPremiumDate(Date premiumDate) {
        this.premiumDate = premiumDate;
    }

    public Character getAutoExercInd() {
        return autoExercInd;
    }

    public void setAutoExercInd(Character autoExercInd) {
        this.autoExercInd = autoExercInd;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public ExternalComment getExternalCommentOid() {
        return externalCommentOid;
    }

    public void setExternalCommentOid(ExternalComment externalCommentOid) {
        this.externalCommentOid = externalCommentOid;
    }

    public ExternalTrade getExternalTrade() {
        return externalTrade;
    }

    public void setExternalTrade(ExternalTrade externalTrade) {
        this.externalTrade = externalTrade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (externalTradeOid != null ? externalTradeOid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExchToolsTrade)) {
            return false;
        }
        ExchToolsTrade other = (ExchToolsTrade) object;
        if ((this.externalTradeOid == null && other.externalTradeOid != null) || (this.externalTradeOid != null && !this.externalTradeOid.equals(other.externalTradeOid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExchToolsTrade[ externalTradeOid=" + externalTradeOid + " ]";
    }
    
}
