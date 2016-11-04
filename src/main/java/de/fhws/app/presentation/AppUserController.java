package de.fhws.app.presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.fhws.app.business.usermgmt.boundary.UserMgmt;
import de.fhws.app.business.usermgmt.entity.AppUser;

@ManagedBean
@SessionScoped
public class AppUserController {
	
	UserMgmt um = new UserMgmt();


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
