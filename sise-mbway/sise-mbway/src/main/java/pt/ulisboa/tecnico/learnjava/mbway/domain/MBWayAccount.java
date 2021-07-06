package pt.ulisboa.tecnico.learnjava.mbway.domain;

public class MBWayAccount {
	
	String _iban;
	String _phone_number;
	boolean _confirmed;
	
	public MBWayAccount (String iban, String phone_number) {
		_iban = iban;
		_phone_number = phone_number;
		_confirmed = false;
	}
	
	public String getIban() {
		return _iban;
	}
	
	public String getPhoneNumber() {
		return _phone_number;
	}
	
	public void confirmAccount() {
		_confirmed = true;
	}
	
	public boolean getConfirmationStatus() {
		return _confirmed;
	}
}
