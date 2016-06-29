package application;

public class Address
{
	private String streetName;

	public String getStreetName()
	{
		return streetName;
	}

	public void setStreetName(String streetName)
	{
		this.streetName = streetName;
	}
	
	@Override
	public String toString()
	{
		return getStreetName();
	}
}