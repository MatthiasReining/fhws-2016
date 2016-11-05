package de.fhws.app.presentation;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import de.fhws.app.business.usermgmt.boundary.UserMgmt;
import de.fhws.app.business.usermgmt.entity.AppUser;

@ManagedBean
public class AppUserListController {
	
	@PersistenceContext
	EntityManager em;
	
	@Resource
	UserTransaction tx;

	
	UserMgmt um;
	
	@PostConstruct
	public void init() {
		um = new UserMgmt(em,tx);
	}
	
	
	public List<AppUser> getAllUsers() {
		return um.getAllUsers();
	}
}
