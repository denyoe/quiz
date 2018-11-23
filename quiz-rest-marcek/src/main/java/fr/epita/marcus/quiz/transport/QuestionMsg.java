package fr.epita.marcus.quiz.transport;

import java.util.HashSet;
import java.util.Set;

import fr.epita.marcus.quiz.datamodel.Question;
import fr.epita.marcus.quiz.datamodel.constant.QuestionType;

public class QuestionMsg {
	
	private Long id;
	
	private String label;
	
	private QuestionType type;
	
	private Set<MCQChoiceMsg> options = new HashSet<>();
	
	/************ Methods ***********/
	
	public QuestionMsg() {}
	
	public QuestionMsg(String label, QuestionType type) {
		this.setLabel(label);
		this.setType(type);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public QuestionType getType() {
		return type;
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public Set<MCQChoiceMsg> getOptions() {
		return options;
	}

	public void setOptions(Set<MCQChoiceMsg> options) {
		this.options = options;
	}
	
	public void addOption(MCQChoiceMsg option) {
		this.options.add(option);
	}
	
	/*************** Convert To Model ***************/
	
	public Question toQuestion() {
		Question question = new Question(this.label, this.type);
		
		for(MCQChoiceMsg choice : this.options) {
			question.addOption(choice.toMCQChoice());
		}
		
		return question;
	}
}
