package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class MainWindowController implements Initializable
{
	//ObservableList data = FXCollections.observableArrayList("One", "Two", "Three", "Four");
	
	//@FXML
	//private ListView listView;
	
	@Inject
	private String prefix;
	
	@FXML
	BorderPane mainWindowBorderPane;
	
	@FXML
    private void initialize() 
    {
    	System.out.println("...........");
    }
    
    public MainWindowController()
    {
    	System.out.println("constructor");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    	System.out.println(mainWindowBorderPane);
        //fetched from dashboard.properties
        if(rb != null)
        {
        	System.out.println("theEnd : " + rb.getString("theEnd") + "prefix :" + prefix);
        	/*listView.setItems(data);
        	
            listView.setCellFactory(CheckBoxListCell.forListView(new Callback<String, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(String item) {
                    BooleanProperty observable = new SimpleBooleanProperty();
                    observable.addListener((obs, wasSelected, isNowSelected) -> 
                        System.out.println("Check box for "+item+" changed from "+wasSelected+" to "+isNowSelected)
                    );
                    return observable ;
                }
            }));*/
        }
    }
}