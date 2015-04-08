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
import commands.readers.Authenticate;
import commands.writers.NewUser;
import commands.writers.RemoveUser;
import commands.writers.UpdateEmail;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

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
	
	/**
	 * Adds a new user.
	 * 
	 * @param incomingData
	 * @return
	 */
	@Path("signup/{username}/{password}/{email}")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response newUser(@PathParam("username") String username, @PathParam("password") String password, 
			@PathParam("email") String email){//String incomingData){
		
//		ObjectMapper mapper = new ObjectMapper();  
//		UserSignup userEntry = null;
//		try {
//			userEntry = mapper.readValue(incomingData, UserSignup.class);
//		} catch (Exception e1) {
//			return Response.status(400).entity("Could not retrieve data.").build();
//		}
		
		try {
			new NewUser(username, password, email).execute();//userEntry.USERNAME, userEntry.PASSWORD, userEntry.EMAIL).execute();
		} catch (CommandException e) {
			return Response.status(422).entity("Could not process entity.").build();
		}
		
		Response response = Response.status(201).build();
		return response;
	}
	
	/**
	 * Authenticates a user.
	 * 
	 * @param incomingData
	 * @return
	 */
	@Path("/login/{username}/{password}")
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response authenticateUser(@PathParam("username") String username, @PathParam("password") String password ){//String incomingData){
		
//		ObjectMapper mapper = new ObjectMapper();  
//		UserSignup userEntry = null;
//		try {
//			userEntry = mapper.readValue(incomingData, UserSignup.class);
//		} catch (Exception e1) {
//			return Response.status(400).entity("Could not retrieve data.").build();
//		}
		
		JSONObject json;
		try {
			json = new Authenticate(username, password).call();//userEntry.USERNAME, userEntry.PASSWORD).call();
		} catch (CommandException e) {
			return Response.status(422).entity("Could not process entity.").build();
		}
		
		if(json.toString().contains("\"Authentication\":false")){
			return Response.status(401).entity("Wrong login data.").build();
		}
		
		return Response.status(200).entity(json.toString()).build();
	}
	
	/**
	 * Updates the user's email to a new one.
	 * 
	 * @param username
	 * @param email
	 * @return
	 */
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
	
	/**
	 * Deletes the user.
	 * 
	 * @param username
	 * @return
	 */
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

///**
// * Used to map information about the user from a form, to enable creating a new user.
// * 
// * @author Lucas Andrade
// *
// */
//class UserSignup {
//	
//	protected String USERNAME;
//	protected String PASSWORD;
//	protected String EMAIL;
//}
//
///**
// * Used to map information about the user from a form, to enable authenticating the user.
// * 
// * @author Lucas Andrade
// *
// */
//class UserLogin {
//	
//	protected String USERNAME;
//	protected String PASSWORD;
//}