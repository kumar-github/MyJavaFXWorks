package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController implements Initializable
{
    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> c1;

    @FXML
    private TableColumn<?, ?> c2;

    @FXML
    private TableColumn<?, ?> c3;

    @FXML
    private TableColumn<?, ?> c4;
	
    @Override
	public void initialize(URL location, ResourceBundle resources)
	{
    	TableColumn idColumn = new TableColumn("Id");
    	idColumn.setCellValueFactory(new PropertyValueFactory("id"));
    	TableColumn nameColumn = new TableColumn("Name");
    	nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
    	tableView.getColumns().addAll(idColumn, nameColumn);
	}
}