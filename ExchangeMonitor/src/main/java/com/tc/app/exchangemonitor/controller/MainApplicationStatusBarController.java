package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.controlsfx.control.StatusBar;

import com.tc.app.exchangemonitor.util.ApplicationHelper;
import com.tc.app.exchangemonitor.viewmodel.MainApplicationStatusBarViewModel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainApplicationStatusBarController implements Initializable
{
	@FXML
	private StatusBar mainApplicationStatusBar;
	
	@Inject
	private MainApplicationStatusBarViewModel mainApplicationStatusBarViewModel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		addThisControllerToControllersMap();
		doInitialBinding();
	}
	
	private void addThisControllerToControllersMap()
	{
		ApplicationHelper.controllersMap.putInstance(MainApplicationStatusBarController.class, this);
	}
	
	private void doInitialBinding()
	{
		/*
		mainApplicationStatusBar.textProperty().bind(statusMessagesProperty());
		mainApplicationStatusBar.progressProperty().bind(progressStatusesProperty());
		*/
		
		/* modified the above 2 lines as below. implemented the MVVM pattern */
		mainApplicationStatusBar.textProperty().bind(mainApplicationStatusBarViewModel.statusMessagesProperty());
		mainApplicationStatusBar.progressProperty().bind(mainApplicationStatusBarViewModel.progressStatusProperty());
		
	}
	
	private StringProperty statusMessagesProperty = null;
	//private StringProperty statusMessagesProperty()
	public StringProperty statusMessagesProperty()
	{
		if (statusMessagesProperty == null)
		{
			statusMessagesProperty = new SimpleStringProperty();
		}
		return statusMessagesProperty;
	}

	private DoubleProperty progressStatusesProperty = null;
	//private DoubleProperty progressStatusesProperty()
	public DoubleProperty progressStatusesProperty()
	{
		if (progressStatusesProperty == null)
		{
			progressStatusesProperty = new SimpleDoubleProperty();
		}
		return progressStatusesProperty;
	}
}