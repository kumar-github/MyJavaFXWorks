import java.math.BigDecimal;

import org.hibernate.Query;

public class PersonDAOImpl extends GenericDAOImpl<Person, BigDecimal> implements IPersonDAO
{
	public Person findPersonByName(String name, String surname)
    {
        Person person = null;
        String sql = "SELECT p FROM Person p WHERE p.name = :name AND p.surname = :surname";
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("name", name).setParameter("surname", surname);
        person = findOneByQuery(query);
        return person;
    }
}