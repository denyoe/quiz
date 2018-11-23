package fr.epita.marcus.quiz.tests;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.marcus.quiz.datamodel.MCQChoice;
import fr.epita.marcus.quiz.datamodel.Question;
import fr.epita.marcus.quiz.datamodel.constant.QuestionType;
import fr.epita.marcus.quiz.services.business.QuestionManagementService;
import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class QuestionManagementServiceTest {
	
	@Inject QuestionManagementService qs;
	
	@Test
	public void createTextQuestion() {
		String label = "label";
		
		// Given
		Question question = new Question(label, QuestionType.TXT);
		
		// When
		qs.saveQuestion(question);
		
		// Then
		Question check = qs.getQuestion(new Question(label, QuestionType.TXT));
		
		Assert.assertEquals(label, check.getLabel());
		Assert.assertEquals(QuestionType.TXT, check.getType());
		
	}

	@Test
	public void createMCQQuestion() {
		String label = "label MCQ";
		
		// Given
		Question question = new Question(label, QuestionType.MCQ);
		
		Set<MCQChoice> choices = new HashSet<>();
		choices.add(new MCQChoice("Option 1", false));
		choices.add(new MCQChoice("Option 2", true));
		choices.add(new MCQChoice("Option 3", false));
		
		// When
		qs.saveQuestion(question, choices);
		
		// Then
		Question check = qs.getQuestion(new Question(label, QuestionType.MCQ));
		
		Assert.assertEquals(3, check.getOptions().size());
		Assert.assertEquals(label, check.getLabel());
		
		List<MCQChoice> correctOptions = qs.getCorrectOptions(check);
		
		Assert.assertEquals(1, correctOptions.size());
		Assert.assertEquals("Option 2", correctOptions.get(0).getLabel());
		
	}

}
