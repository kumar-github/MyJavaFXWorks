package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.tc.app.exchangemonitor.model.ExternalTradeSource;
import com.tc.app.exchangemonitor.util.ApplicationHelper;
import com.tc.app.exchangemonitor.util.ReferenceDataCache;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class MainApplicationMappingsTabController implements Initializable
{
	@FXML
	private ListView<ExternalTradeSource> externalTradeSourcesListView;
	
	private ObservableList<ExternalTradeSource> observableExternalTradeSourceList = FXCollections.observableArrayList();
	
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
		assert externalTradeSourcesListView != null : "fx:id=\"externalTradeSourcesListView\" was not injected. Check your FXML file MainApplicationMappingsTabView.fxml";
	}

	private void doInitialDataBinding()
	{
		externalTradeSourcesListView.setItems(observableExternalTradeSourceList);
	}

	private void initializeGUI()
	{
		fetchExternalTradeSources();
	}
	
	private void setAnyUIComponentStateIfNeeded()
	{
		//exchangesListView.setCellFactory((param) -> new ExternalTradeSourceRadioButtonCellFactory());
		externalTradeSourcesListView.setCellFactory(new ExternalTradeSourceRadioButtonCellFactory());
	}

	private void setComponentToolTipIfNeeded()
	{
	}
	
	private void initializeListeners()
	{
	}
	
	private void fetchExternalTradeSources()
	{
		observableExternalTradeSourceList.addAll(ReferenceDataCache.fetchExternalTradeSources().values());
	}
}