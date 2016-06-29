package application;

import org.controlsfx.control.CheckListView;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class fx extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
    	//create the data to show in the CheckListView
    	final ObservableList<String> listOfItems = FXCollections.observableArrayList();
        for (int i = 0; i <= 100; i++)
        {
            listOfItems.add("Item " + i);
        }
        
        //Create the CheckListView with the data 
        final CheckListView<String> checkListView = new CheckListView<>(listOfItems);

        // Select the first checkListView element
        checkListView.getItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                c.next();
                if (c.wasAdded()) {
                    checkListView.getSelectionModel().select(c.getAddedSubList().get(0));
                }
            }
        });

        // On CheckBox event
        checkListView.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends String> c)
            {
            	System.out.println(checkListView.getCheckModel().getCheckedItems());
                c.next();
                if(c.wasAdded()) {
                    System.out.println("Item Checked : " + c.getAddedSubList().get(0));
                } else if (c.wasRemoved()) {
                    System.out.println("Item Unchecked : " + c.getRemoved().get(0));
                }
            }
        });

    Button button = new Button("Add");
    button.setOnAction(e -> {
        checkListView.getItems().add(0, "Itachi");
            checkListView.requestFocus();
        });
        Scene scene = new Scene(new VBox(checkListView, button), 300, 275);
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
        }
    }