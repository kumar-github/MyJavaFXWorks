package com.tc.app.exchangemonitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewFocusModel;
import javafx.scene.control.TableView.TableViewSelectionModel;

/**
 * An implementation of TableViewSelectionModel that allows rows of the table
 * to declare themselves ineligible for selection via a selectableProperty,
 * as implemented in the Selectable interface. This implementation is for
 * row selection only, and ignores the cellSelectionEnabled property.
 *
 * @param <S> The type of data displayed in each row of the table.
 */
public class ConditionalTableRowSelectionModel<S extends Selectable> extends TableViewSelectionModel<S> {
  
  @SuppressWarnings("rawtypes")
  private final ObservableList<TablePosition> selectedPositions ;
  private final ObservableList<Integer> selectedRowIndices ;
  private final ObservableList<S> selectedRows ;

  public ConditionalTableRowSelectionModel(TableView<S> tableView) {
    super(tableView);
    selectedPositions = FXCollections.observableArrayList();
    selectedRowIndices = FXCollections.observableArrayList();
    selectedRows = FXCollections.observableArrayList() ;
    
    // Listeners:
    
    // listener for removing items from the selection if they become unselectable:
    final ChangeListener<Boolean> unselectableChangeListener = new ChangeListener<Boolean>() {
      @Override
      public void changed(ObservableValue<? extends Boolean> observable,
          Boolean oldValue, Boolean newValue) {
        if (! newValue) {
          Object s = ((Property<? extends Boolean>)observable).getBean();
          final int index = getTableModel().indexOf(s);
          clearSelection(index);
        }
      }
    };
    
    for (S s : tableView.getItems()) {
      s.selectableProperty().addListener(unselectableChangeListener);
    }
    
    // listener for changes to the contents of the items list:
    final ListChangeListener<S> listChangeListener = new ListChangeListener<S>() {
      @Override
      public void onChanged(
          javafx.collections.ListChangeListener.Change<? extends S> change) {
        while (change.next()) {
          if (change.wasAdded()) {
            for (S s : change.getAddedSubList()) {
              s.selectableProperty().addListener(unselectableChangeListener);
            }
          }
          if (change.wasRemoved()) {
            for (S s: change.getRemoved()) {
              s.selectableProperty().removeListener(unselectableChangeListener);
            }
          }
        }
      }
    };
    tableView.getItems().addListener(listChangeListener);
    
    // listener for changes to the items list itself:
    tableView.itemsProperty().addListener(new ChangeListener<ObservableList<S>>() {
      @Override
      public void changed(
          ObservableValue<? extends ObservableList<S>> observable,
          ObservableList<S> oldValue, ObservableList<S> newValue) {
        if (oldValue!=null) {
          oldValue.removeListener(listChangeListener);
          for (S s : oldValue) {
            s.selectableProperty().removeListener(unselectableChangeListener);
          }
        }
        if (newValue!=null) {
          newValue.addListener(listChangeListener);
          for (S s : newValue) {
            s.selectableProperty().addListener(unselectableChangeListener);
          }
        }
      }
    });
    
  }

  /**
   * Selects the entire row, ignoring the column parameter (even if cellSelection is enabled).
   */
  @Override
  public void clearAndSelect(int row, TableColumn<S, ?> column) {
    clearAndSelect(row);
  }

  /**
   * Clears the selection for the entire row, ignoring the column parameter
   */
  @Override
  public void clearSelection(int row, TableColumn<S, ?> column) {
    clearSelection(row);
  }

  @SuppressWarnings("rawtypes")
  @Override
  public ObservableList<TablePosition> getSelectedCells() {
    return selectedPositions ;
  }

  @Override
  public boolean isSelected(int row, TableColumn<S, ?> column) {
    return selectedRowIndices.contains(row);
  }

  /**
   * Adds the entire row to the selection, ignoring the column parameter
   */
  @Override
  public void select(int row, TableColumn<S, ?> column) {
    select(row);
  }

  /**
   * Adds the entire previous row to the selection.
   */
  @Override
  public void selectAboveCell() {
    selectPrevious();
  }

  /**
   * Adds the entire next row to the selection.
   */
  @Override
  public void selectBelowCell() {
    selectNext();
  }

  /**
   * Since this implementation does not support cell selection, this method does nothing.
   */
  @Override
  public void selectLeftCell() {
  }

  /**
   * Since this implementation does not support cell selection, this method does nothing.
   */  
  @Override
  public void selectRightCell() {    
  }

  /**
   * Returns an unmodifiable list of the selected row indices.
   */
  @Override
  public ObservableList<Integer> getSelectedIndices() {
    return FXCollections.unmodifiableObservableList(selectedRowIndices) ;
  }

