package fr.epita.marcus.quiz.services.business;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.epita.marcus.quiz.datamodel.MCQChoice;
import fr.epita.marcus.quiz.datamodel.Question;
import fr.epita.marcus.quiz.datamodel.Quiz;
import fr.epita.marcus.quiz.services.data.MCQChoiceDAO;
import fr.epita.marcus.quiz.services.data.QuestionDAO;
import fr.epita.marcus.quiz.services.data.QuizDAO;

@Repository
public class QuizManagementService {

	@Inject QuestionDAO questionDAO;
	
	@Inject MCQChoiceDAO mcqDAO;
	
	@Inject QuizDAO quizDAO;
	
	@Transactional
	public void createQuiz(Quiz quiz) {
		this.quizDAO.create(quiz);
	}
	
	@Transactional
	public void createQuiz(Quiz quiz, Set<Question> questions) {
		
		this.quizDAO.create(quiz);
		
		for( Question question : questions ) {
			Set<Quiz> new_set = new HashSet<>();
			new_set.add(quiz);
			question.setQuiz( new_set );
			
			this.questionDAO.create(question);
			
			for( MCQChoice choice : question.getOptions() ) {
				choice.setQuestion(question);
				this.mcqDAO.create(choice);
			}
		}
		
	}
	
//	@Transactional
//	public void createQuiz(Quiz quiz, List<Integer> questions) {
//		
//		this.quizDAO.create(quiz);
//		
//		for( Integer id : questions ) {
//			Question question = this.questionDAO.getById(id);
//			
//			Set<Quiz> new_set = new HashSet<>();
//			new_set.add(quiz);
//			question.setQuiz( new_set );
//			
//			this.questionDAO.create(question);
//			
//			for( MCQChoice choice : question.getOptions() ) {
//				choice.setQuestion(question);
//				this.mcqDAO.create(choice);
//			}
//		}
//		
//	}
	
	public List<Quiz> findQuiz(Quiz instance) {
		return this.quizDAO.search(instance);
	}
	
	public List<Quiz> all() {
		return this.quizDAO.all();
	}
	
	public Quiz byId(Long id) {
		return this.quizDAO.getById(id);
	}
	
	public void deleteById(Long id) {
		quizDAO.delete(this.byId(id));
	}
	
	public void delete(Long id) {
		this.quizDAO.delete(this.quizDAO.getById(id));
	}
	
	public void delete(List<Long> ids) {
		for(Long id: ids) {
			this.quizDAO.delete(this.quizDAO.getById(id));
		}
	}
	
}
