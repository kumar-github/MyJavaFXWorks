package application;

import javafx.beans.property.StringProperty;

public class User
{
	//private String firstName;
	//private String lastName;
	private StringProperty firstName;
	private StringProperty lasttName;
	
	public StringProperty getFirstNameProperty()
	{
		return firstName;
	}
	
	/*public String getLastName()
	{
		return lastName.;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}*/
	public String getFirstName()
	{
		return getFirstNameProperty().get();
	}
	public void setFirstName(String firstName)
	{
		this.getFirstNameProperty().set(firstName);
	}
}