package de.fhws.app.presentation.showcase;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class MessageTest {
	
	Message cut;
	
	@Before
	public void init() {
		cut = new Message();
		cut.dc = new DataContainer();
	}

	@Test
	public void testCounter() {
		
		cut.init();
		String m = cut.getMessage();
		
		Assert.assertEquals("1 Message ", m);
		
		cut.init();
		m = cut.getMessage();
		
		Assert.assertEquals("2 Message ", m);
			
	}

}
