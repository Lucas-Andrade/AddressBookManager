package backendEntities;

import org.junit.Test;

public class ApplicationUserTest {

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
}
