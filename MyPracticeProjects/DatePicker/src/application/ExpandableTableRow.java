package application;


import java.util.Arrays;
import java.util.concurrent.Callable;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
 
public class ExpandableTableRow extends Application {
 
    private TableView<Person> table = new TableView<Person>();
    private final ObservableList<Person> data =
        FXCollections.observableArrayList(
            new Person("Jacob", "Smith", "jacob.smith@example.com"),
            new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
            new Person("Ethan", "Williams", "ethan.williams@example.com"),
            new Person("Emma", "Jones", "emma.jones@example.com"),
            new Person("Michael", "Brown", "michael.brown@example.com")
        );
   
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(450);
        stage.setHeight(500);
 
        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
        
        final ObservableSet<Person> expandedRows = FXCollections.observableSet();
        
        final TableColumn<Person, Person> expandCol = new TableColumn<>();
        expandCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person,Person>, ObservableValue<Person>>() {
            @Override
            public ObservableValue<Person> call(
                    CellDataFeatures<Person, Person> cdf) {
                return new ReadOnlyObjectWrapper<Person>(cdf.getValue());
            }
        });
        expandCol.setCellFactory(new Callback<TableColumn<Person,Person>, TableCell<Person,Person>>() {

            @Override
            public TableCell<Person, Person> call(TableColumn<Person, Person> col) {
                return createExpanderCell(expandedRows);
            }
        });
 
        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));
 
        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));
 
        TableColumn<Person, String> emailCol = new TableColumn<>("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));
 
        table.setItems(data);
        table.getColumns().addAll(Arrays.asList(expandCol, firstNameCol, lastNameCol, emailCol));
        
        
        // BROKEN: this doesn't do what I wanted at all
        // I think the graphic property is not used for a TableRow implementation
        // (or at least, is not used to display the content of the row)
        table.setRowFactory(new Callback<TableView<Person>, TableRow<Person>>() {  
            @Override  
            public TableRow<Person> call(TableView<Person> tableView) {  
                
                // default table row displays table cells:
                final TableRow<Person> defaultRow = new TableRow<>();

                // actual row we are going to use:
                final TableRow<Person> row = new TableRow<Person>() ;
                
                // ensure default row has the same tableView and item set:
                defaultRow.updateTableView(tableView);
                defaultRow.itemProperty().bind(row.itemProperty());

                // content of row:
                final VBox vbox = new VBox();

                // extra content displayed when expanded:
                final Text additional = new Text();
                additional.textProperty().bind(Bindings.format("This could be an arbitrary node, containing information from the item in the current row: in this case %s", row.itemProperty()));
                
                // Is this row expanded?
                final ObservableBooleanValue expanded = Bindings.createBooleanBinding(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return expandedRows.contains(row.getItem());
                    }
                }, row.itemProperty(), expandedRows);
                
                // update display if item changes:
                row.itemProperty().addListener(new ChangeListener<Person>() {
                    @Override
                    public void changed(
                            ObservableValue<? extends Person> observable,
                            Person oldValue, Person newValue) {
                        vbox.getChildren().setAll(defaultRow);
                    }
                });
                
                // update display if expanded state changes:
                expanded.addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(
                            ObservableValue<? extends Boolean> observable,
                            Boolean oldValue, Boolean newValue) {
                        System.out.println("Expanded rows: " + expandedRows + " Item: " + row.getItem() + " include additional: " + expandedRows.contains(row.getItem()));
                        if (newValue) {
                            vbox.getChildren().add(additional);
                        } else {
                            vbox.getChildren().remove(additional);
                        }
                        System.out.println(((Parent) row.getGraphic()).getChildrenUnmodifiable());
                    }
                });
                
                row.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                row.setGraphic(vbox);
                return row ;  
            }  
        });  
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
 
    private TableCell<Person, Person> createExpanderCell(final ObservableSet<Person> expandedRows) {
        final TableCell<Person, Person> cell = new TableCell<>();
        final Button expandButton = new Button();
        cell.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        
        // display button only when cell is non-empty:
        cell.graphicProperty().bind(Bindings.when(cell.emptyProperty()).then((Node)null).otherwise(expandButton));
        
        // add or remove current item from expandedRows on button press
        expandButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (expandedRows.contains(cell.getItem())) {
                    expandedRows.remove(cell.getItem());
                } else {
                    expandedRows.add(cell.getItem());
                }
            }
        });
        
        // set text depending on whether row is expanded or not:
        expandButton.textProperty().bind(Bindings.createStringBinding(new Callable<String>() {
            @Override
            public String call() throws Exception {
                if (expandedRows.contains(cell.getItem())) {
                    return "-";
                } else {
                    return "+";
                }
            }
        }, cell.itemProperty(), expandedRows));
        
        return cell ;
    }

    public static class Person {
 
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;
 
        private Person(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }
 
        public String getFirstName() {
            return firstName.get();
        }
 
        public void setFirstName(String fName) {
            firstName.set(fName);
        }
 
        public String getLastName() {
            return lastName.get();
        }
 
        public void setLastName(String fName) {
            lastName.set(fName);
        }
 
        public String getEmail() {
            return email.get();
        }
 
        public void setEmail(String fName) {
            email.set(fName);
        }
        
        @Override
        public String toString() {
            return String.format("%s %s", getFirstName(), getLastName());
        }
    }
} 