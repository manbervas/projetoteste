package pt.ulisboa.tecnico.learnjava.mbway.domain;

import java.util.Hashtable;
import java.util.Set;
import java.util.Map.Entry;

public class MBWay {
	
	Hashtable<Integer,MBWayAccount> _associations = new Hashtable<Integer,MBWayAccount>();
	MBWayAccount _mbway_account;
	
	public Hashtable<Integer,MBWayAccount> getAssociations() {
		return _associations;
	}
	
	public int getRandomCode(int min, int max) {
		return (int) ((Math.random() * (max-min)) + min);
	}

	
	public int generateCode() {
		int random = getRandomCode(100000,999999);
//		for (int i = 0; i < _codes.size(); i++) {
//			if (random == _codes.get(i))
//				generateCode();
//		}
//		_codes.add(random);
		return random;
	}
	
	public String getIban(int code) {
		String iban = _associations.get(code).getIban();
		return iban;
	}
	
	public String getPhoneNumber(int code) {
		String phone = _associations.get(code).getPhoneNumber();
		return phone;
	}
	
	public String getIbanFromPhone(String phone) {
		Set<Entry<Integer,MBWayAccount>> entries = _associations.entrySet();
		for (Entry<Integer,MBWayAccount> entry: entries) {
			if (entry.getValue().getPhoneNumber().equals(phone))
				return entry.getValue().getIban();
		}
		return null;
	}
	
	public boolean getConfirmationStatusFromPhone(String phone) {
		Set<Entry<Integer,MBWayAccount>> entries = _associations.entrySet();
		for (Entry<Integer,MBWayAccount> entry: entries) {
			if (entry.getValue().getPhoneNumber().equals(phone))
				return entry.getValue().getConfirmationStatus();
		}
		return false;
	}
	
	public boolean confirmPhoneIsRegistered(String phone) {
		Set<Entry<Integer,MBWayAccount>> entries = _associations.entrySet();
		for (Entry<Integer,MBWayAccount> entry: entries) {
			if (entry.getValue().getPhoneNumber().equals(phone))
				return true;
		}
		return false;
	}
	
	public boolean confirmAccountByPhone(String phone) {
		System.out.println(_associations.size());
		for (int i=0; i < _associations.size(); i++) {
			if (_associations.get(i).getPhoneNumber().equals(phone))
				return true;
		}
		return false;
	}
}
	
	
