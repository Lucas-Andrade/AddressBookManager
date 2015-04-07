package commands.writers;

import static org.junit.Assert.*;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import commands.CommandException;
import commands.SessionFactorySingleton;
import commands.writers.RemoveAddress;
import commands.writers.RemovePhoneNumber;
import backendEntities.ApplicationUser;
import backendEntities.Contact;
import backendEntities.Person;
import backendEntities.PhoneNumber;

public class RemovePhoneNumberTest {

	@Test
	public void shouldRemoveThePhoneNumber() throws CommandException {
		
		SessionFactory sessionFact = SessionFactorySingleton.getInstance();
		Session session = sessionFact.openSession();
		
		ApplicationUser user = new ApplicationUser("user", "pass", "email@l.l");
		Person person = new Person("name", new PhoneNumber(123456789));
		user.getBookedEntities().put(person.getName(), person);
		
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
		session.close();
		
		new RemovePhoneNumber("user", "name", "123456789").execute();
		
		Session session2 = sessionFact.openSession();
		session2.beginTransaction();
		ApplicationUser user2 = (ApplicationUser) session2.get(ApplicationUser.class, "user");
		
		Person updatedPerson = (Person) user2.getBookedEntities().get("name");
		
		Set<Contact> set = updatedPerson.getContacts();
		
		assertEquals(0, set.size());
		
		session2.getTransaction().commit();
		session2.close();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotRemoveAPhoneNumberThatDoesNotExist() throws CommandException{
		
		new RemoveAddress("user", "name", "does not exist").execute();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotRemoveFromAPersonThatDoesNotExist() throws CommandException{
		
		new RemoveAddress("user", "does not exist", "street").execute();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotRemoveFromAnUserThatDoesNotExist() throws CommandException{
		
		new RemoveAddress("does not exist", "name", "street").execute();
	}

}
