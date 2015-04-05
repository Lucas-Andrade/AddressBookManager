package commands;

import backendEntities.ApplicationUser;

/**
 * Adds a new {@code User} to the database
 * 
 * @author Lucas Andrade
 *
 */
public class NewUser extends DatabaseCommand{

	/**
	 * The username of the {@code User} to be added to the database.
	 */
	private String username;
	
	/**
	 * The password of the {@code User} to be added to the database.
	 */
	private String password;
	
	/**
	 * The email of the {@code User} to be added to the database.
	 */
	private String email;

	/**
	 * Constructs the command that will insert in the database the user with the username,
	 * password, and email passed as parameter.
	 * 
	 * @param username
	 * @param password
	 * @param email
	 */
	public NewUser(String username, String password, String email){
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	/**
	 * @see DatabaseCommand#execute()
	 */
	public void execute() throws CommandException {
		
		try{
			ApplicationUser user = new ApplicationUser(username, password, email);
			saveUser(user);
		} catch (IllegalArgumentException e) {
			throw new CommandException();
		}
	}

}
