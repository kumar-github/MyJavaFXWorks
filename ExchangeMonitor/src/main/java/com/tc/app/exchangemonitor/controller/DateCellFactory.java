package com.tc.app.exchangemonitor.controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.tc.app.exchangemonitor.model.ExternalTrade;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class DateCellFactory implements Callback<TableColumn<ExternalTrade, ZonedDateTime>, TableCell<ExternalTrade, ZonedDateTime>>
{
	@Override
	public TableCell<ExternalTrade, ZonedDateTime> call(TableColumn<ExternalTrade, ZonedDateTime> param)
	{
		return new TableCell<ExternalTrade, ZonedDateTime>(){
			@Override
			protected void updateItem(ZonedDateTime item, boolean empty)
			{
				super.updateItem(item, empty);
				if(empty || item == null)
				{
					setText(null);
				}
				else
				{
					setText(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss z").format(item));
				}
			}
		};
	}
}