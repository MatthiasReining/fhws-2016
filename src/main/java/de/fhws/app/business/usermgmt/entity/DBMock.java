package de.fhws.app.business.usermgmt.entity;

import java.util.ArrayList;
import java.util.List;

public class DBMock {

	static List<AppUser> users;
	static {
		users = new ArrayList<>();
		users.add(new AppUser("mickey.mouse@disney.com", "geheim", "Mickey", "Mouse"));
		users.add(new AppUser("donald.duck@disney.com", "geheim", "Donald", "Duck"));
		users.add(new AppUser("daisy.duck@disney.com", "geheim", "Daisy", "Duck"));

	}

	public List<AppUser> getAllUsers() {
		return users;
	}

	public AppUser getUserByEmail(String email) {

		for (AppUser au : users) {
			if (au.getEmail().equalsIgnoreCase(email))
				return au;
		}
		return null;
	}

}
