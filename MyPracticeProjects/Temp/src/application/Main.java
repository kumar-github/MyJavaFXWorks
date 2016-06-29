package application;
	
import org.controlsfx.control.CheckListView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			//BorderPane root = new BorderPane();
			//Parent root = FXMLLoader.load(this.getClass().getResource("main.fxml"));
			Parent root = FXMLLoader.load(this.getClass().getResource("testfxml.fxml"));
			Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			CheckListView<String> x;
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
