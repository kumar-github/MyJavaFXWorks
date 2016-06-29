package application;
	
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


public class Main1 extends Application
{
	@Override
	public void start(Stage primaryStage) {
		try {
			/*TableView tableView = new TableView();
			TableColumn idColumn = new TableColumn("Id");
			idColumn.setCellValueFactory(new PropertyValueFactory("id"));
			TableColumn nameColumn = new TableColumn("Name");
			nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
			tableView.getColumns().addAll(idColumn, nameColumn);
			BorderPane root = new BorderPane();*/
			Parent root = FXMLLoader.load(this.getClass().getResource("testtable.fxml"));
			//root.getChildren().add(tableView);
			Scene scene = new Scene(root,600,400);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}