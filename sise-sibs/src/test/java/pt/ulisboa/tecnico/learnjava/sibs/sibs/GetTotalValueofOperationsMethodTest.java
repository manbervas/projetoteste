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

public class GetTotalValueofOperationsMethodTest {
	private static final String TARGET_IBAN = "TargetIban";
	private static final String SOURCE_IBAN = "SourceIban";
	
	private Sibs sibs;

	@Before
	public void setUp(){
		this.sibs = new Sibs(3, new Services());
	}

	@Test
	public void GivenLessOperationsThanMaxReturnTotalValueofAll() throws OperationException, SibsException, AccountException{
		this.sibs.addOperation(SOURCE_IBAN, TARGET_IBAN, 100);
		this.sibs.addOperation(SOURCE_IBAN, TARGET_IBAN, 150);
		assertEquals(250, sibs.getTotalValueofOperations());
	}
	
	@Test
	public void GivenMaxNumberOfOperationsReturnTotalValueofAll() throws OperationException, SibsException, AccountException{
		this.sibs.addOperation(SOURCE_IBAN, TARGET_IBAN, 100);
		this.sibs.addOperation(SOURCE_IBAN, TARGET_IBAN, 150);
		this.sibs.addOperation(SOURCE_IBAN, TARGET_IBAN, 300);
		assertEquals(550, sibs.getTotalValueofOperations());
	}

	@After
	public void tearDown() {
		this.sibs = null;
	}
}
