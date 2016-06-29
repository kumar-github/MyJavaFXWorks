package com.airhacks.followme;

import com.airhacks.afterburner.injection.Injector;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Map<Object, Object> customProperties = new HashMap<>();
        customProperties.put("message", " Programmatic hello");
        Injector.setConfigurationSource(customProperties::get);

        AppView appView = new AppView();
        Scene scene = new Scene(appView.getView());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        Injector.forgetAll();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
