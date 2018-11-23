package fr.epita.marcus.quiz.services.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import fr.epita.marcus.quiz.datamodel.MCQChoice;
import fr.epita.marcus.quiz.datamodel.Question;

@Repository
public class QuestionDAO extends GenericDAO<Question> {

	@Override
	public List<Question> search(Question instance) {
		
		String hql = "from Question where (:inputString is null) or (:inputString is not null and label like :inputString) ";
		
		TypedQuery<Question> query = this.em.createQuery(hql, this.getType());
		
		String questionLabel = instance.getLabel();
		
		if (questionLabel == null) {
			query.setParameter("inputString", null);
		} else {
			query.setParameter("inputString", "%" + questionLabel + "%");
		}
		
		return query.getResultList();
		
	}
	
	/**
	 * Get valid choices for question
	 * @param question
	 * @return
	 */
	public List<MCQChoice> getValidOptions(Question question) {
		
		List<MCQChoice> choices = new ArrayList<>();
		
		for( MCQChoice option : question.getOptions() ) {
			if( option.isValid() ) {
				choices.add(option);
			}
		}
		
		return choices;
	}
	

	@Override
	public Class<Question> getType() {
		return Question.class;
	}

	@Override
	public List<Question> all() {
		return this.em.createQuery(
			    "from Question", this.getType())
			    .getResultList();
	}
	
}
