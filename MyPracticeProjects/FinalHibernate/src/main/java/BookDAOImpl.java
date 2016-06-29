import java.math.BigDecimal;

import org.hibernate.Query;

public class BookDAOImpl extends GenericDAOImpl<Book, BigDecimal> implements IBookDAO
{
	public Book findByName(String bookName)
    {
        Book book = null;
        String sql = "SELECT b FROM Book b WHERE b.name = :name";
        Query query = HibernateUtil.getSession().createQuery(sql).setParameter("name", bookName);
        book = findOneByQuery(query);
        return book;
    }
}