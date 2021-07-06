package pt.ulisboa.tecnico.learnjava.sibs.sibs;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank.AccountType;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Operation;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Sibs;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.SibsException;

public class TransferMethodTest {
	private static final String ADDRESS = "Ave.";
	private static final String PHONE_NUMBER = "987654321";
	private static final String NIF = "123456789";
	private static final String LAST_NAME = "Silva";
	private static final String FIRST_NAME = "António";

	private Sibs sibs;
	private Bank sourceBank;
	private Bank targetBank;
	private Client sourceClient;
	private Client targetClient;
	private Services services;
	private String sourceIBAN;
	private String targetIBAN;


	@Before
	public void setUp() throws BankException, AccountException, ClientException {
		this.services = new Services();
		this.sibs = new Sibs(100, services);
		this.sourceBank = new Bank("CGD");
		this.targetBank = new Bank("BPI");
		this.sourceClient = new Client(this.sourceBank, FIRST_NAME, LAST_NAME, NIF, PHONE_NUMBER, ADDRESS, 33);
		this.targetClient = new Client(this.targetBank, FIRST_NAME, LAST_NAME, NIF, PHONE_NUMBER, ADDRESS, 22);
		this.sourceIBAN = this.sourceBank.createAccount(AccountType.CHECKING, sourceClient, 200, 0);
		this.targetIBAN = this.targetBank.createAccount(AccountType.CHECKING, targetClient, 50, 0);
	}

	@Test
	public void success() throws BankException, AccountException, SibsException, OperationException, ClientException {
		this.sibs.transfer(sourceIBAN, targetIBAN, 150);
//		assertEquals(sourceIBAN, sibs.getOperation(0).getSourceIban());
//		assertEquals(targetIBAN, sibs.getOperation(0).getTargetIban());
//		assertEquals(47, this.services.getAccountByIban(sourceIBAN).getBalance());
//		assertEquals(200, this.services.getAccountByIban(targetIBAN).getBalance());
//		assertEquals(150, sibs.getOperation(0).getValue());
	}
	
	/*@Test(expected = SibsException.class)
	public void exceptionSibs() throws BankException, AccountException, SibsException, OperationException, ClientException {
		Sibs sibs2 = new Sibs(0, new Services());
		sibs2.transfer(sourceIBAN, targetIBAN, 50);
	}*/
	
	@Test (expected = AccountException.class)
	public void GivenAmountSourceIbanMoreThanPossibleReturnAccountException() throws BankException, AccountException, SibsException, OperationException, ClientException {
		Sibs sibs2 = new Sibs(2, new Services());
		sibs2.transfer(sourceIBAN, targetIBAN, 250);
	}
	
	@Test //(expected = AccountException.class)
	public void GivenTransferAmountBiggerThanSourceIbanReturnBalancesUnchanged() throws BankException, AccountException, SibsException, OperationException, ClientException {
		try{
			sibs.transfer(sourceIBAN, targetIBAN, 500);
		} catch(AccountException e) {
			assertEquals(200, this.services.getAccountByIban(sourceIBAN).getBalance());
			assertEquals(50, this.services.getAccountByIban(targetIBAN).getBalance());
			assertEquals(null, sibs.getOperation(0));
		}
	}
	
	@After
	public void tearDown() {
		Bank.clearBanks();
	}

}
