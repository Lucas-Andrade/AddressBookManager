package commands;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import backendEntities.ApplicationUser;
import backendEntities.Contact;
import backendEntities.Person;

public class NewPhoneNumberTest {

	@Test
	public void shouldAddThePhoneNumber() throws CommandException {
		
		SessionFactory sessionFact = SessionFactorySingleton.getInstance();
		Session session = sessionFact.openSession();
		
		ApplicationUser user = new ApplicationUser("user", "pass", "email@l.l");
		Person person = new Person("name");
		user.getBookedEntities().put(person.getName(), person);
		
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
		session.close();
		
		new NewPhoneNumber("user", "name", "123456789").execute();
		
		Session session2 = sessionFact.openSession();
		session2.beginTransaction();
		ApplicationUser user2 = (ApplicationUser) session2.get(ApplicationUser.class, "user");
		
		Person updatedPerson = (Person) user2.getBookedEntities().get("name");
		
		Set<Contact> set = updatedPerson.getContacts();
		Iterator<Contact> iterator = set.iterator();
		
		assertEquals(1, set.size());
		assertEquals("123456789", iterator.next().getContact());
		
		session2.getTransaction().commit();
		session2.close();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotAddThePhoneNumberWithANumberTooShort() throws CommandException{
		
		new NewPhoneNumber("user", "name", "123").execute();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotAddThePhoneNumberToAUserThatDoesNotExist() throws CommandException{
		
		new NewPhoneNumber("a user that does not exist", "name", "123456789").execute();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotAddThePhoneNumberToAPersonThatDoesNotExist() throws CommandException{
		
		new NewPhoneNumber("user", "a person that does not exist", "123456789").execute();
	}

}
