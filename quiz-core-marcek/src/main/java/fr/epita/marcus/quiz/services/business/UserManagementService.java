package fr.epita.marcus.quiz.services.business;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import fr.epita.marcus.quiz.datamodel.User;
import fr.epita.marcus.quiz.services.data.IdentityDAO;
import fr.epita.marcus.quiz.services.data.UserDAO;
import fr.epita.marcus.quiz.utils.Password;

@Repository
public class UserManagementService {
	
	private static final Logger log = LogManager.getLogger(UserManagementService.class);
	
	@Inject
	IdentityDAO identityDAO;
	
	@Inject
	UserDAO userDAO;
	
	@Transactional
	public void saveUser(User user) {
		this.identityDAO.create(user.getIdentity());
		String encodePWD = null;
		try {
			encodePWD = Password.encode(user.getPassword());
		} catch (NoSuchAlgorithmException e) {
			log.error("Error Encoding User Password");
		}
		user.setPassword(encodePWD);
		this.userDAO.create(user);
	}
	
	public List<User> searchUser(User criteria) {
		return userDAO.search(criteria);
	}
	
	public User getByEmail(User criteria) {
		return userDAO.getByEmail(criteria);
	}
	
	public void deleteUser(User criteria) {
		userDAO.delete(userDAO.getByEmail(criteria));
	}
	
	public List<User> all() {
		return this.userDAO.all();
	}

}
