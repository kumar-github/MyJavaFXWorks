package gr.persons.dao;

import gr.persons.entities.Person;
import java.math.BigDecimal;

/**
 *
 * @author leonidas
 */
public interface PersonDAO extends GenericDAO<Person, BigDecimal> {

    public Person findByName(String name, String surname);
}
