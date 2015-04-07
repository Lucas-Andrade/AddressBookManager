package commands.writers;

import org.hibernate.Session;

import commands.CommandException;
import commands.CommandUtils;
import backendEntities.ApplicationUser;

/**
 * Command that updates the password of the {@code ApplicationUser} to a new one.
 * 
 * @author Lucas Andrade
 *
 */
public class UpdatePassword implements CommandWriter{

	/**
	 * username of the {@code ApplicationUser}
	 */
	private String username;
	
	/**
	 * Future password of the {@code ApplicationUser}
	 */
	private String newPassword;

	/**
	 * Constructs the command that will update the password of the {@code ApplicationUser}.
	 * in the database.
	 * @param username
	 * @param newPassword
	 */
	public UpdatePassword(String username, String newPassword) {
		this.username = username;
		this.newPassword = newPassword;
	}
	
	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandException {
		
		Session session = CommandUtils.openSession();
		try{
			
			ApplicationUser user = (ApplicationUser) session.get(ApplicationUser.class, username);
			user.setPassword(newPassword);
			
			session.update(user);
			session.getTransaction().commit();
			
		} catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
	}
	
	
}
