package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StockQuoteOL {
 
    private StringProperty symbol; //   Name of the company
    private StringProperty name; // Name of the company
    private DoubleProperty lastPrice;// The last price of the company's stock
 
    public StockQuoteOL(String symbol, String name, Double lastPrice) {
        super();
        this.symbol = new SimpleStringProperty(symbol);
        this.name = new SimpleStringProperty(name);
        this.lastPrice = new SimpleDoubleProperty(lastPrice);
    }
 
    public StringProperty getSymbol() {
        return symbol;
    }
 
    public void setSymbol(StringProperty symbol) {
        this.symbol = symbol;
    }
 
    public StringProperty getName() {
        return name;
    }
 
    public void setName(StringProperty name) {
        this.name = name;
    }
 
    public DoubleProperty getLastPrice() {
        return lastPrice;
    }
 
    public void setLastPrice(DoubleProperty lastPrice) {
        this.lastPrice = lastPrice;
    }
 
}