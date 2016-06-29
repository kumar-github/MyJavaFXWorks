import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
	private static final SessionFactory sessionFactory;
	static
	{
		try
		{
			// Create the SessionFactory from standard (hibernate.cfg.xml) config file.
			//sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
			sessionFactory = new Configuration().configure().buildSessionFactory();
			
			/*
			Configuration configuration = new Configuration().configure();
			//ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	         
	        // builds a session factory from the service registry
	        //SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			
			
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			
			final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
			//configures settings from hibernate.cfg.xml
			SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();*/
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
	
	public static void closeSessionFactory()
	{
		sessionFactory.close();
	}

	public static Session beginTransaction()
	{
		Session hibernateSession = HibernateUtil.getSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}
	
	public static void commitTransaction()
	{
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	public static void rollbackTransaction()
	{
		HibernateUtil.getSession().getTransaction().rollback();
	}
	
	public static Session getSession()
	{
		Session hibernateSession = sessionFactory.getCurrentSession();
		return hibernateSession;
	}
	
	public static void closeSession()
	{
		HibernateUtil.getSession().close();
	}
}