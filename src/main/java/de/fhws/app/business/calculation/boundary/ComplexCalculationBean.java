package de.fhws.app.business.calculation.boundary;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(ComplexCalculationBeanRemote.class)
public class ComplexCalculationBean implements ComplexCalculationBeanLocal, ComplexCalculationBeanRemote {

	@Override
	public int calc(int in) {
		return 42 * in;
	}

}
