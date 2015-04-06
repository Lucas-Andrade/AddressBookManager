package commands;

import org.hibernate.Session;

import backendEntities.Address;
import backendEntities.ApplicationUser;
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
	private String personName;
	
	/**
	 * {@code Address} to add to the {@code Person}.
	 */
	private String address;

	/**
	 * Username of the {@code ApplicationUser} to whom the {@code Person} is associated
	 */
	private String username;

	/**
	 * Constructs the command that will add the address passed as parameter to the {@code Person}
	 * with the name passed as parameter. 
	 * @param personName
	 * @param address
	 */
	public NewAddress(String username, String personName, String address) {
		this.username = username;
		this.personName = personName;
		this.address = address;
	}
	
	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandException {

		Session session = openSession();
		try{
			ApplicationUser user = (ApplicationUser) session.get(ApplicationUser.class, username);
			
			Person person = getThePerson(user, personName);
			Address addressObj = new Address(address);
			person.getContacts().add(addressObj);
			
			session.update(user);
			session.getTransaction().commit();
			
		} catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
		
	}

}
