package commands;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import backendEntities.ApplicationUser;
import backendEntities.BookableEntity;
import backendEntities.Person;
/**
 * Abstract class of the {@code Command}s that save, update and remove data to the database.
 * 
 * @author Lucas Andrade
 *
 */
public abstract class DatabaseCommand implements Command{

	/**
	 * Opens and returns a {@code Session}.
	 * @return a {@code Session}
	 * @throws CommandException
	 */
	public Session openSession() throws CommandException{
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
	protected Person getThePerson(ApplicationUser user, String personName) throws CommandException {
		BookableEntity entity = user.getBookedEntities().get(personName);
		
		if(entity == null || ! (entity instanceof Person)){
			throw new CommandException();
		}
		
		return (Person)entity;	
	}
}
