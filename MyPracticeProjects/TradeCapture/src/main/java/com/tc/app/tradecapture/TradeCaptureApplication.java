package com.tc.app.tradecapture;

import com.tc.app.tradecapture.util.HibernateUtil;
import com.tc.app.tradecapture.view.java.FuturesView;
import com.tc.framework.injection.Injector;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TradeCaptureApplication extends Application 
{
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void init()
	{
		HibernateUtil.getSessionFactory();
	}
	
	@Override
	public void start(Stage primaryStage)
	{
		/*
		Parent root = FXMLLoader.load(this.getClass().getResource("MainWindowView.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		*/
		try
		{
			createAndShowGUI(primaryStage);
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			Injector.forgetAll();
			Platform.exit();
		}
		finally
		{
			//Injector.forgetAll();
			//Platform.exit();
		}
	}
	
	private void createAndShowGUI(Stage primaryStage)
	{
		//primaryStage.setTitle("Exchange Monitor");
		primaryStage.setTitle("Trade Capture");
		primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/play.png")));
		//primaryStage.getIcons().add(new Image(this.getClass().getClassLoader().getResourceAsStream("icons/play.png")));
		primaryStage.centerOnScreen();
		primaryStage.setHeight(700);
		primaryStage.setWidth(1000);
		//primaryStage.setMaxHeight();
		//primaryStage.setMaxWidth();
		//primaryStage.setOpacity(0.9);
		
//		MainWindowView appView = new MainWindowView();
//		Scene scene = new Scene(appView.getView());
//		primaryStage.setScene(scene);
		
//		TCWindowView appView = new TCWindowView();
//		Scene scene = new Scene(appView.getView());
//		primaryStage.setScene(scene);
		
		FuturesView appView = new FuturesView();
		Scene scene = new Scene(appView.getView());
		primaryStage.setScene(scene);
		
		primaryStage.show();
		primaryStage.toFront();
	}
	
	@Override
	public void stop() throws Exception
	{
		super.stop();
		Injector.forgetAll();
		HibernateUtil.closeSessionFactory();
		System.out.println("Application Terminated.");
		Platform.exit();
		System.exit(0);
	}
}