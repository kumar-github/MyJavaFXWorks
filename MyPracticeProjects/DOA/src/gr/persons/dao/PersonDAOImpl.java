package gr.persons.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;

import gr.persons.entities.Person;
import gr.persons.utils.HibernateUtil;

/**
 *
 * @author leonidas
 */
public class PersonDAOImpl extends GenericDAOImpl<Person, BigDecimal> implements PersonDAO
{

    public Person findByName(String name, String surname) {
        Person person = null;
        String sql = "SELECT p FROM Person p WHERE p.name = :name AND p.surname = :surname";
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("name", name).setParameter("surname", surname);
        person = findOne(query);
        return person;
    }

	@Override
	public List<Person> findMany(Query query)
	{
		return null;
	}

	@Override
	public Person findOne(Query query) {
		// TODO Auto-generated method stub
		return null;
	}
}