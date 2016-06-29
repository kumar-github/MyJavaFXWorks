package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
 
    private Stage primaryStage;
    private BorderPane rootLayout;
 
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("BATS Ticker Application");
        FXMLLoader loader = null;
        try {
            // Load the root layout from the fxml file
            loader = new FXMLLoader(MainApp.class.getResource("MainUI.fxml"));
            rootLayout = (BorderPane) loader.load();
        } catch (Exception e) {
            // Exception gets thrown if the fxml file could not be loaded
            System.out.println("FXML NOT LOADED !!!!!");
            e.printStackTrace();
        }
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}