package com.tc.app.exchangemonitor.util;

import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.tc.app.exchangemonitor.controller.Account;
import com.tc.app.exchangemonitor.controller.ContractStatus;
import com.tc.app.exchangemonitor.controller.ExecutionType;
import com.tc.app.exchangemonitor.controller.IctsUser;
import com.tc.app.exchangemonitor.controller.OrderInstruction;
import com.tc.app.exchangemonitor.controller.Portfolio;
import com.tc.app.exchangemonitor.controller.Trade;
import com.tc.app.exchangemonitor.controller.Uom;

public class HibernateReferenceDataFetchUtil
{
	public static List<IctsUser> fetchAllIctsUsers()
	{
		return fetchDataFromDBForNamedQuery("IctsUser.findAll");
	}
	
	public static List<IctsUser> fetchIctsUsersByUserStatus(String userStatus)
	{
		return fetchDataFromDBForNamedQuery("IctsUser.findByUserStatus", "userStatus", userStatus);
	}
	
	public static List<IctsUser> fetchIctsUsersByUserJobTitle(String userJobTitle)
	{
		return fetchDataFromDBForNamedQuery("IctsUser.findByUserJobTitle", "userJobTitle", userJobTitle);
	}
	
	public static List<Account> fetchAccountsByAcctTypeCode(String acctTypeCode)
	{
		return fetchDataFromDBForNamedQuery("Account.findByAcctTypeCode", "acctTypeCode", acctTypeCode);
	}
	
	public static List<ContractStatus> fetchContractStatuses()
	{
		return fetchDataFromDBForNamedQuery("ContractStatus.findAll");
	}
	
	public static List<OrderInstruction> fetchOrderInstructions()
	{
		return fetchDataFromDBForNamedQuery("OrderInstruction.findAll");
	}
	
	public static List<ExecutionType> fetchExecutionTypes()
	{
		return fetchDataFromDBForNamedQuery("ExecutionType.findAll");
	}
	
	public static List<Uom> fetchUoms()
	{
		return fetchDataFromDBForNamedQuery("Uom.findAllActiveUoms");
	}
	
	public static List<Portfolio> fetchPortfolios()
	{
		return fetchDataFromDBForNamedQuery("Portfolio.findByPortLockedAndPortType");
	}
	
	public static List<Trade> fetchTrade()
	{
		return fetchDataFromDBForNamedQuery("Trade.findByTradeNum", "tradeNum", "2479348");
	}
	
	public static List fetchDataFromDBForNamedQuery(String hibernateNamedQueryName)
	{
		List data = null;
		Session session = null;

		try
		{
			session = HibernateUtil.beginTransaction();
			//session = IctsEOSessionFactory.getFactory().editingContext().getSession();
			Query query = session.getNamedQuery(hibernateNamedQueryName);
			data = query.list();
		}
		catch(Exception localException)
		{
			throw localException;
		}
		finally
		{
			if(session!=null && session.isOpen())
			{
				if(session.getTransaction() != null && session.getTransaction().getStatus() == TransactionStatus.ACTIVE)
				{
					session.getTransaction().commit();//This is mandatory - to avoid DB locking
				}
			}
		}
		//HibernateUtil.commitTransaction();
		//HibernateUtil.closeSession();

		return data;
	}

	public static List fetchDataFromDBForNamedQuery(String hibernateNamedQueryName, String paramter, String argument)
	{
		List data = null;
		Session session = null;
		try
		{
			session = HibernateUtil.beginTransaction();
			//session = IctsEOSessionFactory.getFactory().editingContext().getSession();

			Query query = session.getNamedQuery(hibernateNamedQueryName);
			query.setString(paramter, argument);
			data = query.list();
		}
		catch(Exception localException)
		{
			throw localException;
		}
		finally
		{
			if(session!=null && session.isOpen())
			{
				if(session.getTransaction() != null && session.getTransaction().getStatus() == TransactionStatus.ACTIVE)
				{
					session.getTransaction().commit();//This is mandatory - to avoid DB locking
				}
			}
		}
		//HibernateUtil.commitTransaction();
		//HibernateUtil.closeSession();

		return data;
	}
}