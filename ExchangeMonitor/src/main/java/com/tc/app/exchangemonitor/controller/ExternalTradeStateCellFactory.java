package com.tc.app.exchangemonitor.controller;

import com.tc.app.exchangemonitor.model.ExternalTrade;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ExternalTradeStateCellFactory implements Callback<TableColumn<ExternalTrade, String>, TableCell<ExternalTrade, String>>
{
	/*
	// 1st way of implementing
	@Override
	public TableCell<DummyExternalTrade, ZonedDateTime> call(TableColumn<DummyExternalTrade, ZonedDateTime> param)
	{
		return new TableCell<DummyExternalTrade, ZonedDateTime>(){
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
	*/
	
	//2nd way of implementing
	@Override
	public TableCell<ExternalTrade, String> call(TableColumn<ExternalTrade, String> param)
	{
		final TableCell<ExternalTrade, String> aTableCell = new TableCell<ExternalTrade, String>(){
			@Override
			protected void updateItem(String item, boolean empty)
			{
				super.updateItem(item, empty);
				if(empty || item == null)
				{
					setText(null);
				}
				else
				{
					if(item.equals("1"))
						setText("Add");
					else if(item.equals("2"))
						setText("Update");
					else if(item.equals("3"))
						setText("Delete");
					else if(item.equals("4"))
						setText("DeleteAndAdd");
				}
			}
		};
		return aTableCell;
	}
}