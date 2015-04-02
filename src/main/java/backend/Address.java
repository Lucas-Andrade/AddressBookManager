package backend;

public class Address implements Property{

	private final String NAME = "Address";
	private String address;
	
	public Address(String address){
		this.address = address;
	}
	
	public String getPropertyName() {
		return NAME;
	}
	
	public String getNumber() {
		return address;
	}
}
