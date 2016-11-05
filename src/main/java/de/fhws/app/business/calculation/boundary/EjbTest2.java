package de.fhws.app.business.calculation.boundary;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.fhws.app.business.log.entity.LogData;

@Stateless
public class EjbTest2 {

	@PersistenceContext
	EntityManager em;

	
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void m3() {

		System.out.println("in EjbTest2#m3");

		LogData ld = new LogData();
		ld.setMessage("in EjbTest2m3");

		em.persist(ld);
		
	}

}
