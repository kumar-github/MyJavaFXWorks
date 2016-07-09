package com.tc.app.exchangemonitor.controller;

import com.tc.app.exchangemonitor.model.ExternalTradeSource;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.util.Callback;

public class ExternalTradeSourceRadioButtonCellFactory implements Callback<ListView<ExternalTradeSource>, ListCell<ExternalTradeSource>>
{
	private static final ToggleGroup toggleGroup = new ToggleGroup();
	@Override
	public ListCell<ExternalTradeSource> call(ListView<ExternalTradeSource> param)
	{
		final ListCell<ExternalTradeSource> cell = new ListCell<ExternalTradeSource>(){
			@Override
			protected void updateItem(ExternalTradeSource item, boolean empty)
			{
				super.updateItem(item, empty);
				if(item == null || empty)
				{
					this.setText(null);
					this.setGraphic(null);
				}
				else
				{
					RadioButton radioButton = new RadioButton(item.getExternalTradeSrcName());
					radioButton.setToggleGroup(toggleGroup);
					this.setGraphic(radioButton);
					//Add Listeners if any
					//this.setText(item.getExternalTradeSrcName());
				}
			}
		};
		return cell;
	}
}