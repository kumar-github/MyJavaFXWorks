package com.app;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee
{
	@Id
	private int eno;
	private String ename;
	
	public int getEno()
	{
		return eno;
	}
	
	public void setEno(int eno)
	{
		this.eno = eno;
	}
	
	public String getEname()
	{
		return ename;
	}
	
	public void setEname(String ename)
	{
		this.ename = ename;
	}
}

/*
@Entity
public class Employee
{
	//@Id
	private IntegerProperty eno = new SimpleIntegerProperty();
	private StringProperty ename = new SimpleStringProperty();
	
	public IntegerProperty enoProperty()
	{
		return eno;
	}
	
	public void setEno(int eno)
	{
		this.eno.set(eno);
	}
	
	@Id
	public int getEno()
	{
		return this.eno.get();
	}
	
	public StringProperty enameProperty()
	{
		return ename;
	}
	
	public void setEname(String ename)
	{
		this.ename.set(ename);
	}
	
	public String getEname()
	{
		return this.ename.get();
	}
	
}
*/