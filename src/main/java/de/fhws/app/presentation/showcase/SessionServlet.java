package de.fhws.app.presentation.showcase;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("session")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int count = 0;
		HttpSession session = req.getSession();
		Object countObj = session.getAttribute("counter");
		if (countObj != null)
			count = (int) countObj;

		count++;
		session.setAttribute("counter", count);

		resp.getWriter().println("SessionId: " + session.getId() + "   -   count: " + count);

	}

}
