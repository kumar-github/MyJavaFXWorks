package application;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TreeBind extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

    @Override
    public void start(Stage primaryStage) {

        TreeItem<Plug> treeItemRoot = new TreeItem<>(new Plug("root","a"));
        TreeView<Plug> treeView = new TreeView<>(treeItemRoot);
        for (int i=0;i<10;i++)
            treeItemRoot.getChildren().add(new TreeItem<>(new Plug("name"+String.valueOf(i),"")));
        treeView.setMinWidth(150);

        final TextArea ta1 = new TextArea();

        treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if (oldValue!=null){
                    ((TreeItem<Plug>)oldValue).getValue().s1.unbindBidirectional(ta1.textProperty());
                    ta1.clear();
                }
                if (newValue!=null){
                    ta1.setText(((TreeItem<Plug>)newValue).getValue().s1.getValue());
                    ((TreeItem<Plug>)newValue).getValue().s1.bindBidirectional(ta1.textProperty());
                }
            }
        });

        HBox root = new HBox();
        root.getChildren().addAll(treeView,ta1);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class Plug{
        public final StringProperty name, s1;

        Plug(String name, String s1){
            this.name = new SimpleStringProperty(name);
            this.s1 = new SimpleStringProperty(s1);
        }
        @Override
        public String toString(){
            return name.getValue();
        }
    }
}