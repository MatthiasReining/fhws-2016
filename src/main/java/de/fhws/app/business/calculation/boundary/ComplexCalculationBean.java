package de.fhws.app.business.calculation.boundary;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.fhws.app.business.log.entity.LogData;

@Stateless
@Remote(ComplexCalculationBeanRemote.class)
@WebService
public class ComplexCalculationBean implements ComplexCalculationBeanLocal, ComplexCalculationBeanRemote {
	@PersistenceContext
	EntityManager em;

	@Override
	public int calc(int in) {
		return 42 * in;
	}

	public LogData getEvent(long id) {
		
		return em.find(LogData.class, id);
	}
}
