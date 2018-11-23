package fr.epita.marcus.quiz.services.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericDAO<T> {

	@PersistenceContext
	protected EntityManager em;

	@Transactional(propagation=Propagation.REQUIRED)
	public void update(T instance) {
		em.merge(instance);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(T instance) {
		em.remove(instance);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void create(T instance) {
		em.persist(instance);

	}
	
	public abstract List<T> all();

	public abstract List<T> search(T instance);
	
	public T getById(Serializable id) {
		return em.find(getType(), id);
	}

	public abstract Class<T> getType();

}