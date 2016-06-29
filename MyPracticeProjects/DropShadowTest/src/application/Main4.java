package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main4 extends Application {
	@Override
	public void start(Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.getStyleClass().add("shadow-pane");
 
        stage.setTitle("Hello World");
        Scene scene = new Scene(borderPane, 300, 275);
        //scene.getStylesheets().add("/sample/style.css");
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getScene().setFill(Color.TRANSPARENT);
        stage.show();
    }	
	public static void main(String[] args) {
		launch(args);
	}
}
