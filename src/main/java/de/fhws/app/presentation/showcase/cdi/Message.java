package de.fhws.app.presentation.showcase.cdi;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped //see different behaviour
//@Dependent //see different behaviour
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;
	
	private static int counter = 0;
	
	@Inject DataContainer dc;
	
	@PostConstruct
	public void init() {
		counter++;
		message = counter + " Message ";
		System.out.println( dc.getData()) ;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
