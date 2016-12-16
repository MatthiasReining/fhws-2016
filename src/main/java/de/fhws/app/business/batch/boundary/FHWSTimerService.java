package de.fhws.app.business.batch.boundary;

import javax.ejb.Singleton;

@Singleton
public class FHWSTimerService {
  
    //@Schedule(second="*/1", minute="*",hour="*", persistent=false)
    public void doWork(){
    	
        System.out.println("log message: " + System.currentTimeMillis());
    }
}