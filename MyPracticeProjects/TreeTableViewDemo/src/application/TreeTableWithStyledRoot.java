package application;

import java.io.File;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Date;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableRow;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
 
 
public class TreeTableWithStyledRoot extends Application
{
    private final static NumberFormat NumberFormater = NumberFormat.getIntegerInstance();
    private final static DateFormat DateFormater = DateFormat.getDateTimeInstance();
   
    @Override
    public void start(Stage primaryStage) {
               
        TreeTableView<File> treeTableView = new TreeTableView<>();
        buildFileBrowserTreeTableView(treeTableView);
        
        final PseudoClass firstRowClass = PseudoClass.getPseudoClass("first-row");

        treeTableView.setRowFactory(treeTable -> {
            TreeTableRow<File> row = new TreeTableRow<File>(); 
            row.treeItemProperty().addListener((ov, oldTreeItem, newTreeItem) -> 
                row.pseudoClassStateChanged(firstRowClass, newTreeItem == treeTable.getRoot()));
            return row ;
        });
              
        BorderPane root = new BorderPane(treeTableView);
        root.setPadding(new Insets(10));
       
        Scene scene = new Scene(root, 640, 480);
        scene.getStylesheets().add(getClass().getResource("tree-table-view.css").toExternalForm());
       
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
 
    private void buildFileBrowserTreeTableView(TreeTableView<File> treeTableView) {
        TreeItem<File> root = createNode(new File(System.getProperty("user.home")));
        root.setExpanded(true);
     
        treeTableView.setRoot(root);
     
        // --- name column
        TreeTableColumn<File, String> nameColumn = new TreeTableColumn<File, String>("Name");
        nameColumn.setPrefWidth(300);
        nameColumn.setCellValueFactory(
                new Callback<TreeTableColumn.CellDataFeatures<File, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<File, String> p) {
                File f = p.getValue().getValue();
                String text = f.getParentFile() == null ? "/" : f.getName();
                return new ReadOnlyObjectWrapper<String>(text);
            }
        });
     
        // --- size column
        TreeTableColumn<File, File> sizeColumn = new TreeTableColumn<File, File>("Size");
        sizeColumn.setPrefWidth(100);
        sizeColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<File, File>, ObservableValue<File>>() {
            @Override public ObservableValue<File> call(TreeTableColumn.CellDataFeatures<File, File> p) {
                return new ReadOnlyObjectWrapper<File>(p.getValue().getValue());
            }
        });
        sizeColumn.setCellFactory(new Callback<TreeTableColumn<File, File>, TreeTableCell<File, File>>() {
            @Override public TreeTableCell<File, File> call(final TreeTableColumn<File, File> p) {
                return new TreeTableCell<File, File>() {
                    @Override protected void updateItem(File item, boolean empty) {
                        super.updateItem(item, empty);
     
                        TreeTableView<File> treeTable = p.getTreeTableView();
    
                        TreeItem<File> treeItem = treeTable.getTreeItem(getIndex());
                        if (item == null || empty || treeItem == null ||
                                treeItem.getValue() == null || treeItem.getValue().isDirectory()) {
                            setText(null);
                        } else {
                            setText(NumberFormater.format(item.length()) + " KB");
                        }
                    }
                };
            }
        });
        sizeColumn.setComparator(new Comparator<File>() {
            @Override public int compare(File f1, File f2) {
                long s1 = f1.isDirectory() ? 0 : f1.length();
                long s2 = f2.isDirectory() ? 0 : f2.length();
                long result = s1 - s2;
                if (result < 0) {
                    return -1;
                } else if (result == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
     
        // --- modified column
        TreeTableColumn<File, Date> lastModifiedColumn = new TreeTableColumn<File, Date>("Last Modified");
        lastModifiedColumn.setPrefWidth(130);
        lastModifiedColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<File, Date>, ObservableValue<Date>>() {
            @Override public ObservableValue<Date> call(TreeTableColumn.CellDataFeatures<File, Date> p) {
                return new ReadOnlyObjectWrapper<Date>(new Date(p.getValue().getValue().lastModified()));
            }
        });
        lastModifiedColumn.setCellFactory(new Callback<TreeTableColumn<File, Date>, TreeTableCell<File, Date>>() {
            @Override public TreeTableCell<File, Date> call(TreeTableColumn<File, Date> p) {
                return new TreeTableCell<File, Date>() {
                    @Override protected void updateItem(Date item, boolean empty) {
                        super.updateItem(item, empty);
     
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(DateFormater.format(item));
                        }
                    }
                };
            }
        });
     
        treeTableView.getColumns().setAll(nameColumn, sizeColumn, lastModifiedColumn);
    }
 
    private TreeItem<File> createNode(final File f) {
        final TreeItem<File> node = new TreeItem<File>(f) {
            private boolean isLeaf;
            private boolean isFirstTimeChildren = true;
            private boolean isFirstTimeLeaf = true;
     
            @Override public ObservableList<TreeItem<File>> getChildren() {
                if (isFirstTimeChildren) {
                    isFirstTimeChildren = false;
                    super.getChildren().setAll(buildChildren(this));
                }
                return super.getChildren();
            }
     
            @Override public boolean isLeaf() {
                if (isFirstTimeLeaf) {
                    isFirstTimeLeaf = false;
                    File f = (File) getValue();
                    isLeaf = f.isFile();
                }
     
                return isLeaf;
            }
        };
       
        return node;
    }
 
    private ObservableList<TreeItem<File>> buildChildren(TreeItem<File> TreeItem) {
        File f = (File) TreeItem.getValue();
        if (f != null && f.isDirectory()) {
            File[] files = f.listFiles();
            if (files != null) {
                ObservableList<TreeItem<File>> children = FXCollections.observableArrayList();
     
                for (File childFile : files) {
                    children.add(createNode(childFile));
                }
     
                return children;
            }
        }
     
        return FXCollections.emptyObservableList();
    }   
   
    public static void main(String[] args) {
        launch(args);
    }
   
}