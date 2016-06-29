import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public abstract class GenericDAOImpl<T, ID extends Serializable> implements IGenericDAO<T, ID>
{
	protected Session getSession()
	{
		return HibernateUtil.getSession();
	}
	
	@Override
	public void save(T entity)
	{
		Session hibernateSession = this.getSession();
		hibernateSession.saveOrUpdate(entity);
		//hibernateSession.save(entity);
	}

	@Override
	public void delete(T entity)
	{
		Session hibernateSession = this.getSession();
		hibernateSession.delete(entity);
	}

	@Override
	public void update(T entity)
	{
		Session hibernateSession = this.getSession();
		hibernateSession.update(entity);
	}
	
	@Override
	public void deleteAll()
	{
	}

	@Override
	public T findById(ID id)
	{
		T t = null;
		Session hibernateSession = this.getSession();
		t = (T)hibernateSession.get("", id);
		return t;
	}
	
	@Override
	public T findByID(Class clazz, BigDecimal id)
	{
		T t = null;
		Session hibernateSession = this.getSession();
		t = (T) hibernateSession.get(clazz, id);
		return t;
	}

	@Override
	public List findAll()
	{
		Session hibernateSession = this.getSession();
		List T = null;
		Query query = hibernateSession.createQuery("from " + null);
		T = query.list();
		return T;
	}
	
	@Override
	public List findAll(Class clazz)
	{
		Session hibernateSession = this.getSession();
		List T = null;
		Query query = hibernateSession.createQuery("from " + clazz.getName());
		T = query.list();
		return T;
	}

	@Override
	public T findOneByQuery(Query query)
	{
		T t;
		t = (T) query.uniqueResult();
		return t;
	}

	@Override
	public List<T> findManyByQuery(Query query)
	{
		List<T> t;
		t = (List<T>) query.list();
		return t;
	}
}