package commands;

import org.hibernate.Session;

import backendEntities.Person;

/**
 * Command that updates the name of the {@code Person} to a new one.
 * 
 * @author Lucas Andrade
 *
 */
public class UpdatePersonName extends DatabaseCommand{
	
	/**
	 * The name old name of the {@code Person}.
	 */
	private String oldName;
	
	/**
	 * The new name of the {@code Person}.
	 */
	private String newName;

	/**
	 * Constructs the command that will update the name of the {@code Person} 
	 * to the new name passed as parameter
	 * @param oldName
	 * @param newName
	 */
	public UpdatePersonName(String oldName, String newName) {
		this.oldName = oldName;
		this.newName = newName;
	}
	
	/**
	 * @see Command#execute()
	 */
	public void execute() throws CommandException {

		Session session = openSession();
		try{
			Person person = (Person) session.get(Person.class, oldName);
			
			person.setName(newName);
			session.update(person);
			session.getTransaction().commit();
			
		} catch(Exception e) {
			throw new CommandException();
		} finally {
			session.close();
		}
		
	}
}
