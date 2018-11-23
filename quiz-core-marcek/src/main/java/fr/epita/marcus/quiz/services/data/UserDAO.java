package fr.epita.marcus.quiz.services.data;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.epita.marcus.quiz.datamodel.Identity;
import fr.epita.marcus.quiz.datamodel.User;

@Repository
public class UserDAO extends GenericDAO<User> {
	
	@Inject
	IdentityDAO identityDAO;
	
	@SuppressWarnings("unused")
	private static final Logger log = LogManager.getLogger(UserDAO.class);

	@Override
	public List<User> search(User instance) {
		
		String hql = "from Identity as i where i.lname like :plname";
		
		TypedQuery<User> query = this.em.createQuery(hql, this.getType());
		
		query.setParameter("plname", instance.getIdentity().getLname());
		
		return query.getResultList();
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public User getByEmail(User instance) {
		
		Identity identity = identityDAO.getByEmail(instance.getIdentity());
		
		// Identity
		String hql = "from User as u where u.identity = :pIdentity";
		TypedQuery<User> query = this.em.createQuery(hql, this.getType());
		query.setParameter("pIdentity", identity);
		
		try {
			return query.getSingleResult();
		} catch( NoResultException nre) {
			return null;
		}
		
	}
	
	@Override
	public Class<User> getType() {
		return User.class;
	}

	@Override
	public List<User> all() {
		return this.em.createQuery(
			    "from User", this.getType())
			    .getResultList();
	}
	
}
