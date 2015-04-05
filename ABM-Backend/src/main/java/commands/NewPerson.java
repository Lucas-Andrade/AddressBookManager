package commands;

import org.hibernate.Session;

import backendEntities.Person;

/**
 * Adds a new {@code Person} to the database
 * 
 * @author Lucas Andrade
 *
 */
public class NewPerson extends DatabaseCommand{

	/**
	 * The name of the {@code Person} that will be added to the database.
	 */
	private String name;

	/**
	 * Constructs the command that will instantiate a {@code Person} with the name
	 * passed as parameter, and add it to the database.
	 * @param name
	 */
	public NewPerson(String name) {
		this.name = name;
	}
	
	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandException {

		Session session = openSession();
		try{
			Person person = new Person(name);
			
			session.save(person);
			session.getTransaction().commit();
			
		} catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
		
	}

	
}
