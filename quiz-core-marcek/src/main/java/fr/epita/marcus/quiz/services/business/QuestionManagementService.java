package fr.epita.marcus.quiz.services.business;


import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.epita.marcus.quiz.datamodel.MCQChoice;
import fr.epita.marcus.quiz.datamodel.Question;
import fr.epita.marcus.quiz.services.data.MCQChoiceDAO;
import fr.epita.marcus.quiz.services.data.QuestionDAO;

@Repository
public class QuestionManagementService {
	
	@Inject QuestionDAO questionDAO;
	
	@Inject MCQChoiceDAO mcqDAO;
	
	
	@Transactional
	public void saveQuestion(Question question) {
		
		this.questionDAO.create(question);
		
	}
	
	@Transactional
	public void saveQuestion(Question question, Set<MCQChoice> choices) {
		
		this.questionDAO.create(question);
		
		for( MCQChoice choice : choices ) {
			choice.setQuestion(question);
			this.mcqDAO.create(choice);
		}
		
	}
	
	/**
	 * Get valid options
	 * @param question
	 * @return
	 */
	public List<MCQChoice> getCorrectOptions(Question question) {
		return this.questionDAO.getValidOptions(question);
	}

	public Question getQuestion(Question instance) {
		return this.questionDAO.search(instance).get(0);
	}
	
	public List<Question> all() {
		return this.questionDAO.all();
	}
	
	public Question byId(Long id) {
		return this.questionDAO.getById(id);
	}
	
	public void delete(Long id) {
		this.questionDAO.delete(this.questionDAO.getById(id));
	}
	
	public void delete(List<Long> ids) {
		for(Long id: ids) {
			this.questionDAO.delete(this.questionDAO.getById(id));
		}
	}

}
