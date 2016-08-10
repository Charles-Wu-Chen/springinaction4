package spittr.db.hibernate4;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import spittr.data.SpittleRepository;
import spittr.Spittle;
@Transactional
@Repository
public class HibernateSpittleRepository implements SpittleRepository {

	private SessionFactory sessionFactory;

	@Inject
	public HibernateSpittleRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();// <co
													// id="co_RetrieveCurrentSession"/>
	}

	public long count() {
		return findAll().size();
	}

	public List<Spittle> findRecent() {
		return findRecent(10);
	}

	public List<Spittle> findRecent(int count) {
		return (List<Spittle>) spittleCriteria().setMaxResults(count).list();
	}

	public Spittle findOne(long id) {
		return (Spittle) currentSession().get(Spittle.class, id);
	}

	public void save(Spittle spittle) {
		Serializable id = currentSession().save(spittle);
	}

	public List<Spittle> findBySpitterId(long spitterId) {
		return spittleCriteria().add(Restrictions.eq("spitter.id", spitterId)).list();
	}

	public void delete(long id) {
		currentSession().delete(findOne(id));
	}

	public List<Spittle> findAll() {
		return (List<Spittle>) spittleCriteria().list();
	}

	private Criteria spittleCriteria() {
		return currentSession().createCriteria(Spittle.class).addOrder(Order.desc("postedTime"));
	}

	@Override
	public List<Spittle> findSpittles(long max, int count) {
		// TODO Auto-generated method stub
		return null;
	}

}
