package de.fhws.app.presentation;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import de.fhws.app.business.usermgmt.boundary.UserMgmt;
import de.fhws.app.business.usermgmt.entity.AppUser;

@ManagedBean
@SessionScoped
public class AppUserController {
	
	@PersistenceContext
	EntityManager em;
	
	@Resource
	UserTransaction tx;

	
	UserMgmt um;
	
	@PostConstruct
	public void init() {
		um = new UserMgmt(em, tx);
	}

	AppUser appUser;
	
	public String load(AppUser appUser) {
		this.appUser = appUser;
		
		System.out.println(appUser.getEmail());
		
		return "appuser?faces-redirect=true";
	}
	
	public String save() {
		System.out.println("save");
		um.save(appUser);	
		
		return "appuser-list?faces-redirect=true";
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
	
}
