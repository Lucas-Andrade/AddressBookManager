package backendEntities;

import java.util.Map;
import java.util.TreeMap;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * The {@code User} represents the entity that will use the application. The {@code User} possesses 
 * a {@code Map} of {@code Entities}, that are the entities {@code this} {@code User} booked.
 * 
 * A {@code User} must have an username, a password, and an email.
 * 
 * @author Lucas Andrade
 *
 */
@Entity
public class User {
	
	/**
	 * The entities {@code this} {@code User} has booked.
	 */
	@OneToMany(cascade = CascadeType.ALL)
	private Map<String, BookableEntity> bookedEntities = new TreeMap<String, BookableEntity>();
	
	/**
	 * {@code this} {@code User}'s username.
	 */
	@Id
	private String username;
	
	/**
	 * {@code this} {@code User}'s password.
	 */
	private String password;
	
	/**
	 * {@code this} {@code User}'s email.
	 */
	private String email;
	
	/**
	 * Lock that must be acquired by every thread trying to access {@code this} {@code User}.
	 */
	@Transient
	private final Object USERLOCK = new Object();
	
	/**
	 * Constructs a new {@code User} with an username, password and email. It is verified
	 * if the email is valid. If the email is deemed not valid, an {@code IllegalArgumentException}
	 * is thrown. The {@code Set} of booked entities by {@code this} user is empty, and the 
	 * entities can be added later.
	 * 
	 * @param username
	 * @param password
	 * @param email
	 */
	public User(String username, String password, String email){
		
		emailChecker(email);
		passwordChecker(password);
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	/**
	 * Default constructer for the {@code User}. Constructs a {@code User} object
	 * with no information: no username, password or email.
	 */
	public User(){}
	
	/**
	 * @return the {@code Map} of booked entities by {@code this} {@code User}.
	 */
	public Map<String, BookableEntity> getBookedEntities() {
		synchronized(USERLOCK){
			return bookedEntities;
		}
	}
	
	/**
	 * Sets a new {@code Map} as the booked entities by {@code this} {@code User}.
	 * @param bookedEntities
	 */
	public void setBookedEntities(Map<String, BookableEntity> bookedEntities) {
		synchronized(USERLOCK){
			this.bookedEntities = bookedEntities;
		}
	}
	
	/**
	 * @return {@code this} {@code User}'s username
	 */
	public String getUsername(){
		synchronized(USERLOCK){
			return username;
		}
	}
	
	/**
	 * @return {@code this} {@code User}'s password
	 */
	public String getPassword(){
		synchronized(USERLOCK){
			return password;
		}
	}
	
	/**
	 * @return {@code this} {@code User}'s email
	 */
	public String getEmail(){
		synchronized(USERLOCK){
			return email;
		}
	}
	
	/**
	 * Sets a new password for {@this} {@code User}. If it does not have the minimum
	 * number of characters an {@code IllegalArgumentException} will be thrown.
	 * @param newPassword
	 */
	public void setPassword(String newPassword){
		passwordChecker(newPassword);
		synchronized(USERLOCK){
			this.password = newPassword;
		}
	}
	
	/**
	 * Sets a new email for {@this} {@code User}. If it does not have the minimum
	 * requirements to be considered valid an {@code IllegalArgumentException} will be thrown.
	 * @param newEmail
	 */
	public void setEmail(String newEmail){
		emailChecker(newEmail);
		synchronized(USERLOCK){
			this.email = newEmail;
		}
	}
	
	/**
	 * Sets a new username for {@this} {@code User}. 
	 * @param username
	 */
	public void setUsername(String username) {
		synchronized(USERLOCK){
			this.username = username;
		}
	}
	
	/**
	 * Verifies if the email has some requirements to be considered valid. If must contain one
	 * and only one '@', and, in the right hand side of the '@' the {@code String} must have at least
	 * 3 characters, and a dot must be one of them. The {@code String} to the left hand side of '@' must
	 * have at least one character.
	 * 
	 * If at least one of these requirements is not fulfilled, an {@code IllegalArgumentException} is thrown.
	 * @param email
	 */
	private void emailChecker(String email) {
		String invalid = "Invalid email.";
		if(!email.contains("@")){
			throw new IllegalArgumentException(invalid);
		}
		
		String[] emailWithoutAt = email.split("@");
		if(emailWithoutAt.length != 2) {
			throw new IllegalArgumentException(invalid);
		}
		
		if(emailWithoutAt[0].length() == 0 || emailWithoutAt[1].length() < 3 || !emailWithoutAt[1].contains(".")){
			throw new IllegalArgumentException(invalid);
		}
	}
	
	/**
	 * Verifies whether the password has at least 4 characters. If it has not, an 
	 * {@code IllegalArgumentException} is thrown.
	 * @param password
	 */
	private void passwordChecker(String password) {
		if(password.length() < 4) {
			throw new IllegalArgumentException("Password does not have the minimum number of characters.");
		}
	}
}
