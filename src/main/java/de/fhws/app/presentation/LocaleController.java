package de.fhws.app.presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LocaleController {
	
	private String lang;
	
	public void changeLanguage(String lang) {
		System.out.println("set lang to "+ lang);
		this.lang = lang;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}
	
	
	

}
