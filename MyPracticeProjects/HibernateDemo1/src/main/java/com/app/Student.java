package com.app;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*public class Student
{
	private int sno;
	private String sname;
	
	public int getSno()
	{
		return sno;
	}
	
	public void setSno(int sno)
	{
		this.sno = sno;
	}
	
	public String getSname()
	{
		return sname;
	}
	
	public void setSname(String sname)
	{
		this.sname = sname;
	}
}*/

public class Student
{
	private IntegerProperty sno = new SimpleIntegerProperty();
	private StringProperty sname = new SimpleStringProperty();
	
	public int getSno()
	{
		return sno.get();
	}
	
	public void setSno(int sno)
	{
		this.sno.set(sno);
	}
	
	public String getSname()
	{
		return sname.get();
	}
	
	public void setSname(String sname)
	{
		this.sname.set(sname);
	}
	
	public IntegerProperty snoProperty()
	{
		return sno;
	}
	
	public StringProperty snameProperty()
	{
		return sname;
	}
}