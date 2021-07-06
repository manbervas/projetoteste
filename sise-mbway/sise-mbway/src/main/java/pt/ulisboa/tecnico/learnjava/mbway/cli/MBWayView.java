package pt.ulisboa.tecnico.learnjava.mbway.cli;

public class MBWayView {

	public void associationSuccess(int code) {
		System.out.println("Code: "+code+" (don't share it with anyone)");
	}
	
	public void associationFailureIban() {
		System.out.println("Invalid IBAN");
	}
	
	public void associationFailurePhone() {
		System.out.println("Invalid phone number");
	}
	
	public void exit() {
		System.out.println("MBWAY terminated with success");
	}
	
	public void confirmationSuccess() {
		System.out.println("MBWAY association confirmed successfully!");
	}
	
	public void confirmationFailure() {
		System.out.println("Wrong confirmation code. Try association again.");
	}
	
	public void tranferSuccess() {
		System.out.println("Transfer performed successfully!");
	}
	
	public void wrongSourceNumber() {
		System.out.println("Wrong source phone number.");
	}
	
	public void wrongTargetNumber() {
		System.out.println("Wrong target phone number.");
	}
	
	public void transferFailure() {
		System.out.println("Not enough money on the souce account.");
	}
	
	public void numberNotConfirmed() {
		System.out.println("The specified phone number has not been confirmed");
	}
	
	public void tooManyElements() {
		System.out.println("Oh no! Too many family members.");
	}
	
	public void tooFewElements(int number) {
		System.out.println("Oh no! "+number+" family members are missing.");
	}
	
	public void splitInsuranceSuccess() {
		System.out.println("Insurance paid successfully!");
	}
	
	public void wrongAmount() {
		System.out.println("Something is wrong. Is the insurance amount right?");
	}
	
	public void notEnoughMoney(String phone) {
		System.out.println("Oh no! Family member with phone "+phone+" doesn’t have money to pay!");
	}
	
	public void phoneNotRegistered(String phone) {
		System.out.println("Friend "+phone+" is not registered");
	}
	
	public void phoneNotConfirmed(String phone) {
		System.out.println("Friend "+phone+" is not registered");
	}
	
}
