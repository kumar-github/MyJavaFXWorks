package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentMap;

import org.hibernate.Session;

import com.tc.app.exchangemonitor.util.HibernateUtil;
import com.tc.app.exchangemonitor.util.ReferenceDataCache;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class FuturesController implements Initializable
{
	//@FXML
	//private TreeView ordersTreeView;

	@FXML
	private ComboBox<IctsUser> tradersComboBox;

	@FXML 
	private ComboBox<Account> counterpartiesComboBox;

	@FXML
	private ComboBox<IctsUser> contractAnalystsComboBox;

	@FXML
	private ComboBox<ContractStatus> contractStatusesComboBox;

	@FXML
	private ComboBox<Account> bookingCompaniesComboBox;

	@FXML
	private ComboBox<Account> floorBrokersComboBox;

	@FXML
	private ComboBox<OrderInstruction> orderInstructionComboBox;

	@FXML
	private ComboBox<ExecutionType> executionTypeComboBox;

	@FXML
	private ComboBox<Uom> orderPriceUomsComboBox;

	@FXML
	private ComboBox<Portfolio> portfolioComboBox;

	@FXML
	private ComboBox<Uom> costUomsComboBox;

	@FXML
	private ComboBox<Account> clearingHouseComboBox;

	@FXML
	private ComboBox<Account> clearingServiceComboBox;

	@FXML
	private ComboBox<Account> financingBankComboBox;


	private ObservableList<IctsUser> observableTradersList = FXCollections.observableArrayList();
	private ObservableList<Account> observableCounterPartiesList = FXCollections.observableArrayList();
	private ObservableList<ContractStatus> observableContractStatusesList = FXCollections.observableArrayList();
	private ObservableList<IctsUser> observableContractAnalystsList = FXCollections.observableArrayList();
	private ObservableList<Account> observableBookingCompaniesList = FXCollections.observableArrayList();
	private ObservableList<Account> observableFloorBrokersList = FXCollections.observableArrayList();
	private ObservableList<OrderInstruction> observableOrderInstructionsList = FXCollections.observableArrayList();
	private ObservableList<ExecutionType> observableExecutionTypesList = FXCollections.observableArrayList();
	private ObservableList<Uom> observableOrderPriceUomsList = FXCollections.observableArrayList();
	private ObservableList<Portfolio> observablePortfoliosList = FXCollections.observableArrayList();
	private ObservableList<Uom> observableCostUomsList = FXCollections.observableArrayList();
	private ObservableList<Account> observableClearingHousesList = FXCollections.observableArrayList();
	private ObservableList<Account> observableClearingServicesList = FXCollections.observableArrayList();
	private ObservableList<Account> observableFinancingBanksList = FXCollections.observableArrayList();



	@Override
	public void initialize(URL url, ResourceBundle rb)
	{
		ReferenceDataCache.loadAllReferenceData();
		
		initializeGUIAndConfigureListenersAndInitializeAnimation();
	}

	private void initializeGUIAndConfigureListenersAndInitializeAnimation()
	{
		tradersComboBox.setItems(observableTradersList);
		counterpartiesComboBox.setItems(observableCounterPartiesList);
		contractStatusesComboBox.setItems(observableContractStatusesList);
		contractAnalystsComboBox.setItems(observableContractAnalystsList);
		bookingCompaniesComboBox.setItems(observableBookingCompaniesList);
		floorBrokersComboBox.setItems(observableFloorBrokersList);
		orderInstructionComboBox.setItems(observableOrderInstructionsList);
		executionTypeComboBox.setItems(observableExecutionTypesList);
		orderPriceUomsComboBox.setItems(observableOrderPriceUomsList);
		portfolioComboBox.setItems(observablePortfoliosList);
		costUomsComboBox.setItems(observableCostUomsList);
		clearingHouseComboBox.setItems(observableClearingHousesList);
		clearingServiceComboBox.setItems(observableClearingServicesList);
		financingBankComboBox.setItems(observableFinancingBanksList);

		/*tradersComboBox.setCellFactory(new TraderCellFactory());
		tradersComboBox.setButtonCell(new TraderCellFactory().call(null));
		tradersComboBox.setConverter(new TraderConverter());*/
		
		/* Need to find the diff between setConverter and setButtonCell since both are working similarly  */

		/*tradersComboBox.setCellFactory(new Callback<ListView<IctsUser>, ListCell<IctsUser>>()
		{
			@Override
			public ListCell<IctsUser> call(ListView<IctsUser> param)
			{
				return new ListCell<IctsUser>(){
					@Override
					protected void updateItem(IctsUser item, boolean empty)
					{
						super.updateItem(item, empty);
						if(item == null || empty)
							setText(null);
						else
							setText(item.getUserInit());
					}
				};
			}
		});*/

		/*Callback<ListView<IctsUser>, ListCell<IctsUser>> traderCellFactory = null;
		traderCellFactory = (a) -> {
			return new ListCell<IctsUser>(){
				@Override
				protected void updateItem(IctsUser item, boolean empty)
				{
					super.updateItem(item, empty);

					if(item == null || empty)
						setText(null);
					else
						setText(item.getUserInit());
				}
			};
		};
		tradersComboBox.setCellFactory(traderCellFactory);*/

		fetchTraders();
		fetchCounterParties();
		fetchContractStatuses();
		fetchContractAnalysts();
		fetchBookingCompanies();
		fetchFloorBrokers();
		fetchOrderInstructions();
		fetchExecutionTypes();
		fetchOrderPriceUoms();
		fetchPortfolios();
		fetchCostUoms();
		fetchClearingHouses();
		fetchClearingServices();
		fetchFinancingBanks();
	}

	/*
	public void createTree(String... rootItems)
	{
		TreeItem<String> treeItemRoot = new TreeItem<> ("Root");

		TreeItem<String> nodeItemA = new TreeItem<>("Item A");
		TreeItem<String> nodeItemB = new TreeItem<>("Item B");
		TreeItem<String> nodeItemC = new TreeItem<>("Item C");
		TreeItem<String> nodeItemD = new TreeItem<>("Item D");
		TreeItem<String> nodeItemE = new TreeItem<>("Item E");
		TreeItem<String> nodeItemF = new TreeItem<>("Item F");
		TreeItem<String> nodeItemG = new TreeItem<>("Item G");
		TreeItem<String> nodeItemH = new TreeItem<>("Item H");
		TreeItem<String> nodeItemI = new TreeItem<>("Item I");
		treeItemRoot.getChildren().addAll(nodeItemA, nodeItemB, nodeItemC, nodeItemD, nodeItemE, nodeItemF, nodeItemG, nodeItemH, nodeItemI);

		TreeItem<String> nodeItemA1 = new TreeItem<>("Item A1");
		TreeItem<String> nodeItemA2 = new TreeItem<>("Item A2");
		TreeItem<String> nodeItemA3 = new TreeItem<>("Item A3");
		nodeItemA.getChildren().addAll(nodeItemA1, nodeItemA2, nodeItemA3);

		//create root
		TreeItem<String> root = new TreeItem<>("Root");

		ordersTreeView.setRoot(treeItemRoot);
	}
	 */

	public void fetchTraders()
	{
		//traders = FXCollections.observableArrayList(HibernateReferenceDataFetchUtil.fetchIctsUsersByUserJobTitle("TRADER"));

		ConcurrentMap<String,IctsUser> tempTradersHashMap = ReferenceDataCache.fetchTraders();
		observableTradersList.addAll(tempTradersHashMap.values());

		/*for(ConcurrentMap.Entry<String, IctsUser> anEntry : tempTradersHashMap.entrySet())
		{
		}*/
	}

	public void fetchCounterParties()
	{
		ConcurrentMap<Integer, Account> tempCounterPartiesHashMap = ReferenceDataCache.fetchCounterParties();
		observableCounterPartiesList.addAll(tempCounterPartiesHashMap.values());
	}

	public void fetchContractStatuses()
	{
		ConcurrentMap<String, ContractStatus> tempContractStatusesHashMap = ReferenceDataCache.fetchContractStatuses();
		observableContractStatusesList.addAll(tempContractStatusesHashMap.values());
	}

	public void fetchContractAnalysts()
	{
		ConcurrentMap<String, IctsUser> tempContractAnalystsHashMap = ReferenceDataCache.fetchContractAnalysts();
		observableContractAnalystsList.addAll(tempContractAnalystsHashMap.values());
	}

	public void fetchBookingCompanies()
	{
		ConcurrentMap<Integer, Account> tempBookingCompaniesHashMap = ReferenceDataCache.fetchBookingCompanies();
		observableBookingCompaniesList.addAll(tempBookingCompaniesHashMap.values());
	}

	public void fetchFloorBrokers()
	{
		ConcurrentMap<Integer, Account> tempFloorBrokersHashMap = ReferenceDataCache.fetchFloorBrokers();
		observableFloorBrokersList.addAll(tempFloorBrokersHashMap.values());
	}

	public void fetchOrderInstructions()
	{
		ConcurrentMap<String, OrderInstruction> tempOrderInstructionsHashMap = ReferenceDataCache.fetchOrderInstructions();
		observableOrderInstructionsList.addAll(tempOrderInstructionsHashMap.values());
	}

	public void fetchExecutionTypes()
	{
		ConcurrentMap<String, ExecutionType> tempExecutionTypesHashMap = ReferenceDataCache.fetchExecutionTypes();
		observableExecutionTypesList.addAll(tempExecutionTypesHashMap.values());
	}

	public void fetchOrderPriceUoms()
	{
		ConcurrentMap<String, Uom> tempOrderPriceUomsHashMap = ReferenceDataCache.fetchUoms();
		observableOrderPriceUomsList.addAll(tempOrderPriceUomsHashMap.values());
	}

	public void fetchPortfolios()
	{
		ConcurrentMap<Integer, Portfolio> tempPortfoliosHashMap = ReferenceDataCache.fetchPortfolios();
		observablePortfoliosList.addAll(tempPortfoliosHashMap.values());
	}

	public void fetchCostUoms()
	{
		ConcurrentMap<String, Uom> tempCostUomsHashMap = ReferenceDataCache.fetchUoms();
		observableCostUomsList.addAll(tempCostUomsHashMap.values());
	}

	public void fetchClearingHouses()
	{
		ConcurrentMap<Integer, Account> tempClearingHousesHashMap = ReferenceDataCache.fetchClearingHouses();
		observableClearingHousesList.addAll(tempClearingHousesHashMap.values());
	}

	public void fetchClearingServices()
	{
		ConcurrentMap<Integer, Account> tempClearingServicesHashMap = ReferenceDataCache.fetchClearingServices();
		observableClearingServicesList.addAll(tempClearingServicesHashMap.values());
	}

	public void fetchFinancingBanks()
	{
		ConcurrentMap<Integer, Account> tempFinancingBanksHashMap = ReferenceDataCache.fetchFinancingBanks();
		observableFinancingBanksList.addAll(tempFinancingBanksHashMap.values());
	}

	@FXML
	public void openNewTrade()
	{
		Trade trade = new Trade();
		TradeSync tradeSync = new TradeSync();
		TradeComment tradeComment = new TradeComment();
		Comment comment = new Comment();
		TradeOrder tradeOrder = new TradeOrder();
		FutureTradeItem futureTradeItem = new FutureTradeItem();
		TradeItemFut tradeItemFut = new TradeItemFut();
		
		openExistingTrade();
		
		/*trade.setTradeSync(tradeSync);
		//tradeSync.sett
		trade.setConclusionType('C');
		trade.setConcludedDate(new Date());
		trade.setInvoiceCapType('N');
		trade.setCpGovContrInd('N');
		trade.setInhouseInd('N');
		trade.setCreationDate(new Date());
		//trade.setCreator(null);
		//trade.setCreatorInit("");
		trade.setContrStatusCode(null);
		trade.setContrDate(null);
		trade.setTradeStatusCode(null);
		trade.setMaxOrderNum(Short.valueOf("0"));
		trade.setIsLongTermInd('N');
		Comment c = new Comment();
		TradeComment tc = new TradeComment();
		//tc.setComment(c);
		//tc.tradeCmntType = "T";*/
	}
	
	@FXML
	public void openExistingTrade()
	{
		//2479348
		Session session = HibernateUtil.beginTransaction();
		Trade aTrade = (Trade)session.get(Trade.class, 2479348);
		
		System.out.println(aTrade.getTradeNum());
		System.out.println(aTrade.getTradeStatusCode());
		System.out.println(aTrade.getConclusionType());
		
		//HibernateReferenceDataFetchUtil.fetchTrade();
		System.out.println("..............");
	}
}