package de.fhws.app.presentation;

import javax.enterprise.event.Observes;

import de.fhws.app.business.news.boundary.NewLogin;

public class ChatController {

	
	public void loginObserver(@Observes @NewLogin String message) {
		System.out.println("ChatController: " + message);
	}
}
