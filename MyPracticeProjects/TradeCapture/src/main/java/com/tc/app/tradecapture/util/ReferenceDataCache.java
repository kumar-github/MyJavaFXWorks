package com.tc.app.tradecapture.util;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.tc.app.tradecapture.controller.Account;
import com.tc.app.tradecapture.controller.ContractStatus;
import com.tc.app.tradecapture.controller.ExecutionType;
import com.tc.app.tradecapture.controller.IctsUser;
import com.tc.app.tradecapture.controller.OrderInstruction;
import com.tc.app.tradecapture.controller.Portfolio;
import com.tc.app.tradecapture.controller.Uom;

public class ReferenceDataCache
{
	//	private static List<IctsUser> ictsUsersList = null;
	//	private static List<IctsUser> tradersList = null;
	//	private static List<Account> counterPartiesList = null;

	private static ConcurrentMap<String, IctsUser> ictsUsersReferenceDataHashMap = null;
	private static ConcurrentMap<String, IctsUser> tradersReferenceDataHashMap = null;
	private static ConcurrentMap<Integer, Account> counterPartiesReferenceDataHashMap = null;
	private static ConcurrentMap<String, ContractStatus> contractStatusesReferenceDataHashMap = null;
	private static ConcurrentMap<String, IctsUser> contractAnalystsReferenceDataHashMap = null;
	private static ConcurrentMap<Integer, Account> bookingCompaniesReferenceDataHashMap = null;
	private static ConcurrentMap<Integer, Account> floorBrokersReferenceDataHashMap = null;
	private static ConcurrentMap<String, OrderInstruction> orderInstructionsReferenceDataHashMap = null;
	private static ConcurrentMap<String, ExecutionType> executionTypesReferenceDataHashMap = null;
	private static ConcurrentMap<String, Uom> uomsReferenceDataHashMap = null;
	private static ConcurrentMap<Integer, Portfolio> portfoliosReferenceDataHashMap = null;
	private static ConcurrentMap<Integer, Account> clearingHousesReferenceDataHashMap = null;
	private static ConcurrentMap<Integer, Account> clearingServicesReferenceDataHashMap = null;
	private static ConcurrentMap<Integer, Account> financingBanksReferenceDataHashMap = null;

	/*public static void loadTraders()
	{
		if(tradersList == null)
			tradersList = HibernateReferenceDataFetchUtil.fetchIctsUsersByUserJobTitle("TRADER");
	}

	public static void loadIctsUsers()
	{
		if(ictsUsersList == null)
			ictsUsersList = HibernateReferenceDataFetchUtil.fetchAllIctsUsers();
	}*/

	/*public static List<IctsUser> fetchTraders()
	{
		if(tradersList == null)
			tradersList = HibernateReferenceDataFetchUtil.fetchIctsUsersByUserJobTitle("TRADER");
		return tradersList;
	}

	public static List<IctsUser> fetchctsUsers()
	{
		if(ictsUsersList == null)
			ictsUsersList = HibernateReferenceDataFetchUtil.fetchAllIctsUsers();
		return ictsUsersList;
	}

	public static List fetchCounterParties()
	{
		if(counterPartiesList == null)
			counterPartiesList = HibernateReferenceDataFetchUtil.fetchAccountsByAcctTypeCode("CUSTOMER");
		return counterPartiesList;
	}*/
	
	public static ConcurrentMap<String, IctsUser> fetchTraders()
	{
		if(tradersReferenceDataHashMap == null)
		{
			loadTradersReferenceData();
		}
		return tradersReferenceDataHashMap;
	}

	public static ConcurrentMap<String, IctsUser> fetchIctsUsers()
	{
		if(ictsUsersReferenceDataHashMap == null)
		{
			loadIctsUsersReferenceData();
		}
		return ictsUsersReferenceDataHashMap;
	}

	public static ConcurrentMap<Integer, Account> fetchCounterParties()
	{
		if(counterPartiesReferenceDataHashMap == null)
		{
			loadCounterPartiesReferenceData();
		}
		return counterPartiesReferenceDataHashMap;
	}
	
	public static ConcurrentMap<String, ContractStatus> fetchContractStatuses()
	{
		if(contractStatusesReferenceDataHashMap == null)
		{
			loadContractStatusesReferenceData();
		}
		return contractStatusesReferenceDataHashMap;
	}
	
	public static ConcurrentMap<String, IctsUser> fetchContractAnalysts()
	{
		if(contractAnalystsReferenceDataHashMap == null)
		{
			loadContractAnalystsReferenceData();
		}
		return contractAnalystsReferenceDataHashMap;
	}
	
	public static ConcurrentMap<Integer, Account> fetchBookingCompanies()
	{
		if(bookingCompaniesReferenceDataHashMap == null)
		{
			loadBookingCompaniesReferenceData();
		}
		return bookingCompaniesReferenceDataHashMap;
	}
	
