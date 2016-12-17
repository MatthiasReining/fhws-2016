package de.fhws.app.presentation.showcase.listener;

import java.util.Date;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("session wird angelegt");
		se.getSession().setAttribute("createTime", new Date());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		//int count = (int) se.getSession().getAttribute("counter");
		//System.out.println("Die Session hatte " + count + " counts");
		System.out.println("Die Session wurde angelegt um " + se.getSession().getAttribute("createTime"));
		System.out.println("session wird gelöscht");
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
	}

}
