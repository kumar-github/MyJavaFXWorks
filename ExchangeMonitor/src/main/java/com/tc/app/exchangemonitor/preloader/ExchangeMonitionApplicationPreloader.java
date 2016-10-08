package com.tc.app.exchangemonitor.preloader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.tc.app.exchangemonitor.view.java.PreloaderView;

import javafx.animation.FadeTransition;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ExchangeMonitionApplicationPreloader extends Preloader
{
	private static final Logger LOGGER = LogManager.getLogger(ExchangeMonitionApplicationPreloader.class);
	//private ProgressBar progressBar;
	private Stage preloaderStage;
	private Scene preloaderScene;

	public ExchangeMonitionApplicationPreloader()
	{
		LOGGER.debug("ExchangeMonitorApplicationPreloader constructor called by ", Thread.currentThread().getName());
	}

	@Override
	public void init() throws Exception
	{
		LOGGER.debug("ExchangeMonitorApplicationPreloader init called by ", Thread.currentThread().getName());
		super.init();
		/* If the preloader has complex UI it's initialization can be done in here. Ensure that you do it with Platform.runLater() */
	}

	@Override
	public void start(Stage primaryStage) throws Exception
	{
		LOGGER.debug("ExchangeMonitorApplicationPreloader start called by ", Thread.currentThread().getName());
		preloaderStage = primaryStage;
		preloaderStage.setScene(createPreloaderScene());

		preloaderStage.sizeToScene();
		preloaderStage.centerOnScreen();
		preloaderStage.setAlwaysOnTop(true);
		preloaderStage.initStyle(StageStyle.TRANSPARENT);
		preloaderStage.show();
		preloaderStage.toFront();
	}

	@Override
	public void handleApplicationNotification(PreloaderNotification info)
	{
	}

	@Override
	public void handleProgressNotification(ProgressNotification info)
	{
		super.handleProgressNotification(info);
		if(info.getProgress() != 1 && !preloaderStage.isShowing())
		{
			preloaderStage.show();
		}
		//progressBar.setProgress(info.getProgress());
	}

	@Override
	public void handleStateChangeNotification(StateChangeNotification info)
	{
		super.handleStateChangeNotification(info);
		StateChangeNotification.Type notificationType = info.getType();

		switch(notificationType)
		{
		case BEFORE_LOAD:
			// Called after preloader#start is called.
			LOGGER.debug("ExchangeMonitorApplicationPreloader ", notificationType);
			break;

		case BEFORE_INIT:
			//Called before application#init is called.
			LOGGER.debug("ExchangeMonitorApplicationPreloader ", notificationType);
			break;

		case BEFORE_START:
			//Called after application#init and before application#start is called.
			LOGGER.debug("ExchangeMonitorApplicationPreloader ", notificationType);
			if(preloaderStage.isShowing())
			{
				FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), preloaderStage.getScene().getRoot());
				fadeTransition.setFromValue(1.0);
				fadeTransition.setToValue(0.0);
				final Stage tempStage = preloaderStage;

				EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>()
				{
					public void handle(ActionEvent event)
					{
						tempStage.hide();
					}
				};
				fadeTransition.setOnFinished(eventHandler);
				fadeTransition.play();
				//this.preloaderStage.hide();
			}
			break;
		}
	}

	private Scene createPreloaderScene()
	{
		//progressBar = new ProgressBar();
		//BorderPane borderPane = new BorderPane();
		//borderPane.setCenter(progressBar);
		//return new Scene(borderPane, 300, 150);

		PreloaderView preloaderView = new PreloaderView();
		preloaderScene = new Scene(preloaderView.getView());
		preloaderScene.setFill(Color.TRANSPARENT);
		return preloaderScene;

		/*
		LoginView loginView = new LoginView();
		return new Scene(loginView.getView());
		 */
	}
}