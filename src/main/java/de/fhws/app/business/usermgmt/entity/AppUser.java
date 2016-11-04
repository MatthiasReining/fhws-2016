package de.fhws.app.business.usermgmt.entity;

import java.util.UUID;

public class AppUser {

	private String id;
	private String email;
	private String password;
	private String firstname;
	private String lastname;

	public AppUser() {
	}

	public AppUser(String email, String password, String firstname, String lastname) {
		super();
		this.id = UUID.randomUUID().toString();
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