  /**
   * Returns an unmodifiable list of the selected rows.
   */
  @Override
  public ObservableList<S> getSelectedItems() {
    return FXCollections.unmodifiableObservableList(selectedRows);
  }

  /**
   * Selects all rows corresponding to items with the selectable flag set to true.
   */
  @Override
  public void selectAll() {
    List<Integer> rowIndicesToSelect = new ArrayList<Integer>();
    List<S> rowsToSelect = new ArrayList<S>();
    List<TablePosition<S,?>> positionsToSelect = new ArrayList<TablePosition<S,?>>();
    int index = 0 ;
    int firstSelectedIndex = -1 ;
    S firstSelectedItem = null ;
    for (S s : getTableModel()) {
      if (s.isSelectable()) {
        if (firstSelectedItem == null) {
          firstSelectedItem = s ;
          firstSelectedIndex = index ;
        }
        if (! selectedRowIndices.contains(index)) {
          rowIndicesToSelect.add(index);
          rowsToSelect.add(s);
          positionsToSelect.addAll(getPositionsForRow(index));
        }
      }
      index++;
    }
    selectedRowIndices.setAll(rowIndicesToSelect);
    selectedRows.setAll(rowsToSelect);
    selectedPositions.setAll(positionsToSelect);
    if (firstSelectedItem != null) {
      setSelectedItem(firstSelectedItem);
      setSelectedIndex(firstSelectedIndex);
    }
    getTableView().getFocusModel().focus(0);
  }

  /**
   * Selects the first row corresponding to an item in the table which has the selectable property set to true.
   * If clearSelection() is not called first, this method will have the result of selecting the first selectable index, 
   * whilst retaining the selection of any other currently selected indices. If the first row corresponding to 
   * a selectable item is already selected, or if there are no selectable items, this method will do nothing.
   */
  @Override
  public void selectFirst() {
    int index = 0 ;
    for (S s : getTableModel()) {
      if (s.isSelectable()) {
        select(index);
        break ;
      }
    }
  }

  /**
   * This method allows for one or more selections to be set at the same time. 
   * It will ignore any value that is not within the valid range (i.e. greater than or equal to zero, 
   * and less than the total number of items in the underlying data model), or any value corresponding to 
   * an item that is not selectable. Any duplication of indices will be ignored.
   * 
   * If there is already one or more indices selected in this model, calling this method will not clear these selections - 
   * to do so it is necessary to first call clearSelection.
   * 
   * The last valid value given will become the selected index / selected item.
   */
  @Override
  public void selectIndices(int index, int... indices) {
    List<Integer> rowIndicesToSelect = new ArrayList<Integer>();
    List<S> rowsToSelect = new ArrayList<S>();
    List<TablePosition<S,?>> positionsToSelect = new ArrayList<TablePosition<S,?>>();
    List<Integer> suppliedIndices = new ArrayList<Integer>();
    suppliedIndices.add(index);
    int lastAddedIndex = -1 ;
    S lastAddedRow = null ;
    for (int i : indices) suppliedIndices.add(i);
    for (int i : suppliedIndices) {
      if (i>=0 && i < getTableModel().size()) {
        final S s = getTableModel().get(i);
        if (s.isSelectable()) {
          lastAddedIndex = i;
          lastAddedRow = s;
          if (! selectedRows.contains(s)) {
            rowIndicesToSelect.add(i);
            rowsToSelect.add(s);
            positionsToSelect.addAll(getPositionsForRow(i));
          }
        }
      }
    }
    selectedRows.addAll(rowsToSelect);
    selectedRowIndices.addAll(rowIndicesToSelect);
    selectedPositions.addAll(positionsToSelect);
    if (lastAddedRow != null) {
      setSelectedIndex(lastAddedIndex);
      setSelectedItem(lastAddedRow);
    }
    getTableView().getFocusModel().focus(suppliedIndices.get(suppliedIndices.size()-1));
  }

  /** Selected the last row corresponding to an item that has its selectable property set to true.
   * If clearSelection() is not called first, this method will have the result of selecting the last selectable index, 
   * whilst retaining the selection of any other currently selected indices. If the last row corresponding to 
   * a selectable item is already selected, or if there are no selectable items, this method will do nothing.   * 
   */
  @Override
  public void selectLast() {
    final List<S> tableModel = getTableModel();
    int index = tableModel.size()-1;
    for (ListIterator<S> iterator = tableModel.listIterator(tableModel.size()); iterator.hasPrevious(); ) {
      S s = iterator.previous();
      if (s.isSelectable()) {
        select(index);
        break ;
      }
      index-- ;
    }
    getTableView().getFocusModel().focus(getTableModel().size()-1);
  }

