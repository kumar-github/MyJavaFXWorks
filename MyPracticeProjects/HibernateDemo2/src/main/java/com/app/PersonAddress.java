package com.app;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "person_address", catalog = "postgres", schema = "public")
//@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "PersonAddress.findAll", query = "SELECT p FROM PersonAddress p"),
//    @NamedQuery(name = "PersonAddress.findByPersonNum", query = "SELECT p FROM PersonAddress p WHERE p.personAddressPK.personNum = :personNum"),
//    @NamedQuery(name = "PersonAddress.findByPersonAddrNum", query = "SELECT p FROM PersonAddress p WHERE p.personAddressPK.personAddrNum = :personAddrNum"),
//    @NamedQuery(name = "PersonAddress.findByAddrName", query = "SELECT p FROM PersonAddress p WHERE p.addrName = :addrName")})
public class PersonAddress
{
	protected PersonAddressPK personAddressPK;
    private String addrName;
    
    private Person person;

    public PersonAddress() {
    }

    public PersonAddress(PersonAddressPK personAddressPK) {
        this.personAddressPK = personAddressPK;
    }

    public PersonAddress(int personNum, int personAddrNum) {
        this.personAddressPK = new PersonAddressPK(personNum, personAddrNum);
    }

    @EmbeddedId
    public PersonAddressPK getPersonAddressPK() {
        return personAddressPK;
    }

    public void setPersonAddressPK(PersonAddressPK personAddressPK) {
        this.personAddressPK = personAddressPK;
    }

    @Column(name = "addr_name", length = 2147483647)
    public String getAddrName() {
        return addrName;
    }

    public void setAddrName(String addrName) {
        this.addrName = addrName;
    }

    @JoinColumn(name = "person_num", referencedColumnName = "person_num", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    /*@Override
    public int hashCode() {
        int hash = 0;
        hash += (personAddressPK != null ? personAddressPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonAddress)) {
            return false;
        }
        PersonAddress other = (PersonAddress) object;
        if ((this.personAddressPK == null && other.personAddressPK != null) || (this.personAddressPK != null && !this.personAddressPK.equals(other.personAddressPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "generated10.PersonAddress[ personAddressPK=" + personAddressPK + " ]";
    }*/
    
}
