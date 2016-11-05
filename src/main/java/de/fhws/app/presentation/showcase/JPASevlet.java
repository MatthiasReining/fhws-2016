package de.fhws.app.presentation.showcase;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import de.fhws.app.business.log.entity.LogData;

@WebServlet("jpa")
public class JPASevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	EntityManager em;

	@Resource
	UserTransaction tx;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getParameter("action");
		if (action == null)
			action = "create";

		if ("create".equalsIgnoreCase(action)) {
			createAction();
		} else if ("find".equalsIgnoreCase(action)) {
			String idText = req.getParameter("id");
			Long id = new Long(idText);

			LogData ld = findAndModify(id);

			
			resp.getWriter().println(ld);
		} else if("findAll".equalsIgnoreCase(action)) {
			
			

			List<LogData> result = em.createNamedQuery(LogData.findAll, LogData.class).getResultList();
			
			for(LogData ld: result) {
				resp.getWriter().println(ld);
			}
		}

	}

	protected LogData findAndModify(Long id) {
		try {
			tx.begin();
			LogData ld = em.find(LogData.class, id);
			
			System.out.println(ld);
			em.detach(ld);
			
			ld.setMessage("Geändert am " + new Date().toString());
			
			
			
			ld = em.merge(ld);
			
						

			tx.commit();
			return ld;
			
		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			throw new RuntimeException(e);
		}

	}

	protected void createAction() {
		LogData ld = new LogData();
		ld.setInsertTime(new Date());
		ld.setMessage("eintrag von JPA " + System.currentTimeMillis());

		try {
			tx.begin();
			em.persist(ld);
			tx.commit();

		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException | RollbackException
				| HeuristicMixedException | HeuristicRollbackException e) {
			e.printStackTrace();
		}
	}

}
