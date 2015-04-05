package commands;

import org.hibernate.Session;

import backendEntities.ApplicationUser;

/**
 * Command that updates the username of the {@code ApplicationUser} to a new one.
 * 
 * @author Lucas Andrade
 *
 */
public class UpdateUsername extends DatabaseCommand{

	/**
	 * Old username of the {@code ApplicationUser}
	 */
	private String oldUsername;
	
	/**
	 * Future username of the {@code ApplicationUser}
	 */
	private String newUsername;

	/**
	 * Constructs the command that will update the oldUsername to the newUsername
	 * in the database.
	 * @param username
	 * @param newUsername
	 */
	public UpdateUsername(String username, String newUsername) {
		this.oldUsername = username;
		this.newUsername = newUsername;
	}
	
	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandException {
		
		Session session = openSession();
		try{
			
			ApplicationUser user = (ApplicationUser) session.get(ApplicationUser.class, oldUsername);
			user.setUsername(newUsername);
			
			session.update(user);
			session.getTransaction().commit();
			
		} catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
	}
	
	
}
