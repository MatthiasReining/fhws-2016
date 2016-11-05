package de.fhws.app.business.calculation.boundary;

import de.fhws.app.business.log.entity.LogData;

public interface ComplexCalculationBeanRemote {

	public int calc(int in);
	
	public LogData getEvent(long id);

}
