package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main3 extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(this.getClass().getResource("AA3.fxml"));
		
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initStyle(StageStyle.TRANSPARENT); // Important one!
		
		//root.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.4), 10, 0.5, 0.0, 0.0);" + "-fx-background-color: white;"); // Shadow effect
		
		root.setStyle("-fx-border-insets: 23");
		root.setStyle("-fx-background-insets: 23");
		root.setStyle("-fx-background-radius: 6");
		root.setStyle("-fx-border-radius: 6");
		root.setStyle("-fx-border-color: gray");
		root.setStyle("-fx-border-style: solid");
		root.setStyle("-fx-border-width: 1");
		root.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(100, 100, 100, 1), 24, 0.5, 0, 0)");

//		root.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(100, 100, 100, 1), 24, 0.5, 0, 0)");
	    Scene scene = new Scene(root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}