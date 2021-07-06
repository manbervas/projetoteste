package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.SibsException;

public class Operation {

	private final int value;
	private final String targetIban;
	private final String sourceIban;
	private State stateOperation;

	public Operation(String sourceIban, String targetIban, int value) throws OperationException {
		checkParameters(value);
		this.value = value;

		if (invalidString(targetIban)) {
			throw new OperationException();
		}
		if (invalidString(sourceIban)) {
			throw new OperationException();
		}

		this.targetIban = targetIban;
		this.sourceIban = sourceIban;
		this.stateOperation = new Registered();
	}

	private void checkParameters(int value) throws OperationException {

		if (value <= 0) {
			throw new OperationException(value);
		}
	}

	public int getValue() {
		return this.value;
	}

	private boolean invalidString(String name) {
		return name == null || name.length() == 0;
	}

	public int commission() {
		return (this.value*2)/100;
	}

	public String getTargetIban() {
		return this.targetIban;
	}
	public String getSourceIban() {
		return this.sourceIban;
	}

	public State getState() {
		return this.stateOperation;
//		if(this.stateOperation instanceof Registered)
//			return "Registered";
//		else if (this.stateOperation instanceof Withdrawn)
//			return "Withdrawn";
//		else if (this.stateOperation instanceof Deposited)
//			return "Deposited";
//		else if (this.stateOperation instanceof Completed)
//			return "Completed";
//		else
//			return "Cancelled";
	}
	
	public void changeState(State newstate) {
		this.stateOperation = newstate;
	}
	
	public void process() throws SibsException, AccountException, OperationException {
		if (!(stateOperation instanceof Completed || stateOperation instanceof Cancelled))
			this.stateOperation.process(this);
	}
	
	public void cancel() throws AccountException {
		if (!(stateOperation instanceof Completed || stateOperation instanceof Cancelled))
			this.stateOperation.cancel(this);
	}

}
