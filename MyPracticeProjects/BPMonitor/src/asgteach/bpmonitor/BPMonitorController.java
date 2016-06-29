// BPMonitorController.java - BPMonitor controller
// Copyright 2015, Anderson Software Group, Inc.
// Gail and Paul Anderson

package asgteach.bpmonitor;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class BPMonitorController implements Initializable {

    // ObservableList holds all BPData objects
    private final ObservableList<BPData> backingList = FXCollections.observableArrayList();
    
    // Define sortedBackingList based on source backingList and
    // sort criteria the date property.  Note that sortedBackingList
    // wraps backingList and lets you access the data sorted
    private final SortedList<BPData> sortedBackingList = new SortedList<>(backingList, (data1, data2) -> data1.getDate().compareTo(data2.getDate()));

    // Define filteredList to filter sortedBackingList for TableView control.
    // Initially the predicate is false and the list is empty.
    // When the startDate date picker control is set, the predicate will
    // be updated and the items selected
    private final FilteredList<BPData> filteredList = new FilteredList<>(sortedBackingList, p -> false);

    // Date and Time formatters
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yy hh:mma");
    private final DateTimeFormatter rangeFormatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
    
    // Line Chart Series
    private final XYChart.Series<String, Number> systolicSeries =
            new XYChart.Series<>();
    private final XYChart.Series<String, Number> diastolicSeries =
            new XYChart.Series<>();
    private final XYChart.Series<String, Number> pulseSeries =
            new XYChart.Series<>();

    // Configure the Line Chart Series observable lists 
    private final ObservableList<Data<String, Number>> sysData =
            FXCollections.observableArrayList();

    private final ObservableList<Data<String, Number>> diasData =
            FXCollections.observableArrayList();

    private final ObservableList<Data<String, Number>> pulseData =
            FXCollections.observableArrayList();                    

    // Make the chart lists sorted.
    // First, use our custom comparator to convert 
    // LocalDateTime to and from a String
    private final XDateComparator xDateComparator = new XDateComparator();
    // Then define each sorted list to use this customized comparator
    private final SortedList<Data<String, Number>> sortedsysData =
            new SortedList<>(sysData, (data1, data2) ->
                xDateComparator.compare(data1.getXValue(), data2.getXValue()));
    private final SortedList<Data<String, Number>> sorteddiasData =
            new SortedList<>(diasData, (data1, data2) ->
                xDateComparator.compare(data1.getXValue(), data2.getXValue()));
    private final SortedList<Data<String, Number>> sortedpulseData =
            new SortedList<>(pulseData, (data1, data2) ->
                xDateComparator.compare(data1.getXValue(), data2.getXValue()));
    
    // Custom comparator that compares String representations of LocalDateTime
    final class XDateComparator implements Comparator<String> {
        @Override
        public int compare(String t1, String t2) {
            LocalDateTime d1 = LocalDateTime.parse(t1, formatter);
            LocalDateTime d2 = LocalDateTime.parse(t2, formatter);
            return d1.compareTo(d2);
        }
    }    

    // Bar Chart Series
    private final XYChart.Series<String, Number> systolicStats =
            new XYChart.Series<>();
    private final XYChart.Series<String, Number> diastolicStats =
            new XYChart.Series<>();
    private final XYChart.Series<String, Number> pulseStats =
            new XYChart.Series<>();

    // Keep track of UI state
    private final IntegerProperty daysWindow = new SimpleIntegerProperty(7);

    // Keep track of when it's OK to Add based on state of UI.
    // The okToAdd property also affects the disable property of the
    // Date and Time controls (slightly differently than the Add button).
    // The Update and Delete buttons are enabled/disabled at specific
    // methods and don't really need separate booleans for binding
    private final BooleanProperty okToAdd = new SimpleBooleanProperty(true);

    // FXML controls for date and time
    @FXML
    private DatePicker datePicker;
    @FXML
    private Spinner<Integer> hourSpinner;
    @FXML
    private Spinner<Integer> minuteSpinner;
    @FXML
    private SpinnerValueFactory.IntegerSpinnerValueFactory hourFactory;
    @FXML
    private SpinnerValueFactory.IntegerSpinnerValueFactory minuteFactory;
    @FXML
    private ComboBox<String> ampmControl;

    // FXML input fields for systolic, diastolic, and pulse values
    @FXML
    private TextField systolicField;
    @FXML
    private TextField diastolicField;
    @FXML
    private TextField pulseField;

    // FXML buttons to Add, Update, Delete BP readings
    @FXML
    private Button addBPButton;
    @FXML
    private Button updateBPButton;
    @FXML
    private Button deleteBPButton;

    // FXML display messages to user
    @FXML
    private Label mesg;

    // Display data of the selected (MOUSE_ENTERED event) node 
    // in LineChart or BarChart.
    // This label control is moved around by manipulating its
    // translateX and translateY properties
    @FXML
    private Label nodeDisplay;

    // FXML controls to manipulate Date range of the displayed data
    @FXML
    private Button firstButton;
    @FXML
    private Button prevButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button lastButton;
    @FXML
    private Label toLabel;

    // This startDate date picker control determines which DBData items
    // are included in the filteredList
    @FXML
    private DatePicker startDate;

    // FXML chart and status charts
    // X axis is String converted from LocalDateTime, Y axis is numbers
    @FXML
    private LineChart<String, Number> chart;
    @FXML
    private BarChart<String, Number> statChart;

    // FXML table and tablecolumns
    @FXML
    private TableView<BPData> bpTable;
    @FXML
    private TableColumn<BPData, LocalDateTime> dateColumn;
    @FXML
    private TableColumn<BPData, Integer> systolicColumn;
    @FXML
    private TableColumn<BPData, Integer> diastolicColumn;
    @FXML
    private TableColumn<BPData, Integer> pulseColumn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Sets the charts series data to the sorted lists
        // so we don't have to worry about keeping the list sorted!
        systolicSeries.setData(sortedsysData);
        diastolicSeries.setData(sorteddiasData);
        pulseSeries.setData(sortedpulseData);

        // Set up bindings with enabling/disabling the UI controls        
        addBPButton.disableProperty().bind(
                systolicField.textProperty().isEmpty().or
                    (diastolicField.textProperty().isEmpty()).or
                        (pulseField.textProperty().isEmpty()).or
                            (okToAdd.not()));

        updateBPButton.setDisable(true);
        deleteBPButton.setDisable(true);

        // Initialize datePicker to today's date
        LocalDateTime localDateTime = LocalDateTime.now();
        datePicker.setValue(localDateTime.toLocalDate());

        // Initialize startDate so we can define binding for the Label
        startDate.setValue(localDateTime.toLocalDate());
        toLabel.textProperty().bind(Bindings.createStringBinding(() ->
                String.format("To: %s",
                    startDate.getValue().plusDays(daysWindow.get())
                        .format(rangeFormatter)),
                            startDate.valueProperty()));

        // Initialize the items in the ampmControl
        ampmControl.setItems(FXCollections.observableArrayList("am", "pm"));

        // Initialize time controls (minute, hour, and ampm) with current time
        setTimeControls(localDateTime);

        // Configure InvalidationListener for spinners, combobox, datepicker,
        // and textfields
        InvalidationListener listener = observable -> {
            if (!deleteBPButton.isDisabled()) {
                updateBPButton.setDisable(false);
            }
        };

        hourSpinner.valueProperty().addListener(listener);
        minuteSpinner.valueProperty().addListener(listener);
        ampmControl.valueProperty().addListener(listener);
        systolicField.textProperty().addListener(listener);
        diastolicField.textProperty().addListener(listener);
        pulseField.textProperty().addListener(listener);
        datePicker.valueProperty().addListener(listener);

        // Set up Line Chart
        // Note that our code assumes the default, chart.createSymbols(true).
        // Otherwise, the series data points have no associated nodes
        // and we assume that the nodes exist
        systolicSeries.setName("Systolic");
        chart.getData().add(systolicSeries);
        diastolicSeries.setName("Diastolic");
        chart.getData().add(diastolicSeries);
        pulseSeries.setName("Pulse");
        chart.getData().add(pulseSeries);

        // Set up Stat Chart. Bar Chart data bound to Line Chart
        systolicStats.setName("Systolic");
        statChart.getData().add(systolicStats);
        diastolicStats.setName("Diastolic");
        statChart.getData().add(diastolicStats);
        pulseStats.setName("Pulse");
        statChart.getData().add(pulseStats);

        // Bind the statChart's titleProperty to the size of the
        // BP Readings list, so that the min / average / max data
        // are based on the number of readings.
        // Note that Bindings.size here takes an ObservableList,
        // which is itself not a Property, but its size is observable
        // with the Bindings.size() method.
        // (We could also have used displayList with Bindings.size.)
        statChart.titleProperty().bind(Bindings.concat(
                "Blood Pressure Stats for ",
                    Bindings.size((systolicSeries.getData())).asString(),
                        " Readings"));

        // Initialize Bar Chart data
        initStatChart(systolicStats);
        initStatChart(diastolicStats);
        initStatChart(pulseStats);

        // Make sure the Bar Chart data always reflects the 
        // current YValue data from the Line Chart data.
        setUpChartBindings(systolicSeries.getData(), systolicStats.getData());
        setUpChartBindings(diastolicSeries.getData(), diastolicStats.getData());
        setUpChartBindings(pulseSeries.getData(), pulseStats.getData());

        // Add ListChangeListener to the filteredList to keep the Line Chart
        // synchronized with the TableView data
        filteredList.addListener(
                (ListChangeListener.Change<? extends BPData> change) -> {
                    while (change.next()) {
                        if (change.wasRemoved()) {
                            System.out.println("Remove from Filtered List (" +
                                change.getRemovedSize() + " items)");                    
                            change.getRemoved().forEach(data ->
                                removeBPDataFromChart(change.getFrom()));
                        }
                        if (change.wasAdded()) {
                            System.out.println("Add to Filtered List (" +
                                change.getAddedSize() +
                                    " items) at index " + change.getFrom());
                            change.getAddedSubList().forEach(data ->
                                    addBPDataToChart(data));
                        }
                    }
        });

        // This puts BPdata into the backingList observable list
        MyData.fillBPData(backingList);

        // Add InvalidationListener to the startDate. When startDate
        // changes, re-invoke filteredList's predicate 
        // (which depends on startDate)
        startDate.valueProperty().addListener(observable -> {
            filteredList.setPredicate(data -> inRange(data.getDate()));
        });

        // Initialize startDate to first date in data.
        // This puts items in filteredList starting from the earliest date
        // and includes the daysWindow number of days
        startDate.setValue(sortedBackingList.get(0).getDate().toLocalDate());

        // Add an event handler to the Line Chart that clears
        // the BP reading controls, the drop shadow effect,
        // and the selection in the table when chart background is clicked
        chart.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            clearFields();
        });

        // Set up TableView
        // Define CellValueFactorys for each BPData property that
        // we want to display. The PropertyValueFactory converts its value 
        // to String and if that works, there's nothing more you have to do
        dateColumn.setCellValueFactory(
                new PropertyValueFactory<>(BPData.DATE_PROP_NAME));
        systolicColumn.setCellValueFactory(
                new PropertyValueFactory<>(BPData.SYSTOLIC_PROP_NAME));
        diastolicColumn.setCellValueFactory(
                new PropertyValueFactory<>(BPData.DIASTOLIC_PROP_NAME));
        pulseColumn.setCellValueFactory(
                new PropertyValueFactory<>(BPData.PULSE_PROP_NAME));

        // Customize the dateColumn to use our previously
        // defined DateTimeFormatter.
        // The CellFactory is a CallBack, which is invoked when
        // the cell needs to update its contents and calls updateItem.
        // A Callback is like a listener . . . 
        // Note that it's still necessary to define the above 
        // CellValueFactory for the date property
        dateColumn.setCellFactory(column -> {
            return new TableCell<BPData, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        // Format date
                        setText(formatter.format(item));
                    }
                }
            };
        });

        // This sets the tableview items property to the filteredList.
        // The table automatically updates when any item 
        // in filteredList changes, or when items are added to
        // or removed from the underlying observable list (backingList)
        bpTable.setItems(filteredList);

        // This eliminates empty columns to the right.
        bpTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // These 2 lines make the tablesize as big as necessary for the data.
        // By default, TableView creates a bunch of empty space to fill in
        // its preferred height
        bpTable.setFixedCellSize(25);
        bpTable.prefHeightProperty().bind(Bindings.size(bpTable.getItems())
                .multiply(bpTable.getFixedCellSize()).add(30));

        // Define a Changelistener for the table selectedIndexProperty,
        // which changes when the user selects a different row.
        // Although the selectedIndexProperty is ReadOnly, we can
        // change it using the SelectionModel select() method (see the
        // Line Chart nodes MOUSE_CLICKED event handler).
        // This lets users manually select rows in the table in
        // addition to the Line Chart selection UI defined.
        // If the selectedIndexProperty is -1, the selection has been
        // cleared by method clearFields() and we return
        bpTable.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    System.out.println("row is selected = "
                            + bpTable.getSelectionModel().getSelectedIndex()
                            + " old row = " + oldValue);
                    // Remove drop shadow from previously selected Line Chart
                    // points if necessary
                    if (oldValue.intValue() >= 0 &&
                            oldValue.intValue() < sortedsysData.size()) {
                        sortedsysData.get(oldValue.intValue())
                                .getNode().setEffect(null);
                        sorteddiasData.get(oldValue.intValue())
                                .getNode().setEffect(null);
                        sortedpulseData.get(oldValue.intValue())
                                .getNode().setEffect(null);
                    }

                    // If selection has been cleared, don't do anything                  
                    if (bpTable.getSelectionModel().getSelectedIndex() < 0) {
                        return;
                    }

                    // Make sure selected index is visible.
                    // Note that this code causes the following Warning if a
                    // scrollbar is NOT present.
                    // com.sun.javafx.scene.control.skin.VirtualFlow 
                    // addTrailingCells
                    // INFO: index exceeds maxCellCount. Check size calculations
                    // for class javafx.scene.control.TableRow
                    bpTable.scrollTo(bpTable.getSelectionModel()
                            .getSelectedIndex());
                    System.out.println("calling displayBPData for index " +
                            bpTable.getSelectionModel().getSelectedIndex() +
                                " from table selection listener");

                    // Configures the UI to shows selections
                    displayBPData();
                });

        // Configure range setting control buttons
        startDate.disableProperty().bind(
                Bindings.equal(Bindings.size(sortedBackingList), 0));

        // Disable firstButton if sortedBackingList is empty, 
        // or if the date in the first element of the (non-empty) filteredList
        // is the same as the first element in the sortedBackingList.
        // This custom binding is dependend on both filteredList
        // and sortedBackingList
        firstButton.disableProperty().bind(Bindings.createBooleanBinding(() ->
                sortedBackingList.isEmpty() ||
                    (!filteredList.isEmpty() &&
                        inRange(sortedBackingList.get(0).getDate())),
                            filteredList, sortedBackingList));

        // Custom binder version
        // firstButton.disableProperty().bind(new BooleanBinding() {
        //    { super.bind(filteredList, sortedBackingList); }
        //
        //    @Override
        //    protected boolean computeValue() {
        //        return (sortedBackingList.isEmpty() || 
        //            (!filteredList.isEmpty() &&
        //                inRange(filteredList.get(0).getDate())));
        //    }
        // });
        
        // Disable lastButton if  the sortedBackingList is empty, 
        // or if the date in the last element of the (non-empty) filteredList
        // is the same as the last element in the sortedBackingList.
        // This custom binding is dependent on both filteredList
        // and sortedBackingList
        lastButton.disableProperty().bind(Bindings.createBooleanBinding(() ->
                sortedBackingList.isEmpty() ||
                    (!filteredList.isEmpty() &&
                        filteredList.getSourceIndex(filteredList.size() - 1) ==
                            sortedBackingList.size() - 1),
                                filteredList, sortedBackingList));

        nextButton.disableProperty().bind(lastButton.disableProperty());
        prevButton.disableProperty().bind(firstButton.disableProperty());

        // end of initialize() method
    }

    // Set hour, minute, and ampm controls from the provided LocalDateTime.
    // Must convert the 24 (0-23) hour time to 1-12 with appropriate am/pm
    private void setTimeControls(LocalDateTime localDateTime) {
        int hour = localDateTime.getHour();
        minuteFactory.setValue(localDateTime.getMinute());
        ampmControl.getSelectionModel().select("am");

        if (hour >= 12) {
            ampmControl.getSelectionModel().select("pm");
            hour = (hour > 12) ? hour - 12 : hour;
        } else if (hour == 0) {
            hour = 12;
        }
        hourFactory.setValue(hour);
    }

    // Initialize Bar Chart with data and then configure it.
    // Use the same data when the Line Chart is empty
    private void initStatChart(XYChart.Series<String, Number> target) {
        target.getData().add(new XYChart.Data<>("Max", 1));
        target.getData().add(new XYChart.Data<>("Avg", 1.0));
        target.getData().add(new XYChart.Data<>("Min", 1));
        System.out.println("iniStatChart for " + target.getName());
        configureBarChartNodes(target);
    }

    // Add MOUSE_ENTERED and MOUSE_EXITED events for the Bar Chart.
    // This displays the YValue data in a Label on MOUSE_ENTERED
    // and clear the Label on MOUSE_EXITED
    private void configureBarChartNodes(
            XYChart.Series<String, Number> target) {
        target.getData().forEach(data -> {
            Node node = data.getNode();
            node.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                if (data.getXValue().equalsIgnoreCase("Avg")) {
                    nodeDisplay.setText(
                        String.format("%5.1f", data.getYValue()));
                } else {
                    nodeDisplay.setText(
                        String.format("%s", data.getYValue()));
                }
                // localToScene() positions the selected node relative to
                // the entire scene. This positions the nodeDisplay Label
                // control relative to the selected chart node
                Bounds screenBounds = node.localToScene(
                        node.getBoundsInLocal());
                nodeDisplay.setTranslateX(screenBounds.getMinX() + 10);
                nodeDisplay.setTranslateY(screenBounds.getMaxY() - 20);
            });
            node.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                // No need to reposition nodeDisplay Label when
                // its text property is empty
                nodeDisplay.setText("");
            });
        });
    }

    // Use binding to find max, average, min values for each observable list.       
    // In each of these bindings, sourceList is the binding dependency
    private void setUpChartBindings(
            ObservableList<Data<String, Number>> sourceList,
            ObservableList<Data<String, Number>> targetList) {

        // Use custom binding to compute maximum
        targetList.get(0).YValueProperty().bind(new IntegerBinding() {
            { super.bind(sourceList); }      // dependency

            @Override
            protected int computeValue() {
                return sourceList.stream()
                    .mapToInt(d -> d.getYValue().intValue())
                    .max()
                    .orElse(1);
            }
        });

        // Use Factory Bindings method to compute average
        targetList.get(1).YValueProperty().bind(
                Bindings.createDoubleBinding(() -> sourceList.stream()
                    .mapToInt(d -> d.getYValue().intValue())
                    .average()
                    .orElse(1.0),
                sourceList));           // dependency   

        // Use InvalidationListener to compute minimum
        // Note: no implicit assignment
        sourceList.addListener((Observable observable) -> {
            targetList.get(2).setYValue(sourceList.stream()
                .mapToInt(d -> d.getYValue().intValue())
                .min()
                .orElse(1));
        });
    }

    // Add BPData to Line Chart series.
    // All three series share the same XValue value (the date) 
    // for each data point.
    // Create a new XYChart.Data<String, Number> object,
    // add it to the series (unsorted) data,
    // and configure the node for the listeners.
    // Add the data to the unsorted wrapped list
    // and the SortedList wrapper keeps the Line Chart data sorted.
    // This method is called from the filteredList ListChangeListener
    private void addBPDataToChart(BPData data) {
        String myDate = data.getDate().format(formatter);
        // Systolic Series
        XYChart.Data<String, Number> xyData =
                new XYChart.Data<>(myDate, data.getSystolic());
        sysData.add(xyData);
        // This adds the MOUSE event listeners to the data's node
        // (the same listener for each series)
        addChartNodeHandler(xyData, sortedsysData);
        // Diastolic Series
        xyData = new XYChart.Data<>(myDate, data.getDiastolic());
        diasData.add(xyData);
        addChartNodeHandler(xyData, sorteddiasData);
        // Pulse Series
        xyData = new XYChart.Data<>(myDate, data.getPulse());
        pulseData.add(xyData);
        addChartNodeHandler(xyData, sortedpulseData);
    }

    // This method is called from the filteredList ListChangeListener
    // when one or more BPData elements are removed
    private void removeBPDataFromChart(int index) {
        // Note: we must remove the index associated with the sorted lists.
        // But we can't remove directly from the sorted lists, so we
        // get the correct item and use that as an argument for removal!
        // There is a bug in the charts that throws an exception if the last
        // item in the data observable list is removed. The work-around
        // is to turn off animation for remove operations
        if (index >= 0 && index < sysData.size()) {
            chart.setAnimated(false);
            sysData.remove(sortedsysData.get(index));
            diasData.remove(sorteddiasData.get(index));
            pulseData.remove(sortedpulseData.get(index));
            chart.setAnimated(true);
        }
    }

    // These mouse event handlers let users display the YValue
    // of the Node selected by the user (MOUSE_ENTERED) and clear the display
    // when the mouse is moved to another location (MOUSE_EXITED).
    // The MOUSE_CLICKED handler selects the corresponding row in the
    // TableView and the TableView selectedIndexProperty ChangeListener
    // makes changes to the UI (such as adding drop shadows, updating
    // the UI controls, etc.). To de-select a Line Chart node, the user 
    // clicks in the background of the Line Chart (see method initialize()
    // for this code)
    private void addChartNodeHandler(XYChart.Data<String, Number> data,
            ObservableList<Data<String, Number>> chartList) {
        Node node = data.getNode();
        node.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
            // Method localToScene() provides the node's bounds
            // in relationship to the entire scene.
            // This moves the nodeDisplay control within the scene
            // relative to the selected node.
            // The parents bounds positions the nodeDisplay
            // below the node at a constant place regardless of which series
            // that the user selects
            Bounds screenBounds = node.localToScene(node.getBoundsInLocal());
            Bounds parentBounds = node.getParent()
                    .localToScene(node.getParent().getBoundsInLocal());
            nodeDisplay.setTranslateX(screenBounds.getMinX());
            nodeDisplay.setTranslateY(parentBounds.getMaxY() + 20);
            nodeDisplay.setText(String.format("%s", data.getYValue()));
        });
        node.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
            nodeDisplay.setText("");
        });
        node.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            System.out.println("Select chart index " + chartList.indexOf(data));
            // This selects the chart nodes.
            // Since the TableView's and Line Chart's SortedLists 
            // are sorted the same, select the corresponding row in the
            // TableView using the same index as this selected node. 
            // This invokes the listener we set up 
            // for the TableView's SelectionModel selectedIndexProperty
            bpTable.getSelectionModel().select(chartList.indexOf(data));
            // The call to event.consume() is necessary because the 
            // MOUSE_CLICK event handler that was added to the chart would 
            // also receive this event, effectively canceling out this code
            event.consume();
        });
    }

    // Display selected data point's data in the controls for
    // the selected BPData item.
    // Add effects to all the corresponding Line Chart nodes.
    // This is called from the TableView's selection listener
    private void displayBPData() {
        // Grab BPData item that is selected
        int newIndex = bpTable.getSelectionModel().getSelectedIndex();
        System.out.println("disPlayBPData newIndex = " + newIndex);
        if (newIndex < 0 || newIndex > filteredList.size()) {
            return;
        }
        BPData data = filteredList.get(newIndex);
        LocalDateTime targetDate = data.getDate();
        // Set datePicker and time controls
        datePicker.setValue(targetDate.toLocalDate());
        setTimeControls(targetDate);
        // Set BP reading TextField controls
        systolicField.setText(String.format("%s", data.getSystolic()));
        diastolicField.setText(String.format("%s", data.getDiastolic()));
        pulseField.setText(String.format("%s", data.getPulse()));
        updateBPButton.setDisable(true);
        deleteBPButton.setDisable(false);
        okToAdd.set(false);

        // Add drop shadow to selected Chart Line symbols (nodes)
        DropShadow dropshadow = new DropShadow();
        sortedsysData.get(newIndex).getNode().setEffect(dropshadow);
        sorteddiasData.get(newIndex).getNode().setEffect(dropshadow);
        sortedpulseData.get(newIndex).getNode().setEffect(dropshadow);
    }

    // Clear all text fields.
    // Reset all ok boolean properties.
    // Change date and time to now.
    // This is called from the Line Chart's MOUSE_CLICKED event handler
    // to de-select a node or TableView row, as well as from
    // various user-initiated actions (add, delete, update, and
    // set new time span). The TableView SelectionModel's 
    // selectedIndexProperty ChangeListener is invoked when the table
    // selection is cleared
    private void clearFields() {
        // Set date/time to now
        LocalDateTime localDateTime = LocalDateTime.now();
        datePicker.setValue(localDateTime.toLocalDate());
        setTimeControls(localDateTime);
        // Clear the data fields
        systolicField.setText("");
        diastolicField.setText("");
        pulseField.setText("");
        // Update the disable property of buttons
        updateBPButton.setDisable(true);
        deleteBPButton.setDisable(true);
        okToAdd.set(true);
        System.out.println("Clear table selection");
        // Note: this invokes the TableView's selectedIndexProperty
        // listener if the table selection is NOT currently clear
        bpTable.getSelectionModel().clearSelection();
    }

    // Event Handlers
    // Add, Update, Delete
    // User clicks Add to add new BP Data to underlying backingList
    // (and maybe the displayList if new data is within the display range)
    @FXML
    private void addButtonHandler(ActionEvent event) {
        System.out.println("Add button pressed.");
        // Read controls to get all the new data
        int hour = hourSpinner.getValue();
        String ampm = "am";
        if ("pm".equals(ampmControl.getSelectionModel().getSelectedItem())) {
            ampm = "pm";
        }
        LocalDateTime newDate = LocalDateTime.of(
            datePicker.getValue().getYear(),
                datePicker.getValue().getMonthValue(),
                    datePicker.getValue().getDayOfMonth(), hour,
                        minuteSpinner.getValue());

        // Check new date for possible errors
        if (checkDateTime(newDate)) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Please Confirm Add New BP Reading");
            String s = new StringBuilder(
                "Confirm to add BP Reading to Chart\nDate:\t")
                    .append(datePicker.getValue()).append("\nTime:\t")
                    .append(hour).append(":")
                    .append(String.format("%02d", minuteSpinner.getValue()))
                    .append(" ").append(ampm).append("\nSystolic:\t")
                    .append(systolicField.getText()).append("\nDiastolic:\t")
                    .append(diastolicField.getText()).append("\nPulse:\t")
                    .append(pulseField.getText()).toString();
            alert.setContentText(s);
            // Great use of Optional here for the Confirmation
            Optional<ButtonType> result = alert.showAndWait();
            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                System.out.println("OK to add BP reading");
                configureAndAddBP();
            }
        }
    }

    // Add new data to the backingList.
    // If provided date is not unique, display error message and don't Add
    private void configureAndAddBP() {
        // Verify user entered numeric data
        try {
            // These are the BP Reading values
            Integer sys = Integer.parseInt(systolicField.getText());
            Integer dia = Integer.parseInt(diastolicField.getText());
            Integer pul = Integer.parseInt(pulseField.getText());

            int hour = getHourSpinner();

            // Create new BPData object from control values
            BPData data = MyData.buildData(LocalDateTime.of(
                datePicker.getValue().getYear(),
                    datePicker.getValue().getMonthValue(),
                        datePicker.getValue().getDayOfMonth(), hour,
                            minuteSpinner.getValue()), sys, dia, pul);

            // ObservableList backingList is wrapped in sortedBackingList.
            // For an add, sortedBackingList stays sorted.
            // Note: you cannot add data to the sortedBackingList.
            // Must add data to the wrapped list (backingList) and then
            // sortedBackingList does its thing.
            backingList.add(data);
            System.out.println("Add to sortedBackingList at index " +
                    sortedBackingList.indexOf(data));
            System.out.println("Item found at filteredList at index " +
                    filteredList.indexOf(data));

            // If new BP reading is within the range of the currently
            // displayed Line Chart data, then the FilteredList predicate
            // will automatically include it and display it in TableView.
            // The FilteredList ListChangeListener then triggers the
            // Line Chart and then the Bar Chart updates
            showMessage(new StringBuilder(data.toString())
                    .append(" added.").toString());
            // select if in range, otherwise clear 
            if (inRange(data.getDate())) {
                bpTable.getSelectionModel().select(data);
            } else {
                clearFields();
            }
        } catch (NumberFormatException e) {
            // Conversion error with one or more of the
            // numeric data entered in the TextField controls.
            // Tell the user to try again
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Number Formatting Error");
            alert.setContentText(String.format("%s\n%s %s",
                "Please input a whole number.", "Error:",
                    e.getLocalizedMessage()));
            alert.showAndWait();
        }
    }

    // This is the FilteredList predicate--all based on the startDate control.
    // We convert LocalDateTime to LocalDate--want to ignore time here
    private boolean inRange(LocalDateTime targetDate) {
        // Check range for inclusivity, so substract 1 from start
        // and add 2 to ending
        LocalDate date = targetDate.toLocalDate();
        LocalDate range = startDate.getValue().minusDays(1);
        return (date.isAfter(range) &&
                date.isBefore(range.plusDays(daysWindow.get() + 2)));
    }

    // User clicks Update to modify the BP Reading data.
    // Note that we don't use a Confirm dialog for Updates
    @FXML
    private void updateBPData(ActionEvent event) {
        System.out.println("Update button pressed.");
        try {
            // Add data from the text fields to the corresponding element
            // in the filtered list
            BPData data = bpTable.getSelectionModel().getSelectedItem();
            if (data == null) {
                return;
            }
            Integer sys = Integer.parseInt(systolicField.getText());
            Integer dia = Integer.parseInt(diastolicField.getText());
            Integer pul = Integer.parseInt(pulseField.getText());

            int hour = getHourSpinner();

            LocalDateTime newDate = LocalDateTime.of(
                datePicker.getValue().getYear(),
                    datePicker.getValue().getMonthValue(),
                        datePicker.getValue().getDayOfMonth(), hour,
                            minuteSpinner.getValue());

            // Check new date for possible errors
            if (data.getDate().equals(newDate) || checkDateTime(newDate)) {
                BPData newData = MyData.buildData(newDate, sys, dia, pul);
                // Could possibly cause a re-sort and re-filter,
                // more efficient to do a remove and add for updates
                bpTable.getSelectionModel().clearSelection();
                backingList.remove(data);
                backingList.add(newData);
                System.out.println("Updating " + newData);
                showMessage(new StringBuilder(newData.toString())
                        .append(" updated.").toString());
                int index = filteredList.indexOf(newData);
                if (index < 0) {
                    // no longer in list, clear selection
                    System.out.println("update no longer in table");
                    clearFields();
                } else {
                    System.out.println("set table selection for update");
                    bpTable.getSelectionModel().select(newData);
                }

            }
        } catch (NumberFormatException e) {
            // Feedback on NumberFormatException
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Number Formatting Error");
            alert.setContentText(String.format("%s %s",
                "Please input a whole number. Error: ",
                    e.getLocalizedMessage()));
            alert.showAndWait();
        }
    }

    // The user clicks the Delete button to remove the selected
    // data from the chart. The data is displayed via the MOUSE_CLICKED
    // event associated with a Line Chart data point or table selection
    @FXML
    private void deleteBPData(ActionEvent event) {
        System.out.println("Delete button pressed.");
        System.out.println("selected table index = " +
                bpTable.getSelectionModel().getSelectedIndex());
        BPData data = bpTable.getSelectionModel().getSelectedItem();
        if (data == null) {
            return;
        }

        // Get unchanged data to display in the Alert popup dialog
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Please Confirm Delete BP Reading Data");
        String s = new StringBuilder(
            "Confirm to delete BP Reading from Chart\nDate:\t")
                .append(data.getDate().format(formatter))
                .append("\nSystolic:\t")
                .append(data.getSystolic()).append("\nDiastolic:\t")
                .append(data.getDiastolic()).append("\nPulse:\t")
                .append(data.getPulse()).toString();
        alert.setContentText(s);

        Optional<ButtonType> result = alert.showAndWait();
        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            System.out.println("OK to delete BP reading");
            // Do a sanity check on selectedIndex
            if (bpTable.getSelectionModel().getSelectedIndex() >= 0 &&
                    bpTable.getSelectionModel().getSelectedIndex() <
                        sortedsysData.size()) {
                // Have to remove by the data, since the index
                // refers to the (sorted) filteredList.
                // Remove corresponding entry in backingList
                bpTable.getSelectionModel().clearSelection();
                backingList.remove(data);
                showMessage(new StringBuilder(data.toString())
                        .append(" deleted.").toString());
                // Once we've done the remove, clear all UI fields
                clearFields();
            } 
        }
    }

    private void showMessage(String msg) {
        mesg.setText(msg);
        FadeTransition ft = new FadeTransition(Duration.millis(7000), mesg);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.playFromStart();
    }

    // Anytime the startDate.valueProperty() changes, the action
    // event handler is invoked, which is method setRange() defined below
    @FXML
    private void goFirst(ActionEvent event) {
        System.out.println("Go First");
        startDate.setValue(sortedBackingList.get(0).getDate().toLocalDate());
    }

    @FXML
    private void goPrevious(ActionEvent event) {
        // Subtract daysWindow days from startDate
        startDate.setValue(startDate.getValue().minusDays(daysWindow.get()));
        System.out.println("Go Previous");
    }

    @FXML
    private void goNext(ActionEvent event) {
        // Add daysWindow days from startDate
        startDate.setValue(startDate.getValue().plusDays(daysWindow.get()));
        System.out.println("Go Next");
    }

    @FXML
    private void goLast(ActionEvent event) {
        // Set the range to the ending date
        // of the data in the sortedBackingList minus daysWindow days
        System.out.println("Go Last");
        startDate.setValue(sortedBackingList.get(sortedBackingList.size() - 1)
                .getDate().toLocalDate().minusDays(daysWindow.get()));

    }

    // This method is invoked whenever the startDate date picker changes
    // either directly by the user, or indirectly through the
    // goNext, goPrevious, goFirst, and goLast methods
    // that also change the startDate date picker control
    @FXML
    private void setRange(ActionEvent event) {
        // If the new range start point is after the last item in the data list,
        // reset the start picker to the last day - daysWindow.get()
        System.out.println("set range");
        if (startDate.getValue().isAfter(
                sortedBackingList.get(sortedBackingList.size() - 1)
                    .getDate().toLocalDate())) {
            // startDate is after last date - set to last date
            startDate.setValue(
                sortedBackingList.get(sortedBackingList.size() - 1)
                    .getDate().toLocalDate().minusDays(daysWindow.get()));
            System.out.println("startDate is after last date");
            return;
        } else if (startDate.getValue().isBefore(
                sortedBackingList.get(0).getDate().toLocalDate())) {
            // startDate is before first date - set to first date
            startDate.setValue(sortedBackingList.get(0)
                    .getDate().toLocalDate());
            System.out.println("startDate is before first date");
            return;
        }
        System.out.println("Series size = " + systolicSeries.getData().size());
        clearFields();
    }

    private boolean checkDateTime(LocalDateTime dateTime) {
        // Check for future date and get current date and time
        LocalDateTime localDateTime = LocalDateTime.now();
        if (dateTime.isAfter(localDateTime)) {
            // Disallow future date and display error message
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Date Time Error");
            alert.setContentText("Future date/time not allowed.");
            alert.showAndWait();
            return false;
        }
        // Check for duplicate date
        if (!sortedBackingList.stream()
                .noneMatch(data -> data.getDate().equals(dateTime))) {
            // Disallow duplicate date and display error message
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Date Time Error");
            alert.setContentText("Duplicate date/times not allowed.");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    // Get hour from Spinner control and ampmControl.
    // Converts 1-12 hours with am/pm to a 24 (0-23) hour clock.
    // 12:15 am converts to 00:15, 4:25 pm converts to 16:25,
    // and 12:30 pm is 12:30
    private int getHourSpinner() {
        int hour = hourSpinner.getValue();
        if (hour == 12 && "am".equals(
                ampmControl.getSelectionModel().getSelectedItem())) {
            hour = 0;
        } else if (hour < 12 && "pm".equals(
                ampmControl.getSelectionModel().getSelectedItem())) {
            hour += 12;
        }
        return hour;
    }
}
