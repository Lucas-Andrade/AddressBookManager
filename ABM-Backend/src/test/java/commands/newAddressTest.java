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

public class newAddressTest {

	@Test
	public void shouldAddAddress() throws CommandException {
		
		SessionFactory sessionFact = SessionFactorySingleton.getInstance();
		Session session = sessionFact.openSession();
		
		ApplicationUser user = new ApplicationUser("user", "pass", "email@l.l");
		Person person = new Person("name");
		user.getBookedEntities().put(person.getName(), person);
		
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
		session.close();
		
		new NewAddress("name", "street").execute();
		
		Session session2 = sessionFact.openSession();
		session2.beginTransaction();
		Person updatedPerson = (Person) session2.get(Person.class, "name");
		session2.getTransaction().commit();
		session2.close();
		
		Set<Contact> set = updatedPerson.getContacts();
		Iterator<Contact> iterator = set.iterator();
		
		System.out.println("tamanho do sete: " + set.size());
		System.out.println("nome da rua: " + iterator.next().getContact());
		assertEquals(1, set.size());
//		assertEquals("street", iterator.next().getProperty());
	}

}
