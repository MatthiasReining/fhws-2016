package de.fhws.app.presentation.showcase.servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("qp")
public class QueryParamServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().println("IP Adresss: " + request.getLocalAddr() );
		response.getWriter().println("");
		
		response.getWriter().println("HEADER");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			response.getWriter().println(headerName + ": " + request.getHeader(headerName));
		}

		response.getWriter().println("");
		response.getWriter().println("QUERY PARAM");

		for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			response.getWriter().println(entry.getKey() + ": " + String.join(", ", entry.getValue()));
		}

	}
}
