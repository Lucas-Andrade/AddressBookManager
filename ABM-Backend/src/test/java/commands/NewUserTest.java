package commands;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import backendEntities.ApplicationUser;

public class NewUserTest {

	@Test
	public void shouldAddTheUser() throws CommandException {
		
		new NewUser("new username", "new password", "newEmail@mail.com").execute();
		
		SessionFactory sessionFact = SessionFactorySingleton.getInstance();
		Session session2 = sessionFact.openSession();
		session2.beginTransaction();
		ApplicationUser user = (ApplicationUser) session2.get(ApplicationUser.class, "new username");
		
		assertEquals("new username", user.getUsername());
		assertEquals("new password", user.getPassword());
		assertEquals("newEmail@mail.com", user.getEmail());
		
		session2.getTransaction().commit();
		session2.close();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotAddAUserWithTooShortPassword() throws CommandException{
		
		new NewUser("new username", "p", "newEmail@mail.com").execute();
	}
	
	@Test(expected = CommandException.class)
	public void shouldNotAddAUserWithInvalidEmail() throws CommandException{
		
		new NewUser("new username", "new password", "mail").execute();
	}

}
