package commands.readers;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.junit.Test;

import backendEntities.ApplicationUser;
import commands.CommandException;
import commands.SessionFactorySingleton;

public class AuthenticateTest {

	@Test
	public void shouldAuthenticate() throws CommandException {
		
		SessionFactory sessionFact = SessionFactorySingleton.getInstance();
		Session session = sessionFact.openSession();
		
		ApplicationUser user = new ApplicationUser("user", "pass", "email@l.l");
		
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		
		JSONObject json = new Authenticate("user", "pass").call();
		String jsonString = json.toString();
		
		assertTrue(jsonString.contains("\"Authentication\":true"));
	}
	
	@Test
	public void shouldNotAuthenticateWithWrongPassword() throws CommandException {
		
		JSONObject json = new Authenticate("user", "incorrect").call();
		String jsonString = json.toString();
		
		assertTrue(jsonString.contains("\"Authentication\":false"));
	}
	
	@Test
	public void shouldNotAuthenticateWithWrongUsername() throws CommandException {
		
		JSONObject json = new Authenticate("incorrect", "pass").call();
		String jsonString = json.toString();
		
		assertTrue(jsonString.contains("\"Authentication\":false"));
	}
}
