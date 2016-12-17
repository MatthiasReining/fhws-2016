package de.fhws.app.presentation.showcase.cdi;

import java.io.IOException;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("inject")
public class ManualInjectionServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	

	@Inject @InjectTest
	Instance<Message> messageProducer;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in doGet");
		
		Message message = messageProducer.get();
		
		
		resp.getWriter().println(message.getMessage());
		
	}

}
