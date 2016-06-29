import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class BookServiceImpl extends GenericServiceImpl  implements IBookService
{
	private IBookDAO bookDAO = null;

	public BookServiceImpl()
	{
		bookDAO = new BookDAOImpl();
	}

	public BookServiceImpl(IBookDAO bookDAO)
	{
		this.bookDAO = bookDAO;
	}

	public void saveNewBook(Book book)
	{
		try
		{
			HibernateUtil.beginTransaction();
			bookDAO.save(book);
			HibernateUtil.commitTransaction();
		}
		catch (HibernateException ex)
		{
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	public void updateBook(Book book)
	{
		try
		{
			HibernateUtil.beginTransaction();
			bookDAO.update(book);
			HibernateUtil.commitTransaction();
		}
		catch (HibernateException ex)
		{
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	public Book findByBookName(String bookName)
	{
		Book book = null;
		try
		{
			HibernateUtil.beginTransaction();
			book = bookDAO.findByName(bookName);
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
		return book;
	}

	public List<Book> loadAllBooks()
	{
		List<Book> allBooks = new ArrayList<Book>();
		try
		{
			HibernateUtil.beginTransaction();
			allBooks = bookDAO.findAll(Book.class);
			HibernateUtil.commitTransaction();
		}
		catch (HibernateException ex)
		{
			System.out.println("Handle your error here");
		}
		return allBooks;
	}


	public Book findBookById(BigDecimal id)
	{
		Book book = null;
		try
		{
			HibernateUtil.beginTransaction();
			book = (Book) bookDAO.findByID(Book.class, id);
			HibernateUtil.commitTransaction();
		}
		catch (HibernateException ex)
		{
			System.out.println("Handle your error here");
		}
		return book;
	}

	public void deleteBook(Book book)
	{
		try
		{
			HibernateUtil.beginTransaction();
			bookDAO.delete(book);
			HibernateUtil.commitTransaction();
		}
		catch (HibernateException ex)
		{
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> findAll()
	{
		HibernateUtil.beginTransaction();
		List<Book> books = (List<Book>)HibernateUtil.getSession().createQuery("from Book").list();
		HibernateUtil.commitTransaction();
		return books;
	}
	public void update(Book book)
	{
		try
		{
			HibernateUtil.beginTransaction();
			bookDAO.update(book);
			HibernateUtil.commitTransaction();
		}
		catch (HibernateException ex)
		{
			System.out.println("Handle your error here");
			HibernateUtil.rollbackTransaction();
		}
	}

	@Override
	public void deleteAllBook()
	{
		List<Book> entityList = findAll();
		for (Book entity : entityList)
		{
			deleteBook(entity);
		}
	}
}