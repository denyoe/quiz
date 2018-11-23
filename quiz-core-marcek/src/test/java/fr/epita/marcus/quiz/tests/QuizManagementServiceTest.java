package fr.epita.marcus.quiz.tests;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.marcus.quiz.datamodel.Question;
import fr.epita.marcus.quiz.datamodel.Quiz;
import fr.epita.marcus.quiz.datamodel.constant.QuestionType;
import fr.epita.marcus.quiz.services.business.QuizManagementService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class QuizManagementServiceTest {
	
	@Inject QuizManagementService qs;
	
	@Test
	public void createQuiz() {
		
		String name = "Quiz No.1";
		
		// Given
		Quiz quiz = new Quiz(name);
		
		// When
		qs.createQuiz(quiz);
		
		// Then
		List<Quiz> check = qs.findQuiz(quiz);
		
		Assert.assertEquals(name, check.get(0).getName());
		Assert.assertEquals(1, check.size());
	}
	
	@Test
	public void createQuizWithQuestions() {
		
		String name = "Quiz No.2";
		
		// Given
		Quiz quiz = new Quiz(name);
		quiz.addQuestion(new Question("Question 1", QuestionType.TXT));
		quiz.addQuestion(new Question("Question 2", QuestionType.TXT));
		quiz.addQuestion(new Question("Question 3", QuestionType.TXT));
		
		// When
		qs.createQuiz(quiz, quiz.getQuestions());
		
		// Then
		List<Quiz> check = qs.findQuiz(quiz);
		
		Assert.assertEquals(3, check.get(0).getQuestions().size());
		
	}

}
