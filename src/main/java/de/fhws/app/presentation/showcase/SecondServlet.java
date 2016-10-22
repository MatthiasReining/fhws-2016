package de.fhws.app.presentation.showcase;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("second")
public class SecondServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Calendar now = Calendar.getInstance();
		if (now.get(Calendar.HOUR_OF_DAY) < 12)
			resp.getWriter().append("Guten Morgen");
		else
			resp.getWriter().append("Guten Abend");
		
		
	}
	
	

}
