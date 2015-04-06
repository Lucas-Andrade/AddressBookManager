package commands;

import static org.junit.Assert.*;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import backendEntities.ApplicationUser;
import backendEntities.BookableEntity;
import backendEntities.Person;

public class RemovePersonTest {

	@Test
	public void shouldRemoveThePerson() throws CommandException {
		
		SessionFactory sessionFact = SessionFactorySingleton.getInstance();
		Session session = sessionFact.openSession();
		
		ApplicationUser user = new ApplicationUser("user", "pass", "email@l.l");
		Person person = new Person("name");
		user.getBookedEntities().put(person.getName(), person);
		
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
		session.close();
		
		new RemovePerson("user", "name").execute();
		
		Session session2 = sessionFact.openSession();
		session2.beginTransaction();
		ApplicationUser user2 = (ApplicationUser) session2.get(ApplicationUser.class, "user");
		
		Map<String, BookableEntity> map = user2.getBookedEntities();
		
		assertEquals(0, map.size());
		assertTrue(map.get("name") == null);
		
		session2.getTransaction().commit();
		session2.close();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotRemoveFromAUserThatDoesNotExist() throws CommandException{
		
		new RemovePerson("does not exist", "name").execute();
	}

}
