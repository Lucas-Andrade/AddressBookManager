package backendEntities;

import javax.persistence.Entity;

/**
 * Stores the phone number of an {@code Entity}.
 * 
 * @author Lucas Andrade
 *
 */
@Entity
public class PhoneNumber extends Contact{
	
	/**
	 * The name of this type of {@code Contact}.
	 */
	public final String NAME = "Phone Number";
	
	/**
	 * The phone number.
	 */
	private String contact;
	
	/**
	 * Constructs a new {@code PhoneNumber}, by receiving an integer number.
	 * @param number
	 */
	public PhoneNumber(int number){
		checkPhoneNumber(number);
		this.contact = String.valueOf(number);
	}
	
	/**
	 * Constructs a new empty {@code PhoneNumber} object 
	 */
	public PhoneNumber(){}

	/**
	 * Sets a new phone number in {@code this} {@code PhoneNumber} object
	 * @param number
	 */
	@Override
	public void setContact(String number) {
		
		Integer intNumber;
		try{
			intNumber = Integer.parseInt(number);
		} catch(NumberFormatException e) {
			throw new IllegalArgumentException();
		}
		
		checkPhoneNumber(intNumber);
		this.contact = number;
	}

	/**
	 * Sets a new phone number in {@code this} {@code PhoneNumber} object
	 * @param number
	 */
	public void setContact(int number) {
		checkPhoneNumber(number);
		this.contact = String.valueOf(number);
	}
	
	/**
	 * @see Contact#getContact()
	 * @return the phone number stored in {@code this}, as a {@code String}.
	 */
	@Override
	public String getContact() {
		return contact;
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
	
	/**
	 * Compares the specified object with this set for equality. If the object is an instance of
	 * {@code PhoneNumber} and stores the same contact, {@code true} is returned.
	 * 
	 * @return {@code true} if the object passed as parameter is equal to {@code this}.
	 */
	@Override
	public boolean equals(Object obj) {
		
		if(obj == null)
			return false;
		
		if(this == obj)
			return true;
		
		if(! getClass().equals(obj.getClass()))
			return false;
		
		PhoneNumber adr = (PhoneNumber)obj;
		
		if(!contact.equals(adr.getContact()))
			return false;
		
		return true;
	}
	
	/**
	 * @return the name of the type of contact.
	 */
	public String getName() {
		return NAME;
	}
	
}
