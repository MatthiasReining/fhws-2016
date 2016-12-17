package de.fhws.app.presentation.showcase.cdi;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.fhws.app.business.performance.boundary.TestWorker;

@WebServlet("performance")
public class PerformanceTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	TestWorker tw;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		tw.work();		
	}

}
