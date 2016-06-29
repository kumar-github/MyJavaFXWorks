package com.app;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainClass 
{
    public static void main( String[] args )
    {
/*    	Employee employee1 = new Employee();
    	employee1.setEno(102);
    	employee1.setEname("Efgh");
    	
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	
    	session.save(employee1);
    	
    	session.getTransaction().commit();*/
    	
    	
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	
/*    	Person aPerson1 = new Person();
    	aPerson1.setPersonNum(501);
    	aPerson1.setPersonName("Some Person");
    	
    	Address anAddress1 = new Address();
    	anAddress1.setAddrNum(201);
    	anAddress1.setAddrName("Some Address");
    	
    	session.save(aPerson1);
    	session.save(anAddress1);*/
    	
    	Person aPerson2 = new Person();
    	aPerson2.setPersonNum(1002);
    	aPerson2.setPersonName("B");
    	
    	PersonAddress aPersonAddress1 = new PersonAddress(1002, 3);
    	aPersonAddress1.setAddrName("B Address3");
    	PersonAddress aPersonAddress2 = new PersonAddress(1002, 4);
    	aPersonAddress2.setAddrName("B Address4");
    	
    	Collection<PersonAddress> personAddressCollection = new ArrayList<PersonAddress>();
    	personAddressCollection.add(aPersonAddress1);
    	personAddressCollection.add(aPersonAddress2);
    	
    	aPerson2.setPersonAddressCollection(personAddressCollection);
    	
    	session.save(aPerson2);
    	//session.save(aPersonAddress1);
    	//session.save(aPersonAddress2);
    	
    	session.getTransaction().commit();
    	
    	System.exit(0);
    }
}