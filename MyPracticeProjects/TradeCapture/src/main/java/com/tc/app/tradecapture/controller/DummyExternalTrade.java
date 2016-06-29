package com.tc.app.exchangemonitor.controller;

import java.util.Date;

public class DummyExternalTrade
{
	private Number oid;
	private Date creationDate;
	private Date entryDate;
	private Number externalTradeSystemOid;
	private Number externalTradeStatusOid;
	private Number externalTradeSourceOid;
	private Number externalTradeStateOid;
	private Number tradeNum;
	private Number portNum;
	private String commentText;
	private String exchToolsTradeNum;
	private String commodity;
	private String tradingPeriod;
	private String callPut;
	//private Double strikePrice;
	private Number strikePrice;
	private Number quantity;
	private Number price;
	private String inputAction;
	private String inputCompany;
	private String inputTrader;
	private String acceptedAction;
	private String acceptedCompany;
	private String acceptedTrader;
	private String buyerAccount;
	private String tradeType;
	private String inputBroker;
	private String acceptedBroker;
	private String sellerClearingBroker;
	private String buyerClearingBroker;
	
	public DummyExternalTrade()
	{
	}
	
	public DummyExternalTrade(Number oid, Date creationDate, Date entryDate, Number externalTradeSystemOid, Number externalTradeStatusOid, Number externalTradeSourceOid, Number externalTradeStateOid, Number tradeNum, Number portNum, String commentText, String exchToolsTradeNum, String commodity, String tradingPeriod, String callPut, Number strikePrice, Number quantity, Number price, String inputAction, String inputCompany, String inputTrader, String acceptedAction, String acceptedCompany, String acceptedTrader, String buyerAccount, String tradeType, String inputBroker, String acceptedBroker, String sellerClearingBroker, String buyerClearingBroker)
	{
		this.oid = oid;
		this.creationDate = creationDate;
		this.entryDate = entryDate;
		this.externalTradeSystemOid = externalTradeSystemOid;
		this.externalTradeStatusOid = externalTradeStatusOid;
		this.externalTradeSourceOid = externalTradeSourceOid;
		this.externalTradeStateOid = externalTradeStateOid;
		this.tradeNum = tradeNum;
		this.portNum = portNum;
		this.commentText = commentText;
		this.exchToolsTradeNum = exchToolsTradeNum;
		this.commodity = commodity;
		this.tradingPeriod = tradingPeriod;
		this.callPut = callPut;
		this.strikePrice = strikePrice;
		this.quantity = quantity;
		this.price = price;
		this.inputAction = inputAction;
		this.inputCompany = inputCompany;
		this.inputTrader = inputTrader;
		this.acceptedAction = acceptedAction;
		this.acceptedCompany = acceptedCompany;
		this.acceptedTrader = acceptedTrader;
		this.buyerAccount = buyerAccount;
		this.tradeType = tradeType;
		this.inputBroker = inputBroker;
		this.acceptedBroker = acceptedBroker;
		this.sellerClearingBroker = sellerClearingBroker;
		this.buyerClearingBroker = buyerClearingBroker;
	}

	public Number getOid()
	{
		return oid;
	}
	
	public void setOid(Number oid)
	{
		this.oid = oid;
	}
	
	public Date getCreationDate()
	{
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate)
	{
		this.creationDate = creationDate;
	}
	
	public Date getEntryDate()
	{
		return entryDate;
	}
	
	public void setEntryDate(Date entryDate)
	{
		this.entryDate = entryDate;
	}
	
	public Number getExternalTradeSystemOid()
	{
		return externalTradeSystemOid;
	}
	
	public void setExternalTradeSystemOid(Number externalTradeSystemOid)
	{
		this.externalTradeSystemOid = externalTradeSystemOid;
	}
	
	public Number getExternalTradeStatusOid()
	{
		return externalTradeStatusOid;
	}
	
	public void setExternalTradeStatusOid(Number externalTradeStatusOid)
	{
		this.externalTradeStatusOid = externalTradeStatusOid;
	}
	
	public Number getExternalTradeSourceOid()
	{
		return externalTradeSourceOid;
	}
	
	public void setExternalTradeSourceOid(Number externalTradeSourceOid)
	{
		this.externalTradeSourceOid = externalTradeSourceOid;
	}
	
