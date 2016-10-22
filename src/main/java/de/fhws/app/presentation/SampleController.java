package de.fhws.app.presentation;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.Size;

@ManagedBean
public class SampleController {
	
	@Size(min=3, max=10)
	private String data1;
	private String data2;
	public String getData1() {
		return data1;
	}
	public void setData1(String data1) {
		this.data1 = data1;
	}
	public String getData2() {
		return data2;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
	
	public void submit() {
		System.out.println("Data");
		System.out.println(data1);
		System.out.println(data2);
	}

}
