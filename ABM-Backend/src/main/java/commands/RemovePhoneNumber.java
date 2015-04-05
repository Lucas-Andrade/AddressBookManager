package commands;

import java.util.Iterator;

import org.hibernate.Session;

import backendEntities.Contact;
import backendEntities.Person;
import backendEntities.PhoneNumber;

/**
 * Command that removes a {@code PhoneNumber} from a {@code Person}.
 * 
 * @author Lucas Andrade
 *
 */
public class RemovePhoneNumber extends DatabaseCommand{

	/**
	 * Number to be removed.
	 */
	private int number;
	
	/**
	 * Name of the {@code Person}.
	 */
	private String name;

	/**
	 * Constructs the {@code Command} that removes the number passed as parameter
	 * from the {@code Person} whose name has been passed as parameter.
	 * @param personName
	 * @param number
	 */
	public RemovePhoneNumber(String personName, int number){
		this.name = personName;
		this.number = number;
	}
	
	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandException {

		Session session = openSession();
		try{
			
			Person person = (Person) session.get(Person.class, name);
			removeTheNumber(person, session);
			session.update(person);
			session.getTransaction().commit();
			
		} catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
		
	}

	/**
	 * Removes the phone number from the {@code Person}'s contacts, and from the database.
	 * If the number is not found a {@code CommandException} is thrown.
	 * @param person
	 * @param session
	 * @throws CommandException
	 */
	private void removeTheNumber(Person person, Session session) throws CommandException {

		Iterator<Contact> iterator = person.getContacts().iterator();
		while (iterator.hasNext()){
			
			Contact contact = iterator.next();
			if(contact instanceof PhoneNumber && 
						contact.getProperty().equals(String.valueOf(number))){
				person.getContacts().remove(contact);
				session.delete(contact);
				return;
			}
		}
		throw new CommandException();
		
	}

}
