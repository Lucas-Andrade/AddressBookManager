package backendEntities;

/**
 * Stores the address of an {@code Entity}.
 * 
 * @author Lucas Andrade
 *
 */
public class Address implements Contact{

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
