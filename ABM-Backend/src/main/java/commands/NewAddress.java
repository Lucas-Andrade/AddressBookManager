package commands;

import org.hibernate.Session;

import backendEntities.Address;
import backendEntities.Person;

/**
 * Adds a new {@code Address} to a {@code Person}.
 * 
 * @author Lucas Address
 *
 */
public class NewAddress extends DatabaseCommand{

	/**
	 * Name of the {@code Person} to whom the {@code Address} will be added.
	 */
	private String name;
	
	/**
	 * {@code Address} to add to the {@code Person}.
	 */
	private String address;

	/**
	 * Constructs the command that will add the address passed as parameter to the {@code Person}
	 * with the name passed as parameter. 
	 * @param personName
	 * @param address
	 */
	public NewAddress(String personName, String address) {
		this.name = personName;
		this.address = address;
	}
	
	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandException {

		Session session = openSession();
//		try{
			Person person = new Person(name);
			Address addressObj = new Address(address);
			person.getContacts().add(addressObj);
			
			session.save(person);
			session.getTransaction().commit();
			
//		} catch(Exception e) {
//			throw new CommandException();
//		} finally {
			session.close();
//		}
		
	}

}
