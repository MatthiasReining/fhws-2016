package de.fhws.app.presentation.showcase;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import de.fhws.app.business.log.boundary.LogService;

@WebServlet("test123")
public class LogServlet extends HttpServlet {

	@Resource(lookup = "java:jboss/datasources/FhwsDS")
	DataSource datasource;

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String message = req.getParameter("message");
		
		new LogService(datasource).save(message);
		
		
	}

}
