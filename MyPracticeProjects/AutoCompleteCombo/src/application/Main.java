package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
			try {
			//Parent root = FXMLLoader.load(this.getClass().getResource("Main2.fxml"));
			Parent root = FXMLLoader.load(this.getClass().getResource("AA.fxml"));
			Scene scene = new Scene(root,400,400);
			
			primaryStage.setScene(scene);
			//root.setStyle("-fx-background-radius: 20");
			//root.setStyle("-fx-effect: dropshadow( two-pass-box , black , 10 , 10 , 10 , 10 )");
			
			//root.setStyle("-fx-background-color:green");
			
			root.setStyle("-fx-border-insets: 23");
			root.setStyle("-fx-background-insets: 23");
			root.setStyle("-fx-background-radius: 6");
			root.setStyle("-fx-border-radius: 6");
			root.setStyle("-fx-border-color: gray");
			root.setStyle("-fx-border-style: solid");
			root.setStyle("-fx-border-width: 1");
			root.setStyle("-fx-effect: dropshadow(one-pass-box, rgba(100, 100, 100, 1), 24, 0.5, 0, 0)");
			
			primaryStage.initStyle(StageStyle.UNDECORATED);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
