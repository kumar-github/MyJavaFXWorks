package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.controlsfx.control.CheckListView;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;

import com.tc.app.exchangemonitor.model.ExternalTrade;
import com.tc.app.exchangemonitor.util.ApplicationHelper;
import com.tc.app.exchangemonitor.util.DatePickerConverter;
import com.tc.app.exchangemonitor.util.HibernateUtil;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MainApplicationMonitorTabController implements Initializable
{

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Variables injected through FXML starts here
	 * ============================================================================================================================================================================
	 */
	
	@FXML
	private ToolBar applicationMainWindowCurrentFilterToolBar;

	@FXML
	private Button startMonitorButton;

	@FXML
	private ImageView startMonitorButtonImageView;

	@FXML
	private Button pauseMonitorButton;

	@FXML
	private Button stopMonitorButton;

	@FXML private Text exchangesFilterKeyText;
	@FXML private Text exchangesFilterValueText;

	@FXML private Text statesFilterKeyText;
	@FXML private Text statesFilterValueText;

	@FXML private Text typesFilterKeyText;
	@FXML private Text typesFilterValueText;

	@FXML private Text accountsFilterKeyText;
	@FXML private Text accountsFilterValueText;

	@FXML private Text startDateFilterKeyText;
	@FXML private Text startDateFilterValueText;

	@FXML private Text endDateFilterKeyText;
	@FXML private Text endDateFilterValueText;

	@FXML
	private TitledPane actionTitledPane;

	@FXML
	private Accordion queryFilterAccordion;

	@FXML
	private CheckListView<String> externalTradeSourcesListView;

	@FXML
	private CheckListView<String> externalTradeStatesListView;

	@FXML
	private CheckListView<String> externalTradeStatusesListView;

	@FXML
	private CheckListView<String> externalTradeAccountsListView;

	@FXML
	private TextField externalTradeAccountsSearchTextField;

	@FXML
	private TitledPane externalTradeSourcesTitledPane;

	@FXML
	private TitledPane externalTradeStatesTitledPane;

	@FXML
	private TitledPane externalTradeStatusesTitledPane;

	@FXML
	private TitledPane externalTradeAccountsTitledPane;

	@FXML
	private DatePicker startDateDatePicker;

	@FXML
	private DatePicker endDateDatePicker;

	@FXML
	private TableView<ExternalTrade> exchangeTradesTableView;

	@FXML
	private TableColumn<ExternalTrade, Number> externalTradeOidTableColumn;

	@FXML
	private TableColumn<ExternalTrade, ZonedDateTime> tradeCreationDateTableColumn;

	@FXML
	private TableColumn<ExternalTrade, ZonedDateTime> tradeEntryDateTableColumn;

	@FXML
	private TableColumn<ExternalTrade, String> tradeStateTableColumn;

	@FXML
	private TableColumn<ExternalTrade, String> tradeStatusTableColumn;

	@FXML
	private TableColumn<ExternalTrade, String> exchangeTableColumn;

	@FXML
	private TableColumn<ExternalTrade, String> commodityTableColumn;

	@FXML
	private TableColumn<ExternalTrade, String> tradingPeriodTableColumn;

	@FXML
	private TableColumn<ExternalTrade, String> callPutTableColumn;

	@FXML
	private TableColumn<ExternalTrade, Number> strikePriceTableColumn;

	@FXML
	private TableColumn<ExternalTrade, Number> quantityTableColumn;

	@FXML
	private TableColumn<ExternalTrade, Number> priceTableColumn;

	@FXML
	private TableColumn<ExternalTrade, String> buyingCompanyTableColumn;

	@FXML
	private TableColumn<ExternalTrade, String> buyingTraderTableColumn;

	@FXML
	private TableColumn<ExternalTrade, String> sellingCompanyTableColumn;

	@FXML
	private TableColumn<ExternalTrade, String> sellingTraderTableColumn;

	@FXML
	private TableColumn<ExternalTrade, String> exchangeTradeNumTableColumn;

	@FXML
	private TableColumn<ExternalTrade, String> accountTableColumn;

	@FXML
	//private TableColumn<ExternalTrade, Number> ictsTradeNumTableColumn;
	//private TableColumn<ExternalTrade, Integer> ictsTradeNumTableColumn;
	private TableColumn<ExternalTrade, String> ictsTradeNumTableColumn;

	@FXML
	//private TableColumn<ExternalTrade, Number> ictsPortNumTableColumn;
	private TableColumn<ExternalTrade, String> ictsPortNumTableColumn;

	@FXML
	private TableColumn<ExternalTrade, String> tradeTypeTableColumn;

	@FXML
	private TableColumn<ExternalTrade, String> inputBrokerTableColumn;

	@FXML
	private TableColumn<ExternalTrade, String> buyerClearingBrokerTableColumn;

	@FXML
	private TableColumn<ExternalTrade, String> sellerClearingBrokerTableColumn;

	@FXML
	private TableColumn<ExternalTrade, String> commentTableColumn;

	@FXML
	private TextField externalTradeTableViewDataFilterTextField;

	/**
	 * ============================================================================================================================================================================
	 * 																																							All FXML Variables ends here
	 * ============================================================================================================================================================================
	 */

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Variables injected through @Inject starts here
	 * ============================================================================================================================================================================
	 */

	@Inject
	private String sqlQueryToFetchExternalTradeSources;

	@Inject
	private String sqlQueryStringToFetchExternalTradeStates;

	@Inject
	private String sqlQueryStringToFetchExternalTradeStatuses;

	@Inject
	private String sqlQueryStringToFetchExternalTradeAccounts;

	@Inject
	private String sqlQueryStringToFetchExternalTradesWithBuyerAccountQualifier;

	@Inject
	private String sqlQueryStringToFetchExternalTradesWithoutBuyerAccountQualifier;

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Variables injected through @Inject ends here
	 * ============================================================================================================================================================================
	 */

	/**
	 * ============================================================================================================================================================================
	 * 																																							All other variable declaration starts here
	 * ============================================================================================================================================================================
	 */

	private List<String> externalTradeAccounts;
	private List<String> checkedExternalTradeAccounts = new ArrayList<String>();
	private ObservableList<ExternalTrade> externalTrades = FXCollections.observableArrayList();
	private Map<String, String> externalTradeSourceTableMap = new HashMap<String, String>();
	private Map<String, String> externalTradeStateTableMap = new HashMap<String, String>();
	private Map<String, String> externalTradeStatusTableMap = new HashMap<String, String>();

	private FetchExternalTradesScheduledService fetchExternalTradesScheduledService = new FetchExternalTradesScheduledService();
	//private FetchExternalTradesService fetchExternalTradesService = new FetchExternalTradesService();

	/**
	 * ============================================================================================================================================================================
	 * 																																							All other variable declaration ends here
	 * ============================================================================================================================================================================
	 */

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		/* add the instantiated controller object to map, so that we can use in the future. */
		addThisControllerToControllersMap();

		/* so that we can track if any fxml variables are not injected. */
		doAssertion();

		/* This is to bind the observable variables to the actual UI control. Once bound, the data in the observable variable will automatically displayed on the UI control. Note: Initially the variables 
		 * value may be null, so nothing appears on the UI control but whenever the value gets changed on the variable either directly or indirectly that will reflect on the UI control automatically.
		 */
		doInitialDataBinding();

		/* This will initialize the user interface ensuring all UI controls are loaded with the proper data. We need to fetch data from DB and construct checkboxes, buttons etc... and display on the UI. */
		initializeGUI();

		/* This will set the state of any UI control. For ex: if we need a TitledPane control to be in collapsed state initially and expanded only when user clicks. */
		setAnyUIComponentStateIfNeeded();

		/* This will set the tooltips for components. Mostly this should be set in the FXML level itself but for some component we are unable to do in FXML, so do it here. */
		setComponentToolTipIfNeeded();

		/* This will initialize bind the listeners to the respective UI controls so that when app is launched, everything is ready for user interaction. */
		initializeListeners();

		/* This will initialize the tables with the columns and bind the cell value factories for the columns. */
		initializeTableViews();

		/* This will initialize the animations if needed so that, we see the table rotation or button fade effect etc... */
		initializeAnimationsIfNeeded();
	}

	private void addThisControllerToControllersMap()
	{
		ApplicationHelper.controllersMap.putInstance(MainApplicationMonitorTabController.class, this);
	}

	private void doAssertion()
	{
		assert externalTradeTableViewDataFilterTextField != null : "fx:id=\"externalTradeTableViewDataFilterTextField\" was not injected. Check your FXML file MainApplicationViewNew.fxml";
	}

	private void doInitialDataBinding()
	{
		/* Since startDate and endDate are set as NULL initially, "null" is appearing in the startDateFilterText and endDateFilterText and bcoz of that the Text control is appearing in the UI. To get rid 
		 * of that we are hiding the Text control if it contains text equals to "null"
		 */
		startDateFilterValueText.visibleProperty().bind(startDateFilterValueText.textProperty().isNotEqualTo("null"));
		endDateFilterValueText.visibleProperty().bind(endDateFilterValueText.textProperty().isNotEqualTo("null"));

		exchangesFilterKeyText.visibleProperty().bind(exchangesFilterValueText.textProperty().isNotEmpty());
		exchangesFilterKeyText.managedProperty().bind(exchangesFilterKeyText.visibleProperty());
		exchangesFilterValueText.managedProperty().bind(exchangesFilterValueText.visibleProperty());

		statesFilterKeyText.visibleProperty().bind(statesFilterValueText.textProperty().isNotEmpty());
		statesFilterKeyText.managedProperty().bind(statesFilterKeyText.visibleProperty());
		statesFilterValueText.managedProperty().bind(statesFilterValueText.visibleProperty());

		typesFilterKeyText.visibleProperty().bind(typesFilterValueText.textProperty().isNotEmpty());
		typesFilterKeyText.managedProperty().bind(typesFilterKeyText.visibleProperty());
		typesFilterValueText.managedProperty().bind(typesFilterValueText.visibleProperty());

		accountsFilterKeyText.visibleProperty().bind(accountsFilterValueText.textProperty().isNotEmpty());
		accountsFilterKeyText.managedProperty().bind(accountsFilterKeyText.visibleProperty());
		accountsFilterValueText.managedProperty().bind(accountsFilterValueText.visibleProperty());

		startDateFilterKeyText.visibleProperty().bind(startDateFilterValueText.textProperty().isNotEqualTo("null"));
		startDateFilterKeyText.managedProperty().bind(startDateFilterKeyText.visibleProperty());
		startDateFilterValueText.managedProperty().bind(startDateFilterValueText.visibleProperty());

		endDateFilterKeyText.visibleProperty().bind(endDateFilterValueText.textProperty().isNotEqualTo("null"));
		endDateFilterKeyText.managedProperty().bind(endDateFilterKeyText.visibleProperty());
		endDateFilterValueText.managedProperty().bind(endDateFilterValueText.visibleProperty());

		exchangeTradesTableView.setItems(externalTrades);
		startDateFilterValueText.textProperty().bind(startDateDatePicker.valueProperty().asString());
		endDateFilterValueText.textProperty().bind(endDateDatePicker.valueProperty().asString());

		startMonitorButton.disableProperty().bind(fetchExternalTradesScheduledService.runningProperty());
		pauseMonitorButton.disableProperty().bind(fetchExternalTradesScheduledService.runningProperty().not());
		stopMonitorButton.disableProperty().bind(fetchExternalTradesScheduledService.runningProperty().not());

		actionTitledPane.disableProperty().bind(fetchExternalTradesScheduledService.runningProperty());
		queryFilterAccordion.disableProperty().bind(fetchExternalTradesScheduledService.runningProperty());
		externalTradeTableViewDataFilterTextField.disableProperty().bind(fetchExternalTradesScheduledService.runningProperty());

		//applicationMainWindowCurrentFilterToolBar.visibleProperty().bind(exchangesFilterText.textProperty().isNotEmpty().or(statesFilterText.textProperty().isNotEmpty()).or(typesFilterText.textProperty().isNotEmpty()).or(accountsFilterText.textProperty().isNotEmpty()).or(startDateFilterText.textProperty().isNotEqualTo("null")).or(endDateFilterText.textProperty().isNotEqualTo("null")));
		/* We are hiding the entire toolbar if no text in any of the Text control. */
		applicationMainWindowCurrentFilterToolBar.visibleProperty().bind
		(
				exchangesFilterKeyText.visibleProperty()
				.or(statesFilterKeyText.visibleProperty())
				.or(typesFilterKeyText.visibleProperty())
				.or(accountsFilterKeyText.visibleProperty())
				.or(startDateFilterKeyText.visibleProperty())
				.or(endDateFilterKeyText.visibleProperty())
				);
	}

	private void initializeGUI()
	{
		/**
		 * fetch exchanges from external_trade_source table and construct checkbox for each exchange and set it on the UI
		 */
		List<String> externalTradeSourceNames = fetchAllExternalTradeSourcesFromDB();
		setExternalTradeSourceCheckBoxesOnUI(externalTradeSourceNames);

		/**
		 * fetch external trades states from external_trade_state table and construct checkbox for each trade state and set it on the UI
		 */
		List<String> externalTradeStateNames = fetchAllExternalTradeStatesFromDB();
		setExternalTradeStateCheckBoxesOnUI(externalTradeStateNames);

		/**
		 * fetch external trades statuses from external_trade_status table and construct checkbox for each trade status and set it on the UI
		 */
		List<String> externalTradeStatusNames = fetchAllExternalTradeStatusesFromDB();
		setExternalTradeStatusCheckBoxesOnUI(externalTradeStatusNames);

		/**
		 * fetch trade accounts from external_mapping table and with mapping_type 'K' and construct checkbox for trade account and set it on the UI
		 */
		//List<String> externalTradeAccounts = fetchExternalTradeAccountsFromDB();
		externalTradeAccounts = fetchAllExternalTradeAccountsFromDB();
		setExternalTradeAccountCheckBoxesOnUI(externalTradeAccounts);

		/**
		 * set yesterday's date as default start date
		 */
		//startDateDatePicker.setValue(LocalDate.now().minusDays(1));
		startDateDatePicker.setValue(null);
		startDateDatePicker.setConverter(new DatePickerConverter("dd-MMM-yyyy"));
		//startDateDatePicker.setPromptText("dd-MMM-yyyy");

		/**
		 * set today's date as default end date
		 */
		//endDateDatePicker.setValue(LocalDate.now());
		endDateDatePicker.setValue(null);
		endDateDatePicker.setConverter(new DatePickerConverter("dd-MMM-yyyy"));

		/**
		 * fetch external trade types from external_trade_type table so that we can use when we display data in the TableView, since we need to display the 
		 * trade_type_name in the UI
		 */
	}

	public List<String> fetchAllExternalTradeSourcesFromDB()
	{
		List<Object> externalTradeSourceTable = Collections.emptyList();
		List<String> externalTradeSourceNamesList = new ArrayList<String>();

		/**
		 * Variables injected through properties files takes priority over the code. so if we found a variable in the properties file
		 * with value then use that value if not then proceed with the value in the code.
		 */
		if(sqlQueryToFetchExternalTradeSources == null || sqlQueryToFetchExternalTradeSources.isEmpty())
		{
			sqlQueryToFetchExternalTradeSources = getSQLQueryToFetchExternalTradeSources();
		}
		externalTradeSourceTable = fetchDataFromDB(sqlQueryToFetchExternalTradeSources, "externalTradeSrcName", "externalTradeSrcOid");
		for(Object anExternalTradeSourceRecord : externalTradeSourceTable)
		{
			String externalTradeSrcName = ((Map)anExternalTradeSourceRecord).get("externalTradeSrcName").toString();
			String externalTradeSrcOid = ((Map)anExternalTradeSourceRecord).get("externalTradeSrcOid").toString();
			externalTradeSourceTableMap.put(externalTradeSrcName, externalTradeSrcOid);
			externalTradeSourceNamesList.add(externalTradeSrcName);
		}
		return externalTradeSourceNamesList;
	}

	public List<String> fetchAllExternalTradeStatesFromDB()
	{
		List<Object> externalTradeStateTable = Collections.emptyList();
		List<String> externalTradeStateNamesList = new ArrayList<String>();

		/**
		 * Variables injected through properties files takes priority over the code. so if we found a variable in the properties file
		 * with value then use that value if not then proceed with the value in the code.
		 */
		if(sqlQueryStringToFetchExternalTradeStates == null || sqlQueryStringToFetchExternalTradeStates.isEmpty())
		{
			sqlQueryStringToFetchExternalTradeStates = getSQLQueryToFetchExternalTradeStates();
		}
		externalTradeStateTable = fetchDataFromDB(sqlQueryStringToFetchExternalTradeStates, "externalTradeStateName", "externalTradeStateOid");
		for(Object anExternalTradeStateRecord : externalTradeStateTable)
		{
			String externalTradeStateName = ((Map)anExternalTradeStateRecord).get("externalTradeStateName").toString();
			String externalTradeStatecOid = ((Map)anExternalTradeStateRecord).get("externalTradeStateOid").toString();
			externalTradeStateTableMap.put(externalTradeStateName, externalTradeStatecOid);
			externalTradeStateNamesList.add(externalTradeStateName);
		}
		return externalTradeStateNamesList;
	}

	public List<String> fetchAllExternalTradeStatusesFromDB()
	{
		List<Object> externalTradeStatusTable = Collections.emptyList();
		List<String> externalTradeStatusNamesList = new ArrayList<String>();

		/**
		 * Variables injected through properties files takes priority over the code. so if we found a variable in the properties file
		 * with value then use that value if not then proceed with the value in the code.
		 */
		if(sqlQueryStringToFetchExternalTradeStatuses == null || sqlQueryStringToFetchExternalTradeStatuses.isEmpty())
		{
			sqlQueryStringToFetchExternalTradeStatuses = getSQLQueryToFetchExternalTradeStatuses();
		}
		externalTradeStatusTable = fetchDataFromDB(sqlQueryStringToFetchExternalTradeStatuses, "externalTradeStatusName", "externalTradeStatusOid");
		for(Object aRecord : externalTradeStatusTable)
		{
			String externalTradeStatusName = ((Map)aRecord).get("externalTradeStatusName").toString();
			String externalTradeStatusOid = ((Map)aRecord).get("externalTradeStatusOid").toString();
			externalTradeStatusTableMap.put(externalTradeStatusName, externalTradeStatusOid);
			externalTradeStatusNamesList.add(externalTradeStatusName);
		}
		return externalTradeStatusNamesList;
	}

	public List<String> fetchAllExternalTradeAccountsFromDB()
	{
		/**
		 * Variables injected through properties files takes priority over the code. so if we found a variable in the properties file
		 * with value then use that value if not then proceed with the value in the code.
		 */
		if(sqlQueryStringToFetchExternalTradeAccounts == null || sqlQueryStringToFetchExternalTradeAccounts.isEmpty())
		{
			sqlQueryStringToFetchExternalTradeAccounts = getSQLQueryToFetchExternalTradeAccounts();
		}
		return fetchDataFromDB(sqlQueryStringToFetchExternalTradeAccounts, "externalValue1");
	}

	public List<String> fetchDataFromDB(String sqlQueryString, String scalarColumn)
	{
		Session session = HibernateUtil.beginTransaction();

		SQLQuery sqlQueryToFetchData = session.createSQLQuery(sqlQueryString);
		sqlQueryToFetchData.addScalar(scalarColumn, StringType.INSTANCE);
		return sqlQueryToFetchData.list();
	}

	public List<Object> fetchDataFromDB(String sqlQueryString, String scalarColumn1, String scalarColumn2)
	{
		List<Object> aTable = Collections.emptyList();
		Session session = HibernateUtil.beginTransaction();

		SQLQuery sqlQueryToFetchData = session.createSQLQuery(sqlQueryString);
		sqlQueryToFetchData.addScalar(scalarColumn1, StringType.INSTANCE);
		//sqlQueryToFetchData.addScalar(scalarColumn2, IntegerType.INSTANCE);
		sqlQueryToFetchData.addScalar(scalarColumn2, StringType.INSTANCE);

		sqlQueryToFetchData.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		aTable = sqlQueryToFetchData.list();

		return aTable;
	}

	public void setExternalTradeSourceCheckBoxesOnUI(List<String> externalTradeSources)
	{
		externalTradeSourcesListView.setItems(FXCollections.observableArrayList(externalTradeSources));
	}

	public void setExternalTradeStateCheckBoxesOnUI(List<String> externalTradeStates)
	{
		externalTradeStatesListView.setItems(FXCollections.observableArrayList(externalTradeStates));
	}

	public void setExternalTradeStatusCheckBoxesOnUI(List<String> externalTradeStatuses)
	{
		externalTradeStatusesListView.setItems(FXCollections.observableArrayList(externalTradeStatuses));
	}

	public void setExternalTradeAccountCheckBoxesOnUI(List<String> externalTradeAccounts)
	{
		externalTradeAccounts.add(0, "Any");
		externalTradeAccountsListView.setItems(FXCollections.observableArrayList(externalTradeAccounts));
	}

	private String getSQLQueryToFetchExternalTradeSources()
	{
		return "select etsource.external_trade_src_name as externalTradeSrcName, etsource.oid as externalTradeSrcOid from external_trade_system etsystem join external_trade_source etsource on etsystem.oid = etsource.external_trade_system_oid and etsource.external_trade_src_name <> 'NonDefined' order by etsource.external_trade_src_name";
	}

	private String getSQLQueryToFetchExternalTradeStates()
	{
		return "select etstate.external_trade_state_name as externalTradeStateName, etstate.oid as externalTradeStateOid from external_trade_state etstate order by etstate.external_trade_state_name";
	}

	private String getSQLQueryToFetchExternalTradeStatuses()
	{
		return "select etstatus.external_trade_status_name as externalTradeStatusName, etstatus.oid as externalTradeStatusOid from external_trade_status etstatus order by etstatus.external_trade_status_name";
	}

	private String getSQLQueryToFetchExternalTradeAccounts()
	{
		return "select distinct em.external_value1 as externalValue1 from external_mapping em where em.mapping_type = 'K' order by em.external_value1";
	}

	private void setAnyUIComponentStateIfNeeded()
	{
		actionTitledPane.setExpanded(false);
	}

	private void setComponentToolTipIfNeeded()
	{
	}

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Listener registration starts here
	 * ============================================================================================================================================================================
	 */

	private void initializeListeners()
	{
		externalTradeSourcesListView.getCheckModel().getCheckedItems().addListener((Change<? extends String> change) ->
		{
			handleExternalTradeSourcesCheckBoxClick(change);
		});

		externalTradeStatesListView.getCheckModel().getCheckedItems().addListener((Change<? extends String> change) ->
		{
			handleExternalTradeStatesCheckBoxClick(change);
		});

		externalTradeStatusesListView.getCheckModel().getCheckedItems().addListener((Change<? extends String> change) ->
		{
			handleExternalTradeStatusesCheckBoxClick(change);
		});

		//tradeAccountListView.getCheckModel().getCheckedItems().addListener(accountsCheckBoxCheckedItemListener);
		externalTradeAccountsListView.getCheckModel().getCheckedItems().addListener((Change<? extends String> change) ->
		{
			handleExternalTradeAccountsCheckBoxClick(change);
		});

		externalTradeAccountsSearchTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) ->
		{
			handleExternalTradeAccountsFilterByKey(oldValue, newValue);
		});

		//filterTableDataTextField.textProperty().addListener(someLisetner);
		//ChangeListener or InvalidationListener? please think
		externalTradeTableViewDataFilterTextField.textProperty().addListener((Observable observable) ->
		{
			handleExternalTradeTableViewFilterByKey();
		});
	}

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Listener registration ends here
	 * ============================================================================================================================================================================
	 */

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Listeners methods starts here
	 * ============================================================================================================================================================================
	 */

	public void handleExternalTradeAccountsFilterByKey(String oldValue, String newValue)
	{
		// If the number of characters in the text box is less than last time it must be because the user pressed delete
		if(oldValue != null && (newValue.length() < oldValue.length()))
		{
			// Restore the lists original set of entries and start from the beginning
			externalTradeAccountsListView.setItems(FXCollections.observableArrayList(externalTradeAccounts));
		}

		// Change to upper case so that case is not an issue
		newValue = newValue.toUpperCase();

		// Filter out the entries that don't contain the entered text
		ObservableList<String> subentries = FXCollections.observableArrayList();

		//for ( Object entry: tradeAccountListView.getItems() ) {
		for ( String entry: externalTradeAccountsListView.getItems() )
		{
			if(entry.toUpperCase().contains(newValue))
			{
				subentries.add(entry);
			}
		}
		externalTradeAccountsListView.setItems(subentries);

		for(String string : checkedExternalTradeAccounts)
		{
			if(subentries.contains(string))
			{
				externalTradeAccountsListView.getCheckModel().check(string);
			}
		}
		//tradeAccountListView.getCheckModel().getCheckedItems().addListener(accountsCheckBoxCheckedItemListener);
		externalTradeAccountsListView.getCheckModel().getCheckedItems().addListener((Change<? extends String>change) ->
		{
			handleExternalTradeAccountsCheckBoxClick(change);
		});
	}

	public void handleExternalTradeSourcesCheckBoxClick(Change<? extends String> change)
	{
		if(externalTradeSourcesListView.getCheckModel().getCheckedItems().size() == 0)
			externalTradeSourcesTitledPane.setText(ApplicationConstants.EXTERNAL_TRADE_SOURCES_TITLEDPANE_TEXT);
		else
			externalTradeSourcesTitledPane.setText(ApplicationConstants.EXTERNAL_TRADE_SOURCES_TITLEDPANE_TEXT + "(" + externalTradeSourcesListView.getCheckModel().getCheckedItems().size() + ")");

		//exchangesFilterText.setText(externalTradeSourcesListView.getCheckModel().getCheckedItems().toString());
		if(externalTradeSourcesListView.getCheckModel().getCheckedItems().size() > 0)
			exchangesFilterValueText.setText(externalTradeSourcesListView.getCheckModel().getCheckedItems().toString());
		else
			exchangesFilterValueText.setText(null);
	}

	public void handleExternalTradeStatesCheckBoxClick(Change<? extends String> change)
	{
		if(externalTradeStatesListView.getCheckModel().getCheckedItems().size() == 0)
			externalTradeStatesTitledPane.setText(ApplicationConstants.EXTERNAL_TRADE_STATES_TITLEDPANE_TEXT);
		else
			externalTradeStatesTitledPane.setText(ApplicationConstants.EXTERNAL_TRADE_STATES_TITLEDPANE_TEXT + "(" + externalTradeStatesListView.getCheckModel().getCheckedItems().size() + ")");

		//statesFilterText.setText(externalTradeStatesListView.getCheckModel().getCheckedItems().toString());
		if(externalTradeStatesListView.getCheckModel().getCheckedItems().size() > 0)
			statesFilterValueText.setText(externalTradeStatesListView.getCheckModel().getCheckedItems().toString());
		else
			statesFilterValueText.setText(null);
	}

	public void handleExternalTradeStatusesCheckBoxClick(Change<? extends String> change)
	{
		if(externalTradeStatusesListView.getCheckModel().getCheckedItems().size() == 0)
			externalTradeStatusesTitledPane.setText(ApplicationConstants.EXTERNAL_TRADE_STATUSES_TITLEDPANE_TEXT);
		else
			externalTradeStatusesTitledPane.setText(ApplicationConstants.EXTERNAL_TRADE_STATUSES_TITLEDPANE_TEXT + "(" + externalTradeStatusesListView.getCheckModel().getCheckedItems().size() + ")");

		//typesFilterText.setText(externalTradeStatusesListView.getCheckModel().getCheckedItems().toString());
		if(externalTradeStatusesListView.getCheckModel().getCheckedItems().size() > 0)
			typesFilterValueText.setText(externalTradeStatusesListView.getCheckModel().getCheckedItems().toString());
		else
			typesFilterValueText.setText(null);
	}

	public void handleExternalTradeAccountsCheckBoxClick(Change<? extends String> change)
	{
		change.next();
		//System.out.println(change.getAddedSubList().get(0));
		if(change.wasAdded())
		{
			checkedExternalTradeAccounts.add(change.getAddedSubList().get(0));
		}
		else if(change.wasRemoved())
		{
			checkedExternalTradeAccounts.remove(change.getRemoved().get(0));
		}
		if(checkedExternalTradeAccounts.size() == 0)
			externalTradeAccountsTitledPane.setText(ApplicationConstants.EXTERNAL_TRADE_ACCOUNTS_TITLEDPANE_TEXT);
		else
			externalTradeAccountsTitledPane.setText(ApplicationConstants.EXTERNAL_TRADE_ACCOUNTS_TITLEDPANE_TEXT + "(" + checkedExternalTradeAccounts.size() + ")");

		if(checkedExternalTradeAccounts.size() > 0)
			accountsFilterValueText.setText(checkedExternalTradeAccounts.toString());
		else
			accountsFilterValueText.setText(null);
	}

	public void handleExternalTradeTableViewFilterByKey()
	{
		if(externalTradeTableViewDataFilterTextField.textProperty().get().isEmpty())
		{
			exchangeTradesTableView.setItems(FXCollections.observableArrayList(externalTrades));
			return;
		}
		ObservableList<ExternalTrade> tableItems = FXCollections.observableArrayList();
		ObservableList<TableColumn<ExternalTrade, ?>> allCoulmns = exchangeTradesTableView.getColumns();
		for(int i=0; i<externalTrades.size(); i++)
		{
			for(int j=0; j<allCoulmns.size(); j++)
			{
				TableColumn<ExternalTrade, ?> col = allCoulmns.get(j);
				String cellValue = col.getCellData(externalTrades.get(i)).toString();
				cellValue = cellValue.toLowerCase();
				if(cellValue.contains(externalTradeTableViewDataFilterTextField.textProperty().get().toLowerCase()))
				{
					tableItems.add(externalTrades.get(i));
					break;
				}
			}
		}
		exchangeTradesTableView.setItems(tableItems);
	}

	/*public ListChangeListener<String> accountsCheckBoxCheckedItemListener = new ListChangeListener<String>()
	{
		@Override
		public void onChanged(javafx.collections.ListChangeListener.Change<? extends String> change)
		{
			change.next();
			//System.out.println(change.getAddedSubList().get(0));
			if(change.wasAdded())
			{
				checkedExternalTradeAccounts.add(change.getAddedSubList().get(0));
			}
			else if(change.wasRemoved())
			{
				checkedExternalTradeAccounts.remove(change.getRemoved().get(0));
			}
			String temp = "Trade Account";
			if(checkedExternalTradeAccounts.size() == 0)
				tradeAccountTitledPane.setText(temp);
			else
				tradeAccountTitledPane.setText(temp + "(" + checkedExternalTradeAccounts.size() + ")");
		}
	};*/

	/**
	 * Here logic goes for filtering the table data
	 */

	public InvalidationListener someLisetner = new InvalidationListener()
	{
		final ObservableList<ExternalTrade> initialData = exchangeTradesTableView != null ? exchangeTradesTableView.getItems() : null;

		@Override
		public void invalidated(Observable observable)
		{
			if(externalTradeTableViewDataFilterTextField.textProperty().get().isEmpty())
			{
				exchangeTradesTableView.setItems(initialData);
				return;
			}
			ObservableList<ExternalTrade> tableItems = FXCollections.observableArrayList();
			ObservableList<TableColumn<ExternalTrade, ?>> cols = exchangeTradesTableView.getColumns();
			for(int i=0; i<initialData.size(); i++)
			{
				for(int j=0; j<cols.size(); j++)
				{
					TableColumn<ExternalTrade, ?> col = cols.get(j);
					String cellValue = col.getCellData(initialData.get(i)).toString();
					cellValue = cellValue.toLowerCase();
					if(cellValue.contains(externalTradeTableViewDataFilterTextField.textProperty().get().toLowerCase()))
					{
						tableItems.add(initialData.get(i));
						break;
					}                        
				}
			}
			exchangeTradesTableView.setItems(tableItems);	
		}
	}; 

	/*filterTableDataTextField.textProperty().addListener(new InvalidationListener() {
		@Override
		public void invalidated(Observable o) {
			if(filterTableDataTextField.textProperty().get().isEmpty()) {
				exchangeTradesTableView.setItems(getDummyTableData());
				return;
			}
			ObservableList<DummyExternalTrade> tableItems = FXCollections.observableArrayList();
			ObservableList<TableColumn<DummyExternalTrade, ?>> cols = exchangeTradesTableView.getColumns();
			for(int i=0; i<getDummyTableData().size(); i++) {

				for(int j=0; j<cols.size(); j++) {
					TableColumn col = cols.get(j);
					String cellValue = col.getCellData(getDummyTableData().get(i)).toString();
					cellValue = cellValue.toLowerCase();
					if(cellValue.contains(filterTableDataTextField.textProperty().get().toLowerCase())) {
						tableItems.add(getDummyTableData().get(i));
						break;
					}                        
				}

			}
			exchangeTradesTableView.setItems(tableItems);
		}
	});*/

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Listeners methods ends here
	 * ============================================================================================================================================================================
	 */

	private void initializeTableViews()
	{
		initializeExternalTradeTableView();
	}

	private void initializeExternalTradeTableView()
	{
		//exchangeTradesTableView.getSelectionModel().setCellSelectionEnabled(true);
		//exchangeTradesTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		/*tradeOidTableColumn.setCellValueFactory(new PropertyValueFactory<>("oid"));*/
		/*tradeOidTableColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<DummyExternalTrade, Number>, ObservableValue<Number>>()
		{
			@Override
			public ObservableValue<Number> call(CellDataFeatures<DummyExternalTrade, Number> param)
			{
				return new SimpleIntegerProperty(param.getValue().getOid().intValue());
			}});/*

		/* commenting the above code, bcoz the same can be implemented as below using java 8 Lambda*/
		externalTradeOidTableColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOid()));

		//tradeCreationDateTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<Date>(cellData.getValue().getCreationDate()));
		/*
		 modified the above code as below. creationDate column in DB is Date or TimeStamp, so it is mandatory to define it as Date in the DummyExternalTrade bean class. 
		 But to utilize the java 8 LocalDate concept, we declared the TableView's creation date column as LocalDate. The value returned by the DummyExternalTrade bean is Date but 
		 the UI column is expecting a LocalDate. so we convert the date to LocalDate.    
		 */
		tradeCreationDateTableColumn.setCellValueFactory(new TradeCreationDateCellValueFactory());
		tradeCreationDateTableColumn.setCellFactory(new TradeCreationDateCellFactory());

		tradeEntryDateTableColumn.setCellValueFactory(new TradeEntryDateCellValueFactory());
		tradeEntryDateTableColumn.setCellFactory(new TradeEntryDateCellFactory());

		tradeStateTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalTradeStateOid().getExternalTradeStateName()));
		//tradeStateTableColumn.setCellFactory(new ExternalTradeStateCellFactory());

		tradeStatusTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalTradeStatusOid().getExternalTradeStatusName()));
		//tradeStatusTableColumn.setCellFactory(new ExternalTradeStatusCellFactory());

		exchangeTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalTradeSourceOid().getExternalTradeSrcName()));
		//exchangeTableColumn.setCellFactory(new ExternalTradeExchangesCellFactory());

		commodityTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExchToolsTrade().getCommodity()));
		tradingPeriodTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExchToolsTrade().getTradingPeriod()));
		callPutTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExchToolsTrade().getCallPut()));

		strikePriceTableColumn.setCellValueFactory(cellData -> {
			if(cellData.getValue().getExchToolsTrade().getStrikePrice() != null)
				return new ReadOnlyDoubleWrapper(cellData.getValue().getExchToolsTrade().getStrikePrice().doubleValue());
			return null;
		});	

		quantityTableColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getExchToolsTrade().getQuantity()));
		priceTableColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getExchToolsTrade().getPrice()));

		buyingCompanyTableColumn.setCellValueFactory(cellData -> {
			if(cellData.getValue().getExchToolsTrade().getInputAction().trim().equals("BUY"))
			{
				return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getInputCompany());
			}
			else
			{
				return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getAcceptedCompany());
			}
		});

		buyingTraderTableColumn.setCellValueFactory(cellData -> {
			if(cellData.getValue().getExchToolsTrade().getInputAction().trim().equals("BUY"))
				return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getInputTrader());
			return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getAcceptedTrader());
		});

		sellingCompanyTableColumn.setCellValueFactory(cellData -> {
			if(cellData.getValue().getExchToolsTrade().getInputAction().trim().equals("BUY"))
				return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getAcceptedCompany());
			return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getInputCompany());
		});

		sellingTraderTableColumn.setCellValueFactory(cellData -> {
			if(cellData.getValue().getExchToolsTrade().getInputAction().trim().equals("BUY"))
				return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getAcceptedTrader());
			return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getInputTrader());
		});

		exchangeTradeNumTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExchToolsTrade().getExchToolsTradeNum()));
		accountTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExchToolsTrade().getBuyerAccount()));

		ictsTradeNumTableColumn.setCellValueFactory(cellData -> {
			if(cellData.getValue().getTradeNum() != null)
				//return new ReadOnlyDoubleWrapper(cellData.getValue().getTradeNum().intValue());
				//return new ReadOnlyIntegerWrapper(cellData.getValue().getTradeNum().intValue());
				return new ReadOnlyStringWrapper(cellData.getValue().getTradeNum().toString());
			return null;
		});

		ictsPortNumTableColumn.setCellValueFactory(cellData -> {
			if(cellData.getValue().getPortNum() != null)
				//return new ReadOnlyDoubleWrapper(cellData.getValue().getPortNum().intValue());
				return new ReadOnlyStringWrapper(cellData.getValue().getPortNum().toString());
			return null;
		});

		tradeTypeTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExchToolsTrade().getTradeType()));

		inputBrokerTableColumn.setCellValueFactory(cellData -> {
			if(cellData.getValue().getExchToolsTrade().getInputAction().trim().equals("BUY"))
				return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getInputBroker());
			return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getAcceptedBroker());
		});

		buyerClearingBrokerTableColumn.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getBuyerClrngBroker());
		});

		sellerClearingBrokerTableColumn.setCellValueFactory(cellData -> {
			return new ReadOnlyStringWrapper(cellData.getValue().getExchToolsTrade().getSellerClrngBroker());
		});

		commentTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExternalCommentOid() != null ? cellData.getValue().getExternalCommentOid().getCommentText() : null));
	}


	/**
	 * ============================================================================================================================================================================
	 * 																																							All Event Handling logic starts here
	 * ============================================================================================================================================================================
	 */

	@FXML
	private void handleStartMonitorButtonClick()
	{
		startMonitoringExternalTrades();
	}

	@FXML
	private void handlePauseMonitorButtonClick()
	{
		pauseMonitoringExternalTrades();
	}

	@FXML
	private void handleStopMonitorButtonClick()
	{
		stopMonitoringExternalTrades();
	}

	private void startMonitoringExternalTrades()
	{
		//acc.setExpandedPane(externalTradeSourcesTitledPane);
		fetchExternalTradesFromDBForTableView();
	}

	private void pauseMonitoringExternalTrades()
	{
		if(fetchExternalTradesScheduledService != null)
		{
			if(fetchExternalTradesScheduledService.isRunning())
			{
				fetchExternalTradesScheduledService.cancel();
				//statusMessagesProperty().set("Task Stopped.");
				//progressStatusesProperty().set(0.0);
			}
		}
	}

	private void stopMonitoringExternalTrades()
	{
		pauseMonitoringExternalTrades();
		externalTrades.clear();

	}

	@FXML
	private void handleReEnterFailedTradeButtonClick()
	{
	}

	@FXML
	private void handleReEnterAllFailedTradesButtonClick()
	{
	}

	public void fetchExternalTradesFromDBForTableView()
	{
		SQLQuery sqlQueryToFetchData = null;

		List<String> selectedExternalTradeSources = getExternalTradeSourcesSelectedByUserFromUI();
		List<String> selectedExternalTradeStatuses = getExternalTradeStatusesSelectedByUserFromUI();
		List<String> selectedExternalTradeStates = getExternalTradeStatesSelectedByUserFromUI();
		List<String> selectedExternalTradeAccounts = getExternalTradeAccountsSelectedByUserFromUI();

		String startDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(startDateDatePicker.getValue());
		String endDate = DateTimeFormatter.ofPattern("dd-MMM-yyyy").format(endDateDatePicker.getValue());

		//SQLQuery sqlQueryStringToFetchExternalTrade = session.createSQLQuery("SELECT et.oid as oid, ett.creation_date as creationDate, et.entry_date as entryDate, et.external_trade_system_oid as externalTradeSystemOid, et.external_trade_status_oid as externalTradeStatusOid, et.external_trade_source_oid as externalTradeSourceOid, et.external_trade_state_oid as externalTradeStateOid, et.trade_num as tradeNum, et.port_num as portNum, ec.comment_text as commentText, ett.exch_tools_trade_num as exchToolsTradeNum, ett.commodity as commodity, ett.trading_period as tradingPeriod, ett.call_put as callPut, ett.strike_price as strikePrice, ett.quantity as quantity, ett.price as price, ett.input_action as inputAction, ett.input_company as inputCompany, ett.input_trader as inputTrader, ett.accepted_action as acceptedAction, ett.accepted_company as acceptedCompany, ett.accepted_trader as acceptedTrader, ett.buyer_account as buyerAccount, ett.trade_type as tradeType ,ett.input_broker as inputBroker, ett.seller_clrng_broker as sellerClearingBroker, ett.buyer_clrng_broker as buyerClearingBroker, ett.accepted_broker as acceptedBroker FROM  dbo.external_trade AS et LEFT OUTER JOIN dbo.external_comment AS ec ON et.external_comment_oid = ec.oid INNER JOIN dbo.exch_tools_trade AS ett ON et.oid = ett.external_trade_oid WHERE (et.external_trade_system_oid IN(1,1)) AND (et.external_trade_source_oid IN (3,1,12)) AND (et.external_trade_status_oid IN (2,3,1,4)) AND(et.external_trade_state_oid IN (1,3,4,2) ) AND (ett.creation_date >= CONVERT(datetime, CONVERT(varchar, '01-Dec-2015 12:00 AM', 109))) AND (ett.creation_date <= CONVERT(datetime,convert(varchar,'06-Mar-2016 11:59 PM',109))) ORDER BY ett.creation_date DESC");

		/**
		 * Variables injected through properties files takes priority over the code. so if we found a variable in the properties file
		 * with value then use that value if not then proceed with the value in the code.
		 */
		if(sqlQueryStringToFetchExternalTradesWithBuyerAccountQualifier == null || sqlQueryStringToFetchExternalTradesWithBuyerAccountQualifier.isEmpty())
		{
			//sqlQueryStringToFetchExternalTradesWithBuyerAccountQualifier = "SELECT et.oid as externalTradeOid, ett.creation_date as creationDate, et.entry_date as entryDate, et.external_trade_system_oid as externalTradeSystemOid, et.external_trade_status_oid as externalTradeStatusOid, et.external_trade_source_oid as externalTradeSourceOid, et.external_trade_state_oid as externalTradeStateOid, et.trade_num as tradeNum, et.port_num as portNum, ec.comment_text as commentText, ett.exch_tools_trade_num as exchToolsTradeNum, ett.commodity as commodity, ett.trading_period as tradingPeriod, ett.call_put as callPut, ett.strike_price as strikePrice, ett.quantity as quantity, ett.price as price, ett.input_action as inputAction, ett.input_company as inputCompany, ett.input_trader as inputTrader, ett.accepted_action as acceptedAction, ett.accepted_company as acceptedCompany, ett.accepted_trader as acceptedTrader, ett.buyer_account as buyerAccount, ett.trade_type as tradeType ,ett.input_broker as inputBroker, ett.seller_clrng_broker as sellerClearingBroker, ett.buyer_clrng_broker as buyerClearingBroker, ett.accepted_broker as acceptedBroker FROM dbo.external_trade AS et LEFT OUTER JOIN dbo.external_comment AS ec ON et.external_comment_oid = ec.oid INNER JOIN dbo.exch_tools_trade AS ett ON et.oid = ett.external_trade_oid WHERE (et.external_trade_source_oid IN (:externalTradeSourcesParam)) AND (et.external_trade_status_oid IN (:externalTradeStatusesParam)) AND (et.external_trade_state_oid IN (:externalTradeStatesParam)) AND (ett.buyer_account IN (:buyerAccountsParam)) AND (ett.creation_date >= (:startDate)) AND (ett.creation_date <= (:endDate)) ORDER BY ett.creation_date DESC";
			sqlQueryStringToFetchExternalTradesWithBuyerAccountQualifier = "SELECT et.* FROM dbo.external_trade AS et LEFT OUTER JOIN dbo.external_comment AS ec ON et.external_comment_oid = ec.oid INNER JOIN dbo.exch_tools_trade AS ett ON et.oid = ett.external_trade_oid WHERE (et.external_trade_source_oid IN (:externalTradeSourcesParam)) AND (et.external_trade_status_oid IN (:externalTradeStatusesParam)) AND (et.external_trade_state_oid IN (:externalTradeStatesParam)) AND (ett.buyer_account IN (:buyerAccountsParam)) AND (ett.creation_date >= (:startDate)) AND (ett.creation_date <= (:endDate)) ORDER BY ett.creation_date DESC";
		}
		if(sqlQueryStringToFetchExternalTradesWithoutBuyerAccountQualifier == null || sqlQueryStringToFetchExternalTradesWithoutBuyerAccountQualifier.isEmpty())
		{
			//sqlQueryStringToFetchExternalTradesWithoutBuyerAccountQualifier = "SELECT et.oid as externalTradeOid, ett.creation_date as creationDate, et.entry_date as entryDate, et.external_trade_system_oid as externalTradeSystemOid, et.external_trade_status_oid as externalTradeStatusOid, et.external_trade_source_oid as externalTradeSourceOid, et.external_trade_state_oid as externalTradeStateOid, et.trade_num as tradeNum, et.port_num as portNum, ec.comment_text as commentText, ett.exch_tools_trade_num as exchToolsTradeNum, ett.commodity as commodity, ett.trading_period as tradingPeriod, ett.call_put as callPut, ett.strike_price as strikePrice, ett.quantity as quantity, ett.price as price, ett.input_action as inputAction, ett.input_company as inputCompany, ett.input_trader as inputTrader, ett.accepted_action as acceptedAction, ett.accepted_company as acceptedCompany, ett.accepted_trader as acceptedTrader, ett.buyer_account as buyerAccount, ett.trade_type as tradeType ,ett.input_broker as inputBroker, ett.seller_clrng_broker as sellerClearingBroker, ett.buyer_clrng_broker as buyerClearingBroker, ett.accepted_broker as acceptedBroker FROM dbo.external_trade AS et LEFT OUTER JOIN dbo.external_comment AS ec ON et.external_comment_oid = ec.oid INNER JOIN dbo.exch_tools_trade AS ett ON et.oid = ett.external_trade_oid WHERE (et.external_trade_source_oid IN (:externalTradeSourcesParam)) AND (et.external_trade_status_oid IN (:externalTradeStatusesParam)) AND(et.external_trade_state_oid IN (:externalTradeStatesParam)) AND (ett.buyer_account NOT IN (:buyerAccountsParam)) AND (ett.creation_date >= (:startDate)) AND (ett.creation_date <= (:endDate)) ORDER BY ett.creation_date DESC";
			sqlQueryStringToFetchExternalTradesWithoutBuyerAccountQualifier = "SELECT et .* FROM dbo.external_trade AS et LEFT OUTER JOIN dbo.external_comment AS ec ON et.external_comment_oid = ec.oid INNER JOIN dbo.exch_tools_trade AS ett ON et.oid = ett.external_trade_oid WHERE (et.external_trade_source_oid IN (:externalTradeSourcesParam)) AND (et.external_trade_status_oid IN (:externalTradeStatusesParam)) AND(et.external_trade_state_oid IN (:externalTradeStatesParam)) AND (ett.buyer_account NOT IN (:buyerAccountsParam)) AND (ett.creation_date >= (:startDate)) AND (ett.creation_date <= (:endDate)) ORDER BY ett.creation_date DESC";
		}

		//selectedExternalTradeSources.forEach(anExternalTradeSourceName -> externalTradeSources.add(externalTradeSourceTableMap.get(a)));
		List<String> externalTradeSources = new ArrayList<String>();
		for(String anExternalTradeSourceName : selectedExternalTradeSources)
		{
			externalTradeSources.add(externalTradeSourceTableMap.get(anExternalTradeSourceName));
		}

		List<String> externalTradeStatuses = new ArrayList<String>();
		for(String anExternalTradeStatusName : selectedExternalTradeStatuses)
		{
			externalTradeStatuses.add(externalTradeStatusTableMap.get(anExternalTradeStatusName));
		}

		List<String> externalTradeStates= new ArrayList<String>();
		for(String anExternalTradeStateName : selectedExternalTradeStates)
		{
			externalTradeStates.add(externalTradeStateTableMap.get(anExternalTradeStateName));
		}

		Session session = HibernateUtil.beginTransaction();
		if(selectedExternalTradeAccounts.contains("Any"))
		{
			sqlQueryToFetchData = session.createSQLQuery(sqlQueryStringToFetchExternalTradesWithoutBuyerAccountQualifier);
			sqlQueryToFetchData.setParameter("buyerAccountsParam", "" );
		}
		else
		{
			sqlQueryToFetchData = session.createSQLQuery(sqlQueryStringToFetchExternalTradesWithBuyerAccountQualifier);
			sqlQueryToFetchData.setParameterList("buyerAccountsParam", selectedExternalTradeAccounts);
		}

		sqlQueryToFetchData.setParameterList("externalTradeSourcesParam", externalTradeSources);
		sqlQueryToFetchData.setParameterList("externalTradeStatusesParam", externalTradeStatuses);
		sqlQueryToFetchData.setParameterList("externalTradeStatesParam", externalTradeStates);
		sqlQueryToFetchData.setParameter("startDate", startDate);
		sqlQueryToFetchData.setParameter("endDate", endDate);

		sqlQueryToFetchData.addEntity(ExternalTrade.class);

		/* This will fetch the data in FX thread which will freeze the UI.  This will run as a one time task but we need it to a recurring one. */
		//dummyExternalTrades = sqlQueryToFetchData.list();

		/* This will fetch the data in a background thread, so UI will not be freezed and user can interact with the UI. */
		/*FetchExternalTradesTask fetchExternalTradesTask = new FetchExternalTradesTask(sqlQueryToFetchData);
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(fetchExternalTradesTask);

		fetchExternalTradesTask.setOnSucceeded(new EventHandler<WorkerStateEvent>()
		{
			@Override
			public void handle(WorkerStateEvent event)
			{
				//dummyExternalTrades = fetchExternalTradesTask.getValue();
				//exchangeTradesTableView.setItems(dummyExternalTrades);

				System.out.println("fetchExternalTradesTask.getProgress() : " + fetchExternalTradesTask.getProgress());
				System.out.println("fetchExternalTradesTask.getMessage() : " + fetchExternalTradesTask.getMessage());

				dummyExternalTrades.clear();
				dummyExternalTrades.addAll(fetchExternalTradesTask.getValue());
			}
		});*/


		/* This will fetch the data in a background thread, so UI will not be freezed and user can interact with the UI. Here a service will invoke the task but for only one time, but we need it to a recurring one.  */
		/*fetchExternalTradesService = new FetchExternalTradesService(sqlQueryToFetchData);
		applicationMainStatusBar.progressProperty().bind(fetchExternalTradesService.progressProperty());
		applicationMainStatusBar.textProperty().bind(fetchExternalTradesService.messageProperty());

		fetchExternalTradesService.restart();

		fetchExternalTradesService.setOnSucceeded(new EventHandler<WorkerStateEvent>()
		{
			@Override
			public void handle(WorkerStateEvent event)
			{
				dummyExternalTrades.clear();
				dummyExternalTrades.addAll(fetchExternalTradesService.getValue());
			}
		});*/


		/* This will fetch the data in a background thread, so UI will not be freezed and user can interact with the UI. Here we use a scheduled service which will invoke the task recurringly. */
		//FetchExternalTradesScheduledService fetchExternalTradesScheduledService = new FetchExternalTradesScheduledService(sqlQueryToFetchData);
		//fetchExternalTradesScheduledService = new FetchExternalTradesScheduledService(sqlQueryToFetchData, Duration.seconds(1), Duration.seconds(10));
		fetchExternalTradesScheduledService.setSQLQuery(sqlQueryToFetchData);
		fetchExternalTradesScheduledService.setDelay(Duration.seconds(1));
		fetchExternalTradesScheduledService.setPeriod(Duration.seconds(10));

		/*
		fetchExternalTradesScheduledService.messageProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> { statusMessagesProperty().set(newValue); });
		fetchExternalTradesScheduledService.progressProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) -> { progressStatusesProperty().set(newValue.doubleValue()); });
		 */
		/*
		 *  modified the above 2 lines as below. previously statusMessagesProperty and progressStatusesProperty are available in the same class but now moved to a different controller.
		 *  Eventually the below code has to be modified to access those properties from the respective controller class.
		 *  Currently accessing the statusMessagesProperty and progressStatusesProperty through the controller whose reference is injected while loading. this may not be the perfect approach,
		 *  need to find out a better way.
		 */
		//fetchExternalTradesScheduledService.messageProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> { mainApplicationStatusBarViewController.statusMessagesProperty().set(newValue); });
		//fetchExternalTradesScheduledService.progressProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) -> { mainApplicationStatusBarViewController.progressStatusesProperty().set(newValue.doubleValue()); });
		
		fetchExternalTradesScheduledService.messageProperty().addListener((ObservableValue<? extends String> observableValue, String oldValue, String newValue) -> { ApplicationHelper.controllersMap.getInstance(MainApplicationStatusBarController.class).statusMessagesProperty().set(newValue); });
		fetchExternalTradesScheduledService.progressProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) -> { ApplicationHelper.controllersMap.getInstance(MainApplicationStatusBarController.class).progressStatusesProperty().set(newValue.doubleValue()); });

		fetchExternalTradesScheduledService.restart();

		fetchExternalTradesScheduledService.setOnSucceeded(new EventHandler<WorkerStateEvent>()
		{
			@Override
			public void handle(WorkerStateEvent event)
			{
				//statusMessagesProperty().set("");
				externalTrades.clear();
				externalTrades.addAll(fetchExternalTradesScheduledService.getValue());
				//dummyExternalTrades.addAll(fetchExternalTradesScheduledService.getLastValue());
				//dummyExternalTrades.addAll(fetchExternalTradesScheduledService.getLastValue() != null ? fetchExternalTradesScheduledService.getLastValue() : fetchExternalTradesScheduledService.getValue());
			}
		});
	}

	public List<String> getExternalTradeSourcesSelectedByUserFromUI()
	{
		return externalTradeSourcesListView.getCheckModel().getCheckedItems();
	}

	public List<String> getExternalTradeStatesSelectedByUserFromUI()
	{
		return externalTradeStatesListView.getCheckModel().getCheckedItems();
	}

	public List<String> getExternalTradeStatusesSelectedByUserFromUI()
	{
		return externalTradeStatusesListView.getCheckModel().getCheckedItems();
	}

	public List<String> getExternalTradeAccountsSelectedByUserFromUI()
	{
		return externalTradeAccountsListView.getCheckModel().getCheckedItems();
	}

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Event Handling logic ends here
	 * ============================================================================================================================================================================
	 */

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Animation logic starts here
	 * ============================================================================================================================================================================
	 */

	private void initializeAnimationsIfNeeded()
	{
		if(isAnimationNeeded())
		{
			//give a glow effect to a button
			final Glow glow = new Glow();
			glow.setLevel(0.0);
			//startMonitorButton.setEffect(glow);

			final Timeline timeline = new Timeline();
			timeline.setCycleCount(Timeline.INDEFINITE);
			timeline.setAutoReverse(true);
			final KeyValue kv = new KeyValue(glow.levelProperty(), 0.3);
			final KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
			timeline.getKeyFrames().add(kf);
			timeline.play();

			//to rotate a component
			final RotateTransition rotate = new RotateTransition(Duration.seconds(2), startMonitorButtonImageView);
			rotate.setFromAngle(0);
			rotate.setByAngle(360);
			rotate.setCycleCount(-1);
			/*rotate.setAutoReverse(true);
			rotate.setCycleCount(Animation.INDEFINITE);
			rotate.setInterpolator(Interpolator.LINEAR);*/
			rotate.play();

			RotateTransition r = new RotateTransition(Duration.seconds(2), exchangeTradesTableView);
			r.setFromAngle(0);
			r.setByAngle(360);
			r.play();

			FadeTransition ft = new FadeTransition(Duration.seconds(2), exchangeTradesTableView);
			ft.setFromValue(1.0);
			ft.setToValue(0.0);
			ft.setCycleCount(2);
			ft.setAutoReverse(true);
			ft.play();
		}
	}

	/**
	 * ============================================================================================================================================================================
	 * 																																							All Animation logic ends here
	 * ============================================================================================================================================================================
	 */

	private boolean isAnimationNeeded()
	{
		return false;
	}
}