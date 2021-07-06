package pt.ulisboa.tecnico.learnjava.mbway.cli;

import java.util.ArrayList;
import java.util.Scanner;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank.AccountType;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.MBWayAssociationController;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.MBWayConfirmationController;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.MBWaySplitInsuranceController;
import pt.ulisboa.tecnico.learnjava.mbway.controllers.MBWayTransferController;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBWay;
import pt.ulisboa.tecnico.learnjava.mbway.domain.MBWayAccount;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.PhoneNotConfirmedException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.PhoneNotRegisteredException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.VerifyBalanceException;

public class MVC2 {

	public static void main(String[] args) throws BankException, ClientException, AccountException, VerifyBalanceException, PhoneNotConfirmedException {        
		Bank cgd = new Bank("CGD");

		Client clientOne = new Client(cgd, "José", "Manuel", "123456789", "987654321", "Street", 34);
		Client clientTwo = new Client(cgd, "André", "Soares", "123456780", "987654322", "Street", 34);
		Client clientThree = new Client(cgd, "Miguel", "Barbosa", "123455555", "987654323", "Street", 34);

		cgd.createAccount(AccountType.CHECKING, clientOne, 500, 0);
		cgd.createAccount(AccountType.CHECKING, clientTwo, 500, 0);
		cgd.createAccount(AccountType.CHECKING, clientThree, 500, 0);

		Scanner command = new Scanner(System.in);
		MBWay mbway = new MBWay();
		MBWayView view = new MBWayView();
		boolean active = true;
		while (active == true) {
			System.out.println("Enter command:");
			String cmd = command.nextLine();
			String[] inputs = cmd.split(" ");
			switch (inputs[0]) {
			case "exit": {
				view.exit();
				command.close();
				active = false;
				continue;
			}
			case "associate-mbway": {
				MBWayAccount mbway_account = new MBWayAccount(inputs[1],inputs[2]);
				MBWayAssociationController controller = new MBWayAssociationController(mbway_account,mbway);
				boolean iban = controller.verifyIban();
				boolean phone = controller.verifyPhoneNumber();
				if (iban == true && phone == true) {
					int code = controller.associateMbway();
					view.associationSuccess(code);
				}
				else if (iban == false)
					view.associationFailureIban();
				else if (phone == false)
					view.associationFailurePhone();
				continue;
			}
			case "confirm-mbway": {
				MBWayConfirmationController controller = new MBWayConfirmationController(inputs[1],inputs[2],mbway);
				if ((controller.codeRegistered() == true) && (controller.phoneMatchesCode() == true)) {
					controller.confirm();
					view.confirmationSuccess();
				}
				else
					view.confirmationFailure();
				continue;
				//confirm-mbway 987654321
				//confirm-mbway 987654322
				//confirm-mbway 987654323
			}
			case "mbway-transfer": {
				MBWayTransferController controller = new MBWayTransferController(inputs[1],inputs[2],inputs[3],mbway);
				if (mbway.getIbanFromPhone(inputs[1]) == null)
					view.wrongSourceNumber();
				else if (mbway.getIbanFromPhone(inputs[2]) == null)
					view.wrongTargetNumber();
				else if (mbway.getConfirmationStatusFromPhone(inputs[1])==false || mbway.getConfirmationStatusFromPhone(inputs[2])==false)
					view.numberNotConfirmed();
				else if (controller.sufficientFunds() == false)
					view.transferFailure();
				else if (controller.sufficientFunds()) {
					controller.transfer();
					view.tranferSuccess();
				}
				continue;
			}
			case "mbway-split-insurance": {
				ArrayList<ArrayList<String>> fmlist = new ArrayList<ArrayList<String>>(); 
				try {
					addMembers(fmlist,mbway);
					MBWaySplitInsuranceController controller = new MBWaySplitInsuranceController(fmlist,inputs[1],inputs[2],mbway);
					int elements = controller.elementsMissing();
					if (elements > 0)
						view.tooManyElements();
					else if (elements < 0)
						view.tooFewElements(-elements);
					else if (controller.totalEqualsAmount() == false)
						view.wrongAmount();
					else {
						controller.totalEqualsAmount();
						controller.printBalances();
						controller.verifyBalances();
						controller.splitInsurance();
						controller.printBalances();
						view.splitInsuranceSuccess();
					}
//					continue;
				}
				catch(PhoneNotRegisteredException e) {
					view.phoneNotRegistered(e.getPhone());
					continue;
				}
				catch(VerifyBalanceException e) {
					view.notEnoughMoney(e.getPhone());
					continue;
				}
				catch(PhoneNotConfirmedException e) {
					view.phoneNotConfirmed(e.getPhone());
					continue;
				}
				continue;
			}
			}
		}
	}

	public static void addMembers(ArrayList<ArrayList<String>> fmlist, MBWay mbway) throws PhoneNotRegisteredException, PhoneNotConfirmedException {
		Scanner family_members = new Scanner(System.in);
		while (true) {
			System.out.println("Enter insured information:");
			String fm = family_members.nextLine();
			if (fm.equals("end")) {
				break;
			} else {
				String[] insurance_inputs = fm.split(" ");
				if (mbway.confirmPhoneIsRegistered(insurance_inputs[1]) == false) {
					throw new PhoneNotRegisteredException (insurance_inputs[1]);
				} else if (mbway.getConfirmationStatusFromPhone(insurance_inputs[1]) == false) {
					throw new PhoneNotConfirmedException(insurance_inputs[1]);
				} else {
					ArrayList<String> elements = new ArrayList<String>();
					elements.add(insurance_inputs[1]);
					elements.add(insurance_inputs[2]);
					fmlist.add(elements);
				} continue;
			}
		}
	}

}
