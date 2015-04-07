package commands.writers;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import commands.CommandException;
import commands.SessionFactorySingleton;
import commands.writers.UpdatePassword;
import backendEntities.Address;
import backendEntities.ApplicationUser;
import backendEntities.Person;

public class UpdatePasswordTest {

	@Test
	public void shouldUpdateTheEmail() throws CommandException {
		
		SessionFactory sessionFact = SessionFactorySingleton.getInstance();
		Session session = sessionFact.openSession();
		
		ApplicationUser user = new ApplicationUser("user", "pass", "email@l.l");
		Person person = new Person("name", new Address("street"));
		user.getBookedEntities().put(person.getName(), person);
		
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
		session.close();
		
		new UpdatePassword("user", "best password").execute();
		
		Session session2 = sessionFact.openSession();
		session2.beginTransaction();
		ApplicationUser user2 = (ApplicationUser) session2.get(ApplicationUser.class, "user");
		
		assertEquals("best password", user2.getPassword());
		
		session2.getTransaction().commit();
		session2.close();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotUpdateToAnInvalidPassword() throws CommandException{
		
		new UpdatePassword("user", "2").execute();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotUpdateAUserThatDoesNotExist() throws CommandException{
		
		new UpdatePassword("does not exist", "best password").execute();
	}

}
