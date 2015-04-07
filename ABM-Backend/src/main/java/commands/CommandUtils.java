package commands;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import backendEntities.ApplicationUser;
import backendEntities.BookableEntity;
import backendEntities.Person;

/**
 * Has some static utilitarian methods, that are used in the {@code Command}s.
 * 
 * @author Lucas Andrade
 *
 */
public class CommandUtils {

	/**
	 * So that it is not possible to instantiate {@code CommandUtils} objects.
	 */
	private CommandUtils(){}
	
	/**
	 * Opens and returns a {@code Session}. This {@code Session} must be closed later.
	 * @return a {@code Session}
	 * @throws CommandException
	 */
	public static Session openSession() throws CommandException{
		try{
			SessionFactory sessionFact = SessionFactorySingleton.getInstance();
			Session session = sessionFact.openSession();
			session.beginTransaction();
			return session;
		} catch(Exception e) {
			throw new CommandException();
		}
	}
	
	/**
	 * Gets the {@code Person} with the name passed as parameter from the {@code ApplicationUser} passed as parameter.
	 * 
	 * @param user
	 * @param personName
	 * @return the {@code Person} with the name passed as parameter
	 * @throws CommandException
	 */
	public static Person getThePerson(ApplicationUser user, String personName) throws CommandException {
		BookableEntity entity = user.getBookedEntities().get(personName);
		
		if(entity == null || ! (entity instanceof Person)){
			throw new CommandException();
		}
		
		return (Person)entity;
	}
	
}
