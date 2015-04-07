package commands.writers;

import org.hibernate.Session;

import commands.CommandException;
import commands.CommandUtils;
import backendEntities.ApplicationUser;
import backendEntities.Person;

/**
 * Adds a new {@code Person} to the database
 * 
 * @author Lucas Andrade
 *
 */
public class NewPerson implements CommandWriter{

	/**
	 * The name of the {@code Person} that will be added to the database.
	 */
	private String personName;
	
	/**
	 * Username to which the new {@code Person} will be associated
	 */
	private String username;

	/**
	 * Constructs the command that will instantiate a {@code Person} with the name
	 * passed as parameter, and add it to the database.
	 * @param personName
	 */
	public NewPerson(String username, String personName) {
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
			Person person = new Person(personName);
			user.getBookedEntities().put(personName, person);
			
			session.update(user);
			session.getTransaction().commit();
			
		} catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
	}

	
}
