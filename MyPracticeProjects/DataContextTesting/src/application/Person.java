package application;

import com.sun.javafx.beans.IDProperty;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

public class Person
{
	//private String firstName;
	private StringProperty firstName;
	
	private String lastName;
	//private Address address;
	
	public String getFirstName()
	{
		return firstName.get();
	}
	public void setFirstName(String firstName)
	{
		this.firstName.set(firstName);
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	private ObjectProperty address = new SimpleObjectProperty();
	
	public ObjectProperty addressProperty()
	{
		return this.address;
	}
	
	public void setAddress(Address address)
	{
		this.address.set(address);
	}
	
	public Object getAddress()
	{
		return this.address.get();
	}

	
	/*public Address getAddress()
	{
		return address;
	}
	public void setAddress(Address address)
	{
		this.address = address;
	}*/
	
	@Override
	public String toString()
	{
		return getFirstName() + " " + getLastName() + " " + getAddress().toString();
	}
}