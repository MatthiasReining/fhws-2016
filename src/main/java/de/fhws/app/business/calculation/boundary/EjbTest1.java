package de.fhws.app.business.calculation.boundary;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.fhws.app.business.log.entity.LogData;

@Stateless
public class EjbTest1 {

	@PersistenceContext
	EntityManager em;

	@EJB
	EjbTest2 ejb2;

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void m1() {

		System.out.println("in EjbTest1#m1");

		LogData ld = new LogData();
		ld.setMessage("in m1");

		em.persist(ld);

		ejb2.m3();
			
		
		//System.out.println(42/0);

	}
}
