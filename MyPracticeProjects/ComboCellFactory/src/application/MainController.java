package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class MainController implements Initializable
{
	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		  /*cmb.setCellFactory(new Callback<ListView<Member>, ListCell<Member>>() {
		       @Override
		       public ListCell<Member> call(ListView<Member> param) {
		  
		            return new ListCell<Member>(){
		              @Override
		                public void updateItem(Member item, boolean empty){
		                  super.updateItem(item, empty);
		                  if(!empty) {
		                    setText(item.getName());
		                    setGraphic(null);
		                  } else {
		                    setText(null);
		                  }
		                }
		           };
		      }
		  });*/
	}
}