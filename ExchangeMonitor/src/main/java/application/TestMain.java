package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TestMain extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			//VBox root = FXMLLoader.load(this.getClass().getResource("LoginView.fxml"));
			//FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tc/app/exchangemonitor/view/fxml/LoginView.fxml"));
			FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
			Scene scene = new Scene(new StackPane());
			scene.setRoot((Parent) loader.load());
			//Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//LoginManager loginManager = new LoginManager(scene);
			//loginManager.showLoginScreen();
			
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.setScene(scene);	
			primaryStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}