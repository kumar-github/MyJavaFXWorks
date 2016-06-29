import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;

public class PersonServiceImpl extends GenericServiceImpl  implements IPersonService
{
	private IPersonDAO personDAO = new PersonDAOImpl();
	
	public PersonServiceImpl(){}
	
	public Person findByPersonName(String name, String surname)
	{
		Person person = null;
		try
		{
			HibernateUtil.beginTransaction();
			person = personDAO.findPersonByName(name, surname);
			HibernateUtil.commitTransaction();
		}
		catch (NonUniqueResultException ex)
		{
			System.out.println("Handle your error here");
			System.out.println("Query returned more than one results.");
		}
		catch (HibernateException ex)
		{
			System.out.println("Handle your error here");
		}
		return person;
	}
	
	public List<Person> loadAllPersons()
	{
		List<Person> allPersons = new ArrayList<Person>();
		try
		{
			HibernateUtil.beginTransaction();
			allPersons = personDAO.findAll(Person.class);
			HibernateUtil.commitTransaction();
		}
		catch (HibernateException ex)
		{
			System.out.println("Handle your error here");
		}
		return allPersons;
	}

	public void saveNewPerson(Person person)
	{
		try
		{
			HibernateUtil.beginTransaction();
			personDAO.save(person);
			HibernateUtil.commitTransaction();
		}
		catch (HibernateException ex)
		{
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}
	
	public Person findPersonById(BigDecimal id)
	{
		Person person = null;
		try
		{
			HibernateUtil.beginTransaction();
			person = (Person) personDAO.findByID(Person.class, id);
			HibernateUtil.commitTransaction();
		}
		catch (HibernateException ex)
		{
			System.out.println("Handle your error here");
		}
		return person;
	}

	public void deletePerson(Person person)
	{
		try
		{
			HibernateUtil.beginTransaction();
			personDAO.delete(person);
			HibernateUtil.commitTransaction();
		}
		catch (HibernateException ex)
		{
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}
}