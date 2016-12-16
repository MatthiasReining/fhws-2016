package de.fhws.app.business.log.boundary;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class EJBLogService {
	
	@Asynchronous
	public void log(String message) {
		
		try {
			//simulate time consuming activitiy
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("LOG: " + message);
	}

}
