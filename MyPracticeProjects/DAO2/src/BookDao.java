import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class BookDao implements BookDaoInterface<Book, String> {

	private Session currentSession;
	
	private Transaction currentTransaction;

	public BookDao() {
	}

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}
	
	public void closeCurrentSession() {
		currentSession.close();
	}
	
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
	
	private static SessionFactory getSessionFactory()
	{
		/*
		Configuration configuration = new Configuration().configure();
		//ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
         
        // builds a session factory from the service registry
        //SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();*/
		
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		//configures settings from hibernate.cfg.xml
		SessionFactory sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	public void persist(Book entity) {
		getCurrentSession().save(entity);
	}

	public void update(Book entity) {
		getCurrentSession().update(entity);
	}

	public Book findById(String id) {
		Book book = (Book) getCurrentSession().get(Book.class, id);
		return book; 
	}

	public void delete(Book entity) {
		getCurrentSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	public List<Book> findAll() {
		List<Book> books = (List<Book>) getCurrentSession().createQuery("from Book").list();
		return books;
	}

	public void deleteAll() {
		List<Book> entityList = findAll();
		for (Book entity : entityList) {
			delete(entity);
		}
	}

}
