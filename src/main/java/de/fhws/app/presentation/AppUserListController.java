package de.fhws.app.presentation;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import de.fhws.app.business.usermgmt.boundary.UserMgmt;
import de.fhws.app.business.usermgmt.entity.AppUser;


@Named
@RequestScoped
public class AppUserListController {
	
	@EJB	
	UserMgmt um;
	

	
	public List<AppUser> getAllUsers() {
		return um.getAllUsers();
	}
}
