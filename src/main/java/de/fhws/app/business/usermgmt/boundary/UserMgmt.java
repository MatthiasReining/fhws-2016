package de.fhws.app.business.usermgmt.boundary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import de.fhws.app.business.usermgmt.entity.AppUser;
import de.fhws.app.business.usermgmt.entity.EventLog;

public class UserMgmt {

	EntityManager em;
	UserTransaction tx;

	public UserMgmt(EntityManager em, UserTransaction tx) {
		this.em = em;
		this.tx = tx;
	}

	public AppUser getUserByEmail(String email) {
		AppUser au = em.createNamedQuery(AppUser.findByEMail, AppUser.class).setParameter(AppUser.paramEMail, email)
				.getSingleResult();

		return au;
	}

	public AppUser login(String email, String password) {
		try {
			tx.begin();

			AppUser dbUser = getUserByEmail(email);

			if (!dbUser.getPassword().equals(password)) {
				int numberOfLoginFailed = dbUser.getNumberOfLoginFailed();
				numberOfLoginFailed++;
				dbUser.setNumberOfLoginFailed(numberOfLoginFailed);
				tx.commit();
				return null;
			}

			dbUser.setNumberOfLoginFailed(0);
			dbUser.setLastLogin(new Date());

			EventLog el = new EventLog();
			el.setEvent("LOGIN");

			el = em.merge(el);

			List<EventLog> events = dbUser.getEvents();
			if (events == null) {
				events = new ArrayList<>();
				dbUser.setEvents(events);
			}
			events = dbUser.getEvents();
			events.add(el);

			tx.commit();

			return dbUser;

		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			throw new RuntimeException(e);
		}
	}

	public List<AppUser> getAllUsers() {
		try {

			tx.begin();

			List<AppUser> appUsers = em.createNamedQuery(AppUser.findAll, AppUser.class).getResultList();

			for (AppUser au : appUsers) {
				if (au.getEvents() != null)
					au.getEvents().size();
			}

			
			tx.commit();
			return appUsers;

		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			throw new RuntimeException(e);
		}

	}

	public void save(AppUser appUser) {
		try {
			tx.begin();

			em.merge(appUser);

			tx.commit();
		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			throw new RuntimeException(e);
		}
	}
}
