package backendEntities;

import static org.junit.Assert.*;

import org.junit.Test;

public class PersonTest {

	@Test
	public void shouldBeEqual() {
		Person person2 = constructPerson();
		Person person1 = constructPerson();
		assertTrue(person1.equals(person2));
	}
	
	@Test
	public void shouldBeEqualToItself(){
		Person person = constructPerson();
		assertTrue(person.equals(person));
	}
	
	@Test
	public void shouldBeDifferentThanANull(){
		Person person = constructPerson();
		assertFalse(person.equals(null));
	}
	
	@Test
	public void shouldBeDifferentThanAnObject(){
		Person person = constructPerson();
		assertFalse(person.equals(new Object()));
	}

	@Test
	public void shouldBeDifferentDespiteHavingTheSameName(){
		Person person = constructPerson();
		Person person2 = new Person("name", new Contact[]{});
		assertFalse(person.equals(person2));
	}
	
	@Test
	public void shouldBeDifferentDespiteHavingSameContacts(){
		Person person = constructPerson();
		Contact[] contacts = constructContacts();
		Person person2 = new Person("another name", contacts);
		
		assertFalse(person.equals(person2));
	}
	
	private Contact[] constructContacts() {
		return new Contact[]{new PhoneNumber(123456789), new Address("Avenue xyz, nr 42, Earth")};
	}
	
	private Person constructPerson(){
		return new Person("name", constructContacts());
	}
}
