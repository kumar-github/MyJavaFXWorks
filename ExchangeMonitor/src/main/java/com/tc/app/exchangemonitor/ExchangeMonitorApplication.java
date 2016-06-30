package com.tc.app.exchangemonitor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.javafx.application.LauncherImpl;
import com.tc.app.exchangemonitor.util.HibernateUtil;
import com.tc.app.exchangemonitor.view.java.MainApplicationView;
import com.tc.framework.injection.Injector;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class ExchangeMonitorApplication extends Application
{
	private double xOffset = 0.0;
	private double yOffset = 0.0;
	//private Rectangle2D primaryMonitor = Screen.getPrimary().getVisualBounds();
	private static final Logger LOGGER = LogManager.getLogger(ExchangeMonitorApplication.class);
	
	private Stage primaryStage = null;
	private Scene primaryScene = null;
	
	public ExchangeMonitorApplication()
	{
		System.out.println("ExchangeMonitorApplication constructor called by " + Thread.currentThread().getName());
	}
	
	public static void main(String[] args)
	{
		System.out.println("ExchangeMonitorApplication main called by " + Thread.currentThread().getName());
		Application.launch();
		//LauncherImpl.launchApplication(ExchangeMonitorApplication.class, ExchangeMonitionApplicationPreloader.class, args); 
	}

	@Override
	public void init()
	{
		System.out.println("ExchangeMonitorApplication init called by " + Thread.currentThread().getName());
		HibernateUtil.getSessionFactory();
		for(int i=0; i<1000; i++)
		{
			double progress = (100 * i) / 1000;
			LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
		}
	}

	@Override
	public void start(Stage primaryStage)
	{
		System.out.println("ExchangeMonitorApplication start called by " + Thread.currentThread().getName());
		// Do all the heavy lifting stuff. One Question. Can we do the heavy lifting stuff in init() instead here?
		// then load the primary stage
		/*
		Parent root = FXMLLoader.load(this.getClass().getResource("MainWindowView.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
		 */
		try
		{
			this.primaryStage = primaryStage;
			this.primaryScene = createPrimaryScene();
			
			initializePrimaryStage();
			initializePrimaryScene();
			
			makeSceneDraggable();
			//animateStageIfNeeded();
			
			this.primaryStage.setScene(primaryScene);
			animateStageIfNeeded();
			
			primaryStage.show();
			primaryStage.toFront();
			
			
			primaryStage.setOnCloseRequest((WindowEvent windowEvent) -> closeStage(windowEvent));
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

	private void initializePrimaryStage()
	{
		undecoratePrimaryStage();
		
		primaryStage.setTitle("Exchange Monitor");
		primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/Start.png")));
		//primaryStage.getIcons().add(new Image(this.getClass().getClassLoader().getResourceAsStream("icons/play.png")));
		
		primaryStage.setFullScreen(false);
		primaryStage.setResizable(false);
		
		/*primaryStage.setHeight(700);
		primaryStage.setWidth(1000);*/
		primaryStage.sizeToScene();
		primaryStage.centerOnScreen();
		
		//primaryStage.setHeight(primaryMonitor.getHeight());
		//primaryStage.setWidth(primaryMonitor.getWidth());
		//primaryStage.setMaxHeight();
		//primaryStage.setMaxWidth();
		//primaryStage.setMinHeight(650);
		//primaryStage.setMinWidth(650);
		//primaryStage.setOpacity(0.0);
		
		//primaryStage.setX(primaryMonitor.getMinX());
		//primaryStage.setY(primaryMonitor.getMinY());
		
		/*MainApplicationView appView = new MainApplicationView();
		Scene scene = new Scene(appView.getView());
		primaryStage.setScene(scene);*/
		
		//primaryStage.setScene(primaryScene);
	}
	
	private void initializePrimaryScene()
	{
		primaryScene.setFill(Color.TRANSPARENT);
	}
	
	private void undecoratePrimaryStage()
	{
		this.primaryStage.initStyle(StageStyle.UNDECORATED);
		this.primaryStage.initStyle(StageStyle.TRANSPARENT);
	}
	
	
	private Scene createPrimaryScene()
	{
		MainApplicationView appView = new MainApplicationView();
		Scene scene = new Scene(appView.getView());
		return scene;
	}
	
	/* Since our stage is undecorated, we cannot drag it. This method will make the scene draggable. */
	private void makeSceneDraggable()
	{
		primaryScene.setOnMousePressed(event ->
		{
			xOffset = primaryStage.getX() - event.getScreenX();
			yOffset = primaryStage.getY() - event.getScreenY();
			//primaryScene.setCursor(Cursor.MOVE);
		});
		
		primaryScene.setOnMouseReleased(event ->
		{
			//primaryScene.setCursor(Cursor.HAND);
		});
		
		primaryScene.setOnMouseDragged(event ->
		{
			primaryStage.setX(event.getScreenX() + xOffset);
			primaryStage.setY(event.getScreenY() + yOffset);
		});
		
		/*primaryScene.setOnMouseEntered(event ->
		{
			if(!event.isPrimaryButtonDown())
			{
				primaryScene.setCursor(Cursor.HAND);
			}
		});
		
		primaryScene.setOnMouseExited(event ->
		{
			if(!event.isPrimaryButtonDown())
			{
				primaryScene.setCursor(Cursor.DEFAULT);
			}
		});*/
	}
	
	private void animateStageIfNeeded()
	{
		setFadeInTransition();
		//setRotateTransition();
	}
	
	private void setFadeInTransition()
	{
        //super.setOpacity(0);
		primaryScene.getRoot().setOpacity(0.0);
		//primaryStage.setOpacity(0);
		primaryStage.showingProperty().addListener(new ChangeListener<Boolean>()
		{
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if (t1.booleanValue()) {
                    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), primaryScene.getRoot());
                    fadeTransition.setToValue(1);
                    fadeTransition.play();
                }
            }
        });
    }
	
	private void setRotateTransition()
	{
		RotateTransition r = new RotateTransition(Duration.seconds(1.0), primaryScene.getRoot());
		r.setOnFinished((ActionEvent actionEvent) -> {
			//primaryStage.show();
		});
		r.setFromAngle(0);
		r.setByAngle(360);
		r.play();
    }
	
	@Override
	public void stop() throws Exception
	{
		System.out.println("ExchangeMonitorApplication stop called by " + Thread.currentThread().getName());
		super.stop();
		Injector.forgetAll();
		HibernateUtil.closeSessionFactory();
		System.out.println("Application Terminated.");
		Platform.exit();
		System.exit(0);
	}
	
	private void closeStage(WindowEvent windowEvent)
	{
		windowEvent.consume();
		/*windowEvent.consume();
		primaryStage.close();
		Platform.exit();
		System.exit(0);*/
        /*FadeTransition fade = new FadeTransition(Duration.seconds(0.5), primaryScene.getRoot());
        fade.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();*/
		RotateTransition r = new RotateTransition(Duration.seconds(0.5), primaryScene.getRoot());
		r.setOnFinished((ActionEvent actionEvent) -> {
			primaryStage.close();
			Platform.exit();
			System.exit(0);
		});
		r.setFromAngle(0);
		r.setByAngle(360);
		r.play();
	}
}