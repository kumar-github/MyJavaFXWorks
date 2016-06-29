package com.app;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Person
{
	private int personNum;
	
	private String personName;
	
	private Collection<PersonAddress> personAddressCollection;
	
	public Person()
	{
	}

	@Id
	@Column(name="person_num", columnDefinition="NUMERIC")
	public int getPersonNum()
	{
		return personNum;
	}

	public void setPersonNum(int personNum)
	{
		this.personNum = personNum;
	}

	@Column(name="person_name")
	public String getPersonName()
	{
		return personName;
	}

	public void setPersonName(String personName)
	{
		this.personName = personName;
	}
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "person", fetch = FetchType.EAGER)
    public Collection<PersonAddress> getPersonAddressCollection() {
        return personAddressCollection;
    }

    public void setPersonAddressCollection(Collection<PersonAddress> personAddressCollection) {
        this.personAddressCollection = personAddressCollection;
    }
}