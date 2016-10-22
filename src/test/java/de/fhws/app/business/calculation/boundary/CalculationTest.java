package de.fhws.app.business.calculation.boundary;

import org.junit.Before;
import org.junit.Test;

import de.fhws.app.business.calculation.boundary.Calculation;
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

	@Test(expected = ArithmeticException.class)
	public void shouldThrowException1() {
		double result = cut.calc(0);
		System.out.println("noch da");
		Assert.assertEquals(21.0, result, 0.001);
	}

	@Test
	public void shouldThrowException2() {
		try {
			cut.calc(0);
		} catch (ArithmeticException e) {
			Assert.assertTrue(true);
			return;
		}
		Assert.fail("division by 0");
	}
}
