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
 * Command that updates the {@code Address} of the {@code Person}.
 * 
 * @author Lucas Andrade
 *
 */
public class UpdateAddress implements CommandWriter{

	/**
	 * name of the {@code Person}
	 */
	private String personName;
	
	/**
	 * Future address of the {@code Person}
	 */
	private String newAddress;
	
	/**
	 * Old address of the {@code Person}
	 */
	private String oldAddress;

	/**
	 * The username of the {@code ApplicationUser} to whom the person is assigned
	 */
	private String username;

	/**
	 * Constructs the command that will update the address of the {@code Person}.
	 * in the database.
	 * @param personName
	 * @param oldAddress
	 * @param newAddress
	 */
	public UpdateAddress(String username, String personName, String oldAddress, String newAddress) {
		this.personName = personName;
		this.newAddress = newAddress;
		this.oldAddress = oldAddress;
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
			updateTheAddress(person, session);
			
			session.update(user);
			session.getTransaction().commit();
			
		} catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
	}

	/**
	 * Searches for the {@code Person}'s {@code Contact} with the specified oldAddress, and
	 * updates it to the new one. If the oldAddress isn't found a {@code CommandException}
	 * is thrown.
	 * @param person
	 * @throws CommandException
	 */
	private void updateTheAddress(Person person, Session session) throws CommandException {

		Iterator<Contact> iterator = person.getContacts().iterator();
		while (iterator.hasNext()){
			
			Contact contact = iterator.next();
			if(contact instanceof Address && 
						contact.getContact().equals(String.valueOf(oldAddress))){
				((Address)contact).setContact(newAddress);
				session.update(contact);
				return;
			}
		}
		throw new CommandException();
	}

}