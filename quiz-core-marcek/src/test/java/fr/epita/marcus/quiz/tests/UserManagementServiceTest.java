package fr.epita.marcus.quiz.tests;

import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.marcus.quiz.datamodel.*;
import fr.epita.marcus.quiz.services.business.UserManagementService;
import fr.epita.marcus.quiz.utils.Password;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class UserManagementServiceTest {
	
	private static final Logger log = LogManager.getLogger(UserManagementServiceTest.class);
	
	@Inject
	UserManagementService ds;
	
	@Test
	public void CreateNewUserTest() {
		
		final String test_mail = "email@me.io";
		final String pass = "password";
		
		// Given
		Identity identity = new Identity("fname", "lname", test_mail);
		
		User user = new User(identity, pass);
		
		// When
		ds.saveUser(user);
		
		// Then
		User criteria = new User(new Identity(test_mail));
		User result = ds.getByEmail(criteria);
		
		String check_email = result != null ? result.getIdentity().getEmail() : "";
		String check_pass = null;
		try {
			check_pass = Password.encode(pass);
		} catch (NoSuchAlgorithmException e) {
			log.error("Error Encoding User Password");
		}
		
		Assert.assertEquals(test_mail, check_email);
		Assert.assertEquals(check_pass, result.getPassword());
		
	}
	
	@Test
	public void userNotExistsTest() {
		
		final String test_mail = "test@email.com";
		
		// Given
		User result = ds.getByEmail(new User(new Identity(test_mail)));
		
		// When
		String check_email = result != null ? result.getIdentity().getEmail() : "";
		
		// Then
		Assert.assertNotEquals(test_mail, check_email);
		
	}

}
