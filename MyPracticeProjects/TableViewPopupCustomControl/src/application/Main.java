package application;
	
import control.TableViewPopup;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Person;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			TableViewPopup<Person> tableViewPopup = new TableViewPopup<Person>();
			//FXMLLoader.load(this.getClass().getResource("/view/PersonView.fxml"));
			HBox root = new HBox();
			root.setSpacing(10.0);
			root.setPadding(new Insets(10.0));
			TextField txt = new TextField();
			Button btn = new Button("Button");
			btn.setOnAction((event) -> { tableViewPopup.show(txt); });
			
			root.getChildren().addAll(txt, btn);	
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}