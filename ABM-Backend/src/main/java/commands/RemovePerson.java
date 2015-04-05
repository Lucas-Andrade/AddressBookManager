package commands;

import org.hibernate.Session;

import backendEntities.Person;

/**
 * Removes a {@code Person} from the database.
 * 
 * @author Lucas Andrade
 *
 */
public class RemovePerson extends DatabaseCommand{

	/**
	 * Name of the {@code Person} to be removed.
	 */
	private String name;

	/**
	 * Constructs the command that removes the person with the name passed as parameter
	 * from the database.
	 * @param name
	 */
	public RemovePerson(String name){
		this.name = name;
	}
	
	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandException {
		
		Session session = openSession();
		try{
			Person person = (Person) session.get(Person.class, name);
			
			session.delete(person);
			session.getTransaction().commit();
			
		} catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
	}

}
