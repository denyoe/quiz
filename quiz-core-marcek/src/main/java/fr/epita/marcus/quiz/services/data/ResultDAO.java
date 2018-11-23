package fr.epita.marcus.quiz.services.data;

import java.util.Collections;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.epita.marcus.quiz.datamodel.Result;

@Repository
public class ResultDAO extends GenericDAO<Result> {

	@Override
	public List<Result> search(Result instance) {
		
		String hql = "from Result where candidate = :pCandidate";
		
		TypedQuery<Result> query = this.em.createQuery(hql, this.getType());
		
		query.setParameter("pCandidate", instance.getCandidate());

		try {
			return query.getResultList();
		} catch( NoResultException nre) {
			return Collections.emptyList();
		}
		
	}

	@Override
	public Class<Result> getType() {
		return Result.class;
	}

	@Override
	public List<Result> all() {
		// TODO Auto-generated method stub
		return null;
	}

}
