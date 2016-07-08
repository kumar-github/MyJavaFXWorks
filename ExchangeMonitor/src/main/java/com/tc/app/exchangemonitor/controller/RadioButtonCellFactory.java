package com.tc.app.exchangemonitor.controller;

import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class RadioButtonCellFactory extends ListCell<String> 
{
	private static final ToggleGroup toggleGroup = new ToggleGroup();
	@Override
	protected void updateItem(String item, boolean empty)
	{
		super.updateItem(item, empty);
		if(empty)
		{
			setText(null);
			setGraphic(null);
		}
		else
		{
			RadioButton radioButton = new RadioButton(item);
			radioButton.setToggleGroup(toggleGroup);
			setGraphic(radioButton);
			//Add Listeners if any
		}
	}
}