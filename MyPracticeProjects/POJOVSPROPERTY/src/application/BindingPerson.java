package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BindingPerson
{
	private StringProperty firstName = new SimpleStringProperty("firstName");
	private StringProperty lastName = new SimpleStringProperty("lastName");
	
	public BindingPerson() {
	}
	
	public BindingPerson(String firstName, String lastName)
	{
		this.firstName.set(firstName);
		this.lastName.set(lastName);
	}
	
	public String getFirstName() {
		return firstName.get();
	}
	
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	
	public String getLastName() {
		return lastName.get();
	}
	
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	
	public StringProperty firstNameProperty()
	{
		return firstName;
	}
	
	public StringProperty lastNameProperty()
	{
		return lastName;
	}
}