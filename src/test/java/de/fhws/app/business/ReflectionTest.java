package de.fhws.app.business;

import java.lang.reflect.Method;
import java.util.Date;

import org.junit.Test;

import de.fhws.app.business.log.entity.LogInfo;

public class ReflectionTest {
	
	@Test
	public void showLogInfoMethod() {
		
		
		LogInfo li = new LogInfo();
		li.setInserttime(new Date());
		li.setMessage("test");
		
		Class<?> clazz = li.getClass();
		for (Method m : clazz.getMethods()) {
			System.out.print(m.getName()  +     "...>");
			Class<?>[] paramClazzes = m.getParameterTypes();
			for(Class<?> pc : paramClazzes) {
				System.out.print(pc.getSimpleName() + "  -  ");
			}
			System.out.println();
					
		}
		
	}

}
