package application;
import java.time.LocalDate;
import java.time.DayOfWeek;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

public class FxDatePickerExample1 extends Application
{
	// Create the TextArea
	private final TextArea textArea = new TextArea();

	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage stage)
	{
		// Date Pattern
		String pattern = "MM/dd/yyyy";

		// Create the DatePicker
		final DatePicker datePicker = new DatePicker();
		// Make the DatePicker non-editable
		datePicker.setEditable(false);

		// Print the new date in the TextArea
		datePicker.setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
		     public void handle(ActionEvent event)
		     {
		         LocalDate date = datePicker.getValue();
		         writeMessage("Selected date: " + date);
		     }
		});

		// Create the DateConverter
		FxDatePickerConverter converter = new FxDatePickerConverter(pattern);
		// Add the Converter to the DatePicker
		datePicker.setConverter(converter);
		// Set the Date in the Prompt
		datePicker.setPromptText(pattern.toLowerCase());

		// Create a day cell factory
		Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>()
		{
			public DateCell call(final DatePicker datePicker)
			{
				return new DateCell()
				{
					@Override
					public void updateItem(LocalDate item, boolean empty)
					{
						// Must call super
						super.updateItem(item, empty);

						// Show Weekends in blue color
						DayOfWeek day = DayOfWeek.from(item);
						if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
						{
							this.setTextFill(Color.BLUE);
						}
					}
				};
			}
		};

		// Set the day cell factory to the DatePicker
		datePicker.setDayCellFactory(dayCellFactory);

		// Create the Label
		Label selection = new Label("Select your Date:");
		// Create the HBox for the DatePicker
		HBox pickerBox = new HBox(selection, datePicker);

		// Set the preferred number of text rows
		textArea.setPrefRowCount(15);
		// Set the preferred number of text columns
		textArea.setPrefColumnCount(25);

		// Create the VBox
		VBox root = new VBox();
		// Add the TreeView to the VBox
		root.getChildren().addAll(pickerBox,textArea);
		// Set the Style of the VBox
		root.setStyle("-fx-padding: 10;" +
			"-fx-border-style: solid inside;" +
			"-fx-border-width: 2;" +
			"-fx-border-insets: 5;" +
			"-fx-border-radius: 5;" +
			"-fx-border-color: blue;");

		// Create the Scene
		Scene scene = new Scene(root);
		// Add the Scene to the Stage
		stage.setScene(scene);
		// Set the Title
		stage.setTitle("A DatePicker Control Example");
		// Display the Stage
		stage.show();
		// Set the Size of the Window to the Stage
		stage.sizeToScene();
	}

	// Method for Logging
	private void writeMessage(String msg)
	{
		this.textArea.appendText(msg + "\n");
	}
}
