package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MainController implements Initializable
{
	@FXML
	private AController aViewController;
	
	@FXML
	private BController bViewController;
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		System.out.println(this);
	}
}