package commands;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import backendEntities.ApplicationUser;
import backendEntities.Person;
import backendEntities.PhoneNumber;

public class RemoveUserTest {

	@Test
	public void shouldRemoveTheUser() throws CommandException {
		
		SessionFactory sessionFact = SessionFactorySingleton.getInstance();
		Session session = sessionFact.openSession();
		
		ApplicationUser user = new ApplicationUser("user", "pass", "email@l.l");
		Person person = new Person("name", new PhoneNumber(123456789));
		user.getBookedEntities().put(person.getName(), person);
		
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
		session.close();
		
		new RemoveUser("user").execute();
		
		Session session2 = sessionFact.openSession();
		session2.beginTransaction();
		
		assertNull(session2.get(ApplicationUser.class, "user"));
		
		session2.getTransaction().commit();
		session2.close();
	}
}
