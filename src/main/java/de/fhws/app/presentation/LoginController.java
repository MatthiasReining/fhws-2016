package de.fhws.app.presentation;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.fhws.app.business.log.controller.DBLogger;
import de.fhws.app.business.performance.boundary.PerformanceLogger;
import de.fhws.app.business.usermgmt.boundary.CurrentUser;
import de.fhws.app.business.usermgmt.boundary.UserMgmt;
import de.fhws.app.business.usermgmt.entity.AppUser;

@Named
@SessionScoped
public class LoginController implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	UserMgmt um;
	
	@Inject
	DBLogger logger;

	
	private String email;
	private String password;
	
	private AppUser currentUser = null;
	
	@Produces @CurrentUser
	public AppUser produceCurrentUser() {	
		System.out.println("in productCurrentUser");
		return currentUser;
	}

	@PerformanceLogger
	public String login() {
		System.out.println("in login");
		
		

		AppUser au = um.login(email, password);
		if (au != null) {
			currentUser = au;
			
			logger.log(" logged in");
			
			
			return "appuser-list?faces-redirect=true";
		}

		FacesMessage message = new FacesMessage("Login fehlgeschlagen!");
		FacesContext.getCurrentInstance().addMessage("loginMsg", message);

		return "login";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWelcomeMessage() {
		return "Hallo";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AppUser getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(AppUser currentUser) {
		this.currentUser = currentUser;
	}
	
	

}
