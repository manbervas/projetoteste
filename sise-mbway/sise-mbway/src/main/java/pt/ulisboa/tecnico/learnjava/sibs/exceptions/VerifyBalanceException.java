package pt.ulisboa.tecnico.learnjava.sibs.exceptions;

public class VerifyBalanceException extends Exception {

	private final String phone;
	
	public VerifyBalanceException(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public VerifyBalanceException() {
		this.phone = null;
	}
	
}
