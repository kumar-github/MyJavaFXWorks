package application;

import java.util.ArrayList;
import java.util.List;

public class BatchService
{
	public List<String> getSuggestions(String fieldName, String searchString) {
 
 
        List<String> list = null;
 
        //HibernateUtil.getSessionFactory().openSession().close();
 
        //Session s = HibernateUtil.getSessionFactory().openSession();
 
        try {
        	
        	list = new ArrayList<String>();
        	list.add("One");
        	list.add("Two");
        	list.add("four");
        	list.add("six");
        	list.add("nine");
        	
 
            //s.beginTransaction();
 
            //Query query = s.createQuery("select B." + fieldName + " from Batch B where B."+fieldName + " LIKE :search");
 
            //list = query.setParameter("search", "%"+searchString+"%").setMaxResults(10).list();
 
            //s.getTransaction().commit();
 
        } catch (Exception ex) {
 
            //logger.error("Other exception {}", ex);
 
        } finally {

            //s.close();
 
        }       
 
        return list;
 
    } 

}
