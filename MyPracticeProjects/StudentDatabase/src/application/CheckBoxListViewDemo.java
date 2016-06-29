package application;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CheckBoxListViewDemo extends Application
{
	@Override
	public void start(Stage primaryStage) {
		try {
			FlowPane root = new FlowPane();
			
			ListView<String> listView = new ListView<String>(FXCollections.observableArrayList("welcome", "One", "Two", "Three"));
			
			Callback<String, ObservableValue<Boolean>> test = new Callback<String, ObservableValue<Boolean>>()
			{
				@Override
				public ObservableValue<Boolean> call(String param)
				{
					System.out.println("testing");
					return null;
				}
			};
			
			listView.setCellFactory(CheckBoxListCell.forListView(test));
			
			root.getChildren().add(listView);
			Scene scene = new Scene(root,400,400);
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
