package commands;

import org.hibernate.Session;

import backendEntities.ApplicationUser;

/**
 * Command that updates the email of the {@code ApplicationUser} to a new one.
 * 
 * @author Lucas Andrade
 *
 */
public class UpdateEmail extends DatabaseCommand{

	/**
	 * username of the {@code ApplicationUser}
	 */
	private String username;
	
	/**
	 * Future email of the {@code ApplicationUser}
	 */
	private String newEmail;

	/**
	 * Constructs the command that will update the email of the {@code ApplicationUser}
	 * in the database.
	 * @param username
	 * @param newEmail
	 */
	public UpdateEmail(String username, String newEmail) {
		this.username = username;
		this.newEmail = newEmail;
	}
	
	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandException {
		
		Session session = openSession();
		try{
			
			ApplicationUser user = (ApplicationUser) session.get(ApplicationUser.class, username);
			user.setEmail(newEmail);
			
			session.update(user);
			session.getTransaction().commit();
			
		} catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
	}
	
	
}