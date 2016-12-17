package de.fhws.app.business.news.boundary;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@Singleton
@ServerEndpoint("/chat")
public class NewsChat {

	private final static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

	@OnMessage
	public void incomingMessage(String message, Session session) throws IOException {
		synchronized (clients) {
			for (Session s : clients) {
				s.getBasicRemote().sendText(message);
			}
		}
	}

	@OnOpen
	public void open(Session session) {
		clients.add(session);
	}

	@OnClose
	public void close(Session session) {
		clients.remove(session);
	}
	
	public void print2Chat(@Observes @NewLogin String message) throws IOException {
		synchronized (clients) {
			for (Session s : clients) {
				s.getBasicRemote().sendText(message);
			}
		}
		
	}

}
