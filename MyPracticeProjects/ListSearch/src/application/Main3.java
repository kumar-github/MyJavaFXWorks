package application;

    import javafx.application.Application;
    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.event.Event;
    import javafx.event.EventHandler;
    import javafx.scene.Scene;
    import javafx.scene.control.ListView;
    import javafx.scene.control.SelectionMode;
    import javafx.scene.layout.Pane;
    import javafx.stage.Stage;


    public class Main3 extends Application {
        @Override
        public void start(Stage primaryStage) {
            try {
                Pane  root = new Pane();
                Scene scene = new Scene(root,600,600);

                ListView<String> listView = new ListView<String>();

                ObservableList<String> list = FXCollections.observableArrayList();





                listView.setItems(list);

                list.add("item1");
                list.add("item2");
                list.add("item3");

                listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


                listView.setOnMouseClicked(new EventHandler<Event>() {

                    @Override
                    public void handle(Event event) {
                        ObservableList<String> selectedItems =  listView.getSelectionModel().getSelectedItems();

                        for(String s : selectedItems){
                            System.out.println("selected item " + s);
                        }

                    }

                });


                list.add("item4");  

                root.getChildren().add(listView);

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