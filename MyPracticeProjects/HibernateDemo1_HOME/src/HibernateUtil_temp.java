import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil_temp {
    private static SessionFactory sessionFactory;
   
    static {
        try {
            /*Class.forName("org.hsqldb.jdbcDriver" );
            final Configuration configuration = new Configuration().configure();
            final ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);*/
          //sessionFactory = configuration.buildSessionFactory();
        	sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }
   
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
   
    public static void shutdown() {
        sessionFactory.close();
    }
}