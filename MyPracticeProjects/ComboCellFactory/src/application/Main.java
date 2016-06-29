package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.util.Callback;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FlowPane root = new FlowPane();
			//Parent root = FXMLLoader.load(this.getClass().getResource("Main.fxml"));
			
			ComboBox<Member> cmb = new ComboBox<Member>();
			  ObservableList<Member> list = FXCollections.observableArrayList();
			  list.add(new Member("Cengiz"));
			  list.add(new Member("Mueller"));
			  list.add(new Member("Nick"));
			  
			  
			  cmb.setItems(list);
			  cmb.getSelectionModel().selectFirst();
			  
			  Callback x = (comboBox) -> {
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
			           };};
			  
			  
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
			           cmb.setCellFactory(x);
			  
			root.getChildren().add(cmb);
			
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

class X implements Callback<ListView, ListCell>
{
	@Override
	public ListCell call(ListView param)
	{
		return new Y();
	}
}

class Y extends ListCell<String>
{
	@Override
	protected void updateItem(String item, boolean empty) {
		// TODO Auto-generated method stub
		super.updateItem(item, empty);
	}
}