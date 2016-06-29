package com.tc.app.exchangemonitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class SelectableTableTest extends Application {

  @Override
  public void start(Stage primaryStage) {
    final TableView<TestTask> table = new TableView<TestTask>();
    final Random rng = new Random();
    for (int i = 0; i < 20; i++) {
      table.getItems().add(
          new TestTask(rng.nextInt(5000) + 5000, rng.nextInt(50) + 50));
    }

    final TableColumn<TestTask, String> statusCol = new TableColumn<>("Status");
    statusCol.setCellValueFactory(new PropertyValueFactory<TestTask, String>("message"));
    statusCol.setPrefWidth(75);

    final TableColumn<TestTask, Double> progressCol = new TableColumn<>("Progress");
    progressCol.setCellValueFactory(new PropertyValueFactory<TestTask, Double>("progress"));
    progressCol.setCellFactory(ProgressBarTableCell.<TestTask>forTableColumn());
    
    final TableColumn<TestTask, Integer> resultCol = new TableColumn<>("Result");
    resultCol.setCellValueFactory(new PropertyValueFactory<TestTask, Integer>("value"));
    resultCol.setPrefWidth(75);

    table.getColumns().addAll(Arrays.asList(statusCol, progressCol, resultCol));
    
    table.setSelectionModel(new ConditionalTableRowSelectionModel<TestTask>(table));
    table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    
    final Button cancelButton = new Button("Cancel selected");
    cancelButton.disableProperty().bind(Bindings.isEmpty(table.getSelectionModel().getSelectedIndices()));
    cancelButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        // Snapshot list as items will get deselected when cancelled
        List<TestTask> itemsToCancel = new ArrayList<TestTask>(table.getSelectionModel().getSelectedItems());
        for (TestTask tt : itemsToCancel) {
          tt.cancel();
        }
      }
    });

    final BorderPane root = new BorderPane();
    root.setCenter(table);
    root.setBottom(cancelButton);
    primaryStage.setScene(new Scene(root, 400, 350));
    primaryStage.show();

    final ExecutorService executor = Executors.newCachedThreadPool(new ThreadFactory() {
      @Override
      public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
      }
    });
    
    
    for (TestTask task : table.getItems()) {
      executor.execute(task);
    }
  }

  public static void main(String[] args) {
    launch(args);
  }

  static class TestTask extends Task<Integer> implements Selectable {

    private final int waitTime; // milliseconds
    private final int pauseTime; // milliseconds
    
    private final BooleanProperty selectable ;

    public static final int NUM_ITERATIONS = 100;

    TestTask(int waitTime, int pauseTime) {
      this.waitTime = waitTime;
      this.pauseTime = pauseTime;
      this.selectable = new SimpleBooleanProperty(this, "selectable");
      selectable.bind(stateProperty().isEqualTo(Worker.State.RUNNING));
    }
    
    @Override
    public BooleanProperty selectableProperty() {
      return selectable ;
    }
    @Override
    public final boolean isSelectable() {
      return selectable.get();
    }
    @Override
    public final void setSelectable(boolean selectable) {
      this.selectable.set(selectable);
    }

    @Override
    protected Integer call() throws Exception {
      final Random rng = new Random();
      this.updateProgress(ProgressIndicator.INDETERMINATE_PROGRESS, 1);
      this.updateMessage("Waiting...");
      try {
        Thread.sleep(waitTime);
        this.updateMessage("Running...");
        for (int i = 0; i < NUM_ITERATIONS; i++) {
          updateProgress((1.0 * i) / NUM_ITERATIONS, 1);
          Thread.sleep(pauseTime);
        }
      } catch (InterruptedException e) {
        if (isCancelled()) {
          this.updateMessage("Cancelled");
          this.updateProgress(1, 1);
          return -1 ;
        }
      }
      this.updateMessage("Done");
      this.updateProgress(1, 1);
      return rng.nextInt(10000);
    }

  }
}
