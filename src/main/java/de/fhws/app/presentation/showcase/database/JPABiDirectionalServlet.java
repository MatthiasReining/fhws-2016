package de.fhws.app.presentation.showcase.database;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fhws.app.business.usermgmt.entity.AppUser;
import de.fhws.app.business.usermgmt.entity.EventLog;

@WebServlet("bi-directional")
public class JPABiDirectionalServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	EntityManager em;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in bi-directional servlet");
		String eventIdText = req.getParameter("eventId");
		Long eventId = new Long(eventIdText);
		
		EventLog el = em.find(EventLog.class, eventId);
		
		AppUser au = el.getAppUser();
		
		
		
		resp.getWriter().println(el);
		
		resp.getWriter().println(au);
		
	}

}
