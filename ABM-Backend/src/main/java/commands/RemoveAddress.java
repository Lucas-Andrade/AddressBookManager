package commands;

import java.util.Iterator;

import org.hibernate.Session;

import backendEntities.Address;
import backendEntities.Contact;
import backendEntities.Person;

/**
 * Command that removes a {@code Address} from a {@code Person}.
 * 
 * @author Lucas Andrade
 *
 */
public class RemoveAddress extends DatabaseCommand{

	/**
	 * Address to be removed.
	 */
	private String address;
	
	/**
	 * Name of the {@code Person}.
	 */
	private String name;

	/**
	 * Constructs the {@code Command} that removes the address passed as parameter
	 * from the {@code Person} whose name has been passed as parameter.
	 * @param personName
	 * @param address
	 */
	public RemoveAddress(String personName, String address){
		this.name = personName;
		this.address = address;
	}
	
	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandException {

		Session session = openSession();
		try{
			
			Person person = (Person)session.get(Person.class, name);
			removeTheAddress(person, session);
			session.update(person);
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
						contact.getProperty().equals(String.valueOf(address))){
				person.getContacts().remove(contact);
				session.delete(contact);
				return;
			}
		}
		throw new CommandException();
		
	}

}
