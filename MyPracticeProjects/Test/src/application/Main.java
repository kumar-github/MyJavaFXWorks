package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception
    {
    	javafx.scene.text.Font baumansFont = javafx.scene.text.Font.loadFont(getClass().getResourceAsStream("MAGNETOB.ttf"), 10);
            //BorderPane root = new BorderPane();
    	Parent root = FXMLLoader.load(this.getClass().getResource("FinalTest.fxml"));
            Scene scene = new Scene(root,400,400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}