package com.tc.app.exchangemonitor.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class DateCellValueFactory implements Callback<TableColumn.CellDataFeatures<DummyExternalTrade, ZonedDateTime>, ObservableValue<ZonedDateTime>>
{
	@Override
	public ObservableValue<ZonedDateTime> call(CellDataFeatures<DummyExternalTrade, ZonedDateTime> param)
	{
		//return param.getValue().getCreationDate().toInstant().atZone(ZoneId.systemDefault());
		return new ReadOnlyObjectWrapper<ZonedDateTime>(param.getValue().getCreationDate().toInstant().atZone(ZoneId.systemDefault()));
	}
}