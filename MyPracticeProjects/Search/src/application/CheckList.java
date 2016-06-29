package application;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class CheckList extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        ObservableList<Task> tasks = FXCollections.observableArrayList(Arrays.stream(names).map(Task::new).collect(Collectors.toList()));

        ListView<String> reactionLog = new ListView<>();
        tasks.forEach(task -> task.selectedProperty().addListener((observable, wasSelected, isSelected) -> {
            if (isSelected)
            {
            	System.err.println(task.getName());
                reactionLog.getItems().add(reactionStrings.get(task.getName()));
                reactionLog.scrollTo(reactionLog.getItems().size() - 1);
            }
            else
            {
            	System.out.println(task.getName() + " unchecked");
            }
        }));

        ListView<Task> checklist = new ListView<>(tasks);
        checklist.setCellFactory(CheckBoxListCell.forListView(Task::selectedProperty, new StringConverter<Task>() {
            @Override
            public String toString(Task object) {
                return object.getName();
            }

            @Override
            public Task fromString(String string) {
                return null;
            }
        }));

        HBox layout = new HBox(10, checklist, reactionLog);
        layout.setPrefSize(350, 150);
        layout.setPadding(new Insets(10));
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

    public static class Task {
        private ReadOnlyStringWrapper name = new ReadOnlyStringWrapper();
        private BooleanProperty selected = new SimpleBooleanProperty(false);

        public Task(String name) {
            this.name.set(name);
        }

        public String getName() {
            return name.get();
        }
        public ReadOnlyStringProperty nameProperty() {
            return name.getReadOnlyProperty();
        }

        public BooleanProperty selectedProperty() {
            return selected;
        }
        public boolean isSelected() {
            return selected.get();
        }
        public void setSelected(boolean selected) {
            this.selected.set(selected);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static final String[] names = {
            "Walk the dog",
            "Skin the cat",
            "Feed the pig"
    };

    private static final Map<String, String> reactionStrings = new HashMap<>();
    static {
        reactionStrings.put("Walk the dog", "The dog thanks you");
        reactionStrings.put("Skin the cat", "The cat hates you");
        reactionStrings.put("Feed the pig", "The pig wants more");
    }
}