package fr.epita.marcus.quiz.datamodel;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Identity identity;
	
	private String password;
	
	@OneToMany
	private Set<Quiz> quiz = new HashSet<>();
	
	/**********************Methods**************************/
	
	public User() {}
	
	public User(Identity identity) {
		this.setIdentity(identity);
	}
	
	public User(Identity identity, String password) {
		this.setIdentity(identity);
		this.setPassword(password);
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
