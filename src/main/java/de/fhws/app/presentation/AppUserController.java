package de.fhws.app.presentation;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import de.fhws.app.business.usermgmt.boundary.UserMgmt;
import de.fhws.app.business.usermgmt.entity.AppUser;

@Named
@SessionScoped
public class AppUserController implements Serializable {
	private static final long serialVersionUID = 1L;
	

	@EJB
	UserMgmt um;

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
