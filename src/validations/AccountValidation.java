package validations;

public class AccountValidation {

	public boolean checkName(String accHolderName) {
		boolean flag = false;
		for (int i = 0; i < accHolderName.length(); i++) {
			if ((accHolderName.charAt(i) >= 'a' && accHolderName.charAt(i) >= 'z')
					|| (accHolderName.charAt(i) >= 'A' && accHolderName.charAt(i) >= 'Z')) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}

		if (flag == true) {
			return true;
		} else {
			return false;
		}

	}

	public boolean checkAll(int accNo, String accHolderName, double accBal) {

		if (accNo < 0) {
			System.out.println("Account no cannot be -ve.");
			return false;
		}
		if (accBal < 0) {
			System.out.println("Amount cannot be -ve.");
			return false;

		}
		if (checkName(accHolderName) == false) {
			System.out.println("Invalid Name...!");
			return false;
		}
		if (accBal > 50000) {
			System.out.println("Please generate documentations first...");
			return false;
		} else {
			return true;
		}
	}

}