package commands.writers;

import java.util.Iterator;

import org.hibernate.Session;

import commands.CommandException;
import commands.CommandUtils;
import backendEntities.ApplicationUser;
import backendEntities.Contact;
import backendEntities.Person;
import backendEntities.PhoneNumber;

/**
 * Command that updates the {@code PhoneNumber} of the {@code Person}.
 * 
 * @author Lucas Andrade
 *
 */
public class UpdatePhoneNumber implements CommandWriter{

	/**
	 * name of the {@code Person}
	 */
	private String personName;
	
	/**
	 * Future number of the {@code Person}
	 */
	private String newNumber;
	
	/**
	 * Old number of the {@code Person}
	 */
	private String oldNumber;

	/**
	 * Username of the {@code ApplicationUser} to whom the {@code Person} is associated
	 */
	private String username;

	/**
	 * Constructs the command that will update the phone number of the {@code ApplicationUser}
	 * in the database.
	 * 
	 * @param username
	 * @param personName
	 * @param oldNumber
	 * @param newNumber
	 */
	public UpdatePhoneNumber(String username, String personName, int oldNumber, int newNumber) {
		this.personName = personName;
		this.newNumber = String.valueOf(newNumber);
		this.oldNumber = String.valueOf(oldNumber);
		this.username = username;
	}
	
	/**
	 * Constructs the command that will update the phone number of the {@code ApplicationUser}
	 * in the database.
	 * 
	 * @param username
	 * @param personName
	 * @param oldNumber
	 * @param newNumber
	 */
	public UpdatePhoneNumber(String username, String personName, String oldNumber, String newNumber) {
		this.personName = personName;
		this.newNumber = newNumber;
		this.oldNumber = oldNumber;
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
			updateTheNumber(person);
			
			session.update(user);
			session.getTransaction().commit();
			
		} catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
	}

	/**
	 * Searches for the {@code Person}'s {@code Contact} with the specified oldNumber, and
	 * updates it to the new one. If the oldNumber isn't found a {@code CommandException}
	 * is thrown.
	 * @param person
	 * @throws CommandException
	 */
	private void updateTheNumber(Person person) throws CommandException {

		Iterator<Contact> iterator = person.getContacts().iterator();
		while (iterator.hasNext()){
			
			Contact contact = iterator.next();
			if(contact instanceof PhoneNumber && 
						contact.getContact().equals(String.valueOf(oldNumber))){
				((PhoneNumber)contact).setContact(newNumber);
				return;
			}
		}
		throw new CommandException();
	}

}
