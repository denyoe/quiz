package fr.epita.marcus.quiz.datamodel;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.epita.marcus.quiz.datamodel.constant.QuestionType;


@Entity
@Table(name="questions")
public class Question {
	
	@Id
	@Column(name = "question_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * Question label
	 */
	private String label;
	
	/**
	 * Question Type: MCQ, TXT, BOOL
	 */
	private QuestionType type;
	
	@ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "questions")
    private Set<Quiz> quiz = new HashSet<>();
	
	@OneToMany(mappedBy = "question", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<MCQChoice> options = new HashSet<>();
	
	
	/**********************Methods**************************/
	
	public Question() {
		//
	}
	
	public Question(String label, QuestionType type) {
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

	public Set<Quiz> getQuiz() {
		return quiz;
	}

	public void setQuiz(Set<Quiz> quiz) {
		this.quiz = quiz;
	}

	public Set<MCQChoice> getOptions() {
		return options;
	}

	public void setOptions(Set<MCQChoice> options) {
		this.options = options;
	}
	
	public void addOption(MCQChoice option) {
		this.options.add(option);
	}

}
