package com.tc.app.exchangemonitor.controller;

import com.tc.app.exchangemonitor.model.ExternalTrade;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class InputBrokerCellValueFactory implements Callback<TableColumn.CellDataFeatures<ExternalTrade, String>, ObservableValue<String>>
{
	@Override
	public ObservableValue<String> call(CellDataFeatures<ExternalTrade, String> cellData)
	{
		if(cellData.getValue().getExchToolsTrade().getInputAction().trim().equals("BUY"))
			return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getInputBroker());
		return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getAcceptedBroker());
	}
}