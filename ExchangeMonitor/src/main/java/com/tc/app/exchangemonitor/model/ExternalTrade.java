package com.tc.app.exchangemonitor.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.tc.app.exchangemonitor.entitybusiness.ExternalTradeBusiness;

@Entity
@Table(name = "external_trade", catalog = "QA_30_trade_Aug22", schema = "dbo")
public class ExternalTrade extends ExternalTradeBusiness
{
	public ExternalTrade()
	{
	}
}