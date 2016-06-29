import java.math.BigDecimal;

public interface IBookDAO extends IGenericDAO<Book, BigDecimal>
{
	public Book findByName(String bookName);
}