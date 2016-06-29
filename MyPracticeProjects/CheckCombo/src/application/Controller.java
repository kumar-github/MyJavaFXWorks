package application;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class Controller implements Initializable
{
	@FXML
	private CheckComboBox<String>tradeAccountListView1;
	@FXML
	private CheckComboBox<String>tradeAccountListView2;
	@FXML
	private CheckComboBox<String>tradeAccountListView3;
	@FXML
	private CheckComboBox<String>tradeAccountListView4;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		// create the data to show in the CheckComboBox 
		 final ObservableList<String> strings = FXCollections.observableArrayList();
		 for (int i = 0; i <= 100; i++)
		 {
		     strings.add("Item " + i);
		 }
		 
		 // Create the CheckComboBox with the data 
		 //tradeAccountListView = new CheckComboBox<String>(strings);
		 //tradeAccountListView.getItems().add("One");
		 //tradeAccountListView.getItems().add("Two");
		 tradeAccountListView1.getItems().addAll(strings);
		 tradeAccountListView2.getItems().addAll(strings);
		 tradeAccountListView3.getItems().addAll(strings);
		 tradeAccountListView4.getItems().addAll(strings);
		 
		 // and listen to the relevant events (e.g. when the selected indices or 
		 // selected items change).
		 /*checkComboBox.getCheckModel().getSelectedItems().addListener(new ListChangeListener<String>() {
		     public void onChanged(ListChangeListener.Change<? extends String> c) {
		         System.out.println(checkComboBox.getCheckModel().getSelectedItems());
		     }
		 });
		 }*/
	}
}