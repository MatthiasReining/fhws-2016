package de.fhws.app.business.log.controller;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.fhws.app.business.log.entity.LogData;
import de.fhws.app.business.usermgmt.boundary.CurrentUser;
import de.fhws.app.business.usermgmt.entity.AppUser;

@Stateless
public class DBLogger  {
	
	
	@PersistenceContext
	EntityManager em;
	
	@Inject @CurrentUser
	AppUser currentUser;
	

	public void log(String message) {
		message = currentUser.getFirstname() + " " + message;
		
		LogData ld = new LogData();
		ld.setInsertTime(new Date());
		ld.setMessage(message);
		
		em.persist(ld);

		
	}

}
