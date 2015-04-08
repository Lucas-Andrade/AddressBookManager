package commands.readers;

import org.hibernate.Session;
import org.json.JSONObject;

import backendEntities.ApplicationUser;
import commands.CommandException;
import commands.CommandUtils;

/**
 * Command can verify if the username exists in the database and verifies if the password belongs to it. 
 * This allows to authenticate an {@code ApplicationUser}. Returns a boolean in a {@code JSONObject} as a 
 * flag of the success or failure of the authentication attempt.
 * 
 * @author Lucas Andrade
 *
 */
public class Authenticate implements CommandReader{
	
	/**
	 * Username of the {@code ApplicationUser} to be authenticated.
	 */
	private String username;
	
	/**
	 * Password of the {@code ApplicationUser} to be authenticated.
	 */
	private String password;

	/**
	 * Constructor of the command that can be used to authenticate an {@code ApplicationUser}.
	 * @param username
	 * @param password
	 */
	public Authenticate(String username, String password) {
		
		this.username = username;
		this.password = password;
	}

	/**
	 * @see CommandReader#call()
	 */
	public JSONObject call() throws CommandException {
		
		JSONObject json = new JSONObject();
		Session session = CommandUtils.openSession();
		try{
			ApplicationUser user = (ApplicationUser) session.get(ApplicationUser.class, username);
			
			if(user == null || ! user.getPassword().equals(password)) {
				json.put("Authentication", false);
			} else {
				json.put("Authentication", true);
			}
			
		}catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
		
		return json;
	}

}
