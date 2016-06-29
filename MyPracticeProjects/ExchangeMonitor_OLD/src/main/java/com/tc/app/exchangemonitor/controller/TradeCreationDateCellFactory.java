package com.tc.app.exchangemonitor.controller;

import java.time.ZonedDateTime;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

//public class TradeCreationDateCellFactory implements Callback<TableColumn<DummyExternalTrade, ZonedDateTime>, TableCell<DummyExternalTrade, ZonedDateTime>>
public class TradeCreationDateCellFactory extends DateCellFactory implements Callback<TableColumn<DummyExternalTrade, ZonedDateTime>, TableCell<DummyExternalTrade, ZonedDateTime>>
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
	
	/*
	//2nd way of implementing
	@Override
	public TableCell<DummyExternalTrade, ZonedDateTime> call(TableColumn<DummyExternalTrade, ZonedDateTime> param)
	{
		final TableCell<DummyExternalTrade, ZonedDateTime> aTableCell = new TableCell<DummyExternalTrade, ZonedDateTime>(){
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
		return aTableCell;
	}
	*/
	
	//3rd way of implementing. Just move the logic to a base class and call it.
	@Override
	public TableCell<DummyExternalTrade, ZonedDateTime> call(TableColumn<DummyExternalTrade, ZonedDateTime> param)
	{
		return super.call(param);
	}
}