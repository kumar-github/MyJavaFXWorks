package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controller implements Initializable
{
	@FXML
	TextField txt1;
	
	@FXML
	TextField txt2;
	
	@FXML
	TableView<POJOPerson> pojoTable;
	
	@FXML
	TableColumn<POJOPerson, String> pojoFirstNameColumn;
	
	@FXML
	TableColumn<POJOPerson, String> pojoLastNameColumn;
	
	@FXML
	TableView<BindingPerson> bindingTable;
	
	@FXML
	TableColumn<BindingPerson, String> bindingFirstNameColumn;
	
	@FXML
	TableColumn<BindingPerson, String> bindingLastNameColumn;
	
	//List<POJOPerson> pojoModel = new ArrayList<>();
	//List<BindingPerson> bindingModel = new ArrayList<>();

	
	ObservableList<POJOPerson> pojoModel = FXCollections.observableArrayList();
	ObservableList<BindingPerson> bindingModel = FXCollections.observableArrayList();	
	
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		pojoModel.add(new POJOPerson("David", "Glasspool"));
		bindingModel.add(new BindingPerson("David", "Glasspool"));
		
		pojoFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		pojoLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		
		//pojoTable.setItems(FXCollections.observableArrayList(pojoModel));
		pojoTable.setItems(pojoModel);
		
		bindingFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		bindingLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		
		//bindingTable.setItems(FXCollections.observableArrayList(bindingModel));
		bindingTable.setItems(bindingModel);
	}
	
	@FXML
	public void replace()
	{
		String searchText = txt1.getText();
		String replaceText = txt2.getText();
		
		pojoModel.forEach(p->{
			p.setFirstName(p.getFirstName().replaceAll(searchText, replaceText));
			p.setLastName(p.getLastName().replaceAll(searchText, replaceText));
			
		});
		
		bindingModel.forEach(p->{
			p.setFirstName(p.getFirstName().replaceAll(searchText, replaceText));
			p.setLastName(p.getLastName().replaceAll(searchText, replaceText));
			
		});
		
		bindingModel.add(new BindingPerson("A", "B"));
		//pojoModel.add(new POJOPerson("C", "D"));
	}
}