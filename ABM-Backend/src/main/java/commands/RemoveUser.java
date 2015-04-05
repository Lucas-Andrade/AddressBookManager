package commands;

import org.hibernate.Session;

import backendEntities.ApplicationUser;

/**
 * Removes a {@code ApplicationUser} from the database.
 * 
 * @author Lucas Andrade
 *
 */
public class RemoveUser extends DatabaseCommand{

	/**
	 * The username of the {@code ApplicationUser} to be removed from the database.
	 */
	private String username;

	/**
	 * Constructs the command that will remove from the database the {@code ApplicationUser} with 
	 * the username passed as parameter.
	 * 
	 * @param username
	 * @param password
	 * @param email
	 */
	public RemoveUser(String username){
		this.username = username;
	}
	
	/**
	 * @see DatabaseCommand#execute()
	 */
	public void execute() throws CommandException {
		
		Session session = openSession();
		try{
			ApplicationUser user = (ApplicationUser) session.get(ApplicationUser.class, username);
			session.delete(user);
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
