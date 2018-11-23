package fr.epita.marcus.quiz.services.data;

import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.epita.marcus.quiz.datamodel.MCQChoice;

@Repository
public class MCQChoiceDAO extends GenericDAO<MCQChoice> {

	@Override
	public List<MCQChoice> search(MCQChoice instance) {
		
		String hql = "from MCQChoice where question = :pQuestion";
		
		TypedQuery<MCQChoice> query = this.em.createQuery(hql, this.getType());
		
		query.setParameter("pQuestion", instance.getQuestion());

		try {
			return query.getResultList();
		} catch( NoResultException nre) {
			return Collections.emptyList();
		}
		
	}

	@Override
	public Class<MCQChoice> getType() {
		return MCQChoice.class;
	}

	@Override
	public List<MCQChoice> all() {
		// TODO Auto-generated method stub
		return null;
	}

}
