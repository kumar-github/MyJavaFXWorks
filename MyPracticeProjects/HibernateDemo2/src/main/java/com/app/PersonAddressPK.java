package com.app;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PersonAddressPK implements Serializable
{
	private int personNum;
    private int personAddrNum;

    public PersonAddressPK() {
    }

    public PersonAddressPK(int personNum, int personAddrNum) {
        this.personNum = personNum;
        this.personAddrNum = personAddrNum;
    }

    @Basic(optional = false)
    @Column(name = "person_num", nullable = false, columnDefinition="NUMERIC")
    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }

    @Basic(optional = false)
    @Column(name = "person_addr_num", nullable = false, columnDefinition="NUMERIC")
    public int getPersonAddrNum() {
        return personAddrNum;
    }

    public void setPersonAddrNum(int personAddrNum) {
        this.personAddrNum = personAddrNum;
    }

    /*@Override
    public int hashCode()
    {
        int hash = 0;
        hash += (personNum != null ? personNum.hashCode() : 0);
        hash += (personAddrNum != null ? personAddrNum.hashCode() : 0);
        return hash;
    	return super.hashCode();
    }

    @Override
    public boolean equals(Object object)
    {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonAddressPK))
        {
            return false;
        }
        PersonAddressPK other = (PersonAddressPK) object;
        if ((this.personNum == null && other.personNum != null) || (this.personNum != null && !this.personNum.equals(other.personNum)))
        {
            return false;
        }
        if ((this.personAddrNum == null && other.personAddrNum != null) || (this.personAddrNum != null && !this.personAddrNum.equals(other.personAddrNum)))
        {
            return false;
        }
        return true;
    	return super.equals(object);
    }

    @Override
    public String toString() {
        return "generated10.PersonAddressPK[ personNum=" + personNum + ", personAddrNum=" + personAddrNum + " ]";
    }*/
    
}