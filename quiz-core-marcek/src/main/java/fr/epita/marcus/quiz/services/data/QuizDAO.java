package fr.epita.marcus.quiz.services.data;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.epita.marcus.quiz.datamodel.Quiz;

@Repository
public class QuizDAO extends GenericDAO<Quiz> {

	@Override
	public List<Quiz> search(Quiz instance) {
		
		String hql = "from Quiz where (:inputString is null) or (:inputString is not null and name like :inputString) ";
		
		TypedQuery<Quiz> query = this.em.createQuery(hql, this.getType());
		
		String name = instance.getName();
		
		if (name == null) {
			query.setParameter("inputString", null);
		} else {
			query.setParameter("inputString", "%" + name + "%");
		}
		
		return query.getResultList();
	}

	@Override
	public Class<Quiz> getType() {
		return Quiz.class;
	}

	@Override
	public List<Quiz> all() {
		return this.em.createQuery(
			    "from Quiz", this.getType())
			    .getResultList();
	}

}
