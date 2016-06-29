package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	private double yOffset;
	private double xOffset;

	@Override
	public void start(Stage primaryStage) {
		try {
			//BorderPane root = new BorderPane();
			Parent root = FXMLLoader.load(this.getClass().getResource("undec.fxml"));
			Scene scene = new Scene(root,400,400);
			
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			scene.setOnMousePressed(event->{xOffset = primaryStage.getX() - event.getScreenX();
			yOffset = primaryStage.getY() - event.getScreenY();
			});
			
			scene.setOnMouseDragged(event->{primaryStage.setX(event.getScreenX() + xOffset);
			primaryStage.setY(event.getScreenY() + yOffset);
			});		
						
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
