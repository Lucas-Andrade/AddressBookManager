package commands.writers;

import org.hibernate.Session;

import commands.CommandException;
import commands.CommandUtils;
import backendEntities.ApplicationUser;
import backendEntities.Person;
import backendEntities.PhoneNumber;

/**
 * Command that adds a {@code PhoneNumber} to the database and associates it to a {@code Person}.
 * 
 * @author Lucas Andrade
 *
 */
public class NewPhoneNumber implements CommandWriter {

	/**
	 * Name of the {@code Person} to whom the {@code PhoneNumber} will be associated
	 */
	private String personName;
	
	/**
	 * number to be added to the {@code Person}.
	 */
	private String number;

	/**
	 * Username to whom the {@code Person} is assigned to.
	 */
	private String username;

	/**
	 * Constructs the command that adds a {@code PhoneNumber} to the database and associates it 
	 * to a {@code Person}.
	 * @param personName
	 * @param number
	 */
	public NewPhoneNumber(String username, String personName, int number){
		this.personName = personName;
		this.number = String.valueOf(number);
		this.username = username;
	}
	
	/**
	 * Constructs the command that adds a {@code PhoneNumber} to the database and associates it 
	 * to a {@code Person}.
	 * @param personName
	 * @param number
	 */
	public NewPhoneNumber(String username, String personName, String number){
		this.personName = personName;
		this.number = number;
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
			PhoneNumber phoneNumber = new PhoneNumber();
			phoneNumber.setContact(number);
			person.getContacts().add(phoneNumber);
			
			session.update(user);
			session.getTransaction().commit();
		}catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
	}

}
