package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TreeTableViewCreateExpandAndEditMain extends Application
{
  @Override
  public void start(final Stage primaryStage)
  {
    TreeItem<Item> root = new TreeItem<>( new Item( 1, "Root" ) );

    TreeItem<Item> item1 = new TreeItem<>( new Item( 2, "Item1" ) );
    TreeItem<Item> item11 = new TreeItem<>( new Item( 3, "Item11" ) );
    TreeItem<Item> item2 = new TreeItem<>( new Item( 4, "Item2" ) );
    TreeItem<Item> item3 = new TreeItem<>( new Item( 5, "Item3" ) );
    TreeItem<Item> item31 = new TreeItem<>( new Item( 6, "Item31" ) );

    root.getChildren().add( item1 );
    item1.getChildren().add( item11 );
    root.getChildren().add( item2 );
    root.getChildren().add( item3 );
    item3.getChildren().add( item31 );

    TreeTableColumn<Item, Integer> columnId = new TreeTableColumn<>( "ColumnId" );
    TreeTableColumn<Item, String> columnName = new TreeTableColumn<>( "ColumnName" );

    columnId.setCellValueFactory( new TreeItemPropertyValueFactory<Item, Integer>( "id" ) );
    columnName.setCellValueFactory( new TreeItemPropertyValueFactory<Item, String>( "name" ) );

    columnName.setCellFactory( TextFieldTreeTableCell.forTreeTableColumn() );

    columnName.setOnEditCommit(new EventHandler<CellEditEvent<Item, String>>()
    {
      @Override
      public void handle( final CellEditEvent<Item, String> event )
      {
        final Item item = event.getRowValue().getValue();
        System.out.println( "Change Item " + item + " from " + event.getOldValue() + " to new value "
            + event.getNewValue() );
        item.setName( event.getNewValue() );
      }
    } );

    final TreeTableView<Item> treeTableView = new TreeTableView<>( root );
    treeTableView.getColumns().add( columnName );
    treeTableView.getColumns().add( columnId );
    treeTableView.setShowRoot( false );
    treeTableView.setColumnResizePolicy(TreeTableView.CONSTRAINED_RESIZE_POLICY );

    treeTableView.setEditable( true );

    BorderPane layout = new BorderPane();

    Button button = new Button( "DoAction!" );
    button.setOnAction(new EventHandler<ActionEvent>()
    {
      @Override
      public void handle(final ActionEvent event)
      {
        /*
         * This Method should create a new node at the currently selected node, expand the selected node and start editing the new created node.
         */
        final TreeItem<Item> selectedItem = treeTableView.getSelectionModel().getSelectedItem();

        final TreeItem<Item> newItem =
            new TreeItem<>( new Item( 100, "newItemFor" + selectedItem.getValue().getName() ) );
        selectedItem.getChildren().add( newItem );

        selectedItem.expandedProperty().set( true );
        final int rowIndex = treeTableView.getRow( newItem );

        treeTableView.edit( rowIndex, columnName );//Does 99% not start or get canceled, why?
      }

    } );

    layout.setCenter( treeTableView );
    layout.setBottom( button );

    Scene scene = new Scene( layout, 400, 400 );
    primaryStage.setScene( scene );
    primaryStage.show();
  }

  public static void main( final String[] args )
  {
    launch( args );
  }
}