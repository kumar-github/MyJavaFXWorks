package application;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Duration;
 
public class MainController {
 
    @FXML
    private ComboBox<Company> cbQuote;
 
    @FXML
    private TableView<StockQuoteOL> listQuote;
    private ObservableList<StockQuoteOL> listQuoteObservable = FXCollections.observableArrayList();
    @FXML
    private TableColumn<StockQuoteOL, String> symbolCol;
    @FXML
    private TableColumn<StockQuoteOL, String> companyCol;
    @FXML
    private TableColumn<StockQuoteOL, Number> lastpriceCol;    
 
    private ScheduledService<ObservableList<StockQuoteOL>> service;
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize () {
        // fill up combobox with a list of companies
        ObservableList<Company> listCompaniesObservable = FXCollections.observableArrayList();
        listCompaniesObservable.addAll(Company.getListCompanies());
        cbQuote.setItems(listCompaniesObservable);
        // add a change listener on the combobox
        cbQuote.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Company>() {
            @Override
            public void changed(ObservableValue<? extends Company> observable,
                    Company oldValue, Company newValue) {
                    StockQuote stockQuote = getStockQuote(newValue.getSymbol());
                    listQuoteObservable.add(new StockQuoteOL(stockQuote.getSymbol(),
                            stockQuote.getName(),
                            stockQuote.getLastPrice()) );
                }
            });
        // configure the listView with the stock quotes
        listQuote.setItems(listQuoteObservable);
        symbolCol.setCellValueFactory(cellData -> cellData.getValue().getSymbol());
        companyCol.setCellValueFactory(cellData -> cellData.getValue().getName());
        lastpriceCol.setCellValueFactory(cellData -> cellData.getValue().getLastPrice());
        // define the service to fetch the stock quotes
        service = new PollingService(listQuoteObservable);
        service.maximumFailureCountProperty().set(5);
        service.setPeriod(Duration.seconds(3));
        service.setOnFailed(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                event.getSource().getException().printStackTrace();
                System.out.println(" Error in call : "+event.getSource().getException().getMessage());
            }
        });
            // start the service that will run every 3 seconds
        service.start();
 
    }
 
    class PollingService extends ScheduledService<ObservableList<StockQuoteOL>> {
        private ObservableList<StockQuoteOL> listStockQuotes;
        public PollingService(ObservableList<StockQuoteOL> listStockQuotes) {
            this.listStockQuotes=listStockQuotes;
        }
        @Override
        protected Task<ObservableList<StockQuoteOL>> createTask() {
            return new PollingTask(listStockQuotes);
        }
    }
 
    public class PollingTask extends Task<ObservableList<StockQuoteOL>>
    {
        private ObservableList<StockQuoteOL> listStocks;
            public PollingTask(ObservableList<StockQuoteOL> listStocks)
            {
                this.listStocks = listStocks;
            }
            @Override public ObservableList<StockQuoteOL> call() throws InterruptedException
            {
                for (StockQuoteOL quote : listStocks)
                {
                    quote.getLastPrice().set(getStockQuote(quote.getSymbol().get()).getLastPrice());
                }
                return listStocks;
            }
    }
 
    public static StockQuote getStockQuote(String symbol)
    {
        Client c = Client.create();
        WebResource r = c.resource("http://dev.markitondemand.com/Api/v2/Quote?symbol="+symbol);
        ClientResponse response = r.accept( MediaType.APPLICATION_XML_TYPE).get(ClientResponse.class);
        StockQuote stockQuote =  response.getEntity(new GenericType<StockQuote>() {});
        System.out.println("Result REST call:"+stockQuote);
        return stockQuote;
    }
}