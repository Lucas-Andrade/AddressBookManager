package commands;

import java.util.Map;

import org.hibernate.Session;

import backendEntities.ApplicationUser;
import backendEntities.BookableEntity;
import backendEntities.Person;

/**
 * Command that updates the name of the {@code Person} to a new one.
 * 
 * @author Lucas Andrade
 *
 */
public class UpdatePersonName extends DatabaseCommand{
	
	/**
	 * The name old name of the {@code Person}.
	 */
	private String oldName;
	
	/**
	 * The new name of the {@code Person}.
	 */
	private String newName;

	/**
	 * Username to whom the {@code Person} is associated with
	 */
	private String username;

	/**
	 * Constructs the command that will update the name of the {@code Person} 
	 * to the new name passed as parameter
	 * @param oldName
	 * @param newName
	 */
	public UpdatePersonName(String username, String oldName, String newName) {
		this.oldName = oldName;
		this.newName = newName;
		this.username = username;
	}
	
	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandException {

		Session session = openSession();
		try{
			ApplicationUser user = (ApplicationUser) session.get(ApplicationUser.class, username);
			
			Person person = getThePerson(user, oldName);
			
			person.setName(newName);
			Map<String, BookableEntity> bookedEntitiesMap = user.getBookedEntities();
			bookedEntitiesMap.remove(oldName);
			bookedEntitiesMap.put(newName, person);
			
			session.update(user);
			session.getTransaction().commit();
			
		} catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
		
	}
}
