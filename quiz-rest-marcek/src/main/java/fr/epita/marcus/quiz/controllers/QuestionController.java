package fr.epita.marcus.quiz.controllers;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.epita.marcus.quiz.Utils.Map;
import fr.epita.marcus.quiz.datamodel.Question;
import fr.epita.marcus.quiz.services.business.QuestionManagementService;
import fr.epita.marcus.quiz.transport.QuestionMsg;

@RestController
public class QuestionController {
	
	static final String PATH = "/questions";
	
	@Inject QuestionManagementService qs;

	@RequestMapping(value = PATH, method = RequestMethod.GET)
    public Collection<QuestionMsg> all() {
		List<Question> question = qs.all();
		
		return Map.fromListQuestion(question);
    }
	
	@RequestMapping(value = PATH, method = RequestMethod.POST)
	public QuestionMsg save(@RequestBody QuestionMsg qm) {
		Question question = qm.toQuestion();
		
		this.qs.saveQuestion(question, question.getOptions());
		
		return Map.fromQuestion(question);
	}
	
	@RequestMapping(
			value = PATH + "/{id}",
			method = RequestMethod.GET)
	public QuestionMsg ById(
			@PathVariable("id") Long id) {
		
		Question question = this.qs.byId(id);
		
		if( question != null ) {
			return Map.fromQuestion(question);
		}
		
		return null;
	}
	
	@RequestMapping(
			value = PATH + "/{id}",
			method = RequestMethod.DELETE)
	public void delete(
			@PathVariable Long id) {
		this.qs.delete(id);
	}
	
	@RequestMapping(
			value = PATH + "/batch/{ids}",
			method = RequestMethod.DELETE)
	public void batchDelete(
			@PathVariable List<Long> ids) {
		this.qs.delete(ids);
		
	}

}
