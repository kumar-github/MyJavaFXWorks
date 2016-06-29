package com.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainClass 
{
    public static void main( String[] args )
    {
    	Student student1 = new Student();
    	student1.setSno(101);
    	student1.setSname("Abcd");
    	
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	
    	session.save(student1);
    	
    	session.getTransaction().commit();
    }
}