  /**
   * If the row at the specified index corresponds to a selectable item, this row is made the selected item.
   * Otherwise, this method does nothing.
   */
  @Override
  public void clearAndSelect(int index) {
    S s = getTableModel().get(index);
    if (s.isSelectable()) {
      selectedRows.setAll(Arrays.asList(s));
      selectedRowIndices.setAll(index);
      setSelectedItem(s);
      setSelectedIndex(index);
      selectedPositions.setAll(getPositionsForRow(index));
    }
    getTableView().getFocusModel().focus(index);
  }

  /**
   * Clears the selection
   */
  @Override
  public void clearSelection() {
    selectedRows.clear();
    selectedRowIndices.clear();
    selectedPositions.clear();
    setSelectedIndex(-1);
    setSelectedItem(null);
  }

  @Override
  public void clearSelection(int index) {
    selectedRowIndices.remove(new Integer(index));
    selectedRows.remove(getTableModel().get(index));
    selectedPositions.removeAll(getPositionsForRow(index));
    if (index == getSelectedIndex()) {
      if (selectedRowIndices.size()==0) {
        setSelectedIndex(-1);
        setSelectedItem(null);
      } else {
        setSelectedIndex(selectedRowIndices.get(selectedRowIndices.size()-1));
        setSelectedItem(selectedRows.get(selectedRows.size()-1));
      }
    }
  }

  @Override
  public boolean isEmpty() {
    return selectedRowIndices.isEmpty();
  }

  @Override
  public boolean isSelected(int index) {
    return selectedRowIndices.contains(index);
  }

  @Override
  public void select(int index) {
    if (getTableModel().get(index).isSelectable()) {
      setSelectedIndex(index);
      setSelectedItem(getTableModel().get(index));
      if (! selectedRowIndices.contains(index)) {
        selectedRowIndices.add(index);
        selectedRows.add(getTableModel().get(index));
        selectedPositions.addAll(getPositionsForRow(index));
      }
    }
    getTableView().getFocusModel().focus(index);
  }

  @Override
  public void select(S s) {
    final int index = getTableModel().indexOf(s);
    if (s.isSelectable() ) {
      setSelectedIndex(index);
      setSelectedItem(s);
      if (! selectedRows.contains(s)) {
        selectedRows.add(s);
        selectedRowIndices.add(index);
        selectedPositions.addAll(getPositionsForRow(index));
      }
    }
    getTableView().getFocusModel().focus(index);
  }

  /**
   * This method will attempt to select the first index after the current focused index corresponding to an item that is selectable. 
   * If clearSelection is not called first, this method will have the result of selecting the next selectable index, 
   * whilst retaining the selection of any other currently selected indices.
   * 
   * Calling this method will only succeed if:
   * <ul>
   * <li>There is currently a lead/focused index.</li>
   * <li>The lead/focus index is not the last index in the control.</li>
   * <li>The next index is not already selected.</li>
   * </ul>
   * If any of these conditions is false, no selection event will take place.
   */
  @Override
  public void selectNext() {
    final TableViewFocusModel<S> focusModel = getTableView().getFocusModel();
    final int focusedIndex = focusModel.getFocusedIndex();
    if (focusedIndex < 0) {
      return ;
    }
    for (int index = focusedIndex+1; index < getTableModel().size(); index++) {
      if (getTableModel().get(index).isSelectable()) {
        select(index);
        break ;
      }
    }
    if (focusedIndex < getTableModel().size()-1) {
      focusModel.focus(focusedIndex + 1);
    }
  }

  /**
   * This method will attempt to select the last index before the current focused index corresponding to an item that is selectable. 
   * If clearSelection is not called first, this method will have the result of selecting the previous selectable index, 
   * whilst retaining the selection of any other currently selected indices.
   * 
   * Calling this method will only succeed if:
   * <ul>
   * <li>There is currently a lead/focused index.</li>
   * <li>The lead/focus index is not the first index in the control.</li>
   * <li>The previous index is not already selected.</li>
   * If any of these conditions is false, no selection event will take place.
   */
  @Override
  public void selectPrevious() {
    final TableViewFocusModel<S> focusModel = getTableView().getFocusModel();
    final int focusedIndex = focusModel.getFocusedIndex();
    if (focusedIndex < 0) {
      return ;
    }
    for (int index = focusedIndex-1; index >= 0; index--) {
      if (getTableModel().get(index).isSelectable()) {
        select(index);
        break ;
      }
    }
    if (focusedIndex > 0) {
      focusModel.focus(focusedIndex-1);
    }
  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  private List<TablePosition<S,?>> getPositionsForRow(int index) {
    List<TablePosition<S,?>> positions = new ArrayList<TablePosition<S,?>>();
    for (TableColumn<S,?> col : getTableView().getColumns()) {
      positions.add(new TablePosition(getTableView(), index, col));
    }
    return positions ;
  }

}
