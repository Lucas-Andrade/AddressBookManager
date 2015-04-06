package commands;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import backendEntities.Address;
import backendEntities.ApplicationUser;
import backendEntities.Person;

public class UpdateEmailTest {

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
		
		new UpdateEmail("user", "newEmail@bestemail.awessome").execute();
		
		Session session2 = sessionFact.openSession();
		session2.beginTransaction();
		ApplicationUser user2 = (ApplicationUser) session2.get(ApplicationUser.class, "user");
		
		assertEquals("newEmail@bestemail.awessome", user2.getEmail());
		
		session2.getTransaction().commit();
		session2.close();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotUpdateToAnInvalidEmail() throws CommandException{
		
		new UpdateEmail("user", "invalid").execute();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotUpdateAUserThatDoesNotExist() throws CommandException{
		
		new UpdateEmail("does not exist", "newEmail@bestemail.awessome").execute();
	}


}
