package backend;

public class PhoneNumber implements Property{

	private final String NAME = "Phone Number";
	private int number;
	
	public PhoneNumber(int number){
		checkPhoneNumber(number);
		this.number = number;
	}
	
	public String getPropertyName() {
		return NAME;
	}
	
	public int getNumber() {
		return number;
	}

	private void checkPhoneNumber(int number){
		if(number < Math.pow(10, 9)){
			throw new IllegalArgumentException("Phone number too small.");
		}
	}
}
