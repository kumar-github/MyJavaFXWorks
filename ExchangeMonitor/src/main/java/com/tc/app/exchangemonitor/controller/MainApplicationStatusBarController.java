package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.StatusBar;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;

public class MainApplicationStatusBarController implements Initializable
{
	//@FXML
	private StatusBar applicationMainStatusBar;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		//doInitialBinding();
	}
	
	private void doInitialBinding()
	{
		applicationMainStatusBar.textProperty().bind(statusMessagesProperty());
		applicationMainStatusBar.progressProperty().bind(progressStatusesProperty());
	}
	
	private StringProperty statusMessagesProperty = null;
	private StringProperty statusMessagesProperty()
	{
		if (statusMessagesProperty == null)
		{
			statusMessagesProperty = new SimpleStringProperty();
		}
		return statusMessagesProperty;
	}

	private DoubleProperty progressStatusesProperty = null;
	private DoubleProperty progressStatusesProperty()
	{
		if (progressStatusesProperty == null)
		{
			progressStatusesProperty = new SimpleDoubleProperty();
		}
		return progressStatusesProperty;
	}
}