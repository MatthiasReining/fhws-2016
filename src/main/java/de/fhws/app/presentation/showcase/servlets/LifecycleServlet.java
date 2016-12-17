package de.fhws.app.presentation.showcase.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("lifecycle")
public class LifecycleServlet extends HttpServlet {

	private int initCount = 0;
	private int getCount = 0;

	
	private static final long serialVersionUID = 1;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getCount++;
		resp.getWriter().append("initCount:" + initCount);
		resp.getWriter().println();
		resp.getWriter().append("getCount:" + getCount);
	}

	@Override
	public void destroy() {
		System.out.println("LifecycleServlet DESTROY");
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		System.out.println("LifecycleServlet INIT");
		initCount++;
		super.init();
	}

}
