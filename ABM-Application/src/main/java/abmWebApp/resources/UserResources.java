package abmWebApp.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import commands.CommandException;
import commands.readers.GetOnePerson;


@Path("users")
public class UserResources {
	
	@Path("/{username}/persons")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllContacts(@PathParam("username") String username){
		
		String toReturn = "by defenition";
		try {
			toReturn = new GetOnePerson("utilizador", "nome").call().toString();
		} catch (CommandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return toReturn;
		
	}
}
