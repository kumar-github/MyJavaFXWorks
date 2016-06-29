package application;
	
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
			ComboBox<Address> addressCombo = new ComboBox<Address>();
			Address address1 = new Address();
			address1.setStreetName("1st Street");
			
			Address address2 = new Address();
			address2.setStreetName("2nd Street");
			
			Address address3 = new Address();
			address3.setStreetName("3rd Street");
			
			Address address4 = new Address();
			address4.setStreetName("4th Street");
			
			
			Person person1 = new Person();
			person1.setFirstName("A");
			person1.setLastName("B");
			person1.setAddress(address1);
			
			Person person2 = new Person();
			person2.setFirstName("C");
			person2.setLastName("D");
			person2.setAddress(address2);
			
			Person person3 = new Person();
			person3.setFirstName("E");
			person3.setLastName("F");
			person3.setAddress(address3);
			
			Person person4 = new Person();
			person4.setFirstName("G");
			person4.setLastName("H");
			person4.setAddress(address4);
			
			addressCombo.setButtonCell(new ButtonCell());
			addressCombo.setCellFactory(new Callback<ListView<Address>, ListCell<Address>>()
			{
				@Override
				public ListCell<Address> call(ListView<Address> param)
				{
					ListCell<Address> cell = new ListCell<Address>()
							{
						@Override
						protected void updateItem(Address item, boolean empty)
						{
							super.updateItem(item, empty);
							
							if(item == null || empty)
								this.setText(null);
							else
								this.setText(item.getStreetName());
						}
							};
							
							return cell;
				}
			});	
			
			addressCombo.setItems(FXCollections.observableArrayList(address1, address2, address3, address4));
			
			FlowPane root = new FlowPane();
			root.getChildren().add(addressCombo);
			Button b = new Button("Click");
			Button b2 = new Button("Click2");
			
			b.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event)
				{
					System.out.println(person1);
				}
			});
			
			b2.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent event)
				{
					System.out.println(person1);
					person1.setAddress(address4);
					System.out.println(person1);
				}
			});
			
			//person1.addressProperty().bind(addressCombo.valueProperty());
			//person1.addressProperty().bindBidirectional(addressCombo.valueProperty());
			
			/*addressCombo.valueProperty().addListener(new ChangeListener<Address>()
			{
				@Override
				public void changed(ObservableValue<? extends Address> observable, Address oldValue, Address newValue)
				{
					System.out.println("inside value property");
				}});
			
			addressCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Address>()
			{
				@Override
				public void changed(ObservableValue<? extends Address> observable, Address oldValue, Address newValue)
				{
					System.out.println("inside selected item property");
				}});*/
			
			root.getChildren().add(b);
			root.getChildren().add(b2);
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

class ButtonCell extends ListCell<Address>
{
	@Override
	protected void updateItem(Address item, boolean empty)
	{
		super.updateItem(item, empty);
		if(item == null || empty)
			this.setText(null);
		else
			this.setText(item.getStreetName());
	}
}