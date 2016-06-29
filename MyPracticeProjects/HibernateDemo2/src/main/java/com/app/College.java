package com.app;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class College implements Serializable
{
	private int collegeId;
	private String collegeName;
	
	@Id
	@Column(name="college_id")
	public int getCollegeId()
	{
		return collegeId;
	}
	
	public void setCollegeId(int collegeId)
	{
		this.collegeId = collegeId;
	}
	
	@Column(name="college_name")
	public String getCollegeName()
	{
		return collegeName;
	}
	
	public void setCollegeName(String collegeName)
	{
		this.collegeName = collegeName;
	}
	
}