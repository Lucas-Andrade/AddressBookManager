package backendEntities;

/**
 * Stores the phone number of an {@code Entity}.
 * 
 * @author Lucas Andrade
 *
 */
public class PhoneNumber implements Contact{

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
