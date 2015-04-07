package commands.writers;

import org.hibernate.Session;

import commands.CommandException;
import commands.CommandUtils;
import backendEntities.ApplicationUser;

/**
 * Removes a {@code Person} from the database.
 * 
 * @author Lucas Andrade
 *
 */
public class RemovePerson implements CommandWriter{

	/**
	 * Name of the {@code Person} to be removed.
	 */
	private String personName;
	
	/**
	 * Username to whom the {@code Person} is assigned to.
	 */
	private String username;

	/**
	 * Constructs the command that removes the person with the name passed as parameter
	 * from the database.
	 * @param personName
	 */
	public RemovePerson(String username, String personName){
		this.personName = personName;
		this.username = username;
	}
	
	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandException {
		
		Session session = CommandUtils.openSession();
		try{
			ApplicationUser user = (ApplicationUser) session.get(ApplicationUser.class, username);
			
			if( user.getBookedEntities().remove(personName) == null){
				throw new CommandException();
			}
			session.update(user);
			session.getTransaction().commit();
			
		} catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
	}

}
