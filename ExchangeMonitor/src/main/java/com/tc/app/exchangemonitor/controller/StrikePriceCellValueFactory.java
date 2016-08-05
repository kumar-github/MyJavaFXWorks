package com.tc.app.exchangemonitor.controller;

import com.tc.app.exchangemonitor.model.ExternalTrade;

import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class StrikePriceCellValueFactory implements Callback<TableColumn.CellDataFeatures<ExternalTrade, Number>, ObservableValue<Number>>
{
	@Override
	public ObservableValue<Number> call(CellDataFeatures<ExternalTrade, Number> cellData)
	{
		if(cellData.getValue().getExchToolsTrade().getStrikePrice() != null)
			return new ReadOnlyDoubleWrapper(cellData.getValue().getExchToolsTrade().getStrikePrice().doubleValue());
		return null;
	}
}