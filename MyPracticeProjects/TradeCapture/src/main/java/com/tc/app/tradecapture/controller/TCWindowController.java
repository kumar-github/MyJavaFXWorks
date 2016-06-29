package com.tc.app.exchangemonitor.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;

import com.tc.app.exchangemonitor.util.HibernateUtil;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class TCWindowController implements Initializable
{
	@FXML
	BorderPane tcWindowBorderPane;
	
	@FXML
	ComboBox<String> commoditiesComboBox;

	@FXML
	ComboBox<String> tradersComboBox;

	@FXML
	ComboBox<String> counterpartiesComboBox;
	
	@FXML
	ComboBox<String> motsComboBox;
	
	@FXML
	ComboBox<String> deliveryTermsComboBox;
	
	@FXML
	ComboBox<String> riskMarketsComboBox;

	@FXML
	ComboBox<String> riskPeriodsComboBox;

	@FXML
	ComboBox<String> titleTransferLocationsComboBox;

	@FXML
	ComboBox<String> portfoliosComboBox;
	
	@FXML
	ComboBox<String> bookingCompaniesComboBox;

	@FXML TableColumn<DummyTradeItem, Number> tradeNumColumn;

	@FXML TableColumn<DummyTradeItem, Number> orderNumColumn;

	@FXML TableColumn<DummyTradeItem, Number> itemNumColumn;

	@FXML TableColumn<DummyTradeItem, String> itemTypeColumn;

	@FXML TableColumn<DummyTradeItem, String> psIndolumn;

	@FXML TableColumn<DummyTradeItem, Number> bookingCompNumColumn;

	@FXML TableColumn<DummyTradeItem, String> commodityCodeColumn;

	@FXML TableColumn<DummyTradeItem, Number> avgPriceColumn;

	@FXML TableColumn<DummyTradeItem, String> priceCurrCodeColumn;

	@FXML TableColumn<DummyTradeItem, String> priceUomCodeColumn;

	@FXML TableColumn<DummyTradeItem, String> contrQtyUomCodeColumn;

	@FXML TableColumn<DummyTradeItem, String> riskMktCodeColumn;

	@FXML TableColumn<DummyTradeItem, String> titleMktCodeColumn;

	@FXML TableColumn<DummyTradeItem, String> tradingPrdColumn;

	@FXML TableView<DummyTradeItem> tradeItemsTableView;

	@FXML Button tradeSearchButton;
	
	private ObservableList<DummyTradeItem> tradeItems = FXCollections.observableArrayList();

	@FXML
    private void initialize() 
    {
    }
    
    public TCWindowController()
    {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
    	//tradeItemsTableView.setItems(tradeItems);
    	initializeGUIAndConfigureListenersAndInitializeAnimation();
    	
    	tradeNumColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getTradeNum().intValue()));
    	orderNumColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getOrderNum().intValue()));
    	itemNumColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getItemNum().intValue()));
    	itemTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getItemType().toString()));
    	psIndolumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getpsInd().toString()));
    	bookingCompNumColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getBookingCompNum().intValue()));
    	avgPriceColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getAvgPrice() != null ? cellData.getValue().getAvgPrice().intValue() : 0));
    	commodityCodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCmdtyCode()));
    	priceCurrCodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPriceCurrCode()));
    	priceUomCodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPriceUomCode()));
    	contrQtyUomCodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContrQtyUomCode()));
    	riskMktCodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRiskMktCode()));
    	titleMktCodeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitleMktCode()));
    	tradingPrdColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTradingPrd()));
    }

	private void initializeGUIAndConfigureListenersAndInitializeAnimation()
	{
		//fetchTradeItemsFromDB("");
		//tradeItemsTableView.setItems(tradeItems);
		//tradeItemsTableView.setItems(FXCollections.observableArrayList(fetchTradeItemsFromDB("")));
		
		List<String> commoditiesList = fetchActiveCommoditiesFromDB();
		commoditiesComboBox.setItems(FXCollections.observableArrayList(commoditiesList));
		List<String> tradersList = fetchActiveTradersFromDB();
		tradersComboBox.setItems(FXCollections.observableArrayList(tradersList));
		List<String> counterpartiesList = fetchActiveCounterpartiesFromDB();
		counterpartiesComboBox.setItems(FXCollections.observableArrayList(counterpartiesList));
		List<String> motsList = fetchActiveMOTsFromDB();
		motsComboBox.setItems(FXCollections.observableArrayList(motsList));
		List<String> deliveryTermsList = fetchDeliveryTermCodesFromDB();
		deliveryTermsComboBox.setItems(FXCollections.observableArrayList(deliveryTermsList));
		List<String> riskMarketsList = fetchDeliveryTermCodesFromDB();
		riskMarketsComboBox.setItems(FXCollections.observableArrayList(riskMarketsList));
		List<String> riskPeriodsList = fetchRiskPeriodsFromDB();
		riskPeriodsComboBox.setItems(FXCollections.observableArrayList(riskPeriodsList));
		List<String> titleTransferLocationsList = fetchActiveTitleTransferLocationsFromDB();
		titleTransferLocationsComboBox.setItems(FXCollections.observableArrayList(titleTransferLocationsList));
		List<String> portfoliosList = fetchPortfoliosFromDB();
		portfoliosComboBox.setItems(FXCollections.observableArrayList(portfoliosList));
		List<String> bookingCompaniesList = fetchActiveBookingCompaniesFromDB();
		bookingCompaniesComboBox.setItems(FXCollections.observableArrayList(bookingCompaniesList));
		List<String> paymentTermsList = fetchPaymentTermsFromDB();
		//paymentTermsComboBox.setItems(FXCollections.observableArrayList(paymentTermsList));
		
		//tradersComboBox.setId("font-button");
		//tradersComboBox.setStyle("-fx-font: bold italic 12px \"Roboto\";");
		tradersComboBox.setStyle("-fx-font: bold 12px \"Roboto\";");
	}
	
	public List<String> fetchDataFromDB(String sqlQueryString, String scalarColumn)
	{
		Session session = HibernateUtil.beginTransaction();

		SQLQuery sqlQueryToFetchData = session.createSQLQuery(sqlQueryString);
		sqlQueryToFetchData.addScalar(scalarColumn, StringType.INSTANCE);
		return sqlQueryToFetchData.list();
	}
	
	//public List fetchTradeItemsFromDB(String sqlQueryString)
	public ObservableList<DummyTradeItem> fetchTradeItemsFromDB(String sqlQueryString)
	{
		Session session = HibernateUtil.beginTransaction();
		
		SQLQuery sqlQueryToFetchData = session.createSQLQuery("SELECT TOP 100 ti.trade_num as tradeNum, ti.order_num as orderNum, ti.item_num as itemNum, ti.item_type as itemType, ti.p_s_ind as psInd, ti.booking_comp_num  as bookingCompNum, ti.cmdty_code as cmdtyCode, ti.avg_price as avgPrice, ti.price_curr_code as priceCurrCode, ti.price_uom_code as priceUomCode, ti.contr_qty_uom_code as contrQtyUomCode, ti.risk_mkt_code as riskMktCode, ti.title_mkt_code as titleMktCode,  ti.trading_prd as tradingPrd from trade_item ti ORDER BY trade_num desc");
		//SQLQuery sqlQueryToFetchData = session.createSQLQuery("SELECT TOP 100 ti.trade_num as tradeNum, ti.order_num as orderNum, ti.item_num as itemNum, ti.item_type as itemType, ti.p_s_ind as psInd from trade_item ti ORDER BY trade_num desc");
		//return sqlQueryToFetchData.list();
		sqlQueryToFetchData.setResultTransformer(Transformers.aliasToBean(com.tc.app.exchangemonitor.controller.DummyTradeItem.class));
		//return sqlQueryToFetchData.list();
		List<DummyTradeItem> tradeItems = new ArrayList<DummyTradeItem>();
		tradeItems = sqlQueryToFetchData.list();
		return FXCollections.observableArrayList(tradeItems);
	}
	
	public List<String> fetchActiveCommoditiesFromDB()
	{
		return fetchDataFromDB("SELECT distinct(c.cmdty_code) as commodityCode from commodity c where c.cmdty_status = 'A' and c.cmdty_type in ('P', 'S') ORDER BY c.cmdty_code", "commodityCode");
	}
	
	public List<String> fetchActiveTradersFromDB()
	{
		return fetchDataFromDB("SELECT distinct(iu.user_init) as traderUserInit from icts_user iu where iu.user_status = 'A' and iu.user_job_title = 'TRADER' ORDER BY iu.user_init", "traderUserInit");
	}
	
	public List<String> fetchActiveCounterpartiesFromDB()
	{
		return fetchDataFromDB("SELECT distinct(a.acct_short_name) as counterpartyShortName from account a where a.acct_status = 'A' and a.acct_type_code = 'CUSTOMER' ORDER BY a.acct_short_name", "counterpartyShortName");
	}
	
	public List<String> fetchActiveMOTsFromDB()
	{
		return fetchDataFromDB("SELECT distinct(m.mot_short_name) as motShortName from mot m where m.mot_status = 'A' ORDER BY m.mot_short_name", "motShortName");
	}
	
	public List<String> fetchDeliveryTermCodesFromDB()
	{
		return fetchDataFromDB("SELECT distinct(dt.del_term_code) as deliveryTermCode from delivery_term dt ORDER BY dt.del_term_code", "deliveryTermCode");
	}
	
	public List<String> fetchActiveRiskMarketsFromDB()
	{
		return fetchDataFromDB("SELECT distinct(mkt.mkt_short_name) as riskMarketShortName from market mkt where mkt.mkt_status = 'A' ORDER BY mkt.mkt_short_name", "riskMarketShortName");
	}
	
	public List<String> fetchRiskPeriodsFromDB()
	{
		return fetchDataFromDB("SELECT distinct(tp.trading_prd) as riskPeriod from trading_period tp ORDER BY tp.trading_prd", "riskPeriod");
	}
	
	public List<String> fetchActiveTitleTransferLocationsFromDB()
	{
		return fetchDataFromDB("SELECT distinct(loc.loc_name) as titleTransferLocation from location loc where loc.loc_status = 'A' ORDER BY loc.loc_name", "titleTransferLocation");
	}
	
	public List<String> fetchPortfoliosFromDB()
	{
		return fetchDataFromDB("SELECT distinct(p.port_short_name) as portfolioShortName from portfolio p where p.port_type = 'R' ORDER BY p.port_short_name", "portfolioShortName");
	}
	
	public List<String> fetchActiveBookingCompaniesFromDB()
	{
		return fetchDataFromDB("SELECT distinct(a.acct_short_name) as bookingCompanyShortName from account a where a.acct_status = 'A' and a.acct_type_code != 'PEICOMP' ORDER BY a.acct_short_name", "bookingCompanyShortName");
	}
	
	public List<String> fetchPaymentTermsFromDB()
	{
		return fetchDataFromDB("SELECT distinct(pt.pay_term_code) as paymentTermCode from payment_term pt ORDER BY pt.pay_term_code", "paymentTermCode");
	}

	@FXML public void handleTradeSearch()
	{
		//tradeItems = FXCollections.observableArrayList(fetchTradeItemsFromDB(""));
		tradeItems = fetchTradeItemsFromDB("");
		tradeItemsTableView.setItems(tradeItems);
	}
}