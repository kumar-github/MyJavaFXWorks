package com.tc.app.exchangemonitor.controller;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ExternalTradeStatusCellFactory implements Callback<TableColumn<DummyExternalTrade, String>, TableCell<DummyExternalTrade, String>>
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
	public TableCell<DummyExternalTrade, String> call(TableColumn<DummyExternalTrade, String> param)
	{
		final TableCell<DummyExternalTrade, String> aTableCell = new TableCell<DummyExternalTrade, String>(){
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
					{
						setText("Pending");
						//getTableRow().setStyle("");
					}
					else if(item.equals("2"))
					{
						setText("Completed");
						//getTableRow().setStyle("");
					}
					else if(item.equals("3"))
					{
						setText("Failed");
						//getTableRow().setStyle("-fx-control-inner-background: palegreen; -fx-accent: derive(-fx-control-inner-background, -40%); -fx-cell-hover-color: derive(-fx-control-inner-background, -20%);");
					}
					else if(item.equals("4"))
					{
						setText("Skipped");
						//getTableRow().setStyle("");
					}
				}
			}
		};
		return aTableCell;
	}
}