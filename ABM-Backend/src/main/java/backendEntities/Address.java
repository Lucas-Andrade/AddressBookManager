package backendEntities;

import javax.persistence.Entity;

/**
 * Stores the address of an {@code Entity}.
 * 
 * @author Lucas Andrade
 *
 */
@Entity
public class Address extends Contact{
	
	/**
	 * The address.
	 */
	private String contact;
	
	/**
	 * Constructs a new address using a {@code String}.
	 * @param address
	 */
	public Address(String address){
		this.contact = address;
	}
	
	/**
	 * Implicit constructor of the {@code Address}. Constructs an empty {@code Address}
	 * object.
	 */
	public Address() {}

	/**
	 * Sets a new address in {@code this} {@code Address}
	 * @param address
	 */
	@Override
	public void setContact(String address) {
		this.contact = address;
	}

	/**
	 * @return the address stored in this {@code Address}
	 */
	@Override
	public String getContact() {
		return contact;
	}

	/**
	 * Compares the specified object with this set for equality. If the object is an instance of
	 * {@code Address} and stores the same contact, {@code true} is returned.
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
		
		Address adr = (Address)obj;
		
		if(!contact.equals(adr.getContact()))
			return false;
		
		return true;
	}
}
