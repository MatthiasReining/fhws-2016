package de.fhws.app.presentation.showcase.ejb;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fhws.app.business.calculation.boundary.EjbTest1;

@WebServlet("ejb")
public class EjbTestServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@EJB
	EjbTest1 ej1;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		ej1.m1();
		
		resp.getWriter().println("ok");
	}
	
	
	

}
