package commands.readers;

import org.hibernate.Session;
import org.json.JSONObject;

import backendEntities.ApplicationUser;
import commands.CommandException;
import commands.CommandUtils;

/**
 * Command that gets one {@code Person} booked by one {@code ApplicationUser}, and returns it as a
 * {@code JSONObject}.
 * 
 * @author Lucas Andrade
 *
 */
public class GetOnePerson {
	
	/**
	 * username of the person that booked the {@code Person}
	 */
	private String username;
	
	/**
	 * name of the {@code Person} to be looked for
	 */
	private String personName;
	
	/**
	 * Constructs the command that gets one{@code Person} by one {@code ApplicationUser}.
	 * @param username
	 * @param personName
	 */
	public GetOnePerson(String username, String personName){
		this.username = username;
		this.personName = personName;
	}
	
	/**
	 * @see CommandReader#call()
	 */
	public JSONObject call() throws CommandException {

		JSONObject json;
		Session session = CommandUtils.openSession();
		try{
			ApplicationUser user = (ApplicationUser) session.get(ApplicationUser.class, username);
			json = CommandUtils.getThePerson(user, personName).getJson();
			
		}catch(CommandException e) {
			json = new JSONObject();
		}catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
		
		return json;
	}
}
