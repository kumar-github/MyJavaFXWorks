package com.tc.app.exchangemonitor.viewmodel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainApplicationStatusBarViewModel
{
	private StringProperty statusMessagesProperty = null;
	public StringProperty statusMessagesProperty()
	{
		if (statusMessagesProperty == null)
		{
			statusMessagesProperty = new SimpleStringProperty();
		}
		return statusMessagesProperty;
	}

	private DoubleProperty progressStatusProperty = null;
	public DoubleProperty progressStatusProperty()
	{
		if (progressStatusProperty == null)
		{
			progressStatusProperty = new SimpleDoubleProperty();
		}
		return progressStatusProperty;
	}
}