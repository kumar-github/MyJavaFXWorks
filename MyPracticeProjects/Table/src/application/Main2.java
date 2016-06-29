package application;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.SceneBuilder;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPaneBuilder;
import javafx.stage.Stage;
import javafx.util.Callback;


public class Main2 extends Application {

	TableView tableViewRef;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {

		stage.setScene(SceneBuilder.create().width(420).height(200).root(StackPaneBuilder.create().children(tableViewRef = createTableView()).build()).build());
		stage.setTitle("Testing Table View Cell Factory");
		stage.show();

		tableViewRef.getItems().addAll(mockPersonItems());
	}

	@SuppressWarnings("unchecked")
	private TableView createTableView()
	{
		TableView tableView = new TableView();

		TableColumn idColumn = new TableColumn("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory("id"));
		idColumn.setMinWidth(80);

		TableColumn nameColumn = new TableColumn("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
		nameColumn.setMinWidth(150);

		TableColumn cityColumn = new TableColumn("City");
		cityColumn.setCellValueFactory(new PropertyValueFactory("city"));
		cityColumn.setMinWidth(150);

		TableColumn stateColumn = new TableColumn("State");
		//stateColumn.setCellValueFactory(new Callback<P, R>() {});
		stateColumn.setCellValueFactory(new Callback<CellDataFeatures<Person,String>,ObservableValue<String>>(){ 
     
	@Override
     public ObservableValue call(CellDataFeatures<Person, String> c)
     {
      //return new SimpleStringProperty(c.getValue().getCity().getState().getName());
      return new SimpleStringProperty(c.getValue().getCity().getS1().getA());
     }
    });

  tableView.getColumns().addAll(idColumn, nameColumn, cityColumn,stateColumn);
  //tableView.getColumns().addAll(idColumn, nameColumn, cityColumn);

  return tableView;
 }

		private List mockPersonItems() {
			State sp = new State("st1", "st2");

			City saoJoseDosCampos = new City(sp, "city1");
			City taubate = new City(sp, "Taubaté");
			City jacarei = new City(sp, "Jacareí");

			Person p1 = new Person("William Antônio Siqueira", 1, saoJoseDosCampos);
			Person p2 = new Person("Maria", 2, saoJoseDosCampos);
			Person p3 = new Person("João", 3, taubate);
			Person p4 = new Person("Joana", 4, jacarei);

			return Arrays.asList(p1, p2, p3, p4);
		}
	}