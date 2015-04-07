package abmWebApp.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import abmWebApp.encoder.Encoder;
import commands.CommandException;
import commands.readers.GetAllPersons;
import commands.readers.GetOnePerson;

/**
 * Defines the methods for the resources related to the {@code ApplicationUser}s. 
 * 
 * @version 0.1.0
 * 
 * @author Lucas Andrade
 *
 */
@Path("users")
public class UserResources {
	
	/**
	 * Returns information about the {@code Person} with the name in the path.
	 * 
	 * @param username
	 * @param person
	 * @return
	 */
	@Path("v1/{username}/persons/{person}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getOneContact(@PathParam("username") String username, @PathParam("person") String person){
		
		String result = "Something went wrong.";
		try {
			result = new GetOnePerson(username, person).call().toString();
		} catch (CommandException e) {
			return Response.status(404).entity("Could not find the person named: " + person).build();
		}
		
		//TODO encode everything
		String encodedResult = new Encoder(result).encode();
		Response response = Response.ok(result).build();
		return response;
	}
	
	/**
	 * Returns information about all the {@code Persons} associated with the {@code ApplicationUser}, that has the 
	 * username in the path.
	 * 
	 * @param username
	 * @return
	 */
	@Path("v1/{username}/persons")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllContacts(@PathParam("username") String username){
		
		String toReturn = "Something went wrong.";
		try {
			toReturn = new GetAllPersons(username).call().toString();
		} catch (CommandException e) {
			//TODO
			toReturn = "Error encountered\n@Get All";
		}
		
		return toReturn;
	}

}
