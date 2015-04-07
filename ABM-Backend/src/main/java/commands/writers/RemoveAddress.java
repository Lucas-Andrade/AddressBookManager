package commands.writers;

import java.util.Iterator;

import org.hibernate.Session;

import commands.CommandException;
import commands.CommandUtils;
import backendEntities.Address;
import backendEntities.ApplicationUser;
import backendEntities.Contact;
import backendEntities.Person;

/**
 * Command that removes a {@code Address} from a {@code Person}.
 * 
 * @author Lucas Andrade
 *
 */
public class RemoveAddress implements CommandWriter{

	/**
	 * Address to be removed.
	 */
	private String address;
	
	/**
	 * Name of the {@code Person}.
	 */
	private String personName;

	/**
	 * The username of the {@code ApplicationUser} to whom the person is assigned
	 */
	private String username;

	/**
	 * Constructs the {@code Command} that removes the address passed as parameter
	 * from the {@code Person} whose name has been passed as parameter.
	 * @param personName
	 * @param address
	 */
	public RemoveAddress(String username, String personName, String address){
		this.personName = personName;
		this.address = address;
		this.username = username;
	}
	
	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandException {

		Session session = CommandUtils.openSession();
		try{
			ApplicationUser user = (ApplicationUser) session.get(ApplicationUser.class, username);
			
			Person person = CommandUtils.getThePerson(user, personName);
			removeTheAddress(person, session);
			session.update(user);
			session.getTransaction().commit();
			
		} catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
		
	}

	/**
	 * Removes the address from the {@code Person}'s contacts, and from the database.
	 * If the address is not found a {@code CommandException} is thrown.
	 * @param person
	 * @param session
	 * @throws CommandException
	 */
	private void removeTheAddress(Person person, Session session) throws CommandException {

		Iterator<Contact> iterator = person.getContacts().iterator();
		while (iterator.hasNext()){
			
			Contact contact = iterator.next();
			if(contact instanceof Address && 
						contact.getContact().equals(String.valueOf(address))){
				person.getContacts().remove(contact);
				session.delete(contact);
				return;
			}
		}
		throw new CommandException();
		
	}

}