	public static ConcurrentMap<Integer, Account> fetchFloorBrokers()
	{
		if(floorBrokersReferenceDataHashMap == null)
		{
			loadFloorBrokersReferenceData();
		}
		return floorBrokersReferenceDataHashMap;
	}
	
	public static ConcurrentMap<String, OrderInstruction> fetchOrderInstructions()
	{
		if(orderInstructionsReferenceDataHashMap == null)
		{
			loadOrderInstructionsReferenceData();
		}
		return orderInstructionsReferenceDataHashMap;
	}
	
	public static ConcurrentMap<String, ExecutionType> fetchExecutionTypes()
	{
		if(executionTypesReferenceDataHashMap == null)
		{
			loadExecutionTypesReferenceData();
		}
		return executionTypesReferenceDataHashMap;
	}
	
	public static ConcurrentMap<String, Uom> fetchUoms()
	{
		if(uomsReferenceDataHashMap == null)
		{
			loadUomsReferenceData();
		}
		return uomsReferenceDataHashMap;
	}
	
	public static ConcurrentMap<Integer, Portfolio> fetchPortfolios()
	{
		if(portfoliosReferenceDataHashMap == null)
		{
			loadPortfoliosReferenceData();
		}
		return portfoliosReferenceDataHashMap;
	}
	
	public static ConcurrentMap<Integer, Account> fetchClearingHouses()
	{
		if(clearingHousesReferenceDataHashMap == null)
		{
			loadClearingHousesReferenceData();
		}
		return clearingHousesReferenceDataHashMap;
	}
	
	public static ConcurrentMap<Integer, Account> fetchClearingServices()
	{
		if(clearingServicesReferenceDataHashMap == null)
		{
			loadClearingServicesReferenceData();
		}
		return clearingServicesReferenceDataHashMap;
	}
	
	public static ConcurrentMap<Integer, Account> fetchFinancingBanks()
	{
		if(financingBanksReferenceDataHashMap == null)
		{
			loadFinancingBanksReferenceData();
		}
		return financingBanksReferenceDataHashMap;
	}
	
	public static void loadAllReferenceData()
	{
		loadIctsUsersReferenceData();
		loadTradersReferenceData();
		loadCounterPartiesReferenceData();
		loadContractStatusesReferenceData();
		loadContractAnalystsReferenceData();
		loadBookingCompaniesReferenceData();
		loadFloorBrokersReferenceData();
		loadOrderInstructionsReferenceData();
		loadExecutionTypesReferenceData();
		loadUomsReferenceData();
		loadPortfoliosReferenceData();
		loadClearingHousesReferenceData();
		loadClearingServicesReferenceData();
		loadFinancingBanksReferenceData();
	}
	
	private static void loadIctsUsersReferenceData()
	{
		if(ictsUsersReferenceDataHashMap == null)
		{
			ictsUsersReferenceDataHashMap = new ConcurrentHashMap<String, IctsUser>();
			long startTime = System.currentTimeMillis();
			List<IctsUser> ictsUsersList = HibernateReferenceDataFetchUtil.fetchAllIctsUsers();
			long endTime = System.currentTimeMillis();
			
			System.out.println("for fetching icts users : " + (endTime - startTime) + "Count : " + ictsUsersList.size());
			
			if(ictsUsersList != null)
			{
				for(IctsUser anIctsUser : ictsUsersList)
				{
					ictsUsersReferenceDataHashMap.put(anIctsUser.getUserInit(), anIctsUser);
				}
			}
		}
	}
	
	private static void loadTradersReferenceData()
	{
		if(tradersReferenceDataHashMap == null)
		{
			tradersReferenceDataHashMap = new ConcurrentHashMap<String, IctsUser>();
			
			long startTime = System.currentTimeMillis();
			List<IctsUser> tradersList = HibernateReferenceDataFetchUtil.fetchIctsUsersByUserJobTitle("TRADER");
			long endTime = System.currentTimeMillis();
			System.out.println("for fetching traders : " + (endTime - startTime) + "Count : " + tradersList.size());
			
			if(tradersList != null)
			{
				for(IctsUser aTrader : tradersList)
				{
					tradersReferenceDataHashMap.put(aTrader.getUserInit(), aTrader);
				}
			}
		}
	}
	
