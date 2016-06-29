// BPMonitor.java - Main application
// Copyright 2015, Anderson Software Group, Inc.
// Gail and Paul Anderson

package asgteach.bpmonitor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BPMonitor extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("BPMonitor.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("asgteach/bpmonitor/bpStyles.css");
        
        stage.setScene(scene);
        stage.setTitle("Blood Pressure Monitor and Chart Application");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
