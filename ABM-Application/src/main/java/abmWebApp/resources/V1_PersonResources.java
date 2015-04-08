package abmWebApp.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import commands.CommandException;
import commands.readers.GetAllPersons;
import commands.readers.GetOnePerson;
import commands.writers.NewPerson;
import commands.writers.RemovePerson;
import commands.writers.UpdatePersonName;


@Path("v1/users/{username}/persons")
public class V1_PersonResources {

	/**
	 * Returns information about all the {@code Persons} associated with the
	 * {@code ApplicationUser}, that has the username in the path.
	 * 
	 * @param username
	 * @return
	 */ 
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllContacts(@PathParam("username") String username) {

		String toReturn = "Something went wrong.";
		try {
			toReturn = new GetAllPersons(username).call().toString();
		} catch (CommandException e) {
			return "erro";//Response.status(404).entity("Could not find person, or user.").build();
		}

		return toReturn;//Response.ok(toReturn).build();
	}
	
	
	/**
	 * Returns information about the {@code Person} with the name in the path.
	 * 
	 * @param username
	 * @param person
	 * @return
	 */
	@Path("/{person}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getOneContact(@PathParam("username") String username,
			@PathParam("person") String person) {

		String result = "Something went wrong.";
		try {
			result = new GetOnePerson(username, person).call().toString();
		} catch (CommandException e) {
			return Response.status(404)
					.entity("Could not find person, or user.").build();
		}

//		// TODO encode everything
//		String encodedResult = new Encoder(result).encode();
		Response response = Response.ok(result).build();
		return response;
	}
	
	
	@Path("/{person}")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response newPerson(@PathParam("username") String username,
			@PathParam("person") String person) {
		
		try {
			new NewPerson(username, person).execute();
		} catch (CommandException e) {
			return Response.status(422)
					.entity("Unable to process item.").build();
		}
		
		Response response = Response.status(201).build();
		return response;
	}
	
	@Path("/{person}/newname/{newName}")
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public Response editPerson(@PathParam("username") String username,
			@PathParam("person") String person, @PathParam("newName") String newName) {
		
		try {
			new UpdatePersonName(username, person, newName).execute();
		} catch (CommandException e) {
			return Response.status(400)
					.entity("Unable to process item.").build();
		}
		
		Response response = Response.ok("Updated").build();
		return response;
	}
	
	@Path("/{person}")
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public Response deletePerson(@PathParam("username") String username,
			@PathParam("person") String person) {
		
		try {
			new RemovePerson(username, person).execute();
		} catch (CommandException e) {
			return Response.status(422)
					.entity("Unable to process item.").build();
		}
		
		Response response = Response.ok("Removed").build();
		return response;
	}

}
