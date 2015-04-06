package commands;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import backendEntities.ApplicationUser;
import backendEntities.Person;

public class UpdatePersonNameTest {

	@Test
	public void shouldUpdateThePersonName() throws CommandException {
		
		SessionFactory sessionFact = SessionFactorySingleton.getInstance();
		Session session = sessionFact.openSession();
		
		ApplicationUser user = new ApplicationUser("user", "pass", "email@l.l");
		Person person = new Person("name");
		user.getBookedEntities().put(person.getName(), person);
		
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
		session.close();
		
		new UpdatePersonName("user", "name", "best name").execute();
		
		Session session2 = sessionFact.openSession();
		session2.beginTransaction();
		ApplicationUser user2 = (ApplicationUser) session2.get(ApplicationUser.class, "user");
		
		Person updatedPerson = (Person) user2.getBookedEntities().get("best name");
		
		System.out.println(updatedPerson.getName());
		assertTrue(updatedPerson != null);
		
		session2.getTransaction().commit();
		session2.close();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotUpdateAUserThatDoesNotExist() throws CommandException{
		
		new UpdatePersonName("a user that does not exist", "name", "best name").execute();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotUpdateAPersonThatDoesNotExist() throws CommandException{
		
		new UpdatePersonName("user", "a person that does not exist", "best name").execute();
	}

}
