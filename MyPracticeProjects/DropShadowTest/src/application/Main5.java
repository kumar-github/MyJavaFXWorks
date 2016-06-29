package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main5 extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
				primaryStage.initStyle(StageStyle.UNDECORATED);
				primaryStage.initStyle(StageStyle.TRANSPARENT);
				
				Parent root = FXMLLoader.load(this.getClass().getResource("AA4.fxml"));
				
				
				
				
				
				Scene scene = new Scene(root, 400, 400);
				scene.setFill(Color.TRANSPARENT);
				primaryStage.setScene(scene);
				
				scene.getStylesheets().add(getClass().getResource("main5.css").toExternalForm());
				
				root.getStyleClass().add("style4");
				
				primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