	private static void loadCounterPartiesReferenceData()
	{
		if(counterPartiesReferenceDataHashMap == null)
		{
			counterPartiesReferenceDataHashMap = new ConcurrentHashMap<Integer, Account>();
			
			long startTime = System.currentTimeMillis();
			List<Account> counterPartiesList = HibernateReferenceDataFetchUtil.fetchAccountsByAcctTypeCode("CUSTOMER");
			long endTime = System.currentTimeMillis();
			System.out.println("for fetching counterparties : " + (endTime - startTime) + "Count : " + counterPartiesList.size());
			
			if(counterPartiesList != null)
			{
				for(Account aCounterParty : counterPartiesList)
				{
					counterPartiesReferenceDataHashMap.put(aCounterParty.getAcctNum(), aCounterParty);
				}
			}
		}
	}
	
	private static void loadContractStatusesReferenceData()
	{
		if(contractStatusesReferenceDataHashMap == null)
		{
			contractStatusesReferenceDataHashMap = new ConcurrentHashMap<String, ContractStatus>();

			long startTime = System.currentTimeMillis();
			List<ContractStatus> contractStatusesList = HibernateReferenceDataFetchUtil.fetchContractStatuses();
			long endTime = System.currentTimeMillis();
			System.out.println("for fetching contract staus : " + (endTime - startTime) + "Count : " + contractStatusesList.size());
			
			if(contractStatusesList != null)
			{
				for(ContractStatus aContractStatus : contractStatusesList)
				{
					contractStatusesReferenceDataHashMap.put(aContractStatus.getContrStatusCode(), aContractStatus);
				}
			}
		}
	}
	
	private static void loadContractAnalystsReferenceData()
	{
		if(contractAnalystsReferenceDataHashMap == null)
		{
			contractAnalystsReferenceDataHashMap = new ConcurrentHashMap<String, IctsUser>();
			
			long startTime = System.currentTimeMillis();
			List<IctsUser> contractAnalystsList = HibernateReferenceDataFetchUtil.fetchIctsUsersByUserJobTitle("CONTRACT ANALYST");
			long endTime = System.currentTimeMillis();
			System.out.println("for fetching ContractAnalysts : " + (endTime - startTime) + "Count : " + contractAnalystsList.size());
			
			if(contractAnalystsList != null)
			{
				for(IctsUser acontractAnalyst : contractAnalystsList)
				{
					contractAnalystsReferenceDataHashMap.put(acontractAnalyst.getUserInit(), acontractAnalyst);
				}
			}
		}
	}
	
	private static void loadBookingCompaniesReferenceData()
	{
		if(bookingCompaniesReferenceDataHashMap == null)
		{
			bookingCompaniesReferenceDataHashMap = new ConcurrentHashMap<Integer, Account>();
			
			long startTime = System.currentTimeMillis();
			List<Account> bookingCompaniesList = HibernateReferenceDataFetchUtil.fetchAccountsByAcctTypeCode("PEICOMP");
			long endTime = System.currentTimeMillis();
			System.out.println("for fetching BookingCompanies : " + (endTime - startTime) + "Count : " + bookingCompaniesList.size());
			
			if(bookingCompaniesList != null)
			{
				for(Account aBookingCompany : bookingCompaniesList)
				{
					bookingCompaniesReferenceDataHashMap.put(aBookingCompany.getAcctNum(), aBookingCompany);
				}
			}
		}
	}
	
	private static void loadFloorBrokersReferenceData()
	{
		if(floorBrokersReferenceDataHashMap == null)
		{
			floorBrokersReferenceDataHashMap = new ConcurrentHashMap<Integer, Account>();
			
			long startTime = System.currentTimeMillis();
			List<Account> floorBrokersList = HibernateReferenceDataFetchUtil.fetchAccountsByAcctTypeCode("EXCHBRKR");
			long endTime = System.currentTimeMillis();
			System.out.println("for fetching FloorBrokers : " + (endTime - startTime) + "Count : " + floorBrokersList.size());
			
			if(floorBrokersList != null)
			{
				for(Account aFloorBroker : floorBrokersList)
				{
					floorBrokersReferenceDataHashMap.put(aFloorBroker.getAcctNum(), aFloorBroker);
				}
			}
		}
	}
	
	private static void loadOrderInstructionsReferenceData()
	{
		if(orderInstructionsReferenceDataHashMap == null)
		{
			orderInstructionsReferenceDataHashMap = new ConcurrentHashMap<String, OrderInstruction>();
			
			long startTime = System.currentTimeMillis();
			List<OrderInstruction> orderInstructionsList = HibernateReferenceDataFetchUtil.fetchOrderInstructions();
			long endTime = System.currentTimeMillis();
			System.out.println("for fetching OrderInstructions : " + (endTime - startTime) + "Count : " + orderInstructionsList.size());
			
			if(orderInstructionsList != null)
			{
				for(OrderInstruction anOrderInstruction : orderInstructionsList)
				{
					orderInstructionsReferenceDataHashMap.put(anOrderInstruction.getOrderInstrCode(), anOrderInstruction);
				}
			}
		}
	}
	
