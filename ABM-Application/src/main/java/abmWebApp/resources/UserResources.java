package abmWebApp.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import commands.CommandException;
import commands.readers.GetAllPersons;
import commands.readers.GetOnePerson;


@Path("users")
public class UserResources {
	
	@Path("/{username}/persons/{person}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getOneContact(@PathParam("username") String username, @PathParam("person") String person){
		
		String toReturn = "by defenition\n@Get One";
		try {
			toReturn = new GetOnePerson(username, person).call().toString();
		} catch (CommandException e) {
			//TODO
			toReturn = "Error encountered\n@Get One";
		}
		
		return toReturn;
	}
	
	@Path("/{username}/persons")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllContacts(@PathParam("username") String username){
		
		String toReturn = "by defenition\n@Get All";
		try {
			toReturn = new GetAllPersons(username).call().toString();
		} catch (CommandException e) {
			//TODO
			toReturn = "Error encountered\n@Get All";
		}
		
		return toReturn;
	}

}
