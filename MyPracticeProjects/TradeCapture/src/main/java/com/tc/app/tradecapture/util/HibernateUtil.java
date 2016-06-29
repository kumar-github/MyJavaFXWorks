package com.tc.app.exchangemonitor.util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil
{
	private static final SessionFactory sessionFactory;
	static
	{
		try
		{
			//Create the SessionFactory from standard (hibernate.cfg.xml) config file.
			//1st method
			/*
			Configuration configuration = new Configuration();
			configuration.configure("/hibernate/hibernate.cfg.xml");
			//configuration.addAnnotatedClass(com.tc.app.exchangemonitor.controller.ExternalTradeStatus.class);
			sessionFactory = configuration.buildSessionFactory();
			*/
			
			//2nd method
			/*sessionFactory = new Configuration().configure("/hibernate/hibernate.cfg.xml").buildSessionFactory();*/

			//3rd method
			final StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure("/hibernate/hibernate.cfg.xml").build();
			sessionFactory = new MetadataSources( standardServiceRegistry ).buildMetadata().buildSessionFactory();
		}
		catch (Throwable ex)
		{
			// Log the exception. 
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}


	public static Session beginTransaction()
	{
		Session hibernateSession = HibernateUtil.getCurrentSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}

	public static void commitTransaction()
	{
		HibernateUtil.getCurrentSession().getTransaction().commit();
	}

	public static void rollbackTransaction()
	{
		HibernateUtil.getCurrentSession().getTransaction().rollback();
	}

	/**
	 * Returns a session from the session context. If there is no session in the context it opens a session, stores it in the context and returns it.
	 * This factory is intended to be used with a hibernate.cfg.xml including the following property.
	 * <property name="hibernate.current_session_context_class">thread</property>
	 * This would return the current open session or if this does not exist, will create a new session
	 * @return session
	 */
	public static Session getCurrentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	/* Opens a session and will not bind it to a session context */
	public static Session openSession()
	{
		return sessionFactory.openSession();
	}

	public static void closeSession()
	{
		Session hibernateSession = HibernateUtil.getCurrentSession();
		if(hibernateSession != null)
			hibernateSession.close();
	}

	public static void closeSessionFactory()
	{
		if(sessionFactory != null)
			sessionFactory.close();
	}
}