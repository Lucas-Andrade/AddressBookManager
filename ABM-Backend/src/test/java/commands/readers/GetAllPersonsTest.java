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
import commands.writers.RemovePerson;

public class GetAllPersonsTest {

	@Test
	public void shouldGetAllPersons() throws CommandException {
		
		SessionFactory sessionFact = SessionFactorySingleton.getInstance();
		Session session = sessionFact.openSession();
		
		ApplicationUser user = new ApplicationUser("user", "pass", "email@l.l");
		Person person1 = new Person("name1", new PhoneNumber(111111111), new PhoneNumber(222222222), new Address("street of place1"));
		Person person2 = new Person("name2", new PhoneNumber(111111111), new PhoneNumber(333333333), new Address("street of place2"), 
				new Address("street of place3"));
		
		Map<String, BookableEntity> bookedEntities = user.getBookedEntities();
		bookedEntities.put(person1.getName(), person1);
		bookedEntities.put(person2.getName(), person2);
		
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		
		session.close();
		
		JSONObject json = new GetAllPersons("user").call();
		System.out.println(json.toString());
	}

}
