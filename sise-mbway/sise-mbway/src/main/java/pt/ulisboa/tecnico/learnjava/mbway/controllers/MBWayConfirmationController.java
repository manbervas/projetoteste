package pt.ulisboa.tecnico.learnjava.mbway.controllers;

import pt.ulisboa.tecnico.learnjava.mbway.domain.MBWay;

public class MBWayConfirmationController {
	
	MBWay _mbway;
	String _phone_number;
	int _code;
	
	public MBWayConfirmationController (String phone_number, String code, MBWay mbway) {
		_mbway = mbway;
		_phone_number = phone_number;
		_code = Integer.parseInt(code);
	}
	
//	public void confirmMbway() {
////		if (_mbway.getPhoneNumber(_code) == _phone_number)
////			System.out.println("they match");
////		else if (_mbway.getPhoneNumber(_code) != _phone_number)
////			System.out.println("don't match");
//		if ((codeRegistered(_code) == true) && (phoneMatchesCode() == true)) {
//			_view.confirmationSuccess(); }
//		else
//			_view.confirmationFailure();
//	}
	
	public void confirm() {
		_mbway.getAssociations().get(_code).confirmAccount();
	}
	
	public boolean codeRegistered() {
		return _mbway.getAssociations().containsKey(_code);
	}
	
	public boolean phoneMatchesCode() {
		return _mbway.getPhoneNumber(_code).equals(_phone_number);
	}

}
