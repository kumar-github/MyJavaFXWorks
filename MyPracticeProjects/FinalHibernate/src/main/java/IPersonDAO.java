import java.math.BigDecimal;

public interface IPersonDAO extends IGenericDAO<Person, BigDecimal>
{
	public Person findPersonByName(String name, String surname);
}