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
import commands.writers.NewPhoneNumber;
import commands.writers.UpdateAddress;
import commands.writers.UpdatePhoneNumber;

@Path("v1/users/{username}/persons/{person}")
public class V1_ContactResources {

	@Path("/phonenumber/{phonenumber}")
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
	
	@Path("/address/{address}")
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

	@Path("/phonenumber/{phonenumber}/newphone/{newPhone}")
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
	
	@Path("/address/{address}/newaddress/{newAddress}")
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

	@Path("/phonenumber/{phonenumber}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public Response deletePhoneNumber(@PathParam("username") String username,
			@PathParam("person") String person, @PathParam("phonenumber") int phoneNumber) {
		
		try {
			new NewPhoneNumber(username, person, phoneNumber).execute();
		} catch (CommandException e) {
			return Response.status(422)
					.entity("Unable to process item.").build();
		}
		
		Response response = Response.ok("Removed").build();
		return response;
	}
	
	@Path("/address/{address}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public Response deleteAddress(@PathParam("username") String username,
			@PathParam("person") String person, @PathParam("address") String address) {
		
		try {
			new NewAddress(username, person, address).execute();
		} catch (CommandException e) {
			return Response.status(422)
					.entity("Unable to process item.").build();
		}
		
		Response response = Response.ok("Removed").build();
		return response;
	}
}
