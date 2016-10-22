package de.fhws.app.business.boundary.calculation;

public class Calculation {

	
	public double calc(int param) {
		
		if (param > 100)
			param = 99;
		
		return 42.0 / param;
	}
}
