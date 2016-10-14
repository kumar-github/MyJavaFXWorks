package com.tc.app.exchangemonitor.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/** Controls the login screen */
public class LoginController
{
	@FXML private TextField databaseNameTxt;
	@FXML private TextField usernameTxt;
	@FXML private TextField passwordTxt;
	@FXML private Button loginButton;
	@FXML private Button cancelButton;
	@FXML private Text loginStatusTxt;
	private LoginManager loginManager;

	public void initialize()
	{
	}
	
	public void initManager(final LoginManager loginManager)
	{
		this.loginManager = loginManager;
		/*loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				String sessionID = authorize();
				if (sessionID != null) {
					loginManager.authenticated(sessionID);
				}
			}
		});

		cancelButton.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				Platform.runLater(() -> { Platform.exit(); System.exit(0); } );
			}
		});*/
	}
	
	@FXML
	private void handleLoginButtonClick()
	{
		/*String sessionID = authorize();
		if(sessionID != null)
			loginManager.authenticated(sessionID);*/
		if(authorize())
		{
			loginManager.authenticated();
		}
		else
		{
			//loginStatusTxt.setText("Login Failure");
		}
	}
	
	@FXML
	private void handleCancelButtonClick()
	{
		Platform.runLater(() -> { Platform.exit(); System.exit(0); } );
	}

	/**
	 * Check authorization credentials.
	 * 
	 * If accepted, return a sessionID for the authorized session
	 * otherwise, return null.
	 */   
	//private String authorize()
	private boolean authorize()
	{
		//return usernameTxt.getText().equals("admin") && passwordTxt.getText().equals("admin") ? generateSessionID() : null;
		return makeTestConnection();
	}

	private static int sessionID = 0;

	private String generateSessionID()
	{
		sessionID++;
		return "xyzzy - session " + sessionID;
	}
	
	private boolean makeTestConnection()
	{
		boolean isSuccess = false;
		String url = "jdbc:jtds:sqlserver://HYDDB07:1460;databaseName=QA_30_trade";
		//String driver = "net.sourceforge.jtds.jdbc.Driver";
		Connection connection = null;
		try
		{
			connection = DriverManager.getConnection(url);
			if(connection != null)
			{
				isSuccess = true;
				
				loginStatusTxt.setText("Login Success...");
				//Platform.runLater(() -> loginStatusTxt.setText("Login Success..."));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return isSuccess;
		//return false;
	}
}