package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;

import com.tc.app.exchangemonitor.util.ApplicationHelper;
import com.tc.app.exchangemonitor.util.HibernateUtil;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class MainApplicationMappingsTabController implements Initializable
{
	@FXML
	private ListView<String> exchangesListView;

	@Inject
	private String sqlQueryToFetchExternalTradeSources;

	private Map<String, String> externalTradeSourceTableMap = new HashMap<String, String>();

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		addThisControllerToControllersMap();
		doAssertion();
		doInitialDataBinding();
		initializeGUI();
		setAnyUIComponentStateIfNeeded();
		setComponentToolTipIfNeeded();
		initializeListeners();
	}
	
	private void addThisControllerToControllersMap()
	{
		ApplicationHelper.controllersMap.putInstance(MainApplicationMappingsTabController.class, this);
	}

	private void doAssertion()
	{
		assert exchangesListView != null : "fx:id=\"exchangesListView\" was not injected. Check your FXML file MainApplicationMappingsTabView.fxml";
	}

	private void doInitialDataBinding()
	{
	}

	private void initializeGUI()
	{
		List<String> externalTradeSourceNames = fetchAllExternalTradeSourcesFromDB();
		setExternalTradeSourcesOnUI(externalTradeSourceNames);
	}

	public List<String> fetchAllExternalTradeSourcesFromDB()
	{
		List<Object> externalTradeSourceTable = Collections.emptyList();
		List<String> externalTradeSourceNamesList = new ArrayList<String>();

		/**
		 * Variables injected through properties files takes priority over the code. so if we found a variable in the properties file
		 * with value then use that value if not then proceed with the value in the code.
		 */
		if(sqlQueryToFetchExternalTradeSources == null || sqlQueryToFetchExternalTradeSources.isEmpty())
		{
			sqlQueryToFetchExternalTradeSources = getSQLQueryToFetchExternalTradeSources();
		}
		externalTradeSourceTable = fetchDataFromDB(sqlQueryToFetchExternalTradeSources, "externalTradeSrcName", "externalTradeSrcOid");
		for(Object anExternalTradeSourceRecord : externalTradeSourceTable)
		{
			String externalTradeSrcName = ((Map)anExternalTradeSourceRecord).get("externalTradeSrcName").toString();
			String externalTradeSrcOid = ((Map)anExternalTradeSourceRecord).get("externalTradeSrcOid").toString();
			externalTradeSourceTableMap.put(externalTradeSrcName, externalTradeSrcOid);
			externalTradeSourceNamesList.add(externalTradeSrcName);
		}
		return externalTradeSourceNamesList;
	}

	private String getSQLQueryToFetchExternalTradeSources()
	{
		return "select etsource.external_trade_src_name as externalTradeSrcName, etsource.oid as externalTradeSrcOid from external_trade_system etsystem join external_trade_source etsource on etsystem.oid = etsource.external_trade_system_oid and etsource.external_trade_src_name <> 'NonDefined' order by etsource.external_trade_src_name";
	}

	public List<Object> fetchDataFromDB(String sqlQueryString, String scalarColumn1, String scalarColumn2)
	{
		List<Object> aTable = Collections.emptyList();
		Session session = HibernateUtil.beginTransaction();

		SQLQuery sqlQueryToFetchData = session.createSQLQuery(sqlQueryString);
		sqlQueryToFetchData.addScalar(scalarColumn1, StringType.INSTANCE);
		//sqlQueryToFetchData.addScalar(scalarColumn2, IntegerType.INSTANCE);
		sqlQueryToFetchData.addScalar(scalarColumn2, StringType.INSTANCE);

		sqlQueryToFetchData.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		aTable = sqlQueryToFetchData.list();

		return aTable;
	}

	public void setExternalTradeSourcesOnUI(List<String> externalTradeSources)
	{
		exchangesListView.setItems(FXCollections.observableArrayList(externalTradeSources));
	}

	private void setAnyUIComponentStateIfNeeded()
	{
		//exchangesListView.setCellFactory(new ExternalTradeSourceRadioButtonCellFactory());
		exchangesListView.setCellFactory((param) -> new ExternalTradeSourceRadioButtonCellFactory());
	}

	private void setComponentToolTipIfNeeded()
	{
	}
	
	private void initializeListeners()
	{
		/*exchangesListView.getCheckModel().getCheckedItems().addListener((Change<? extends String> change) ->
		{
			handleExternalTradeSourcesCheckBoxClick(change);
		});*/
	}
}