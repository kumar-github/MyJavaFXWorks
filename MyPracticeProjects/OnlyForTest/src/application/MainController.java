package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class MainController implements Initializable
{
	@FXML TableView<User> tableView;
	@FXML TableColumn<User, String> col1;
	@FXML TableColumn<User, String> col2;
	
	@FXML Button btn;
	
	ObservableList<User> items = FXCollections.observableArrayList();
	ObservableList<User> tempItems = FXCollections.observableArrayList();

	@FXML public void handleButtonClick()
	{
		//items.add(new User("First3", "Last3"));
		tempItems.add(new User("First3", "Last3"));
		items = tempItems;
		//tableView.setItems(items);
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		col1.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		col2.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		items.add(new User("First1", "Last1"));
		items.add(new User("First2", "Last2"));
		tableView.setItems(items);
		items.add(new User("Test", "Test"));
	}
}