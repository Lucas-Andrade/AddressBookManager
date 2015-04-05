package commands;

import java.util.Iterator;

import org.hibernate.Session;

import backendEntities.Contact;
import backendEntities.Person;
import backendEntities.PhoneNumber;

/**
 * Command that updates the {@code PhoneNumber} of the {@code Person}.
 * 
 * @author Lucas Andrade
 *
 */
public class UpdatePhoneNumber extends DatabaseCommand{

	/**
	 * name of the {@code Person}
	 */
	private String name;
	
	/**
	 * Future number of the {@code Person}
	 */
	private int newNumber;
	
	/**
	 * Old number of the {@code Person}
	 */
	private int oldNumber;

	/**
	 * Constructs the command that will update the password of the {@code ApplicationUser}.
	 * in the database.
	 * @param username
	 * @param newPassword
	 */
	public UpdatePhoneNumber(String personName, int oldNumber, int newNumber) {
		this.name = personName;
		this.newNumber = newNumber;
		this.oldNumber = oldNumber;
	}
	
	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandException {
		
		Session session = openSession();
		try{
			
			Person person = (Person) session.get(Person.class, name);
			updateTheNumber(person);
			
			session.update(person);
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
						contact.getProperty().equals(String.valueOf(oldNumber))){
				((PhoneNumber)contact).setNumber(newNumber);
				return;
			}
		}
		throw new CommandException();
	}

}
