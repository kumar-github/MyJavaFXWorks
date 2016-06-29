import java.math.BigDecimal;
import java.util.List;

public interface PersonManager_OLD
{
	public Person findByPersonName(String name, String surname);

	public List<Person> loadAllPersons();

	public void saveNewPerson(Person person);

	public Person findPersonById(BigDecimal id);

	public void deletePerson(Person person);
}