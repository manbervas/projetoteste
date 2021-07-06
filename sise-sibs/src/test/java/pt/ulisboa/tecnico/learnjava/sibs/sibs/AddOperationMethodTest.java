package pt.ulisboa.tecnico.learnjava.sibs.sibs;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Sibs;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.SibsException;

public class AddOperationMethodTest {
	private static final String TARGET_IBAN = "TargetIban";
	private static final String SOURCE_IBAN = "SourceIban";
	private static final int VALUE = 100;
	
	private Sibs sibs;

	@Before
	public void setUp() throws OperationException, SibsException {
		this.sibs = new Sibs(4, new Services());
	}

	@Test
	public void success() throws OperationException, SibsException, AccountException {
		this.sibs.addOperation(SOURCE_IBAN, TARGET_IBAN, VALUE);
		assertEquals(SOURCE_IBAN, sibs.getOperation(0).getSourceIban());
		assertEquals(TARGET_IBAN, sibs.getOperation(0).getTargetIban());
		assertEquals(VALUE, sibs.getOperation(0).getValue());
		assertEquals(1, sibs.getNumberOfOperations());
	}
	
	@Test(expected = SibsException.class)
	public void AddMoreOperationsThanPossibleReturnException() throws OperationException, SibsException, AccountException {
		Sibs sibs2 = new Sibs(2, new Services());
		sibs2.addOperation(SOURCE_IBAN, TARGET_IBAN, VALUE);
		sibs2.addOperation(SOURCE_IBAN, TARGET_IBAN, VALUE);
		Sibs sibs3 = new Sibs(1, new Services());
		sibs3.addOperation(SOURCE_IBAN, TARGET_IBAN, VALUE);
		sibs3.addOperation(SOURCE_IBAN, TARGET_IBAN, VALUE);
	}

	@After
	public void tearDown() {
		this.sibs = null;
	}
}
