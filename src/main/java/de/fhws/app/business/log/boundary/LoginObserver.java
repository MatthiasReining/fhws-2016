package de.fhws.app.business.log.boundary;

import javax.enterprise.event.Observes;

import de.fhws.app.business.news.boundary.NewLogin;

public class LoginObserver {
	
	public void printLogin(@Observes @NewLogin String message) {
		System.out.println("LoginObserver: " + message);
		
	}

}
