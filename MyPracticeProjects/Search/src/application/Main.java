package application;
	
import org.controlsfx.control.CheckListView;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			CheckListView<String>filterList = new CheckListView<>();
			filterList.setItems(FXCollections.observableArrayList("Item1", "Item2", "Item3", "Item4"));
			filterList.getItems().addListener(new ListChangeListener<String>()
			{

				@Override
				public void onChanged(javafx.collections.ListChangeListener.Change<? extends String> change)
				{
				    System.out.println("Start of Change Listener 'sourceItemsListener'");

				    while(change.next()) {

				        System.out.println("     Added:      " + change.wasAdded());
				        System.out.println("     Permutated: " + change.wasPermutated());
				        System.out.println("     Removed:    " + change.wasRemoved());
				        System.out.println("     Updated:    " + change.wasUpdated());

				    }

				    System.out.println("End of  of Change Listener 'sourceItemsListener'");
					
				}});
		    filterList.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>()
			{

				@Override
				public void onChanged(javafx.collections.ListChangeListener.Change<? extends String> change)
				{
				    System.out.println("Start of Change Listener 'checkedItemsListener'");
				    System.out.println(change);
				    System.out.println(change.getList());

				    while(change.next()) {
				        System.out.println("     Added:      " + change.wasAdded());
				        System.out.println("     Permutated: " + change.wasPermutated());
				        System.out.println("     Removed:    " + change.wasRemoved());
				        System.out.println("     Updated:    " + change.wasUpdated());
				    }

				    System.out.println("End of  of Change Listener 'checkedItemsListener'");
					
				}});
		    
		    //filterList.getCheckModel().checkAll();
		    
			
			FlowPane root = new FlowPane();
			root.getChildren().add(filterList);
			Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			primaryStage.show();		    
		}
		catch(Exception e){}


		/*ListChangeListener<String> sourceItemsListener = (change) -> {

		    System.out.println("Start of Change Listener 'sourceItemsListener'");

		    while(change.next()) {

		        System.out.println("     Added:      " + change.wasAdded());
		        System.out.println("     Permutated: " + change.wasPermutated());
		        System.out.println("     Removed:    " + change.wasRemoved());
		        System.out.println("     Updated:    " + change.wasUpdated());

		    }

		    System.out.println("End of  of Change Listener 'sourceItemsListener'");
		};

		ListChangeListener<String> checkedItemsListener = (change) -> {

		    System.out.println("Start of Change Listener 'checkedItemsListener'");

		    while(change.next()) {

		        System.out.println("     Added:      " + change.wasAdded());
		        System.out.println("     Permutated: " + change.wasPermutated());
		        System.out.println("     Removed:    " + change.wasRemoved());
		        System.out.println("     Updated:    " + change.wasUpdated());

		    }

		    System.out.println("End of  of Change Listener 'checkedItemsListener'");
		};*/
	}
	public static void main(String[] args) {
		launch(args);
	}
}
