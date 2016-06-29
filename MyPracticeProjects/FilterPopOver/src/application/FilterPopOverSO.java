package application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.layout.BorderPane;
import org.controlsfx.control.CheckListView;
import org.controlsfx.control.PopOver;

public class FilterPopOverSO<T> extends PopOver {

private ObservableList<T> sourceData;

private BorderPane popoverNode;

private CheckListView<T> filterList;

/**
 * ============================================================================================
 * Constructor
 * ============================================================================================
 */

public FilterPopOverSO() {

    super();

    sourceData = FXCollections.observableArrayList();

    popoverNode = new BorderPane();

    filterList = new CheckListView<>();
    filterList.setPrefWidth(600D);
    filterList.setMaxWidth(600D);

    popoverNode.setCenter(filterList);
    setContentNode(popoverNode);

    //filterList.getItems().addListener(sourceItemsListener);
    //filterList.getCheckModel().getCheckedItems().addListener(checkedItemsListener);
    filterList.getCheckModel().checkAll();
}

/**
 * ============================================================================================
 * Listeners
 * ============================================================================================
 */

ListChangeListener<T> sourceItemsListener = (change) -> {

    System.out.println("Start of Change Listener 'sourceItemsListener'");

    while(change.next()) {

        System.out.println("     Added:      " + change.wasAdded());
        System.out.println("     Permutated: " + change.wasPermutated());
        System.out.println("     Removed:    " + change.wasRemoved());
        System.out.println("     Updated:    " + change.wasUpdated());

    }

    System.out.println("End of  of Change Listener 'sourceItemsListener'");
};

ListChangeListener<T> checkedItemsListener = (change) -> {

    System.out.println("Start of Change Listener 'checkedItemsListener'");

    while(change.next()) {

        System.out.println("     Added:      " + change.wasAdded());
        System.out.println("     Permutated: " + change.wasPermutated());
        System.out.println("     Removed:    " + change.wasRemoved());
        System.out.println("     Updated:    " + change.wasUpdated());

    }

    System.out.println("End of  of Change Listener 'checkedItemsListener'");
};

public void setSourceData(ObservableList<T> sourceData) {
    filterList.setItems(sourceData);
}
}