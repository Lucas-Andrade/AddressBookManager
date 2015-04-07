package commands.readers;

import static org.junit.Assert.*;

import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.junit.Test;

import backendEntities.Address;
import backendEntities.ApplicationUser;
import backendEntities.BookableEntity;
import backendEntities.Person;
import backendEntities.PhoneNumber;
import commands.CommandException;
import commands.SessionFactorySingleton;

public class GetOnePersonTest {

	@Test
	public void shouldGetThePersons() throws CommandException {
		
		SessionFactory sessionFact = SessionFactorySingleton.getInstance();
		Session session = sessionFact.openSession();
		
		ApplicationUser user = new ApplicationUser("user", "pass", "email@l.l");
		Person person1 = new Person("name1", new PhoneNumber(111111111), new PhoneNumber(222222222), new Address("street of place1"));
		Person person2 = new Person("name2", new PhoneNumber(444444444), new PhoneNumber(333333333), new Address("street of place2"), 
				new Address("street of place3"));
		
		Map<String, BookableEntity> bookedEntities = user.getBookedEntities();
		bookedEntities.put(person1.getName(), person1);
		bookedEntities.put(person2.getName(), person2);
		
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
		session.close();
		
		JSONObject json = new GetOnePerson("user", "name1").call();
		String jsonString = json.toString();
		
		assertTrue(jsonString.contains("\"Name\":\"name1\""));
		assertTrue(jsonString.contains("\"Address\":\"street of place1\""));
		assertTrue(jsonString.contains("\"Phone Number\":\"111111111\""));
		assertTrue(jsonString.contains("\"Phone Number\":\"222222222\""));
		
		assertFalse(jsonString.contains("\"Name\":\"name2\""));
		assertFalse(jsonString.contains("\"Address\":\"street of place2\""));
		assertFalse(jsonString.contains("\"Address\":\"street of place3\""));
		assertFalse(jsonString.contains("\"Phone Number\":\"333333333\""));
		assertFalse(jsonString.contains("\"Phone Number\":\"444444444\""));
	}

	@Test
	public void shouldGetNoPersons() throws CommandException {
		
		SessionFactory sessionFact = SessionFactorySingleton.getInstance();
		Session session = sessionFact.openSession();
		
		ApplicationUser user = new ApplicationUser("user2", "pass", "email@l.l");
		
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
		session.close();
		
		JSONObject json = new GetOnePerson("user2", "does not exist").call();
		String jsonString = json.toString();
		
		assertTrue(jsonString.contains("{}"));
	}
	
}
