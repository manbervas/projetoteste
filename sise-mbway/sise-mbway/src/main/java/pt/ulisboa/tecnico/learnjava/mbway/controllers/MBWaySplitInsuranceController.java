package pt.ulisboa.tecnico.learnjava.mbway.controllers;

import java.util.ArrayList;
import pt.ulisboa.tecnico.learnjava.bank.domain.Account;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBWay;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.VerifyBalanceException;

public class MBWaySplitInsuranceController {

	String _phone;
	int _amount;
	MBWay _mbway;
	ArrayList<ArrayList<String>> _fmlist;
	int _elements;
	
	public MBWaySplitInsuranceController (ArrayList<ArrayList<String>> fmlist, String elements, String amount, MBWay mbway) {
		_mbway = mbway;
		_fmlist = fmlist;
		_amount = Integer.parseInt(amount);
		_elements = Integer.parseInt(elements);
	}
	
//	public void splitInsurance() throws AccountException {
//		ArrayList<String> target_person = _fmlist.get(0);
//		String target_iban = _mbway.getIbanFromPhone(target_person.get(0));
//		//access target account bank
//		String target_code = target_iban.substring(0, 3);
//		String target_accountId = target_iban.substring(3);
//		Bank target_bank = Bank.getBankByCode(target_code);
//		Account target_account = target_bank.getAccountByAccountId(target_accountId);
//		for (ArrayList<String> friend: _fmlist.subList(1, _fmlist.size())) {
//			//access source account bank
//			String iban = _mbway.getIbanFromPhone(friend.get(0));
//			int money = Integer.parseInt(friend.get(1));
//			//access source account bank
//			String code = iban.substring(0, 3);
//			String accountId = iban.substring(3);
//			Bank bank = Bank.getBankByCode(code);
//			Account account = bank.getAccountByAccountId(accountId);
////			System.out.println("before transfer "+friend.get(0)+" "+account.getBalance());
//			account.withdraw(money);
////			System.out.println("after transfer "+friend.get(0)+" "+account.getBalance());
//			target_account.deposit(money);
//		}
//	}

	
	public void splitInsurance() throws AccountException {
		ArrayList<String> target_person = _fmlist.get(0);
		//access target account bank
		String target_phone = target_person.get(0);
		Account target_account = getAccountFromPhone(target_phone);
		for (ArrayList<String> friend: _fmlist.subList(1, _fmlist.size())) {
			//access source account bank
			String friend_phone = friend.get(0);
			Account friend_account = getAccountFromPhone(friend_phone);
			int money = Integer.parseInt(friend.get(1));
			friend_account.withdraw(money);
			target_account.withdraw(money);
		}
	}
	
	public Account getAccountFromPhone(String phone) {
		String iban = _mbway.getIbanFromPhone(phone);
		String code = iban.substring(0, 3);
		String accountId = iban.substring(3);
		Bank bank = Bank.getBankByCode(code);
		Account account = bank.getAccountByAccountId(accountId);
		return account;
	}
	
	public boolean totalEqualsAmount() {
		int total = 0;
		int target_amount = Integer.parseInt(_fmlist.get(0).get(1));
//		System.out.println("first person has "+target_amount);
		for (ArrayList<String> friend: _fmlist.subList(1, _fmlist.size())) {
			int money = Integer.parseInt(friend.get(1));
			total += money;
		}
		if (total == (_amount- target_amount))
			return true;
		else
			return false;
	}
	
	public void printBalances() {
		for (ArrayList<String> friend: _fmlist) {
			String iban = _mbway.getIbanFromPhone(friend.get(0));
			String code = iban.substring(0, 3);
			String accountId = iban.substring(3);
			Bank bank = Bank.getBankByCode(code);
			Account account = bank.getAccountByAccountId(accountId);
			System.out.println("phone "+friend.get(0)+" has "+account.getBalance());
		}
	}
	
	public void verifyBalances() throws VerifyBalanceException {
		for (ArrayList<String> friend: _fmlist.subList(1, _fmlist.size())) {
			String iban = _mbway.getIbanFromPhone(friend.get(0));
			int money = Integer.parseInt(friend.get(1));
			String code = iban.substring(0, 3);
			String accountId = iban.substring(3);
			Bank bank = Bank.getBankByCode(code);
			Account account = bank.getAccountByAccountId(accountId);
			if (account.getBalance() < money)
				throw new VerifyBalanceException(friend.get(0));
		}
	}
	
	public int elementsMissing() {
		return _fmlist.size()-_elements;
	}
}
