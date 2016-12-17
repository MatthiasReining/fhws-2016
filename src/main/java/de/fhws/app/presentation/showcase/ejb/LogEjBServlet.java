package de.fhws.app.presentation.showcase.ejb;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fhws.app.business.log.boundary.EJBLogService;

@WebServlet("logejb")
public class LogEjBServlet extends HttpServlet {
	private static final long serialVersionUID = 7109320408994416992L;

	@EJB
	EJBLogService logService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		logService.log("logge text");
		
		System.out.println("servlet fertig");	
		
	}

}
