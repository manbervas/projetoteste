package pt.ulisboa.tecnico.learnjava.sibs.exceptions;

public class PhoneNotConfirmedException extends Exception {


	private final String phone;

	public PhoneNotConfirmedException(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return this.phone;
	}

	public PhoneNotConfirmedException() {
		this.phone = null;
	}


}
