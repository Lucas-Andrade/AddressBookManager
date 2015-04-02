package backendEntities;

import org.junit.Test;

import backendEntities.Contact;
import backendEntities.PhoneNumber;
import junit.framework.TestCase;

public class PersonTest extends TestCase {
	
	@Test
	public void shouldBeEqual(){
		assertFalse(true && false);
	}
	
	//TODO
	
	private Contact contact = new PhoneNumber(123456789);
	
	@Test
	public void testTheNumber(){
		assertEquals("123456789", contact.getProperty());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowAnIllegalArgumentException(){
		new PhoneNumber(12345678);
	}
}
