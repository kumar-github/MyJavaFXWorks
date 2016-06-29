package com.tc.app.exchangemonitor.controller;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.util.Callback;

/**
 * The updateItem method should not be called by developers, but it is the best method for developers to override to allow for them to customise the visuals of the cell. To clarify, 
 * developers should never call this method in their code (they should leave it up to the UI control, such as the ListView control) to call this method. However, the purpose of having the 
 * updateItem method is so that developers, when specifying custom cell factories (again, like the ListView cell factory), the updateItem method can be overridden to allow for complete 
 * customization of the cell.
 * It is very important that subclasses of Cell override the updateItem method properly, as failure to do so will lead to issues such as blank cells or cells with unexpected content 
 * appearing within them. 
 * 
 * Here is an example of how to properly override the updateItem method:
 *
 * protected void updateItem(T item, boolean empty)
 * {
 * 	super.updateItem(item, empty);
 * 	if (empty || item == null)
 * 	{
 * 		setText(null);
 * 		setGraphic(null);
 * 	}
 * 	else
 * 	{
 * 		setText(item.toString());
 * 	}
 * }
 *  
 *  Note in this code sample two important points:
 *  We call the super.updateItem(T, boolean) method. If this is not done, the item and empty properties are not correctly set, and you are likely to end up with graphical issues.
 *  We test for the empty condition, and if true, we set the text and graphic properties to null. If we do not do this, it is almost guaranteed that end users will see graphical artifacts in 
 *  cells unexpectedly.
 *  
 *  Parameters:
 *  item - The new item for the cell.
 *  empty - whether or not this cell represents data from the list. If it is empty, then it does not represent any domain data, but is a cell being used to render an "empty" row.
 *  
 */


public class TraderCellFactory implements Callback<ListView<IctsUser>, ListCell<IctsUser>>
{
	private Color testColor;
	
	public Color getTestColor()
	{
		return testColor;
	}

	public void setTestColor(Color color)
	{
		this.testColor = color;
	}

	@Override
	public ListCell<IctsUser> call(ListView<IctsUser> param)
	{
		final ListCell<IctsUser> cell = new ListCell<IctsUser>(){
			@Override
			protected void updateItem(IctsUser item, boolean empty)
			{
				super.updateItem(item, empty);
				if(item == null || empty)
					this.setText(null);
				else
				{
					this.setText(item.getUserInit());
					//this.setTextFill(getTestColor());
				}
			}
		};
		
		return cell;
	}
}