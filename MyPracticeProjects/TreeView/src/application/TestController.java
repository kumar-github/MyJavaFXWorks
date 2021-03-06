package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class TestController implements Initializable
{

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		createTree();
	}
	
	@FXML
	TreeView selectionTreeView;
	@FXML
	private void handleButtonAction(ActionEvent event) {
	    createTree();
	}

	public void createTree(String... rootItems)
	{
		
		TreeItem<String> treeItemRoot = new TreeItem<> ("Root");
        
        TreeItem<String> nodeItemA = new TreeItem<>("Item A");
        TreeItem<String> nodeItemB = new TreeItem<>("Item B");
        TreeItem<String> nodeItemC = new TreeItem<>("Item C");
        treeItemRoot.getChildren().addAll(nodeItemA, nodeItemB, nodeItemC);
         
        TreeItem<String> nodeItemA1 = new TreeItem<>("Item A1");
        TreeItem<String> nodeItemA2 = new TreeItem<>("Item A2");
        TreeItem<String> nodeItemA3 = new TreeItem<>("Item A3");
        nodeItemA.getChildren().addAll(nodeItemA1, nodeItemA2, nodeItemA3);
         
		
	    //create root
	    TreeItem<String> root = new TreeItem<>("Root");
	    
	    selectionTreeView.setRoot(treeItemRoot);
	}	
}