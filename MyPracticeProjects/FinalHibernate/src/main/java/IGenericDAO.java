import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;

public interface IGenericDAO<T, ID extends Serializable>
{
	public void save(T entity);
	public void delete(T entity);
	public void update(T entity);
	public void deleteAll();
	public T findById(ID id);
	public T findByID(Class clazz, BigDecimal id);
	public List findAll();
	public List findAll(Class clazz);
	public T findOneByQuery(Query query);
	public List<T> findManyByQuery(Query query);
}