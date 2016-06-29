package gr.persons.session;

import gr.persons.entities.Person;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author leonidas
 */
public interface PersonManager {

    public Person findByPersonName(String name, String surname);

    public List<Person> loadAllPersons();

    public void saveNewPerson(Person person);

    public Person findPersonById(BigDecimal id);

    public void deletePerson(Person person);
}
