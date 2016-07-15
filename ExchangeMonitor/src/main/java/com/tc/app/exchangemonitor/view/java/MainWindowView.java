package com.tc.app.exchangemonitor.view.java;

import com.tc.framework.fxmlview.FXMLView;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindowView extends FXMLView
{
	private double xOffset = 0.0;
	private double yOffset = 0.0;
	Stage primaryStage;
	public MainWindowView()
	{
	}

	public MainWindowView(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
	}

	Parent x;
	@Override
	public Parent getView()
	{
		return super.getView();
	}
	
	Scene primaryScene;
	public Scene getScene()
	{
		primaryScene = new Scene(getView());
		makeSceneDraggable();
		return primaryScene;
	}

	public void makeSceneDraggable()
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
}