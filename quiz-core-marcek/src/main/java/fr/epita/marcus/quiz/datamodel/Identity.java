package fr.epita.marcus.quiz.datamodel;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.epita.marcus.quiz.datamodel.constant.IdentityType;

@Entity
@Table(name="identities")
public class Identity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String fname;
	private String lname;
	private String email;
	private Date birthDate;
	
	/**
	 * Identity Type i.e. CANDIDATE, ...
	 */
	private IdentityType type;
	
	
	
	/**********************Methods**************************/
	
	/**
	 * Overloaded
	 * @param fname
	 * @param lname
	 * @param email
	 */
	public Identity(String fname, String lname, String email) {
		this.setFname(fname);
		this.setLname(lname);
		this.setEmail(email);
	}
	
	/**
	 * Overloaded
	 * @param email
	 */
	public Identity(String email) {
		this.setEmail(email);
	}
	
	/**
	 * Overloaded
	 */
	public Identity() {}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFname() {
		return fname;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public IdentityType getType() {
		return type;
	}

	public void setType(IdentityType type) {
		this.type = type;
	}
	
	
	
}
