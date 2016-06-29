package com.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student
{
	private int sno;
	private String sname;
	private College college;
	
	@Id
	@Column(name="sno")
	public int getSno()
	{
		return sno;
	}
	
	public void setSno(int sno)
	{
		this.sno = sno;
	}
	
	@Column(name="sname")
	public String getSname()
	{
		return sname;
	}
	
	public void setSname(String sname)
	{
		this.sname = sname;
	}
	
	@ManyToOne
	@JoinColumn(name="college_id")
	public College getCollege()
	{
		return college;
	}
	
	public void setCollege(College college)
	{
		this.college = college;
	}
}