package de.fhws.app.presentation.showcase;

import java.io.Serializable;

import javax.annotation.PostConstruct;

//@RequestScoped //see different behaviour
//@Dependent //see different behaviour
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;
	
	private static int counter = 0;
	
	@PostConstruct
	public void init() {
		counter++;
		message = counter + " Message ";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
