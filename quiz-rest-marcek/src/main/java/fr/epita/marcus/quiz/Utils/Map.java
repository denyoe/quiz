package fr.epita.marcus.quiz.Utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.epita.marcus.quiz.datamodel.MCQChoice;
import fr.epita.marcus.quiz.datamodel.Question;
import fr.epita.marcus.quiz.datamodel.Quiz;
import fr.epita.marcus.quiz.transport.MCQChoiceMsg;
import fr.epita.marcus.quiz.transport.QuestionMsg;
import fr.epita.marcus.quiz.transport.QuizMsg;

public class Map {
	
	public static Collection<QuizMsg> fromListQuiz(Collection<Quiz> quiz) {
		
		List<QuizMsg> QM = new ArrayList<>();
		
		for( Quiz q : quiz ) {
			QM.add(fromQuiz(q));
		}
		
		return QM;
	}
	
	public static QuizMsg fromQuiz(Quiz quiz) {
		
		QuizMsg qm = new QuizMsg(quiz.getName());
		
		qm.setId(quiz.getId());
		qm.setShuffleQuestions(quiz.getShuffleQuestions());
		qm.setShuffleAnswers(quiz.getShuffleAnswers());
			
		for( Question question : quiz.getQuestions() ) {
			qm.addQuestion(fromQuestion(question));
		}
		
		return qm;
	}
	
	public static Collection<QuestionMsg> fromListQuestion(Collection<Question> questions) {
		
		Set<QuestionMsg> QMS = new HashSet<>();
		
		for( Question q : questions ) {
			QMS.add(fromQuestion(q));
		}
		
		return QMS;
	}
	
	public static QuestionMsg fromQuestion(Question instance) {
		QuestionMsg question = new QuestionMsg(instance.getLabel(), instance.getType());
		question.setId(instance.getId());
		
		for( MCQChoice option : instance.getOptions() ) {
			question.addOption(fromMCQChoice(option));
		}
		
		return question;
	}
	
	public static MCQChoiceMsg fromMCQChoice(MCQChoice instance) {
		MCQChoiceMsg option = new MCQChoiceMsg();
		
		option.setValid(instance.isValid());
		option.setLabel(instance.getLabel());
		
		option.setId(instance.getId());

		return option;
	}

}
