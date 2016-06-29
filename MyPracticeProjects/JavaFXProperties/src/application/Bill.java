package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Bill
{
	private DoubleProperty amountDue= new SimpleDoubleProperty();
	
	public final void setAmountDue(double amountDue)
	{
		this.amountDue.set(amountDue);
	}
	
	public final double getAmountDue()
	{
		return this.amountDue.get();
	}
	
	public DoubleProperty amountDueProperty()
	{
		return this.amountDue;
	}
}