	private static void loadExecutionTypesReferenceData()
	{
		if(executionTypesReferenceDataHashMap == null)
		{
			executionTypesReferenceDataHashMap = new ConcurrentHashMap<String, ExecutionType>();
			
			long startTime = System.currentTimeMillis();
			List<ExecutionType> executionTypesList = HibernateReferenceDataFetchUtil.fetchExecutionTypes();
			long endTime = System.currentTimeMillis();
			System.out.println("for fetching ExecutionTypes : " + (endTime - startTime) + "Count : " + executionTypesList.size());
			
			if(executionTypesList != null)
			{
				for(ExecutionType anExecutionType : executionTypesList)
				{
					executionTypesReferenceDataHashMap.put(anExecutionType.getExecTypeCode(), anExecutionType);
				}
			}
		}
	}
	
	private static void loadUomsReferenceData()
	{
		if(uomsReferenceDataHashMap == null)
		{
			uomsReferenceDataHashMap = new ConcurrentHashMap<String, Uom>();
			
			long startTime = System.currentTimeMillis();
			List<Uom> uomsList = HibernateReferenceDataFetchUtil.fetchUoms();
			long endTime = System.currentTimeMillis();
			System.out.println("for fetching Uoms : " + (endTime - startTime) + "Count : " + uomsList.size());
			
			if(uomsList != null)
			{
				for(Uom aUom : uomsList)
				{
					uomsReferenceDataHashMap.put(aUom.getUomCode(), aUom);
				}
			}
		}
	}
	
	private static void loadPortfoliosReferenceData()
	{
		if(portfoliosReferenceDataHashMap == null)
		{
			portfoliosReferenceDataHashMap = new ConcurrentHashMap<Integer, Portfolio>();
			
			long startTime = System.currentTimeMillis();
			List<Portfolio> portfoliosList = HibernateReferenceDataFetchUtil.fetchPortfolios();
			long endTime = System.currentTimeMillis();
			System.out.println("for fetching Portfolios : " + (endTime - startTime) + "Count : " + portfoliosList.size());
			
			if(portfoliosList != null)
			{
				for(Portfolio aPortfolio : portfoliosList)
				{
					portfoliosReferenceDataHashMap.put(aPortfolio.getPortNum(), aPortfolio);
				}
			}
		}
	}
	
	private static void loadClearingHousesReferenceData()
	{
		if(clearingHousesReferenceDataHashMap == null)
		{
			clearingHousesReferenceDataHashMap = new ConcurrentHashMap<Integer, Account>();
			
			long startTime = System.currentTimeMillis();
			List<Account> clearingHousesList = HibernateReferenceDataFetchUtil.fetchAccountsByAcctTypeCode("EXCHBRKR");
			long endTime = System.currentTimeMillis();
			System.out.println("for fetching ClearingHouses : " + (endTime - startTime) + "Count : " + clearingHousesList.size());
			
			if(clearingHousesList != null)
			{
				for(Account aClearingHouse : clearingHousesList)
				{
					clearingHousesReferenceDataHashMap.put(aClearingHouse.getAcctNum(), aClearingHouse);
				}
			}
		}
	}
	
	private static void loadClearingServicesReferenceData()
	{
		if(clearingServicesReferenceDataHashMap == null)
		{
			clearingServicesReferenceDataHashMap = new ConcurrentHashMap<Integer, Account>();
			
			long startTime = System.currentTimeMillis();
			List<Account> clearingServicesList = HibernateReferenceDataFetchUtil.fetchAccountsByAcctTypeCode("CLRSRVC");
			long endTime = System.currentTimeMillis();
			System.out.println("for fetching ClearingServices : " + (endTime - startTime) + "Count : " + clearingServicesList.size());
			
			if(clearingServicesList != null)
			{
				for(Account aClearingService : clearingServicesList)
				{
					clearingServicesReferenceDataHashMap.put(aClearingService.getAcctNum(), aClearingService);
				}
			}
		}
	}
	
	private static void loadFinancingBanksReferenceData()
	{
		if(financingBanksReferenceDataHashMap == null)
		{
			financingBanksReferenceDataHashMap = new ConcurrentHashMap<Integer, Account>();
			
			long startTime = System.currentTimeMillis();
			List<Account> financingBanksList = HibernateReferenceDataFetchUtil.fetchAccountsByAcctTypeCode("PEBANK");
			long endTime = System.currentTimeMillis();
			System.out.println("for fetching FinancingBanks : " + (endTime - startTime) + "Count : " + financingBanksList.size());
			
			if(financingBanksList != null)
			{
				for(Account aFinancingBank : financingBanksList)
				{
					financingBanksReferenceDataHashMap.put(aFinancingBank.getAcctNum(), aFinancingBank);
				}
			}
		}
	}

}