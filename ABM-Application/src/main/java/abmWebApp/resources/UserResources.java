package abmWebApp.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.*;


@Path("users")
public class UserResources {
	
	@Path("/{username}/persons")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getAllContacts(@PathParam("username") String username){
		
		
		
		return "<p> Persons </p>";
		
	}
}
