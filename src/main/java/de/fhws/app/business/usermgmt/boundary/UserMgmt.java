package de.fhws.app.business.usermgmt.boundary;

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

			if (dbUser.getPassword().equals(password)) {
				dbUser.setNumberOfLoginFailed(0);
				dbUser.setLastLogin(new Date());
				tx.commit();
				return dbUser;

			} else {
				int numberOfLoginFailed = dbUser.getNumberOfLoginFailed();
				numberOfLoginFailed++;
				dbUser.setNumberOfLoginFailed(numberOfLoginFailed);
				tx.commit();
				return null;
			}

		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			throw new RuntimeException(e);
		}
	}

	public List<AppUser> getAllUsers() {
		return em.createNamedQuery(AppUser.findAll, AppUser.class).getResultList();
	}

	public void save(AppUser appUser) {
		em.merge(appUser);
	}
}
