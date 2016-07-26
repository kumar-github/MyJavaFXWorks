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

public class ExternalMappingTradingPeriodsController implements Initializable
{
	private ObservableList<ExternalMapping> externalMappingTradingPeriodsObservableList = FXCollections.observableArrayList();
	private FilteredList<ExternalMapping> externalMappingTradingPeriodsFilteredList = new FilteredList<ExternalMapping>(externalMappingTradingPeriodsObservableList, ExternalMappingPredicates.applyNymexTradingPeriodsPredicate);
	private SortedList<ExternalMapping> externalMappingTradingPeriodsSortedList = new SortedList<ExternalMapping>(externalMappingTradingPeriodsFilteredList);
	
	@FXML
	private TableView<ExternalMapping> externalMappingTradingPeriodsTableView;
	@FXML
	private TableColumn<ExternalMapping, String> externalSourceCommodityTableColumn;
	@FXML
	private TableColumn<ExternalMapping, String> tradingPeriodOffsetMonthTableColumn;

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
		externalMappingTradingPeriodsSortedList.comparatorProperty().bind(externalMappingTradingPeriodsTableView.comparatorProperty());
		externalMappingTradingPeriodsTableView.setItems(externalMappingTradingPeriodsSortedList);
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
		externalSourceCommodityTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalValue1()));
		tradingPeriodOffsetMonthTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAliasValue()));
	}
	
	private void initializeListeners()
	{
	}
	
	private void fetchTradersExternalMapping()
	{
		externalMappingTradingPeriodsObservableList.addAll(ReferenceDataCache.fetchExternalMappings());
	}
}