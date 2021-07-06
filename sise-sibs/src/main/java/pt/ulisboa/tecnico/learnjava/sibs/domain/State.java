package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.SibsException;

public abstract class State {
	
	public void process(Operation op)
			throws SibsException, AccountException, OperationException {
	}
	
	public void cancel(Operation op) throws AccountException {}
	
}


