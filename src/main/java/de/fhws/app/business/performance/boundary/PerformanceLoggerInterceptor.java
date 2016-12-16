package de.fhws.app.business.performance.boundary;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@PerformanceLogger
public class PerformanceLoggerInterceptor implements Serializable {
	private static final long serialVersionUID = 1L;

	@AroundInvoke
	public Object aroundInvoke(InvocationContext ic) throws Exception {
		
		long t = System.currentTimeMillis();
		
				
		Object obj = ic.proceed();
		
		t = System.currentTimeMillis() - t;
		
		System.out.println( ic.getTarget().getClass().getName() + "#" + ic.getMethod().getName() + " executed in " + t + "ms" );
		
		return obj;
	}

}
