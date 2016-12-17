package de.fhws.app.business.usermgmt.boundary;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.fhws.app.business.usermgmt.entity.AppUser;

@Path("users")
public class UserResources {
	
	@Inject
	UserMgmt userMgmt;

	@GET       
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/fhws"})
	public AppUser getAllUsers() {
		AppUser au = new AppUser();
		au.setEmail("mickey.mouse@disney.com");
		au.setFirstname("Mickey");
		au.setLastname("Mouse");
		
		
		return au;
	}
}
