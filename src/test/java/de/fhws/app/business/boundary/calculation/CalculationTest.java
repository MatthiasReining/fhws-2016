package de.fhws.app.business.boundary.calculation;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class CalculationTest {
	
	Calculation cut;
	
	@Before
	public void init() {
		cut = new Calculation();
	}

	
	@Test
	public void shouldCalculate() {
		
		double result = cut.calc(1); 
		Assert.assertEquals(42.0, result, 0.001);
		
	}
	
	@Test
	public void shouldCalculate2() {		
		double result = cut.calc(2); 
		Assert.assertEquals(21.0, result, 0.001);
		
	}
	
	@Test
	public void shouldCalculate3() {		
		double result = cut.calc(4); 
		Assert.assertEquals(21.0, result, 0.001);
		
	}
	
	@Test
	public void shouldCalculate4() {		
		double result = cut.calc(0); 
		Assert.assertEquals(21.0, result, 0.001);
		
	}
}
