package de.fhws.app.presentation;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import de.fhws.app.business.usermgmt.boundary.UserMgmt;
import de.fhws.app.business.usermgmt.entity.AppUser;

@ManagedBean
public class LoginController {

	UserMgmt um = new UserMgmt();

	private String email;
	private String password;

	public String login() {
		System.out.println("in login");
		
		AppUser au = um.getUserByEmail(email);
		if (au != null) {
			if (au.getPassword().equals(password))
				return "appuser-list?faces-redirect=true";
		}

		FacesMessage message = new FacesMessage("Login fehlgeschlagen!");
		FacesContext.getCurrentInstance().addMessage("loginForm:loginMsg", message);
		
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

}
