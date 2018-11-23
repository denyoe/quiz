package fr.epita.marcus.quiz.transport;

import fr.epita.marcus.quiz.datamodel.MCQChoice;

public class MCQChoiceMsg {
	
	private Long id;
	
	private Boolean valid;
	
	private String label;
	
	/************ Methods ***********/
	
	public MCQChoiceMsg() {}
	
	public MCQChoiceMsg(String label, Boolean valid) {
		this.setLabel(label);
		this.setValid(valid);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	/*************** Convert To Model ***************/

	public MCQChoice toMCQChoice() {
		return new MCQChoice(this.label, this.valid);
	}

}
