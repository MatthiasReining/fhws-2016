package de.fhws.app.presentation;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class LocaleController implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
