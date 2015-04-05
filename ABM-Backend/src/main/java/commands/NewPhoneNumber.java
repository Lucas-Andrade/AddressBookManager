package commands;

import org.hibernate.Session;

import backendEntities.Person;
import backendEntities.PhoneNumber;

/**
 * Command that adds a {@code PhoneNumber} to the database and associates it to a {@code Person}.
 * 
 * @author Lucas Andrade
 *
 */
public class NewPhoneNumber extends DatabaseCommand {

	/**
	 * Name of the {@code Person} to whom the {@code PhoneNumber} will be associated
	 */
	private String name;
	
	/**
	 * number to be added to the {@code Person}.
	 */
	private int number;

	/**
	 * Constructs the command that adds a {@code PhoneNumber} to the database and associates it 
	 * to a {@code Person}.
	 * @param personName
	 * @param number
	 */
	public NewPhoneNumber(String personName, int number){
		this.name = personName;
		this.number = number;
	}
	
	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandException {

		Session session = openSession();
		try{ 
			PhoneNumber phoneNumber = new PhoneNumber(number);
			Person person = (Person) session.get(Person.class, name);
			person.getContacts().add(phoneNumber);
			
			session.save(person);
			session.getTransaction().commit();
		}catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
	}
	

}
