package backendEntities;

import org.junit.Test;

import backendEntities.Contact;
import backendEntities.PhoneNumber;
import junit.framework.TestCase;

public class PhoneNumberTest extends TestCase {

	private Contact contact = new PhoneNumber(123456789);
	
	@Test
	public void testTheNumber(){
		assertEquals("123456789", contact.getContact());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowAnIllegalArgumentException(){
		new PhoneNumber(12345678);
	}
}
