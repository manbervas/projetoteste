package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.SibsException;

public class Registered extends State{
	Services services = new Services();
	
	@Override
	public void process(Operation op) 
			throws SibsException, AccountException, OperationException {
		this.services.withdraw(op.getSourceIban(),op.getValue());
		op.changeState(new Withdrawn());
	}
	
	@Override
	public void cancel(Operation op) throws AccountException {
		op.changeState(new Cancelled());
	}
}
