import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class MainClass
{
	public static void main(String[] args)
	{
		UserDetails user1 = new UserDetails();
		user1.setUserId(1);
		user1.setUserName("Abcd");
		
		UserDetails user2 = new UserDetails();
		user2.setUserId(2);
		user2.setUserName("Efgh");
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user1);
		session.save(user2);
		session.getTransaction().commit();
		listMessages(session);
	}
	
	//private static void listMessages()
	private static void listMessages(Session session)
	{
		  //Session session2 = HibernateUtil.getSessionFactory().openSession();
		  //Transaction transaction2 = session2.beginTransaction();
		  
		  List<UserDetails> messages = session.createQuery("from UserDetails").list();
		  System.out.println(messages.size() + " message(s) found : ");
		  for (UserDetails msg : messages) {
		   System.out.println(msg.getUserName());
		  }
		  //transaction2.commit();
		  session.close();
		 }
}