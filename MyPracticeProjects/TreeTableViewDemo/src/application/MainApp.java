package application;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static void main(String[] args) {
		Application.launch(MainApp.class, args);
	}

	@Override
	public void start(Stage stage) {

		List<Employee> lstData = new ArrayList<>();

		lstData.add(new Employee("Charles", "charles@sencageek.com"));
		lstData.add(new Employee("John", "john@sencageek.com"));

		TreeItem root = new TreeItem<>(new Employee("Sales Department", "Testing"));
		root.setExpanded(true);

		for (Employee employee : lstData) {
			root.getChildren().addAll(
					new TreeItem<>(employee)
			);
		}

		stage.setTitle("POJO Tree Table View Sample");
		final Scene scene = new Scene(new VBox(), 400, 400);
		scene.setFill(Color.LIGHTGRAY);
		VBox sceneRoot = (VBox) scene.getRoot();

		TreeTableColumn<Employee, String> empColumn = new TreeTableColumn<>("Employee");

		empColumn.setPrefWidth(150);
		empColumn.setEditable(true);
		empColumn.setCellValueFactory((CellDataFeatures<Employee, String> p) -> new ReadOnlyStringWrapper(
				p.getValue().getValue().getName()));

		empColumn.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());

		TreeTableColumn<Employee, String> emailColumn = new TreeTableColumn<>("Email");

		emailColumn.setEditable(true);
		emailColumn.setPrefWidth(190);

		emailColumn.setCellValueFactory((CellDataFeatures<Employee, String> p) -> new ReadOnlyStringWrapper(
						p.getValue().getValue().getEmail()));
		emailColumn.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());

		TreeTableView treeTableView = new TreeTableView<>(root);

		treeTableView.getColumns().setAll(empColumn, emailColumn);
		treeTableView.setEditable(true);

	
		sceneRoot.getChildren().add(treeTableView);
		stage.setScene(scene);

		stage.show();
	}
 
}