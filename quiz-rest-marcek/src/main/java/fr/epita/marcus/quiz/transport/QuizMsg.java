package fr.epita.marcus.quiz.transport;

import java.util.HashSet;
import java.util.Set;

import fr.epita.marcus.quiz.datamodel.Quiz;


public class QuizMsg {
	
	private Long id;
	
	private String name;
	
	/**
	 * Formatted Author Name
	 */
	private String uid;
	
	private Boolean shuffleQuestions = false;
	private Boolean shuffleAnswers = false;
	
	private Set<QuestionMsg> questions = new HashSet<>();
	
	/************ Methods ***********/
	
	public QuizMsg() {}
	
	public QuizMsg(String name) {
		this.setName(name);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Boolean getShuffleQuestions() {
		return shuffleQuestions;
	}

	public void setShuffleQuestions(Boolean shuffleQuestions) {
		this.shuffleQuestions = shuffleQuestions;
	}

	public Boolean getShuffleAnswers() {
		return shuffleAnswers;
	}

	public void setShuffleAnswers(Boolean shuffleAnswers) {
		this.shuffleAnswers = shuffleAnswers;
	}

	public Set<QuestionMsg> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<QuestionMsg> questions) {
		this.questions = questions;
	}
	
	public void addQuestion(QuestionMsg question) {
		this.questions.add(question);
	}
	
	/*************** Convert To Model ***************/
	
	public Quiz toQuiz() {
		Quiz quiz = new Quiz(this.name);
		
		quiz.setShuffleQuestions(this.shuffleQuestions);
		quiz.setShuffleAnswers(this.shuffleAnswers);
		
		for(QuestionMsg qm : this.questions) {
			quiz.addQuestion(qm.toQuestion());
		}
		
		return quiz;
	}

}
