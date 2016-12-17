package de.fhws.app.business.usermgmt.entity;

import java.util.Date;
import java.util.List;

import javax.jms.JMSSessionMode;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@NamedQueries({ 
	@NamedQuery(name = AppUser.findAll, query = "SELECT a FROM AppUser a"),
	@NamedQuery(name = AppUser.findByEMail, query = "SELECT a FROM AppUser a WHERE a.email = :" + AppUser.paramEMail) 
})
@XmlRootElement
public class AppUser {

	public static final String findAll = "AppUser.findAll";
	public static final String findByEMail= "AppUser.findByEmail";
	
	public static final String paramEMail = "appUserParamEmail";

	@Id
	@GeneratedValue
	private long id;
	private String email;
	private String password;
	private String firstname;
	private String lastname;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;
	private int numberOfLoginFailed;
	
	@OneToMany(mappedBy="appUser", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<EventLog> events;

	public AppUser() {
	}

	public AppUser(String email, String password, String firstname, String lastname) {
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	

	@Override
	public String toString() {
		return "AppUser [id=" + id + ", email=" + email + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", lastLogin=" + lastLogin + ", numberOfLoginFailed=" + numberOfLoginFailed
				+ "]";
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public int getNumberOfLoginFailed() {
		return numberOfLoginFailed;
	}

	public void setNumberOfLoginFailed(int numberOfLoginFailed) {
		this.numberOfLoginFailed = numberOfLoginFailed;
	}

	public List<EventLog> getEvents() {
		return events;
	}

	public void setEvents(List<EventLog> events) {
		this.events = events;
	}

	
}
