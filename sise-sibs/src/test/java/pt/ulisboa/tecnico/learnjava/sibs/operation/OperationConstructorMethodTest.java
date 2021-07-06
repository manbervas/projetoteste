package pt.ulisboa.tecnico.learnjava.sibs.operation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Operation;
import pt.ulisboa.tecnico.learnjava.sibs.domain.Registered;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.SibsException;

public class OperationConstructorMethodTest {
	private static final String SOURCE_IBAN = "SourceIban";
	private static final String TARGET_IBAN = "TargetIban";
	private static final int VALUE = 100;

	@Test
	public void success() throws OperationException {
		Operation operation = new Operation(SOURCE_IBAN, TARGET_IBAN, VALUE);
		assertEquals(VALUE, operation.getValue());
		assertEquals(SOURCE_IBAN, operation.getSourceIban());
		assertEquals(TARGET_IBAN, operation.getTargetIban());
	}

	@Test(expected = OperationException.class)
	public void nullTargetIban() throws OperationException {
		new Operation(SOURCE_IBAN, null, VALUE);
	}

	@Test(expected = OperationException.class)
	public void emptyTargetIban() throws OperationException {
		new Operation(SOURCE_IBAN, "", VALUE);
	}
	
	@Test(expected = OperationException.class)
	public void negativeValue() throws OperationException {
		new Operation(SOURCE_IBAN, TARGET_IBAN, -VALUE);
	}
	
	/*@Test(expected = OperationException.class) //PORQUE É QUE ADICIONANDO ESTES 2 TESTES A COVERAGE DIMINUI???
	public void nullSourceIban() throws OperationException {
		new Operation(null, TARGET_IBAN, VALUE);
	}
	
	@Test(expected = OperationException.class)
	public void emptySourceIban() throws OperationException {
		new Operation("", TARGET_IBAN, VALUE);
	}*/
	
	@Test
	public void commision() throws OperationException {
		Operation op = new Operation(SOURCE_IBAN, TARGET_IBAN, VALUE);
		assertEquals(2, op.commission());
	}
	
	@Test
	public void stateRegisteredOperationCorrect() throws OperationException {
		Operation op = new Operation(SOURCE_IBAN, TARGET_IBAN, VALUE);
		assertTrue(op.getState() instanceof Registered);
	}
	
//	@Test
//	public void cancelTest() throws OperationException, SibsException, AccountException {
//		Operation op = new Operation(SOURCE_IBAN, TARGET_IBAN, VALUE);
//		op.cancel();
//		op.process();
//	}
//	
//	@Test
//	public void cancelTest2() throws OperationException, SibsException, AccountException {
//		Operation op = new Operation(SOURCE_IBAN, TARGET_IBAN, VALUE);
//		op.process();
//		op.process();
//		op.process();
//		op.process();
//	}
	

}
