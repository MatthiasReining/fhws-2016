package de.fhws.app.business.log.controller;

import de.fhws.app.business.log.boundary.FHWSLogger;

public class OldSchoolLoggerFactory {
	
	//public static FHWSLogger getDBLogger() {
	//	return new DBLogger();
	//}
	
	public static FHWSLogger getSystemOutLogger() {
		return new SystemOutLogger();
	}
	
	public static FHWSLogger getLogger() {
		return getSystemOutLogger();
	}
	

}
