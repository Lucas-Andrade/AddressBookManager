package commands.writers;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import commands.CommandException;
import commands.SessionFactorySingleton;
import commands.writers.NewPerson;
import backendEntities.ApplicationUser;
import backendEntities.Person;

public class NewPersonTest {

	@Test
	public void shouldAddThePerson() throws CommandException {

		SessionFactory sessionFact = SessionFactorySingleton.getInstance();
		Session session = sessionFact.openSession();
		
		ApplicationUser user = new ApplicationUser("user", "pass", "email@l.l");
		
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
		session.close();
		
		new NewPerson("user", "new person's name").execute();
		
		Session session2 = sessionFact.openSession();
		session2.beginTransaction();
		ApplicationUser user2 = (ApplicationUser) session2.get(ApplicationUser.class, "user");
		
		Person person = (Person) user2.getBookedEntities().get("new person's name");
				
		assertTrue(person != null);
		assertEquals("new person's name", person.getName());
		
		session2.getTransaction().commit();
		session2.close();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotAddThePerson() throws CommandException{
		
		new NewPerson("a user that does not exist", "a person that does not exist").execute();
	}

}
