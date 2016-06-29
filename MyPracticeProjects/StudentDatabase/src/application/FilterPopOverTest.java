package application;
import application.FilterPopOverSO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FilterPopOverTest extends Application {

@Override
public void start(Stage primaryStage) {

    ObservableList<String> sourceList = FXCollections.observableArrayList("Item 1", "Item 2", "Item 3");
    FilterPopOverSO<String> p = new FilterPopOverSO<>();        

    AnchorPane root = new AnchorPane();

    Button btn = new Button("Click Me!");

    btn.setOnAction(event -> {

        System.out.println("Just to show that I was here!");
        p.setSourceData(sourceList);
        p.show(btn);

    });

    root.getChildren().add(btn);
    AnchorPane.setTopAnchor(btn, 50D);
    AnchorPane.setLeftAnchor(btn, 50D);

    primaryStage.setTitle("Filter PopOver Test");
    primaryStage.setScene(new Scene(root, 500, 500));
    primaryStage.show();
}

public static void main(String[] args) {
    launch(args);
}
}