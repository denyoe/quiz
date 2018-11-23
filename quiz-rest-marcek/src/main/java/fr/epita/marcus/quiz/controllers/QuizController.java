package fr.epita.marcus.quiz.controllers;

import java.util.Collection;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.epita.marcus.quiz.Utils.Map;
import fr.epita.marcus.quiz.datamodel.Question;
import fr.epita.marcus.quiz.datamodel.Quiz;
import fr.epita.marcus.quiz.services.business.QuestionManagementService;
import fr.epita.marcus.quiz.services.business.QuizManagementService;
import fr.epita.marcus.quiz.transport.QuestionMsg;
import fr.epita.marcus.quiz.transport.QuizMsg;
import fr.epita.marcus.quiz.transport.QuizMsgWithIds;

@RestController
public class QuizController {
	
	static final String PATH = "/quiz";
	
	@Inject QuizManagementService qs;
	@Inject QuestionManagementService qms;

	@RequestMapping(value = PATH, method = RequestMethod.GET)
    public Collection<QuizMsg> all() {
        return Map.fromListQuiz(qs.all());
    }
	
	
	@RequestMapping(value = PATH + "/withObj", method = RequestMethod.POST)
	public QuizMsg save(@RequestBody QuizMsg qm) {
		Quiz quiz = qm.toQuiz();
		
		this.qs.createQuiz(quiz, quiz.getQuestions());
		
		return Map.fromQuiz(quiz);
	}
	
	@RequestMapping(value = PATH, method = RequestMethod.POST)
	public QuizMsg save(@RequestBody QuizMsgWithIds qm) {
		Quiz quiz = fromQuizMsgWithIds(qm);
		
		this.qs.createQuiz(quiz, quiz.getQuestions());
		
		return Map.fromQuiz(quiz);
	}
	
	@RequestMapping(
			value = PATH + "/{id}",
			method = RequestMethod.GET)
	public QuizMsg ById(
			@PathVariable("id") Long id) {
		
		Quiz quiz = this.qs.byId(id);
		
		if( quiz != null ) {
			return Map.fromQuiz(quiz);
		}
		
		return null;
	}
	
	@RequestMapping(
			value = PATH + "/{id}/questions",
			method = RequestMethod.GET)
	public Collection<QuestionMsg> questions(
			@PathVariable("id") Long id) {
		
		Quiz quiz = this.qs.byId(id);
		
		if( quiz != null ) {
			Set<Question> questions = this.qs.byId(id).getQuestions();
			
			return Map.fromListQuestion(questions);
		}
		
		return null;
	}
	
	public Quiz fromQuizMsgWithIds(QuizMsgWithIds instance) {
		Quiz quiz = new Quiz(instance.getName());
		
		quiz.setShuffleQuestions(instance.getShuffleQuestions());
		quiz.setShuffleAnswers(instance.getShuffleAnswers());
		
		for(Long id : instance.getQuestions()) {
			Question question = qms.byId(id);
			if( question != null ) {
				quiz.addQuestion(question);
			}
		}

		return quiz;
	}
	
	@RequestMapping(
			value = PATH + "/{id}",
			method = RequestMethod.DELETE)
	public void delete(
			@PathVariable Long id) {
		this.qs.delete(id);
	}
	

}
