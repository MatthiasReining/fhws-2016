package de.fhws.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("ssi")
public class ServerSideInclude extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		resp.getWriter().append("<h1>in ServerSideInclude</h1>");
		
		req.getRequestDispatcher("/second").include(req, resp);		
		
		resp.getWriter().append("<h1>immer noch ServerSideInclude</h1>");

	}

}
