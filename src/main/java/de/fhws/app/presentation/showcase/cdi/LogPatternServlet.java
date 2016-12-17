package de.fhws.app.presentation.showcase.cdi;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fhws.app.business.log.controller.OldSchoolLoggerFactory;

@WebServlet("logp")
public class LogPatternServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		OldSchoolLoggerFactory.getLogger().log("in log pattern factory");
		
	}

}
