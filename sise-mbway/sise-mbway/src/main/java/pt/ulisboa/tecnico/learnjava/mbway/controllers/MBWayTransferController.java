package pt.ulisboa.tecnico.learnjava.mbway.controllers;

import pt.ulisboa.tecnico.learnjava.bank.domain.Account;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBWay;

public class MBWayTransferController {
	
	String _source_phone;
	String _target_phone;
	int _amount;
	MBWay _mbway;
	
	public MBWayTransferController (String source_phone, String target_phone, String amount, MBWay mbway) {
		_mbway = mbway;
		_source_phone = source_phone;
		_target_phone = target_phone;
		_amount = Integer.parseInt(amount);
	}
	
//	public void transfer() throws AccountException {
//		if (_mbway.getIbanFromPhone(_source_phone) == null)
//			_view.wrongSourceNumber();
//		else if (_mbway.getIbanFromPhone(_target_phone) == null)
//			_view.wrongTargetNumber();
//		else if (getAccountBalance(_source_phone) < _amount)
//			_view.transferFailure();
//		else if (getAccountBalance(_source_phone) > _amount) {
////			System.out.println("funds before transfer at source "+getAccountBalance(_source_phone));
//			withdraw(_source_phone);
////			System.out.println("funds after transfer at source "+getAccountBalance(_source_phone));
//			deposit(_target_phone);
//			_view.tranferSuccess();
//		}
//	}
	
	public void transfer() throws AccountException {
		withdraw(_source_phone);
		deposit(_target_phone);
	}
	
	public boolean sufficientFunds() {
		String iban = _mbway.getIbanFromPhone(_source_phone);
//		System.out.println("iban: "+_mbway.getIbanFromPhone(phone));
		String code = iban.substring(0, 3);
		String accountId = iban.substring(3);
		Bank bank = Bank.getBankByCode(code);
		Account account = bank.getAccountByAccountId(accountId);
		if (account.getBalance() < _amount)
			return false;
		else
			return true;
	}
	
	public void withdraw(String phone) throws AccountException {
		String iban = _mbway.getIbanFromPhone(phone);
		String code = iban.substring(0, 3);
		String accountId = iban.substring(3);
		Bank bank = Bank.getBankByCode(code);
		Account account = bank.getAccountByAccountId(accountId);
		account.withdraw(_amount);
	}
	
	public void deposit(String phone) throws AccountException {
		String iban = _mbway.getIbanFromPhone(phone);
		String code = iban.substring(0, 3);
		String accountId = iban.substring(3);
		Bank bank = Bank.getBankByCode(code);
		Account account = bank.getAccountByAccountId(accountId);
		account.deposit(_amount);
	}
}
