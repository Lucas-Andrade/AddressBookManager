package commands.writers;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import commands.CommandException;
import commands.SessionFactorySingleton;
import commands.writers.UpdateAddress;
import backendEntities.Address;
import backendEntities.ApplicationUser;
import backendEntities.Contact;
import backendEntities.Person;

public class UpdateAddressTest {

	@Test
	public void shouldUpdateTheAddress() throws CommandException {
		
		SessionFactory sessionFact = SessionFactorySingleton.getInstance();
		Session session = sessionFact.openSession();
		
		ApplicationUser user = new ApplicationUser("user", "pass", "email@l.l");
		Person person = new Person("name", new Address("street"));
		user.getBookedEntities().put(person.getName(), person);
		
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
		session.close();
		
		new UpdateAddress("user", "name", "street", "the new street").execute();
		
		Session session2 = sessionFact.openSession();
		session2.beginTransaction();
		ApplicationUser user2 = (ApplicationUser) session2.get(ApplicationUser.class, "user");
		
		Person updatedPerson = (Person) user2.getBookedEntities().get("name");
		
		Set<Contact> set = updatedPerson.getContacts();
		Iterator<Contact> iterator = set.iterator();
		
		assertEquals(1, set.size());
		assertEquals("the new street", iterator.next().getContact());
		
		session2.getTransaction().commit();
		session2.close();
	}

	@Test(expected = CommandException.class)
	public void shouldNotUpdateAnAddressThatDoesNotExist() throws CommandException{
		
		new UpdateAddress("user", "name", "does not exist", "the new street").execute();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotUpdateFromAPersonThatDoesNotExist() throws CommandException{
		
		new UpdateAddress("user", "does not exist", "street", "the new street").execute();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotUpdateFromAnUserThatDoesNotExist() throws CommandException{
		
		new UpdateAddress("does not exist", "name", "street", "the new street").execute();
	}
}
