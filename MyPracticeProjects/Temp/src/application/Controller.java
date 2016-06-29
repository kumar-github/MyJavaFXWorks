package application;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckListView;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;


public class Controller implements Initializable
{
	@FXML
	private CheckListView<String>test;
	
	@FXML
	private ListView<String> listView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
        // set the cell factory
        Callback<String, ObservableValue<Boolean>> getProperty = new Callback<String, ObservableValue<Boolean>>() {
            @Override
            public BooleanProperty call(String person) {
            	System.out.println(person + " is clicked");
            	return null;
            }
        };
        listView.setCellFactory(CheckBoxListCell.forListView(getProperty));
		//FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30");
		//test.setItems(FXCollections.observableArrayList("One", "Two", "Three"));
		listView.setItems(FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"));
	}
}