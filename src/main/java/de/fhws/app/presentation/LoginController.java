package de.fhws.app.presentation;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import de.fhws.app.business.usermgmt.boundary.UserMgmt;
import de.fhws.app.business.usermgmt.entity.AppUser;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	EntityManager em;

	@Resource
	UserTransaction tx;

	UserMgmt um;

	@PostConstruct
	public void init() {
		um = new UserMgmt(em, tx);
	}

	private String email;
	private String password;
	
	private AppUser currentUser = null;

	public String login() {
		System.out.println("in login");

		AppUser au = um.login(email, password);
		if (au != null) {
			currentUser = au;
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
