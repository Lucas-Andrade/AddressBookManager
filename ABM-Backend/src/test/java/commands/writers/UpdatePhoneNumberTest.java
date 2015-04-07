package commands.writers;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import commands.CommandException;
import commands.SessionFactorySingleton;
import commands.writers.UpdatePhoneNumber;
import backendEntities.ApplicationUser;
import backendEntities.Contact;
import backendEntities.Person;
import backendEntities.PhoneNumber;

public class UpdatePhoneNumberTest {

	@Test
	public void shouldUpdateThePhoneNumber() throws CommandException {
		
		SessionFactory sessionFact = SessionFactorySingleton.getInstance();
		Session session = sessionFact.openSession();
		
		ApplicationUser user = new ApplicationUser("user", "pass", "email@l.l");
		Person person = new Person("name", new PhoneNumber(123456789));
		user.getBookedEntities().put(person.getName(), person);
		
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
		session.close();
		
		new UpdatePhoneNumber("user", "name", "123456789", "987654321").execute();
		
		Session session2 = sessionFact.openSession();
		session2.beginTransaction();
		ApplicationUser user2 = (ApplicationUser) session2.get(ApplicationUser.class, "user");
		
		Person updatedPerson = (Person) user2.getBookedEntities().get("name");
		
		Set<Contact> set = updatedPerson.getContacts();
		Iterator<Contact> iterator = set.iterator();
		
		assertEquals(1, set.size());
		assertEquals("987654321", iterator.next().getContact());
		
		session2.getTransaction().commit();
		session2.close();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotUpdateThePhoneNumberToANumberTooShort() throws CommandException{
		
		new UpdatePhoneNumber("user", "name", "123456789", "123").execute();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotUpdateThePhoneNumberToAUserThatDoesNotExist() throws CommandException{
		
		new UpdatePhoneNumber("a user that does not exist", "name", "123456789", "123456789").execute();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotUpdateThePhoneNumberToAPersonThatDoesNotExist() throws CommandException{
		
		new UpdatePhoneNumber("user", "a person that does not exist", "123456789", "123456789").execute();
	}

}
