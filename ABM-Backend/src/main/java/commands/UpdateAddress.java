package commands;

import java.util.Iterator;

import org.hibernate.Session;

import backendEntities.Address;
import backendEntities.Contact;
import backendEntities.Person;

/**
 * Command that updates the {@code Address} of the {@code Person}.
 * 
 * @author Lucas Andrade
 *
 */
public class UpdateAddress extends DatabaseCommand{

	/**
	 * name of the {@code Person}
	 */
	private String name;
	
	/**
	 * Future address of the {@code Person}
	 */
	private String newAddress;
	
	/**
	 * Old address of the {@code Person}
	 */
	private String oldAddress;

	/**
	 * Constructs the command that will update the address of the {@code Person}.
	 * in the database.
	 * @param personName
	 * @param oldAddress
	 * @param newAddress
	 */
	public UpdateAddress(String personName, String oldAddress, String newAddress) {
		this.name = personName;
		this.newAddress = newAddress;
		this.oldAddress = oldAddress;
	}
	
	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandException {
		
		Session session = openSession();
		try{
			
			Person person = (Person)session.get(Person.class, name);
			updateTheAddress(person);
			
			session.update(person);
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
	private void updateTheAddress(Person person) throws CommandException {

		Iterator<Contact> iterator = person.getContacts().iterator();
		while (iterator.hasNext()){
			
			Contact contact = iterator.next();
			if(contact instanceof Address && 
						contact.getProperty().equals(String.valueOf(oldAddress))){
				((Address)contact).setAddress(newAddress);
				return;
			}
		}
		throw new CommandException();
	}

}