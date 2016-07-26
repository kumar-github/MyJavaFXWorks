package com.tc.app.exchangemonitor.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.tc.app.exchangemonitor.model.ExternalMapping;
import com.tc.app.exchangemonitor.model.ExternalTradeSource;
import com.tc.app.exchangemonitor.model.ExternalTradeState;
import com.tc.app.exchangemonitor.model.ExternalTradeStatus;

public class ReferenceDataCache
{
	public ReferenceDataCache()
	{
	}

	public static void loadAllReferenceData()
	{
		loadExternalTradeSourceReferenceData();
		loadExternalTradeStateReferenceData();
		loadExternalTradeStatusReferenceData();
		loadExternalTradeAccountReferenceData();
		loadExternalMappingReferenceData();
	}
	
	public static ConcurrentMap<Integer, ExternalTradeSource> fetchExternalTradeSources()
	{
		if(externalTradeSourceReferenceDataHashMap == null)
		{
			loadExternalTradeSourceReferenceData();
		}
		return externalTradeSourceReferenceDataHashMap;
	}
	
	public static ConcurrentMap<Integer, ExternalTradeState> fetchExternalTradeStates()
	{
		if(externalTradeStateReferenceDataHashMap == null)
		{
			loadExternalTradeStateReferenceData();
		}
		return externalTradeStateReferenceDataHashMap;
	}
	
	public static ConcurrentMap<Integer, ExternalTradeStatus> fetchExternalTradeStatuses()
	{
		if(externalTradeStatusReferenceDataHashMap == null)
		{
			loadExternalTradeStatusReferenceData();
		}
		return externalTradeStatusReferenceDataHashMap;
	}
	
	public static ConcurrentMap<String, ExternalMapping> fetchExternalTradeAccounts()
	{
		if(externalTradeAccountReferenceDataHashMap == null)
		{
			loadExternalTradeAccountReferenceData();
		}
		return externalTradeAccountReferenceDataHashMap;
	}
	
	public static List<ExternalMapping> fetchExternalMappings()
	{
		if(externalMappingReferenceDataList == null)
		{
			loadExternalMappingReferenceData();
		}
		return externalMappingReferenceDataList;
	}
	
	/* Do we really need a map here? Think please...*/
	private static ConcurrentMap<Integer, ExternalTradeSource> externalTradeSourceReferenceDataHashMap = null;
	@SuppressWarnings("unchecked")
	public static void loadExternalTradeSourceReferenceData()
	{
		if(externalTradeSourceReferenceDataHashMap == null)
		{
			externalTradeSourceReferenceDataHashMap = new ConcurrentHashMap<Integer, ExternalTradeSource>();

			long startTime = System.currentTimeMillis();
			List<ExternalTradeSource> externalTradeSourceList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalTradeSource.findAllExternalTradeSources");
			long endTime = System.currentTimeMillis();
			System.out.println("It took " + (endTime - startTime) + " milli seconds to fetch " + externalTradeSourceList.size() + " external trade sources.");

			if(externalTradeSourceList != null)
			{
				for(ExternalTradeSource anExternalTradeSource : externalTradeSourceList)
				{
					externalTradeSourceReferenceDataHashMap.put(anExternalTradeSource.getOid(), anExternalTradeSource);
				}
			}
		}
	}
	
	private static ConcurrentMap<Integer, ExternalTradeState> externalTradeStateReferenceDataHashMap = null;
	@SuppressWarnings("unchecked")
	public static void loadExternalTradeStateReferenceData()
	{
		if(externalTradeStateReferenceDataHashMap == null)
		{
			externalTradeStateReferenceDataHashMap = new ConcurrentHashMap<Integer, ExternalTradeState>();

			long startTime = System.currentTimeMillis();
			List<ExternalTradeState> externalTradeStateList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalTradeState.findAllExternalTradeStates");
			long endTime = System.currentTimeMillis();
			System.out.println("It took " + (endTime - startTime) + " milli seconds to fetch " + externalTradeStateList.size() + " external trade states.");

			if(externalTradeStateList != null)
			{
				for(ExternalTradeState anExternalTradeState : externalTradeStateList)
				{
					externalTradeStateReferenceDataHashMap.put(anExternalTradeState.getOid(), anExternalTradeState);
				}
			}
		}
	}
	
	private static ConcurrentMap<Integer, ExternalTradeStatus> externalTradeStatusReferenceDataHashMap = null;
	@SuppressWarnings("unchecked")
	public static void loadExternalTradeStatusReferenceData()
	{
		if(externalTradeStatusReferenceDataHashMap == null)
		{
			externalTradeStatusReferenceDataHashMap = new ConcurrentHashMap<Integer, ExternalTradeStatus>();

			long startTime = System.currentTimeMillis();
			List<ExternalTradeStatus> externalTradeStatusList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalTradeStatus.findAllExternalTradeStatuses");
			long endTime = System.currentTimeMillis();
			System.out.println("It took " + (endTime - startTime) + " milli seconds to fetch " + externalTradeStatusList.size() + " external trade statuses.");

			if(externalTradeStatusList != null)
			{
				for(ExternalTradeStatus anExternalTradeStatus : externalTradeStatusList)
				{
					externalTradeStatusReferenceDataHashMap.put(anExternalTradeStatus.getOid(), anExternalTradeStatus);
				}
			}
		}
	}
	
	private static ConcurrentMap<String, ExternalMapping> externalTradeAccountReferenceDataHashMap = null;
	@SuppressWarnings("unchecked")
	public static void loadExternalTradeAccountReferenceData()
	{
		if(externalTradeAccountReferenceDataHashMap == null)
		{
			externalTradeAccountReferenceDataHashMap = new ConcurrentHashMap<String, ExternalMapping>();

			long startTime = System.currentTimeMillis();
			List<ExternalMapping> externalTradeAccountList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalMapping.findAllExternalTradeAccounts");
			long endTime = System.currentTimeMillis();
			System.out.println("It took " + (endTime - startTime) + " milli seconds to fetch " + externalTradeAccountList.size() + " external trade accounts.");

			if(externalTradeAccountList != null)
			{
				for(ExternalMapping anExternalTradeAccount : externalTradeAccountList)
				{
					//externalTradeAccountReferenceDataHashMap.put(anExternalTradeStatus.getOid(), anExternalTradeStatus);
					externalTradeAccountReferenceDataHashMap.put(anExternalTradeAccount.getExternalValue1(), anExternalTradeAccount);
				}
			}
		}
	}
	
	//private static ConcurrentMap<String, ExternalMapping> externalMappingReferenceDataHashMap = null;
	private static List<ExternalMapping> externalMappingReferenceDataList = null;
	@SuppressWarnings("unchecked")
	public static void loadExternalMappingReferenceData()
	{
		if(externalMappingReferenceDataList == null)
		{
			externalMappingReferenceDataList = new ArrayList<ExternalMapping>();
			
			long startTime = System.currentTimeMillis();
			//List<ExternalMapping> externalMappingList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalMapping.findAllExternalMappings");
			externalMappingReferenceDataList = HibernateReferenceDataFetchUtil.fetchDataFromDBForSQLNamedQuery("ExternalMapping.findAllExternalMappings");
			long endTime = System.currentTimeMillis();
			System.out.println("It took " + (endTime - startTime) + " milli seconds to fetch " + externalMappingReferenceDataList.size() + " external mappings.");
		}
	}
}