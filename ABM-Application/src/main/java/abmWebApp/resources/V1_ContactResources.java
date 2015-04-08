package abmWebApp.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import commands.CommandException;
import commands.writers.NewPhoneNumber;
import commands.writers.RemovePhoneNumber;
import commands.writers.UpdatePhoneNumber;

/**
 * Defines the methods for the resources related to the {@code Address}s. 
 * 
 * @version 0.1.0
 * 
 * @author Lucas Andrade
 *
 */
@Path("v1/users/{username}/persons/{person}/phonenumber")
public class V1_ContactResources {

	/**
	 * Adds a new phone number to the person
	 * 
	 * @param username
	 * @param person
	 * @param phoneNumber
	 * @return
	 */
	@Path("/{phonenumber}")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response newPhoneNumber(@PathParam("username") String username,
			@PathParam("person") String person, @PathParam("phonenumber") int phoneNumber) {
		
		try {
			new NewPhoneNumber(username, person, phoneNumber).execute();
		} catch (CommandException e) {
			return Response.status(422)
					.entity("Unable to process item.").build();
		}
		
		Response response = Response.status(201).build();
		return response;
	}
	
/**
 * Updates the phone number to a new one.
 * 
 * @param username
 * @param person
 * @param phoneNumber
 * @param newPhoneNumber
 * @return
 */
	@Path("/{phonenumber}/newphone/{newPhone}")
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public Response editPhoneNumber(@PathParam("username") String username,
			@PathParam("person") String person, @PathParam("phonenumber") int phoneNumber, @PathParam("newPhone") int newPhoneNumber) {
		
		try {
			new UpdatePhoneNumber(username, person, phoneNumber, newPhoneNumber).execute();
		} catch (CommandException e) {
			return Response.status(400)
					.entity("Unable to process item.").build();
		}
		
		Response response = Response.ok("Updated").build();
		return response;
	}

	/**
	 * Deletes the phone number.
	 * 
	 * @param username
	 * @param person
	 * @param phoneNumber
	 * @return
	 */
	@Path("/{phonenumber}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public Response deletePhoneNumber(@PathParam("username") String username,
			@PathParam("person") String person, @PathParam("phonenumber") int phoneNumber) {
		
		try {
			new RemovePhoneNumber(username, person, phoneNumber).execute();
		} catch (CommandException e) {
			return Response.status(422)
					.entity("Unable to process item.").build();
		}
		
		Response response = Response.ok("Removed").build();
		return response;
	}
	

}
