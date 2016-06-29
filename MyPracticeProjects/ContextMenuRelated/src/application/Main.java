package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//BorderPane root = new BorderPane();
			HBox root = new HBox();
			
			final ContextMenu contextMenu = new ContextMenu();
			contextMenu.setOnShowing(new EventHandler<WindowEvent>() {
			    public void handle(WindowEvent e) {
			        System.out.println("showing");
			    }
			});
			contextMenu.setOnShown(new EventHandler<WindowEvent>() {
			    public void handle(WindowEvent e) {
			        System.out.println("shown");
			    }
			});

			MenuItem item1 = new MenuItem("About");
			item1.setOnAction(new EventHandler<ActionEvent>() {
			    public void handle(ActionEvent e) {
			        System.out.println("About");
			    }
			});
			MenuItem item2 = new MenuItem("Preferences");
			item2.setOnAction(new EventHandler<ActionEvent>() {
			    public void handle(ActionEvent e) {
			        System.out.println("Preferences");
			    }
			});
			contextMenu.getItems().addAll(item1, item2);
			
			Label lbl = new Label("Testing");
			lbl.setContextMenu(contextMenu);
					

			TabPane tabs = new TabPane();
			tabs.getTabs().add(new MTab("_this is a test"));
			tabs.getTabs().add(new MTab("t_his is a test"));
			tabs.getTabs().add(new MTab("th_is is a test"));
			
			
			root.getChildren().add(tabs);
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
	
			
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
