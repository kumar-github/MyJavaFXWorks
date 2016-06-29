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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
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
 * @author smurugabushanam
 */
@Entity
@Table(name = "trade", catalog = "QA_30_trade", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trade.findAll", query = "SELECT t FROM Trade t"),
    @NamedQuery(name = "Trade.findByTradeNum", query = "SELECT t FROM Trade t WHERE t.tradeNum = :tradeNum"),
    @NamedQuery(name = "Trade.findByConclusionType", query = "SELECT t FROM Trade t WHERE t.conclusionType = :conclusionType"),
    @NamedQuery(name = "Trade.findByInhouseInd", query = "SELECT t FROM Trade t WHERE t.inhouseInd = :inhouseInd"),
    @NamedQuery(name = "Trade.findByAcctShortName", query = "SELECT t FROM Trade t WHERE t.acctShortName = :acctShortName"),
    @NamedQuery(name = "Trade.findByAcctRefNum", query = "SELECT t FROM Trade t WHERE t.acctRefNum = :acctRefNum"),
    @NamedQuery(name = "Trade.findByPortNum", query = "SELECT t FROM Trade t WHERE t.portNum = :portNum"),
    @NamedQuery(name = "Trade.findByConcludedDate", query = "SELECT t FROM Trade t WHERE t.concludedDate = :concludedDate"),
    @NamedQuery(name = "Trade.findByContrApprovType", query = "SELECT t FROM Trade t WHERE t.contrApprovType = :contrApprovType"),
    @NamedQuery(name = "Trade.findByContrDate", query = "SELECT t FROM Trade t WHERE t.contrDate = :contrDate"),
    @NamedQuery(name = "Trade.findByCpGovContrInd", query = "SELECT t FROM Trade t WHERE t.cpGovContrInd = :cpGovContrInd"),
    @NamedQuery(name = "Trade.findByContrExchMethod", query = "SELECT t FROM Trade t WHERE t.contrExchMethod = :contrExchMethod"),
    @NamedQuery(name = "Trade.findByContrCnfrmMethod", query = "SELECT t FROM Trade t WHERE t.contrCnfrmMethod = :contrCnfrmMethod"),
    @NamedQuery(name = "Trade.findByContrTlxHoldInd", query = "SELECT t FROM Trade t WHERE t.contrTlxHoldInd = :contrTlxHoldInd"),
    @NamedQuery(name = "Trade.findByCreationDate", query = "SELECT t FROM Trade t WHERE t.creationDate = :creationDate"),
    @NamedQuery(name = "Trade.findByTradeModDate", query = "SELECT t FROM Trade t WHERE t.tradeModDate = :tradeModDate"),
    @NamedQuery(name = "Trade.findByInvoiceCapType", query = "SELECT t FROM Trade t WHERE t.invoiceCapType = :invoiceCapType"),
    @NamedQuery(name = "Trade.findByInternalAgreementInd", query = "SELECT t FROM Trade t WHERE t.internalAgreementInd = :internalAgreementInd"),
    @NamedQuery(name = "Trade.findByCreditStatus", query = "SELECT t FROM Trade t WHERE t.creditStatus = :creditStatus"),
    @NamedQuery(name = "Trade.findByCreditResExpDate", query = "SELECT t FROM Trade t WHERE t.creditResExpDate = :creditResExpDate"),
    @NamedQuery(name = "Trade.findByContrAnlyInit", query = "SELECT t FROM Trade t WHERE t.contrAnlyInit = :contrAnlyInit"),
    @NamedQuery(name = "Trade.findByMaxOrderNum", query = "SELECT t FROM Trade t WHERE t.maxOrderNum = :maxOrderNum"),
    @NamedQuery(name = "Trade.findByIsLongTermInd", query = "SELECT t FROM Trade t WHERE t.isLongTermInd = :isLongTermInd"),
    @NamedQuery(name = "Trade.findBySpecialContractNum", query = "SELECT t FROM Trade t WHERE t.specialContractNum = :specialContractNum"),
    @NamedQuery(name = "Trade.findByCargoIdNumber", query = "SELECT t FROM Trade t WHERE t.cargoIdNumber = :cargoIdNumber"),
    @NamedQuery(name = "Trade.findByTransId", query = "SELECT t FROM Trade t WHERE t.transId = :transId"),
    @NamedQuery(name = "Trade.findByInternalParentTradeNum", query = "SELECT t FROM Trade t WHERE t.internalParentTradeNum = :internalParentTradeNum"),
    @NamedQuery(name = "Trade.findByCopyType", query = "SELECT t FROM Trade t WHERE t.copyType = :copyType"),
    @NamedQuery(name = "Trade.findByEconfirmStatus", query = "SELECT t FROM Trade t WHERE t.econfirmStatus = :econfirmStatus"),
    @NamedQuery(name = "Trade.findByExternalTradeType", query = "SELECT t FROM Trade t WHERE t.externalTradeType = :externalTradeType")})
