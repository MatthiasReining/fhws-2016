package de.fhws.app.business.log.boundary;

import java.util.Date;

import javax.sql.DataSource;

import de.fhws.app.business.log.controller.ORMapper;
import de.fhws.app.business.log.entity.LogInfo;

public class LogService {
	
	DataSource ds;

	public LogService(DataSource ds) {
		this.ds = ds;
	}
	
	public void save(String message) {
		
		LogInfo li = new LogInfo();
		li.setInserttime(new Date());
		li.setMessage(message);
		
		
		new ORMapper().save(ds, li);
	}
	
}
