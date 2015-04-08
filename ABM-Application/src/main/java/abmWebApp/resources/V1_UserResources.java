package abmWebApp.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import commands.CommandException;
import commands.writers.NewUser;
import commands.writers.RemoveUser;
import commands.writers.UpdateEmail;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Defines the methods for the resources related to the {@code ApplicationUser}s. 
 * 
 * @version 0.1.0
 * 
 * @author Lucas Andrade
 *
 */
@Path("v1/users")
public class V1_UserResources {
	
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response newUser(String incomingData){
		
		ObjectMapper mapper = new ObjectMapper();  
		UserEntry userEntry = null;
		try {
			userEntry = mapper.readValue(incomingData, UserEntry.class);
		} catch (Exception e1) {
			return Response.status(400).entity("Could not retrieve data.").build();
		}
		
		try {
			new NewUser(userEntry.USERNAME, userEntry.PASSWORD, userEntry.EMAIL).execute();
		} catch (CommandException e) {
			return Response.status(422).entity("Could not process entity.").build();
		}
		
		Response response = Response.ok(201).build();
		return response;
	}
	
	@Path("{username}/email/{email}")
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public Response editEmail(@PathParam("username") String username,
			@PathParam("email") String email){
		
		try {
			new UpdateEmail(username, email).execute();
		} catch (CommandException e) {
			return Response.status(422).entity("Could not process entity.").build();
		}
		
		Response response = Response.ok("Updated").build();
		return response;
	}
	
	@Path("{username}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteUser(@PathParam("username") String username){
		
		try {
			new RemoveUser(username).execute();
		} catch (CommandException e) {
			return Response.status(404).entity("Could not find user.").build();
		}
		
		Response response = Response.ok("Deleted.").build();
		return response;
	}
	
	
}



class UserEntry {
	
	protected String USERNAME;
	protected String PASSWORD;
	protected String EMAIL;
}