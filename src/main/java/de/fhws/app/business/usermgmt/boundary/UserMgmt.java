package de.fhws.app.business.usermgmt.boundary;

import java.util.List;

import de.fhws.app.business.usermgmt.entity.AppUser;
import de.fhws.app.business.usermgmt.entity.DBMock;

public class UserMgmt {
	
	DBMock dbmock = new DBMock();
	
	public AppUser getUserByEmail(String email) {
		return dbmock.getUserByEmail(email);
	}
	
	public List<AppUser> getAllUsers() {
		return dbmock.getAllUsers();
	}

}
