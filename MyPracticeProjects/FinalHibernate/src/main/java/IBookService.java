import java.math.BigDecimal;
import java.util.List;

public interface IBookService extends IGenericService
{
	public void saveNewBook(Book book);
	public Book findByBookName(String bookName);
	public List<Book> loadAllBooks();
	public Book findBookById(BigDecimal id);
	public void deleteBook(Book book);
	public void deleteAllBook();
	public List<Book> findAll();
	public void update(Book book);
}