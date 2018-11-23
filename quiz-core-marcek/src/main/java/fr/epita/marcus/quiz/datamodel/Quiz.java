package fr.epita.marcus.quiz.datamodel;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="quizzes")
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * Quiz Name
	 */
	private String name;
	
	
	private String uid;
	
	@ManyToOne
	private User author;
	
	private Boolean shuffleQuestions = false;
	private Boolean shuffleAnswers = false;
	
	@ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "quiz_question",
            joinColumns = { @JoinColumn(name = "quiz_id") },
            inverseJoinColumns = { @JoinColumn(name = "question_id") })
    private Set<Question> questions = new HashSet<>();
	
	@OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Result> results;
	
	
	/**
	 * Timestamp
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="timestamp", nullable = false,
	    columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
	private Date timestamp = new Date();
	
	
	/**********************Methods**************************/
	
	public Quiz() {}
	
	public Quiz(String name) {
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
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
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
	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Set<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	
	public void addQuestion(Question question) {
		this.questions.add(question);
	}
	
	public Set<Result> getResults() {
		return results;
	}
	public void setResults(Set<Result> results) {
		this.results = results;
	}

}
