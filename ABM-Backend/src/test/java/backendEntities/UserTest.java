package backendEntities;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptEmailWithoutAt() {
		new ApplicationUser("user", "pass", "email");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptEmailMoreThanOneAt() {
		new ApplicationUser("user", "pass", "@em@ail");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptEmailWithoutADotInTheRightHandSideOfTheAt() {
		new ApplicationUser("user", "pass", "e@mail");
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptEmailWithoutAtLeast3CharactersInTheRightHadSideOfTheAt() {
		new ApplicationUser("user", "pass", "emai@.l");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptEmailWithoutCharactersInTheLeftHandSideOfTheAt() {
		new ApplicationUser("user", "pass", "@emai.l");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldNotAcceptWithLessThan4Characters() {
		new ApplicationUser("user", "pas", "ema@i.l");
	}
	
//TODO	
//	@Test
//	public void shouldNotAddRepeatedEntities(){
//		Person person = constructPerson();
//		User user = constructUser();
//		
//		assertTrue(user.addEntity(person));
//		assertFalse(user.addEntity(person));
//		assertEquals(1, user.getEntities().size());
//	}
//	
//	@Test
//	public void cannotRemoveAnEntityThatIsNotThere(){
//		assertFalse(constructUser().removeEntity("something"));
//	}
//	
//	@Test
//	public void shouldCorrectlyRemove(){
//		Person person = constructPerson();
//		User user = constructUser();
//		user.addEntity(person);
//		
//		assertEquals(1, user.getEntities().size());
//		assertTrue(user.removeEntity("name"));
//		assertEquals(0, user.getEntities().size());
//		assertFalse(user.removeEntity("name"));
//		assertEquals(0, user.getEntities().size());
//	}
//	
//	@Test
//	public void cannotGetAnEntityThatIsNotThere(){
//		assertNull(constructUser().getEntity("something"));
//	}
//	
//	@Test
//	public void shouldCorrectlyGet(){
//		Person person = constructPerson();
//		User user = constructUser();
//		user.addEntity(person);
//		
//		assertEquals(1, user.getEntities().size());
//		assertTrue(null != user.getEntity("name"));
//	}
	
	private Person constructPerson(){
		return new Person("name", new PhoneNumber(123456789));
	}
	
	private ApplicationUser constructUser(){
		return new ApplicationUser("user", "pass", "ema@i.l");
	}
}
