package commands;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import backendEntities.ApplicationUser;
import backendEntities.BookableEntity;
import backendEntities.Contact;

/**
 * Abstract class of the {@code Command}s that save, update and remove data to the database.
 * 
 * @author Lucas Andrade
 *
 */
public abstract class DatabaseCommand implements Command{

	/**
	 * Saves the {@code BookableEntity} passed as parameter to the database
	 * @param user
	 * @throws CommandException
	 */
	public void saveEntity(BookableEntity entity) throws CommandException {
		saveToDatabase(entity);
	}
	
	/**
	 * Saves the {@code Contact} passed as parameter to the database
	 * @param user
	 * @throws CommandException
	 */
	public void saveContact(Contact contact) throws CommandException {
		saveToDatabase(contact);
	}
	
	/**
	 * Saves the {@code User} passed as parameter to the database
	 * @param user
	 * @throws CommandException
	 */
	public void saveUser(ApplicationUser user) throws CommandException {
		saveToDatabase(user);
	}
	
	/**
	 * Saves the object passed as parameter to the database. To avoid misuse this
	 * method is set to private
	 * @param object
	 * @throws CommandException 
	 */
	private void saveToDatabase(Object object) throws CommandException {
		
		SessionFactory sessionFact = SessionFactorySingleton.getInstance();
		Session session = sessionFact.openSession();
		
		try{
			session.beginTransaction();
			session.save(object);
			session.getTransaction().commit();
		} catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
		
	}
}
