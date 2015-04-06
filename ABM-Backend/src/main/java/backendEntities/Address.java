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

//	/**
//	 * The name of this type of {@code Contact}.
//	 */
//	private final String NAME = "Address";
	
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

//	/**
//	 * @return the address stored in this {@code Address}
//	 */
//	public String getAddress() {
//		return contact;
//	}

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

//	/**
//	 * @return the name of the {@code Address}
//	 */
//	public String getName() {
//		return NAME;
//	}
//
//	/**
//	 * @see Contact#getContactName()
//	 */
//	public String getContactName() {
//		return NAME;
//	}
//
//	/**
//	 * @see Contact#getContact()
//	 * @return the address stored in {@code this}.
//	 */
//	public String getContact() {
//		return address;
//	}
}
