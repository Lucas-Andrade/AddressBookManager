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
import commands.writers.NewAddress;
import commands.writers.RemoveAddress;
import commands.writers.UpdateAddress;

/**
 * Defines the methods for the resources related to the {@code Address}es. 
 * 
 * @version 0.1.0
 * 
 * @author Lucas Andrade
 *
 */
@Path("v1/users/{username}/persons/{person}/address")
public class V1_AddressResources {
	
	/**
	 * Adds a new address to the person.
	 * 
	 * @param username
	 * @param person
	 * @param address
	 * @return
	 */
	@Path("/{address}")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response newAddress(@PathParam("username") String username,
			@PathParam("person") String person, @PathParam("address") String address) {
		
		try {
			new NewAddress(username, person, address).execute();
		} catch (CommandException e) {
			return Response.status(422)
					.entity("Unable to process item.").build();
		}
		
		Response response = Response.status(201).build();
		return response;
	}
	
	/**
	 * Updates the address to a new one.
	 * 
	 * @param username
	 * @param person
	 * @param address
	 * @param newAddress
	 * @return
	 */
	@Path("/{address}/newaddress/{newAddress}")
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public Response editAddress(@PathParam("username") String username,
			@PathParam("person") String person, @PathParam("address") String address, @PathParam("newAddress") String newAddress) {
		
		try {
			new UpdateAddress(username, person, address, newAddress).execute();
		} catch (CommandException e) {
			return Response.status(400)
					.entity("Unable to process item.").build();
		}
		
		Response response = Response.ok("Updated").build();
		return response;
	}

	/**
	 * Deletes the address.
	 * 
	 * @param username
	 * @param person
	 * @param address
	 * @return
	 */
	@Path("/{address}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteAddress(@PathParam("username") String username,
			@PathParam("person") String person, @PathParam("address") String address) {
		
		try {
			new RemoveAddress(username, person, address).execute();
		} catch (CommandException e) {
			return Response.status(422)
					.entity("Unable to process item.").build();
		}
		
		Response response = Response.ok("Removed").build();
		return response;
	}
}
