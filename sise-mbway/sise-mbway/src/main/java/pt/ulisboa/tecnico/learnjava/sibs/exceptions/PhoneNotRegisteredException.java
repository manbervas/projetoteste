package pt.ulisboa.tecnico.learnjava.sibs.exceptions;

public class PhoneNotRegisteredException extends Exception {

	private final String phone;
	
	public PhoneNotRegisteredException(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public PhoneNotRegisteredException() {
		this.phone = null;
	}
	
	
}
