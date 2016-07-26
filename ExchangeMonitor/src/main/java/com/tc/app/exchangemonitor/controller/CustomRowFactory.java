package com.tc.app.exchangemonitor.controller;

import java.util.List;

import com.tc.app.exchangemonitor.model.ExternalTrade;

import javafx.beans.value.ObservableValue;
import javafx.css.PseudoClass;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class CustomRowFactory<T> implements Callback<TableView<T>, TableRow<T>>
{
	private final Callback<TableView<T>, TableRow<T>> rowFactory;
	private final Callback<T, List<MenuItem>> menuItemFactory;
	
	ContextMenu cm = new ContextMenu();
	/* if we create the row factory through fxml and not though code then we can pass the menu items from the fxml itself */
	private List<MenuItem> menuItems;
	
	PseudoClass pendingStyle = PseudoClass.getPseudoClass("pending");
	PseudoClass completedStyle = PseudoClass.getPseudoClass("completed");
	PseudoClass failedStyle = PseudoClass.getPseudoClass("failed");
	PseudoClass skippedStyle = PseudoClass.getPseudoClass("skipped");
	
	public CustomRowFactory()
	{
		this.rowFactory = null;
		this.menuItemFactory = null;
	}
	
	public CustomRowFactory(Callback<TableView<T>, TableRow<T>> rowFactory, Callback<T, List<MenuItem>> menuItemFactory)
	{
		this.rowFactory = rowFactory;
		this.menuItemFactory = menuItemFactory;
	}
	
	public CustomRowFactory(Callback<T, List<MenuItem>> menuItemFactory)
	{
		this(null, menuItemFactory);
	}
	
	public void setMenuItems(List<MenuItem> menuItems)
	{
		//this.menuItems = menuItems;
		this.cm.getItems().addAll(menuItems);
	}
	
	public List<MenuItem> getMenuItems()
	{
		//return this.menuItems;
		return this.cm.getItems();
	}
	
	@Override
	public TableRow<T> call(TableView<T> theTable)
	{
		final TableRow<T> theRow;
		
		if(rowFactory == null)
			theRow = new TableRow<T>();
		else
			theRow = rowFactory.call(theTable);
		
		theRow.itemProperty().addListener((ObservableValue<? extends T> observable, T oldValue, T newValue) -> {
			if(newValue == null)
			{
				theRow.setContextMenu(null);
				theRow.pseudoClassStateChanged(pendingStyle, false);
				theRow.pseudoClassStateChanged(completedStyle, false);
				theRow.pseudoClassStateChanged(failedStyle, false);
				theRow.pseudoClassStateChanged(skippedStyle, false);
			}
			else
			{
				theRow.setContextMenu(menuItemFactory != null ? createContextMenu(theRow) : null);
				theRow.setContextMenu(cm);
				//theRow.contextMenuProperty().bind(Bindings.when(Bindings.isNotNull(theRow.itemProperty())).then(createContextMenu(theRow)).otherwise((ContextMenu) null));
				theRow.pseudoClassStateChanged(pendingStyle, ((ExternalTrade)newValue).getExternalTradeStatusOid().getExternalTradeStatusName().equals("Pending"));
				theRow.pseudoClassStateChanged(completedStyle, ((ExternalTrade)newValue).getExternalTradeStatusOid().getExternalTradeStatusName().equals("Completed"));
				theRow.pseudoClassStateChanged(failedStyle, ((ExternalTrade)newValue).getExternalTradeStatusOid().getExternalTradeStatusName().equals("Failed"));
				theRow.pseudoClassStateChanged(skippedStyle, ((ExternalTrade)newValue).getExternalTradeStatusOid().getExternalTradeStatusName().equals("Skipped"));
			}
		});
		
		theRow.hoverProperty().addListener((observable) -> {
			if(theRow.isHover() && theRow.getItem() != null)
			{
				/* do whatever you want to do when hovering. */
				System.out.println("Hovering on " + ((ExternalTrade)theRow.getItem()).getOid());
			}
		});
		
		
		return theRow;
	}
	
	private ContextMenu createContextMenu(final TableRow<T> theRow)
	{
		ContextMenu contextMenu = new ContextMenu();
		ContextMenu tableMenu = theRow.getTableView().getContextMenu();
		if(tableMenu != null)
		{
			contextMenu.getItems().addAll(tableMenu.getItems());
			contextMenu.getItems().add(new SeparatorMenuItem());
		}
		contextMenu.getItems().addAll(menuItemFactory.call(theRow.getItem()));
		return contextMenu;
	}
	
	private ContextMenu createContextMenu2()
	{
		ContextMenu contextMenu = new ContextMenu();
		contextMenu.getItems().addAll(menuItems);
		contextMenu.getItems().add(new SeparatorMenuItem());
		return contextMenu;
	}
}