	public Number getExternalTradeStateOid()
	{
		return externalTradeStateOid;
	}
	
	public void setExternalTradeStateOid(Number externalTradeStateOid)
	{
		this.externalTradeStateOid = externalTradeStateOid;
	}
	
	public Number getTradeNum()
	{
		return tradeNum;
	}
	
	public void setTradeNum(Number tradeNum)
	{
		this.tradeNum = tradeNum;
	}
	
	public Number getPortNum()
	{
		return portNum;
	}
	
	public void setPortNum(Number portNum)
	{
		this.portNum = portNum;
	}
	
	public String getCommentText()
	{
		return commentText;
	}
	
	public void setCommentText(String commentText)
	{
		this.commentText = commentText;
	}
	
	public String getExchToolsTradeNum()
	{
		return exchToolsTradeNum;
	}
	
	public void setExchToolsTradeNum(String exchToolsTradeNum)
	{
		this.exchToolsTradeNum = exchToolsTradeNum;
	}
	
	public String getCommodity()
	{
		return commodity;
	}
	
	public void setCommodity(String commodity)
	{
		this.commodity = commodity;
	}
	
	public String getTradingPeriod()
	{
		return tradingPeriod;
	}
	
	public void setTradingPeriod(String tradingPeriod)
	{
		this.tradingPeriod = tradingPeriod;
	}
	
	public String getCallPut()
	{
		return callPut;
	}
	
	public void setCallPut(String callPut)
	{
		this.callPut = callPut;
	}
	
	public Number getStrikePrice()
	{
		return strikePrice;
	}
	
	public void setStrikePrice(Number strikePrice)
	{
		this.strikePrice = strikePrice;
	}
	
	public Number getQuantity()
	{
		return quantity;
	}
	
	public void setQuantity(Number quantity)
	{
		this.quantity = quantity;
	}
	
	public Number getPrice()
	{
		return price;
	}
	
	public void setPrice(Number price)
	{
		this.price = price;
	}
	
	public String getInputAction()
	{
		return inputAction;
	}
	
	public void setInputAction(String inputAction)
	{
		this.inputAction = inputAction;
	}
	
	public String getInputCompany()
	{
		return inputCompany;
	}
	
	public void setInputCompany(String inputCompany)
	{
		this.inputCompany = inputCompany;
	}
	
	public String getInputTrader()
	{
		return inputTrader;
	}
	
	public void setInputTrader(String inputTrader)
	{
		this.inputTrader = inputTrader;
	}
	
	public String getAcceptedAction()
	{
		return acceptedAction;
	}
	
	public void setAcceptedAction(String acceptedAction)
	{
		this.acceptedAction = acceptedAction;
	}
	
	public String getAcceptedCompany()
	{
		return acceptedCompany;
	}
	
	public void setAcceptedCompany(String acceptedCompany)
	{
		this.acceptedCompany = acceptedCompany;
	}
	
	public String getAcceptedTrader()
	{
		return acceptedTrader;
	}
	
	public void setAcceptedTrader(String acceptedTrader)
	{
		this.acceptedTrader = acceptedTrader;
	}
	
	public String getBuyerAccount()
	{
		return buyerAccount;
	}
	
	public void setBuyerAccount(String buyerAccount)
	{
		this.buyerAccount = buyerAccount;
	}
	
	public String getTradeType()
	{
		return tradeType;
	}
	
	public void setTradeType(String tradeType)
	{
		this.tradeType = tradeType;
	}
	
	public String getInputBroker()
	{
		return inputBroker;
	}
	
	public void setInputBroker(String inputBroker)
	{
		this.inputBroker = inputBroker;
	}
	
	public String getAcceptedBroker()
	{
		return acceptedBroker;
	}
	
	public void setAcceptedBroker(String acceptedBroker)
	{
		this.acceptedBroker = acceptedBroker;
	}
	
	public String getSellerClearingBroker()
	{
		return sellerClearingBroker;
	}
	
	public void setSellerClearingBroker(String sellerClearingBroker)
	{
		this.sellerClearingBroker = sellerClearingBroker;
	}
	
	public String getBuyerClearingBroker()
	{
		return buyerClearingBroker;
	}
	
	public void setBuyerClearingBroker(String buyerClearingBroker)
	{
		this.buyerClearingBroker = buyerClearingBroker;
	}
}