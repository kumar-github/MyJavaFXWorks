package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class Main extends Application {
	//@Override
	/*public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("slideout.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public static void main(String[] args){
		launch(args);
	}
	public void start(Stage stage) throws Exception
	{
	    FlowPane contentPane = new FlowPane();
	
	    Label label = new Label("_Test Label");
	    label.setMnemonicParsing(true);
	    contentPane.getChildren().add(label);
	
	    TextField field = new TextField();
	    label.setLabelFor(field);
	    contentPane.getChildren().add(field);
	
	    Button button = new Button("_Go");
	    button.setMnemonicParsing(true);
	    button.setOnAction(new EventHandler<ActionEvent>()
	    {
	        public void handle(ActionEvent event)
	        {
	            System.out.println("Go button triggered");
	        }
	    });
	    contentPane.getChildren().add(button);
	
	    Scene scene = new Scene(contentPane, 800, 600);
	    stage.setScene(scene);
	    stage.show();
	}
}