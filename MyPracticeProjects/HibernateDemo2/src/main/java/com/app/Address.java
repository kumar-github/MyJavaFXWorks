package com.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address
{
	private int addrNum;
	private String addrName;
	
	public Address()
	{
	}

	@Id
	@Column(name="addr_num", columnDefinition="NUMERIC")
	public int getAddrNum()
	{
		return addrNum;
	}

	public void setAddrNum(int addrNum)
	{
		this.addrNum = addrNum;
	}

	@Column(name="addr_name")
	public String getAddrName()
	{
		return addrName;
	}

	public void setAddrName(String addrName)
	{
		this.addrName = addrName;
	}
}