import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.type.StringType;

public class App1
{
	public static void main(String[] args)
	{
		 SessionFactory 	sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		System.out.println(session);
		
		session.beginTransaction();
		
		System.out.println(session.isConnected());
		System.out.println(session.isOpen());
		
		SQLQuery q = session.createSQLQuery("select external_trade_state_name from external_trade_state");
		q.addScalar("external_trade_state_name", StringType.INSTANCE);
		List<Object> externalTradeStates = q.list();
		for (Object entity : externalTradeStates)
		{
			System.out.println(entity);
		}
		System.exit(1);
	}
}