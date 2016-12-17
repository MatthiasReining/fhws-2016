package de.fhws.app.presentation.showcase.cdi;

import java.io.Serializable;

import javax.annotation.PostConstruct;

public class DataContainer implements Serializable {

	private static final long serialVersionUID = 1L;

	private String data;
	
	private static int counter = 0;
	
	@PostConstruct
	public void init() {
		counter++;
		data = counter + " Message ";
	}
	
	public String getData() {
		return data;
	}


}
