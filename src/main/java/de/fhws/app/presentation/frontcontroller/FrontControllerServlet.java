package de.fhws.app.presentation.frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller/*")
public class FrontControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String contextPath = req.getPathInfo();
		

		String commandName = contextPath.substring(1);
		String clazzName = commandName.substring(0, 1).toUpperCase() + commandName.substring(1) + "Command";

		try {
			@SuppressWarnings("unchecked")
			Class<Command> clazz = (Class<Command>) Class
					.forName("de.fhws.app.presentation.frontcontroller.commands." + clazzName);

			Command cmd = clazz.newInstance();

			String nextPage = cmd.process(req, resp);

			String nextPagePath = "/WEB-INF/pages/" + nextPage + ".jsp";
			req.getRequestDispatcher(nextPagePath).forward(req, resp);

		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

		//

	}

}
