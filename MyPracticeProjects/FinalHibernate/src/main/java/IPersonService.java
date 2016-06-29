import java.math.BigDecimal;
import java.util.List;

public interface IPersonService extends IGenericService
{
	public Person findByPersonName(String name, String surname);
	public List<Person> loadAllPersons();
	public void saveNewPerson(Person person);
	public Person findPersonById(BigDecimal id);
	public void deletePerson(Person person);
}