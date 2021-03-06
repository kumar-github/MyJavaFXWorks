
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class TestExample
{

  //final static Logger logger = LoggerFactory.getLogger(TestExample.class);

  /**
   * @param args
   */
  public static void main(String[] args) {
    Honey forestHoney = new Honey();
    forestHoney.setId(1);
    forestHoney.setName("forest honey");
    forestHoney.setTaste("very sweet");
    Honey countryHoney = new Honey();
    countryHoney.setId(2);
    countryHoney.setName("country honey");
    countryHoney.setTaste("tasty");
    createHoney(forestHoney);
    createHoney(countryHoney);
    // our instances have a primary key now:
    //logger.debug("{}", forestHoney);
    //logger.debug("{}", countryHoney);
    listHoney();
    deleteHoney(countryHoney);
    listHoney();
    forestHoney.setName("Norther Forest Honey");
    updateHoney(forestHoney);

  }

  private static void listHoney() {
    Transaction tx = null;
    Session session = HibernateUtil.getInstance().getCurrentSession();
    try {
      tx = session.beginTransaction();
      List honeys = session.createQuery("select h from Honey as h")
              .list();
      for (Iterator iter = honeys.iterator(); iter.hasNext();) {
        Honey element = (Honey) iter.next();
        //logger.debug("{}", element);
        System.out.println(element);
      }
      tx.commit();
    } catch (RuntimeException e) {
      //if (tx != null && tx.isActive())
    	if (tx != null && tx.getStatus() == TransactionStatus.ACTIVE)
      {
        try {
// Second try catch as the rollback could fail as well
          tx.rollback();
        } catch (HibernateException e1) {
          //logger.debug("Error rolling back transaction");
        }
// throw again the first exception
        throw e;
      }


    }
  }

  private static void deleteHoney(Honey honey) {
    Transaction tx = null;
    Session session = HibernateUtil.getInstance().getCurrentSession();
    try {
      tx = session.beginTransaction();
      session.delete(honey);
      tx.commit();
    } catch (RuntimeException e) {
    	if (tx != null && tx.getStatus() == TransactionStatus.ACTIVE){
        try {
// Second try catch as the rollback could fail as well
          tx.rollback();
        } catch (HibernateException e1) {
          //logger.debug("Error rolling back transaction");
        }
// throw again the first exception
        throw e;
      }
    }
  }

  private static void createHoney(Honey honey) {
    Transaction tx = null;
    Session session = HibernateUtil.getInstance().getCurrentSession();
    try {
      tx = session.beginTransaction();
      session.save(honey);
      tx.commit();
    } catch (RuntimeException e) {
    	if (tx != null && tx.getStatus() == TransactionStatus.ACTIVE){
        try {
// Second try catch as the rollback could fail as well
          tx.rollback();
        } catch (HibernateException e1) {
          //logger.debug("Error rolling back transaction");
        }
// throw again the first exception
        throw e;
      }
    }
  }

   private static void updateHoney(Honey honey) {
    Transaction tx = null;
    Session session = HibernateUtil.getInstance().getCurrentSession();
    try {
      tx = session.beginTransaction();
      session.update(honey);
      tx.commit();
    } catch (RuntimeException e) {
    	if (tx != null && tx.getStatus() == TransactionStatus.ACTIVE){
        try {
// Second try catch as the rollback could fail as well
          tx.rollback();
        } catch (HibernateException e1)
        {
          //logger.debug("Error rolling back transaction");
        }
// throw again the first exception
        throw e;
      }
    }
  }
}