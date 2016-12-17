package de.fhws.app.business.usermgmt.boundary;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.fhws.app.business.usermgmt.entity.AppUser;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResources {
	
	@Inject
	UserMgmt userMgmt;
	
	@GET
	public List<AppUser> getAllUsers() {
		return userMgmt.getAllUsers();
	}
	

	@Path("test")
	@GET       
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/fhws"})
	public AppUser test() {
		AppUser au = new AppUser();
		au.setEmail("mickey.mouse@disney.com");
		au.setFirstname("Mickey");
		au.setLastname("Mouse");
		
		
		return au;
	}
}