public class Trade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "trade_num", nullable = false)
    private Integer tradeNum;
    @Column(name = "conclusion_type")
    private Character conclusionType;
    @Column(name = "inhouse_ind")
    private Character inhouseInd;
    
    @Column(name = "acct_short_name", length = 15, columnDefinition="NVARCHAR")
    private String acctShortName;
    
    @Column(name = "acct_ref_num", length = 40)
    private String acctRefNum;
    @Column(name = "port_num")
    private Integer portNum;
    @Column(name = "concluded_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date concludedDate;
    @Column(name = "contr_approv_type")
    private Character contrApprovType;
    @Column(name = "contr_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contrDate;
    @Column(name = "cp_gov_contr_ind")
    private Character cpGovContrInd;
    @Column(name = "contr_exch_method", length = 8)
    private String contrExchMethod;
    @Column(name = "contr_cnfrm_method", length = 8)
    private String contrCnfrmMethod;
    @Column(name = "contr_tlx_hold_ind")
    private Character contrTlxHoldInd;
    @Basic(optional = false)
    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "trade_mod_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tradeModDate;
    @Column(name = "invoice_cap_type")
    private Character invoiceCapType;
    @Column(name = "internal_agreement_ind")
    private Character internalAgreementInd;
    @Column(name = "credit_status")
    private Character creditStatus;
    @Column(name = "credit_res_exp_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creditResExpDate;
    
    @Column(name = "contr_anly_init", length = 8, columnDefinition="CHAR")
    private String contrAnlyInit;
    
    @Column(name = "max_order_num")
    private Short maxOrderNum;
    @Column(name = "is_long_term_ind")
    private Character isLongTermInd;
    @Column(name = "special_contract_num", length = 100)
    private String specialContractNum;
    @Column(name = "cargo_id_number", length = 16)
    private String cargoIdNumber;
    @Basic(optional = false)
    @Column(name = "trans_id", nullable = false)
    private int transId;
    @Column(name = "internal_parent_trade_num")
    private Integer internalParentTradeNum;
    
    @Column(name = "copy_type", length = 8, columnDefinition="CHAR")
    private String copyType;
    
    @Column(name = "econfirm_status", length = 17)
    private String econfirmStatus;
    @Column(name = "external_trade_type", length = 150)
    private String externalTradeType;
    
    @JoinColumn(name = "acct_num", referencedColumnName = "acct_num", insertable=false, updatable=false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Account acctNum;
    
    @JoinColumns({
        @JoinColumn(name = "acct_num", referencedColumnName = "acct_num"),
        @JoinColumn(name = "acct_cont_num", referencedColumnName = "acct_cont_num")})
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountContact accountContact;
    
    @JoinColumn(name = "contr_status_code", referencedColumnName = "contr_status_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private ContractStatus contrStatusCode;
    
    @JoinColumn(name = "credit_term_code", referencedColumnName = "credit_term_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private CreditTerm creditTermCode;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private IctsProduct productId;
    @JoinColumn(name = "trader_init", referencedColumnName = "user_init")
    @ManyToOne(fetch = FetchType.LAZY)
    private IctsUser traderInit;
    @JoinColumn(name = "cr_anly_init", referencedColumnName = "user_init")
    @ManyToOne(fetch = FetchType.LAZY)
    private IctsUser crAnlyInit;
    @JoinColumn(name = "creator_init", referencedColumnName = "user_init", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private IctsUser creatorInit;
    @JoinColumn(name = "trade_mod_init", referencedColumnName = "user_init")
    @ManyToOne(fetch = FetchType.LAZY)
    private IctsUser tradeModInit;
    
    @JoinColumn(name = "trade_status_code", referencedColumnName = "trade_status_code", columnDefinition="CHAR")
    @ManyToOne(fetch = FetchType.LAZY)
    private TradeStatus tradeStatusCode;

    public Trade() {
    }

    public Trade(Integer tradeNum) {
        this.tradeNum = tradeNum;
    }

    public Trade(Integer tradeNum, Date creationDate, int transId) {
        this.tradeNum = tradeNum;
        this.creationDate = creationDate;
        this.transId = transId;
    }

    public Integer getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(Integer tradeNum) {
        this.tradeNum = tradeNum;
    }

    public Character getConclusionType() {
        return conclusionType;
    }

    public void setConclusionType(Character conclusionType) {
        this.conclusionType = conclusionType;
    }

    public Character getInhouseInd() {
        return inhouseInd;
    }

    public void setInhouseInd(Character inhouseInd) {
        this.inhouseInd = inhouseInd;
    }

    public String getAcctShortName() {
        return acctShortName;
    }

    public void setAcctShortName(String acctShortName) {
        this.acctShortName = acctShortName;
    }

    public String getAcctRefNum() {
        return acctRefNum;
    }

    public void setAcctRefNum(String acctRefNum) {
        this.acctRefNum = acctRefNum;
    }

    public Integer getPortNum() {
        return portNum;
    }

    public void setPortNum(Integer portNum) {
        this.portNum = portNum;
    }

    public Date getConcludedDate() {
        return concludedDate;
    }

    public void setConcludedDate(Date concludedDate) {
        this.concludedDate = concludedDate;
    }

    public Character getContrApprovType() {
        return contrApprovType;
    }

    public void setContrApprovType(Character contrApprovType) {
        this.contrApprovType = contrApprovType;
    }

    public Date getContrDate() {
        return contrDate;
    }

    public void setContrDate(Date contrDate) {
        this.contrDate = contrDate;
    }

    public Character getCpGovContrInd() {
        return cpGovContrInd;
    }

    public void setCpGovContrInd(Character cpGovContrInd) {
        this.cpGovContrInd = cpGovContrInd;
    }

    public String getContrExchMethod() {
        return contrExchMethod;
    }

    public void setContrExchMethod(String contrExchMethod) {
        this.contrExchMethod = contrExchMethod;
    }

    public String getContrCnfrmMethod() {
        return contrCnfrmMethod;
    }

    public void setContrCnfrmMethod(String contrCnfrmMethod) {
        this.contrCnfrmMethod = contrCnfrmMethod;
    }

    public Character getContrTlxHoldInd() {
        return contrTlxHoldInd;
    }

    public void setContrTlxHoldInd(Character contrTlxHoldInd) {
        this.contrTlxHoldInd = contrTlxHoldInd;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getTradeModDate() {
        return tradeModDate;
    }

    public void setTradeModDate(Date tradeModDate) {
        this.tradeModDate = tradeModDate;
    }

    public Character getInvoiceCapType() {
        return invoiceCapType;
    }

    public void setInvoiceCapType(Character invoiceCapType) {
        this.invoiceCapType = invoiceCapType;
    }

    public Character getInternalAgreementInd() {
        return internalAgreementInd;
    }

    public void setInternalAgreementInd(Character internalAgreementInd) {
        this.internalAgreementInd = internalAgreementInd;
    }

    public Character getCreditStatus() {
        return creditStatus;
    }

    public void setCreditStatus(Character creditStatus) {
        this.creditStatus = creditStatus;
    }

    public Date getCreditResExpDate() {
        return creditResExpDate;
    }

    public void setCreditResExpDate(Date creditResExpDate) {
        this.creditResExpDate = creditResExpDate;
    }

    public String getContrAnlyInit() {
        return contrAnlyInit;
    }

    public void setContrAnlyInit(String contrAnlyInit) {
        this.contrAnlyInit = contrAnlyInit;
    }

    public Short getMaxOrderNum() {
        return maxOrderNum;
    }

    public void setMaxOrderNum(Short maxOrderNum) {
        this.maxOrderNum = maxOrderNum;
    }

    public Character getIsLongTermInd() {
        return isLongTermInd;
    }

    public void setIsLongTermInd(Character isLongTermInd) {
        this.isLongTermInd = isLongTermInd;
    }

    public String getSpecialContractNum() {
        return specialContractNum;
    }

    public void setSpecialContractNum(String specialContractNum) {
        this.specialContractNum = specialContractNum;
    }

    public String getCargoIdNumber() {
        return cargoIdNumber;
    }

    public void setCargoIdNumber(String cargoIdNumber) {
        this.cargoIdNumber = cargoIdNumber;
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

    public String getCopyType() {
        return copyType;
    }

    public void setCopyType(String copyType) {
        this.copyType = copyType;
    }

    public String getEconfirmStatus() {
        return econfirmStatus;
    }

    public void setEconfirmStatus(String econfirmStatus) {
        this.econfirmStatus = econfirmStatus;
    }

    public String getExternalTradeType() {
        return externalTradeType;
    }

    public void setExternalTradeType(String externalTradeType) {
        this.externalTradeType = externalTradeType;
    }

    public Account getAcctNum() {
        return acctNum;
    }

    public void setAcctNum(Account acctNum) {
        this.acctNum = acctNum;
    }

    public AccountContact getAccountContact() {
        return accountContact;
    }

    public void setAccountContact(AccountContact accountContact) {
        this.accountContact = accountContact;
    }

    public ContractStatus getContrStatusCode() {
        return contrStatusCode;
    }

    public void setContrStatusCode(ContractStatus contrStatusCode) {
        this.contrStatusCode = contrStatusCode;
    }

    public CreditTerm getCreditTermCode() {
        return creditTermCode;
    }

    public void setCreditTermCode(CreditTerm creditTermCode) {
        this.creditTermCode = creditTermCode;
    }

    public IctsProduct getProductId() {
        return productId;
    }

    public void setProductId(IctsProduct productId) {
        this.productId = productId;
    }

    public IctsUser getTraderInit() {
        return traderInit;
    }

    public void setTraderInit(IctsUser traderInit) {
        this.traderInit = traderInit;
    }

    public IctsUser getCrAnlyInit() {
        return crAnlyInit;
    }

    public void setCrAnlyInit(IctsUser crAnlyInit) {
        this.crAnlyInit = crAnlyInit;
    }

    public IctsUser getCreatorInit() {
        return creatorInit;
    }

    public void setCreatorInit(IctsUser creatorInit) {
        this.creatorInit = creatorInit;
    }

    public IctsUser getTradeModInit() {
        return tradeModInit;
    }

    public void setTradeModInit(IctsUser tradeModInit) {
        this.tradeModInit = tradeModInit;
    }

    public TradeStatus getTradeStatusCode() {
        return tradeStatusCode;
    }

    public void setTradeStatusCode(TradeStatus tradeStatusCode) {
        this.tradeStatusCode = tradeStatusCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tradeNum != null ? tradeNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trade)) {
            return false;
        }
        Trade other = (Trade) object;
        if ((this.tradeNum == null && other.tradeNum != null) || (this.tradeNum != null && !this.tradeNum.equals(other.tradeNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generated6.Trade[ tradeNum=" + tradeNum + " ]";
    }
    
}
