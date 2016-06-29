import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;

public class App1
{
	public static void main(String[] args)
	{
		IPersonService personService = new PersonServiceImpl();
		Person p1 = new Person(BigDecimal.valueOf(101), "Abcd", "Efgh", new Date(), "Male");
		personService.saveNewPerson(p1);
		
		Person p2 = new Person(BigDecimal.valueOf(102), "Alpha", "Beta", new Date(), "Female");
		personService.saveNewPerson(p2);
		
		Person temp1 = personService.findPersonById(BigDecimal.valueOf(101));
		System.out.println(temp1.getName());
		Person temp2 = personService.findByPersonName("Alpha", "Beta");
		System.out.println(temp2.getName());
		
		System.out.println("==========================================================================");
		
		List<Person> allPersons = personService.loadAllPersons();
		for(Person aPerson : allPersons)
		{
			System.out.println(aPerson.getId());
		}
		
		System.out.println("==========================================================================");
		
		//SQLQuery q = HibernateUtil.beginTransaction().createSQLQuery("select * from person_details");
		SQLQuery q = HibernateUtil.getSessionFactory().openSession().createSQLQuery("select * from person_details");
	    List<Object[]> entities = q.list();
		
		System.out.println("==========================================================================");
		
		IBookService bookService = new BookServiceImpl();
		Book b1 = new Book(BigDecimal.valueOf(102), "BookName2", "BookAuthor2");
		Book b2 = new Book(BigDecimal.valueOf(103), "BookName3", "BookAuthor3");
		Book b3 = new Book(BigDecimal.valueOf(104), "BookName4", "BookAuthor4");
		bookService.saveNewBook(b1);
		bookService.saveNewBook(b2);
		bookService.saveNewBook(b3);
		List<Book> allBooks = bookService.findAll();
		for (Book aBook : allBooks)
		{
			System.out.println(aBook.toString());
		}
		
		b1.setTitle("The Idiot");
		bookService.update(b1);
		System.out.println("Book Updated is =>" + bookService.findBookById(b1.getId()));
		
		BigDecimal id1 = b1.getId();
		Book anotherBook = bookService.findBookById(id1);
		System.out.println("Book found with id " + id1 + " is =>" + anotherBook.toString());
		
		bookService.deleteBook(anotherBook);
		//System.out.println("Now all books are " + bookService.findAll().size() + ".");
		List<Book> books2 = bookService.findAll();
		System.out.println("Books found are :");
		for (Book b : books2)
		{
			System.out.println("-" + b.toString());
		}
		
		bookService.deleteAllBook();
		System.out.println("Books found are now " + bookService.findAll().size());
		System.exit(0);
	}
}