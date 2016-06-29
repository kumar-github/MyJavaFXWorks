package application;

import java.util.ArrayList;
import java.util.List;

public class Company {
 
    private String symbol;
    private String name;
 
    public Company(String symbol, String name) {
        super();
        this.symbol = symbol;
        this.name = name;
    }
    public String getSymbol() {
        return symbol;
    }
    public String getName() {
        return name;
    }
 
    // This method returns the value that is shown in the combobox.
    public String toString() {
        return this.name;
    }
 
    public static List<Company> getListCompanies() {
        List<Company> list = new ArrayList<Company>();
        list.add(new Company("SIRI","SIRIUS XM HLDGS INC COM"));
        list.add(new Company("ABX","BARRICK GOLD CORP COM"));
        list.add(new Company("GE","GENERAL ELECTRIC CO COM"));
        list.add(new Company("TLM","TALISMAN ENERGY INC COM"));
        list.add(new Company("AAPL","APPLE INC COM"));
        list.add(new Company("SPLS","STAPLES INC COM"));
        list.add(new Company("MSFT","MICROSOFT CORP COM"));
        list.add(new Company("PFE","PFIZER INC COM"));
        list.add(new Company("ORCL","ORACLE CORP."));
        list.add(new Company("BAC","BANK AMER CORP COM"));
        list.add(new Company("XOM","EXXON MOBIL CORP COM"));
        list.add(new Company("AA","ALCOA INC COM"));
        list.add(new Company("KO","COCA COLA CO COM"));
        list.add(new Company("C","CITIGROUP INC COM NEW"));
        return list;
    }
}