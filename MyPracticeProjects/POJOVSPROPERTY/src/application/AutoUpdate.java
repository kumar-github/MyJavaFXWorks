package application;
	
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class AutoUpdate extends Application
{
	ObservableList listItems2 = FXCollections.observableArrayList();
	ObservableList listItems3 = FXCollections.observableArrayList();
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	public void start(Stage stage) throws Exception
	{
		ListView listView1 = new ListView<>();
		ListView listView2 = new ListView<>();
		TextField txt = new TextField();
		txt.setPromptText("Enter data");
		TextField txt2 = new TextField();
		txt2.setPromptText("Initial data");
		txt2.textProperty().bind(txt.textProperty());
		List listItems1 = new ArrayList();
		//ObservableList listItems2 = FXCollections.observableArrayList();
		//ObservableList listItems3 = FXCollections.observableArrayList();
		listItems3.addAll("A", "B", "C");
		
		
		listItems1.add("One");
		listItems1.add("Two");
		
		listItems2.add("Three");
		listItems2.add("Four");
		
		
		listView1.setItems(FXCollections.observableArrayList(listItems1));
		listView2.setItems(listItems2);
		
	    FlowPane contentPane = new FlowPane();
	    contentPane.setHgap(10);
	    contentPane.setVgap(10);
	    Button button1 = new Button("_Go");
	    button1.setMnemonicParsing(true);
	    button1.setOnAction(new EventHandler<ActionEvent>()
	    {
	        public void handle(ActionEvent event)
	        {
	            //System.out.println("Go button triggered");
	        	listItems1.add("Testing1");
	        	listItems2.add("Testing2");
	        	//listItems2 = listItems3;
	        	listItems2.addAll(listItems3);
	        	
	        	//listView2.setItems(listItems2);
	        }
	    });
	    //contentPane.getChildren().addAll(button1, listView1, listView2, txt, txt2);
	    contentPane.getChildren().addAll(button1, listView1, listView2);
	
	    Scene scene = new Scene(contentPane, 800, 600);
	    stage.setScene(scene);
	    stage.show();
	}
}