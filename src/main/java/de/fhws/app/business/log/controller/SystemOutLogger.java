package de.fhws.app.business.log.controller;

import de.fhws.app.business.log.boundary.FHWSLogger;

public class SystemOutLogger implements FHWSLogger {

	@Override
	public void log(String message) {
		System.out.println("LOG: " + message);

	}

}
