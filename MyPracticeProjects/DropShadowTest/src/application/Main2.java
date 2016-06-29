package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main2 extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		AnchorPane shadowPane = FXMLLoader.load(this.getClass().getResource("AA2.fxml"));
	    AnchorPane rootPane = (AnchorPane) shadowPane.lookup("#rootPane");
	    rootPane.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 10, 0.5, 0.0, 0.0);" + "-fx-background-color: white;"); // Shadow effect
	    //shadowPane.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 10, 0.5, 0.0, 0.0);" + "-fx-background-color: white;"); // Shadow effect

	    Scene scene = new Scene(shadowPane);
	    stage.setScene(scene);

	    //shadowPane.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, null, null))); // Some borders for for clarity

	    shadowPane.setStyle("-fx-background-color: transparent;"); // Makes shadowPane transparent
	    scene.setFill(Color.TRANSPARENT); // Fill our scene with nothing
	    stage.initStyle(StageStyle.TRANSPARENT); // Important one!
	    stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
