package fr.epita.marcus.quiz.datamodel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="results")
public class Result {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * User Object
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private User candidate;
	
	/**
	 * Associated Quiz Object
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private Quiz quiz;
	
	/**
	 * Test Score
	 */
	private Long score;
	
	/**
	 * Timestamp
	 */
	private String start;
	private String end;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getCandidate() {
		return candidate;
	}
	public void setCandidate(User candidate) {
		this.candidate = candidate;
	}
	public Quiz getQuiz() {
		return quiz;
	}
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}

}
