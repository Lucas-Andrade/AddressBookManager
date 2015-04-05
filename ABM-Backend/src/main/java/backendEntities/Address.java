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
	 * The name of this type of {@code Contact}.
	 */
	private final String NAME = "Address";
	
	/**
	 * The address of the {@code Entity}.
	 */
	private String address;
	
	/**
	 * Constructs a new address using a {@code String}.
	 * @param address
	 */
	public Address(String address){
		this.address = address;
	}
	
	/**
	 * Implicit constructor of the {@code Address}. Constructs an empty {@code Address}
	 * object.
	 */
	public Address() {}

	/**
	 * @return the address stored in this {@code Address}
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets a new address in {@code this} {@code Address}
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the name of the {@code Address}
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
	 * @return the address stored in {@code this}.
	 */
	public String getProperty() {
		return address;
	}
}
