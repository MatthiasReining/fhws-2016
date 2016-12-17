package de.fhws.app.presentation.showcase.cdi;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.servlet.http.HttpServlet;

@RequestScoped
public class ManualInjectionService extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Produces @InjectTest
	public Message produceMessage() {
		System.out.println("in ManualInjectionServlet#produceMessage");
		Message m = new Message();
		m.setMessage("my message: " + System.currentTimeMillis());
		return m;
	}
	

}
