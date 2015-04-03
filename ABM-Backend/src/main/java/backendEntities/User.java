package backendEntities;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * The {@code User} represents the entity that will use the application. The {@code User} possesses 
 * a {@code Map} of {@code Entities}, that are the entities {@code this} {@code User} booked.
 * 
 * A {@code User} must have an username, a password, and an email.
 * 
 * @author Lucas Andrade
 *
 */
public class User {
	
	/**
	 * The entities {@code this} {@code User} has booked.
	 */
	private Map<String, Entity> bookedEntities = new TreeMap<String, Entity>();
	
	/**
	 * {@code this} {@code User}'s username.
	 */
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
	private Object userLock = new Object();
	
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
	 * Adds a new {@code Entity} to the {@code Map} of booked entities of {@code this} {@code User}.
	 * No repeated entities are allowed.
	 * 
	 * @param newEntity
	 * @return {@code true} if the {@code Entity} was successfully added.
	 */
	public boolean addEntity(Entity newEntity){
		synchronized(userLock){
			return null == bookedEntities.put(newEntity.getName(), newEntity);
		}
		//TODO verificar se isto está bem.
	}
	
	/**
	 * Removes an {@code Entity} from the {@code Map} of booked entities of {@code this} {@code User}.
	 * @param name
	 * @return {@code true} if the {@code Entity} was successfully removed.
	 */
	public boolean removeEntity(String name) {
		synchronized(userLock){
			return null != bookedEntities.remove(name);
		}
	}
	
	/**
	 * @param name
	 * @return the {@code Entity} that has the name passed as parameter.
	 * @return null if there is no {@code Entity} with such name.
	 */
	public Entity getEntity(String name) {
		synchronized(userLock){
			return bookedEntities.get(name);
		}
	}
	
	/**
	 * @return the {@code Collection} of booked entities by {@code this} {@code User}.
	 */
	public Collection<Entity> getEntities(){
		synchronized(userLock){
			return bookedEntities.values();
		}
	}
	
	/**
	 * @return {@code this} {@code User}'s username
	 */
	public String getUsername(){
		synchronized(userLock){
			return username;
		}
	}
	
	/**
	 * @return {@code this} {@code User}'s password
	 */
	public String getPassword(){
		synchronized(userLock){
			return password;
		}
	}
	
	/**
	 * @return {@code this} {@code User}'s email
	 */
	public String getEmail(){
		synchronized(userLock){
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
		synchronized(userLock){
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
		synchronized(userLock){
			this.email = newEmail;
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
