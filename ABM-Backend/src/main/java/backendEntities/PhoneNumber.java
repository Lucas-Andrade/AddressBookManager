package backendEntities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Stores the phone number of an {@code Entity}.
 * 
 * @author Lucas Andrade
 *
 */
@Entity
public class PhoneNumber implements Contact{

	/**
	 * Phone number identifier.
	 */
	@Id
	@GeneratedValue
	private int phoneNumberId;
	
	/**
	 * The name of this type of {@code Contact}.
	 */
	private final String NAME = "Phone Number";
	
	/**
	 * The phone number of the {@code Entity}.
	 */
	private int number;
	
	/**
	 * Constructs a new {@code PhoneNumber}, by receiving an integer number.
	 * @param number
	 */
	public PhoneNumber(int number){
		checkPhoneNumber(number);
		this.number = number;
	}
	
	/**
	 * Constructs a new empty {@code PhoneNumber} object 
	 */
	public PhoneNumber(){}
	
	/**
	 * @return the {@code PhoneNumber} identifier
	 */
	public int getPhoneNumberId() {
		return phoneNumberId;
	}

	/**
	 * sets a new {@code PhoneNumber} identifier
	 * @param phoneNumberId
	 */
	public void setPhoneNumberId(int phoneNumberId) {
		this.phoneNumberId = phoneNumberId;
	}

	/**
	 * @return the phone number stored in {@code this} {@code PhoneNumber} object
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Sets a new phone number in {@code this} {@code PhoneNumber} object
	 * @param number
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * @return the name of {@code this} phone number
	 */
	public String getName() {
		return NAME;
	}

	/**
	 * @see Contact#getPropertyName()
	 */
	public String getPropertyName() {
		return NAME;
	}
	
	/**
	 * @see Contact#getProperty()
	 * @return the phone number stored in {@code this}, as a {@code String}.
	 */
	public String getProperty() {
		return String.valueOf(number);
	}

	/**
	 * Checks if the integer passed as parameters has at least 9 digits. If 
	 * the number has less than 9 digits a {@code IllegalArgumentException} 
	 * is thrown.
	 * 
	 * @param number - the number to be checked
	 */
	private void checkPhoneNumber(int number){
		if(number < Math.pow(10, 8)){
			throw new IllegalArgumentException("Phone number too small.");
		}
	}
	
}
