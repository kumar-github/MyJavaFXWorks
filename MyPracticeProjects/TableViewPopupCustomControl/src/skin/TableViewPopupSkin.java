package skin;

import control.TableViewPopup;
import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Skin;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;

public class TableViewPopupSkin<T> implements Skin<TableViewPopup<T>>
{
	private final TableViewPopup<T> tableViewPopupcontrol;
	//private final TableView<T> innerTableView;
	private TableView<T> innerTableView = null;
	private final int TABLE_ROW_HEIGHT = 24;
	private final int TABLE_TITLE_ROW_HEIGHT = 26;

	public TableViewPopupSkin(TableViewPopup<T> tableViewPopupcontrol)
	{
		this.tableViewPopupcontrol = tableViewPopupcontrol;
		//suggestionList = new ListView<>(control.getSuggestions());
		//suggestionList = new TableView<>();
		try
		{
			innerTableView = FXMLLoader.load(this.getClass().getResource("/view/PersonView.fxml"));
			innerTableView.getStyleClass().add(TableViewPopup.DEFAULT_STYLE_CLASS);
			//suggestionList.getStylesheets().add(AutoCompletionBinding.class.getResource("autocompletion.css").toExternalForm());
			innerTableView.getStylesheets().add(this.getClass().getResource("/css/tableviewpopup.css").toExternalForm());
			
			innerTableView.prefHeightProperty().bind(Bindings.min(tableViewPopupcontrol.visibleRowCountProperty(), Bindings.size(innerTableView.getItems())).multiply(TABLE_ROW_HEIGHT).add(TABLE_TITLE_ROW_HEIGHT));
			
			//suggestionList.setCellFactory(TextFieldListCell.forListView(control.getConverter()));
			
			//Allowing the user to control ListView width.
			innerTableView.prefWidthProperty().bind(tableViewPopupcontrol.prefWidthProperty());
			innerTableView.maxWidthProperty().bind(tableViewPopupcontrol.maxWidthProperty());
			innerTableView.minWidthProperty().bind(tableViewPopupcontrol.minWidthProperty());
			registerEventListener();		
		}
		catch(Exception exception)
		{
			System.err.println(exception);
		}

	}

	private void registerEventListener()
	{
		innerTableView.setOnMouseClicked(me -> {
			if (me.getButton() == MouseButton.PRIMARY)
			{
				onSuggestionChoosen(innerTableView.getSelectionModel().getSelectedItem());
			}
		});

		innerTableView.setOnKeyPressed(ke -> {
			switch (ke.getCode())
			{
			case ENTER:
				onSuggestionChoosen(innerTableView.getSelectionModel().getSelectedItem());
				break;
			case ESCAPE:
				if (tableViewPopupcontrol.isHideOnEscape())
				{
					tableViewPopupcontrol.hide();
				}
				break;
			}
		});
	}

	private void onSuggestionChoosen(T suggestion)
	{
		if(suggestion != null)
		{
			Event.fireEvent(tableViewPopupcontrol, new TableViewPopup.SuggestionEvent<>(suggestion));
		}
	}

	@Override
	public TableViewPopup<T> getSkinnable()
	{
		return tableViewPopupcontrol;
	}

	@Override
	public Node getNode()
	{
		return innerTableView;
	}

	@Override
	public void dispose()
	{
	}
}