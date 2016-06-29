package com.airhacks.followme;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import com.airhacks.afterburner.injection.Injector;
import com.airhacks.followme.dashboard.view.java.DashboardView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
	@Override
    public void start(Stage stage) throws Exception
	{
        /*
         * Properties of any type can be easily injected.
         */
        LocalDate date = LocalDate.of(4242, Month.JULY, 21);
        Map<Object, Object> customProperties = new HashMap<>();
        customProperties.put("date", date);
        
        /* any function which accepts an Object as key and returns an Object as result can be used as source. */
        Injector.setConfigurationSource(customProperties::get);

        System.setProperty("SystemProperty", "SystemPropertyValue");
        DashboardView appView = new DashboardView();
        Scene scene = new Scene(appView.getView());
        stage.setTitle("Test App");
        final String uri = getClass().getResource("App.css").toExternalForm();
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