package de.fhws.app.business.usermgmt.boundary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.fhws.app.business.usermgmt.entity.AppUser;
import de.fhws.app.business.usermgmt.entity.EventLog;

@Stateless
public class UserMgmt {

	@PersistenceContext
	EntityManager em;

	public AppUser getUserByEmail(String email) {
		AppUser au = em.createNamedQuery(AppUser.findByEMail, AppUser.class).setParameter(AppUser.paramEMail, email)
				.getSingleResult();
		return au;
	}

	public AppUser login(String email, String password) {
		AppUser dbUser = getUserByEmail(email);

		if (!dbUser.getPassword().equals(password)) {
			int numberOfLoginFailed = dbUser.getNumberOfLoginFailed();
			numberOfLoginFailed++;
			dbUser.setNumberOfLoginFailed(numberOfLoginFailed);
			return null;
		}

		dbUser.setNumberOfLoginFailed(0);
		dbUser.setLastLogin(new Date());

		EventLog el = new EventLog();
		el.setEvent("LOGIN");
		el.setAppUser(dbUser);

		List<EventLog> events = dbUser.getEvents();
		if (events == null) {
			events = new ArrayList<>();
			dbUser.setEvents(events);
		}
		events = dbUser.getEvents();
		events.add(el);

		return dbUser;

	}

	public List<AppUser> getAllUsers() {
		List<AppUser> appUsers = em.createNamedQuery(AppUser.findAll, AppUser.class).getResultList();
		return appUsers;
	}

	public void save(AppUser appUser) {
		em.merge(appUser);
	}
}
