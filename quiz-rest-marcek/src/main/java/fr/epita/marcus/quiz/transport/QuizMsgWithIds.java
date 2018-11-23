package fr.epita.marcus.quiz.transport;

import java.util.ArrayList;
import java.util.List;


public class QuizMsgWithIds {
	
	
	
	private Long id;
	
	private String name;
	
	/**
	 * Formatted Author Name
	 */
	private String uid;
	
	private Boolean shuffleQuestions = false;
	private Boolean shuffleAnswers = false;
	
	private List<Long> questions = new ArrayList<>();
	
	/************ Methods ***********/
	
	public QuizMsgWithIds() {}
	
	public QuizMsgWithIds(String name) {
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

	public List<Long> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Long> questions) {
		this.questions = questions;
	}
	
	public void addQuestion(Long question) {
		this.questions.add(question);
	}

}
