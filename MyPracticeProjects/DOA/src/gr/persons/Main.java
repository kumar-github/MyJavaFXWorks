package gr.persons;


import gr.persons.entities.Person;
import gr.persons.session.PersonManager;
import gr.persons.session.PersonManagerImpl;
import java.math.BigDecimal;



/**
 *
 * @author leonidas.patouchas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    	
    	PersonManager personManager = new PersonManagerImpl();
    	Person p = new Person(BigDecimal.valueOf(5), "weada", "dassda", null, "Male");
    	personManager.saveNewPerson(p);
    	
    	
    	
         Person pa = personManager.findPersonById(BigDecimal.valueOf(4));
        Person wanted = personManager.findByPersonName("Steven", "Seagal");


        
        //List<Person> allPersons = personManager.loadAllPersons();
//        Person p = new Person(BigDecimal.valueOf(5), "weada", "dassda", null, "Male");
//        personManager.saveNewPerson(p);
       
        //Session ss = HibernateUtil.getSession();
        String s = "sa";
    }

}
