package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.tc.app.exchangemonitor.model.ExternalMapping;
import com.tc.app.exchangemonitor.model.predicates.ExternalMappingPredicates;
import com.tc.app.exchangemonitor.util.ReferenceDataCache;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ExternalMappingAccountsController implements Initializable
{
	@FXML
	private TableView<ExternalMapping> externalMappingAccountsTableView;
	@FXML
	private TableColumn<ExternalMapping, String> externalSourceAccountTableColumn;
	
	private ObservableList<ExternalMapping> externalMappingAccountsObservableList = FXCollections.observableArrayList();
	private FilteredList<ExternalMapping> externalMappingAccountsFilteredList = new FilteredList<ExternalMapping>(externalMappingAccountsObservableList, ExternalMappingPredicates.applyNymexAccountsPredicate);
	private SortedList<ExternalMapping> externalMappingAccountsSortedList = new SortedList<ExternalMapping>(externalMappingAccountsFilteredList);

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
		initializeTableView();
	}
	
	private void addThisControllerToControllersMap()
	{
	}
	
	private void doAssertion()
	{
	}
	
	private void doInitialDataBinding()
	{
		externalMappingAccountsSortedList.comparatorProperty().bind(externalMappingAccountsTableView.comparatorProperty());
		externalMappingAccountsTableView.setItems(externalMappingAccountsSortedList);
	}
	
	private void initializeGUI()
	{
		fetchTradersExternalMapping();
	}

	private void setAnyUIComponentStateIfNeeded()
	{
	}
	
	private void setComponentToolTipIfNeeded()
	{
	}
	
	private void initializeTableView()
	{
		initializeExternalMappingTradersTableView();
	}
	
	private void initializeExternalMappingTradersTableView()
	{
		externalSourceAccountTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue1()));
	}
	
	private void initializeListeners()
	{
	}
	
	private void fetchTradersExternalMapping()
	{
		externalMappingAccountsObservableList.addAll(ReferenceDataCache.fetchExternalMappings());
	}
}