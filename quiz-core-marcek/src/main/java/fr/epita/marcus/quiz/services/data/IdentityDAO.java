package fr.epita.marcus.quiz.services.data;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.epita.marcus.quiz.datamodel.Identity;

@Repository
public class IdentityDAO extends GenericDAO<Identity> {
	
	@Override
	public List<Identity> search(Identity instance) {
		
		String hql = "from Identity as i where i.lname like :plname or i.fname like :pfname or i.email like :pemail";
		
		TypedQuery<Identity> query = this.em.createQuery(hql, Identity.class);
		
		query.setParameter("plname", instance.getLname());
		
		return query.getResultList();
		
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Identity getByEmail(Identity instance) {
		
		String hql = "from Identity as i where i.email = :pEmail";
		TypedQuery<Identity> query = this.em.createQuery(hql, this.getType());
		query.setParameter("pEmail", instance.getEmail());
		
		try {
			return query.getSingleResult();
		} catch( NoResultException nre) {
			return null;
		}
		
	}
	
	@Override
	public Class<Identity> getType() {
		return Identity.class;
	}

	@Override
	public List<Identity> all() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
