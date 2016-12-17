package de.fhws.app.business.usermgmt.boundary;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.fhws.app.business.usermgmt.entity.AppUser;
import de.fhws.app.business.usermgmt.entity.EventLog;

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResources {

	@Inject
	UserMgmt userMgmt;

	@GET
	public List<AppUser> getAllUsers(@QueryParam("firstname") String firstname) {
		List<AppUser> allUsers = userMgmt.getAllUsers(); //do filter in EJB
		if (firstname == null || firstname.isEmpty())
			return allUsers;
		
		List<AppUser> filteredResults = new ArrayList<>();
		for(AppUser au : allUsers) {
			if (firstname.equalsIgnoreCase(au.getFirstname()))
				filteredResults.add(au);
		}
		return filteredResults;
		
	}

	@GET
	@Path("{id}")
	public AppUser getUser(@PathParam("id") long id) {
		return userMgmt.getUserById(id);
	}

	@GET
	@Path("{id}/events")
	public List<EventLog> getEvents(@PathParam("id") long id) {
		return userMgmt.getUserById(id).getEvents();
	}

	@GET
	@Path("{id}/events/{eventId}")
	public Response getEvents(@PathParam("id") long id, @PathParam("eventId") long eventId) {
		List<EventLog> events = userMgmt.getUserById(id).getEvents();
		for (EventLog el : events) {
			if (el.getId() == eventId)
				return Response.ok(el, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
		}

		return Response.status(Response.Status.NOT_FOUND).build();
	}

	@PUT
	@Path("{id}")
	public Response updateAppUser(AppUser appUser) {

		userMgmt.save(appUser);

		return Response.status(Status.NO_CONTENT).build();
	}

	@Path("test")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, "application/fhws" })
	public AppUser test() {
		AppUser au = new AppUser();
		au.setEmail("mickey.mouse@disney.com");
		au.setFirstname("Mickey");
		au.setLastname("Mouse");

		return au;
	}
}
