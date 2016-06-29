package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
			try {
				primaryStage.initStyle(StageStyle.UNDECORATED);
				primaryStage.initStyle(StageStyle.TRANSPARENT);
				
				//BorderPane root = FXMLLoader.load(this.getClass().getResource("AA1.fxml"));
				BorderPane root = new BorderPane();
				
				Scene scene = new Scene(root, 400, 400);
				scene.setFill(Color.TRANSPARENT);
				primaryStage.setScene(scene);
				
				scene.getStylesheets().add(getClass().getResource("shadow.css").toExternalForm());
				
				primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
