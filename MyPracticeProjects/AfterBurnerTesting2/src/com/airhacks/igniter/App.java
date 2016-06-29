package com.airhacks.igniter;

import com.airhacks.afterburner.injection.Injector;
import com.airhacks.igniter.presentation.followme.FollowmeView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
	@Override
    public void start(Stage stage) throws Exception
	{
        FollowmeView appView = new FollowmeView();
        Scene scene = new Scene(appView.getView());
        stage.setTitle("reactivefx.fx v1.0-SNAPSHOT");
        final String uri = getClass().getResource("app.css").toExternalForm();
        scene.getStylesheets().add(uri);
        stage.setScene(scene);
        stage.show();
    }
	
	@Override
    public void stop() throws Exception
	{
    	Injector.forgetAll();
    }
	
	public static void main(String[] args)
	{
        launch(args);
    }
}