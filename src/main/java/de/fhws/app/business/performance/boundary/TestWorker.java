package de.fhws.app.business.performance.boundary;

public class TestWorker {

	@PerformanceLogger
	public void work() {
		System.out.println("start work");
		
		try {
			Thread.sleep((long)(Math.random() * 1000));
		} catch (InterruptedException e) {
		}
		System.out.println("work finished");
	}
}
