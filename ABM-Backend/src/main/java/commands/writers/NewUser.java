package commands.writers;

import org.hibernate.Session;

import commands.CommandException;
import commands.CommandUtils;
import backendEntities.ApplicationUser;

/**
 * Adds a new {@code ApplicationUser} to the database
 * 
 * @author Lucas Andrade
 *
 */
public class NewUser implements CommandWriter{

	/**
	 * The username of the {@code ApplicationUser} to be added to the database.
	 */
	private String username;
	
	/**
	 * The password of the {@code ApplicationUser} to be added to the database.
	 */
	private String password;
	
	/**
	 * The email of the {@code ApplicationUser} to be added to the database.
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
	 * @see CommandWriter#execute()
	 */
	public void execute() throws CommandException {
		
		Session session = CommandUtils.openSession();
		try{
			ApplicationUser user = new ApplicationUser(username, password, email);
			
			session.save(user);
			session.getTransaction().commit();
			
		} catch (IllegalArgumentException e) {
			throw new CommandException("Error constructing user.");
		} catch(Exception e) {
			throw new CommandException("Error saving to the database.");
		} finally {
			session.close();
		}
	}

}
