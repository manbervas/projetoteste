package pt.ulisboa.tecnico.learnjava.mbway.controllers;

import pt.ulisboa.tecnico.learnjava.mbway.domain.MBWay;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBWayAccount;

public class MBWayAssociationController {

	MBWayAccount _mbway_account;
	MBWay _mbway;
	int _code;

	public MBWayAssociationController (MBWayAccount mbway_account, MBWay mbway) {
		_mbway_account = mbway_account;
		_mbway = mbway;
	}

//	public void associateMbway() {
//		if (verifyIban() == true && verifyPhoneNumber() == true) {
//			_code = _mbway.generateCode();
//			_mbway.getAssociations().put(_code,_mbway_account);
//			_view.associationSuccess(_code);
//		}
//		else if (verifyIban() == false)
//			_view.associationFailureIban();
//		else if (verifyPhoneNumber() == false)
//			_view.associationFailurePhone();
//			
//	}
	
	public int associateMbway() {
		_code = _mbway.generateCode();
		_mbway.getAssociations().put(_code,_mbway_account);
		return _code;	
	}
	
	public boolean verifyPhoneNumber() {
		int PHONE_NUMBER_SIZE = 9;
		String trimmed = _mbway_account.getPhoneNumber().trim();
		if (trimmed.length() != PHONE_NUMBER_SIZE)
			return false;
		for (int i = 0; i < trimmed.length(); i++) {
			int charValue = Character.getNumericValue(trimmed.charAt(i));
			if (charValue < 0 || charValue > 9) {
				return false;
			}
		}
		return true;
	}
	
//	public boolean verifyIban() {
//		int IBAN_MIN_SIZE = 15;
//		int IBAN_MAX_SIZE = 34;
//		long IBAN_MAX = 999999999;
//		long IBAN_MODULUS = 97;
//
//		String trimmed = _mbway_account.getIban().trim();
//
//		if (trimmed.length() < IBAN_MIN_SIZE || trimmed.length() > IBAN_MAX_SIZE) {
//			return false;
//		}
//
//		String reformat = trimmed.substring(4) + trimmed.substring(0, 4);
//		long total = 0;
//
//		for (int i = 0; i < reformat.length(); i++) {
//			//get numeric value of characters
//			int charValue = Character.getNumericValue(reformat.charAt(i));
//			//letters have numeric values 10 to 35
//			if (charValue < 0 || charValue > 35) {
//				return false;
//			}
//
//			total = (charValue > 9 ? total * 100 : total * 10) + charValue;
//
//			if (total > IBAN_MAX) {
//				total = (total % IBAN_MODULUS);
//			}
//		}
//
//		return (total % IBAN_MODULUS) == 1;
//	}
	
	public boolean verifyIban() {
		int IBAN_SIZE = 6;
		String trimmed = _mbway_account.getIban().trim();
		if (trimmed.length() != IBAN_SIZE)
			return false;
		for (int i = 0; i < trimmed.length()-1; i++) {
			int charValue = Character.getNumericValue(trimmed.charAt(i));
			if (charValue < 0 || charValue > 35) {
				return false;
			}
		}
		return true;
	}
	
	public int getCode() {
		return _code;
	}
}

