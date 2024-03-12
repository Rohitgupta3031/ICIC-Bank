package userinterface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.jar.Attributes.Name;
import dao.AccountDao;
import entity.Account;
import operations.AccountOperations;
import validations.AccountValidation;

public class TestAccount {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		Account account1 = new Account();
		Account account2 = new Account(2, "Pratik Ingale", 40000);
		AccountValidation accountValidation = new AccountValidation();
		AccountOperations accountOperations = new AccountOperations();
		AccountDao accountDao = new AccountDao();

		Connection con;

		int num;
		String choice;

		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lnt", "root", "password");
		System.out.println("Connection is successful");

		do {
			System.out.println("!...Welcome to ICICI BANK...!");
			System.out.println("Press 1. Accept the details");
			System.out.println("Press 2. Display the details");
			System.out.println("Press 3. Deposit the amount");
			System.out.println("Press 4. Withdraw the amount");
			System.out.println("Press 5. Transfer the amount");
			System.out.println("Press 6. Change the name");
			System.out.println("Press 7. Check the balance");
			System.out.println("Press 8. Update account balance");
			System.out.println("Press 9. Delete the account");
			System.out.println("Press 10. To exit");

			System.out.println("Enter your choice? : ");
			num = sc.nextInt();

			switch (num) {
			case 1:
				System.out.println("Enter account number, account holder name and balance");
				int accNo = sc.nextInt();
				String accHolderName = sc.next();
				double accBal = sc.nextDouble();

				boolean valResult = accountValidation.checkAll(accNo, accHolderName, accBal);
				if (valResult == true) {
					account1.setAccNo(accNo);
					account1.setAccHolderName(accHolderName);
					account1.setAccBalance(accBal);
					System.out.println("Details accepted successfully...!");

					// insert record into account table: jdbc code
					accountDao.insertRecord(con, account1);

				} else {
					System.out.println("Invalid Inputs...!");
				}
				System.out.println("==================================================================");
				break;

			case 2:
				System.out.println("Diplaying the details : ");
				accountDao.getAllRecods(con);
				break;

			case 3:
				System.out.println("Enter the amount you want to deposit? : ");
				double amt = sc.nextDouble();

				if (accountOperations.deposit(con, accountDao, account1, amt)) {
					System.out.println("Amount is deposited successfully...!");
					System.out.println("Current account 1 balance is : " + account1.getAccBalance());
				} else {
					System.out.println("There is an error...!");
				}
				System.out.println("==================================================================");
				break;

			case 4:
				System.out.println("Enter the amount you want to withdraw? : ");
				double amt1 = sc.nextDouble();
				boolean withdrawResult = accountOperations.withdraw(account2, amt1);

				if (withdrawResult == true) {
					System.out.println("Amount is withdrawl successfull...!");
					System.out.println("Current account 2 balance is : " + account2.getAccBalance());
				} else {
					System.out.println("There is an error...!");
				}

				System.out.println("==================================================================");
				break;

			case 5:
				System.out.println("Enter the amount you want to transfer? : ");
				double amt2 = sc.nextDouble();
				boolean transferResult = accountOperations.transfer(account1, account2, amt2);

				if (transferResult == true) {
					System.out.println("Amount is transfered successfully...!");
					System.out.println("Current account 1 balance is : " + account1.getAccBalance());
					System.out.println("Current account 2 balance is : " + account2.getAccBalance());
				} else {
					System.out.println("There is an error...!");
				}
				System.out.println("==================================================================");
				break;

			case 6:
				System.out.println("Enter a new name> : ");
				String newName = sc.next();
				boolean checkNameResult = accountValidation.checkName(newName);
				if (checkNameResult == true) {
					System.out.println("Valid name...!");
					account2.setAccHolderName(newName);
					System.out.println("Name is changed Successfully...!");
					System.out.println(account2);
					System.out.println();
				} else {
					System.out.println("Invalid Name ...!");
				}
				System.out.println("==================================================================");
				break;

			case 7:
				System.out.println("Enter account number 1/2 to check the account balance : ");
				int accNo1 = sc.nextInt();
				if (accNo1 == 1) {
					System.out.println("Account balance for account 1 is : " + account1.getAccBalance());
				} else if (accNo1 == 2) {
					System.out.println("Account balance for account 2 is : " + account2.getAccBalance());
				} else {
					System.out.println("Account not found...!");
				}
				System.out.println("==================================================================");
				break;

			case 8:
				System.out.println("Updating the Account balance : ");
				System.out.println("Enter new increment value and accHolderName : ");
				int incr = sc.nextInt();
				String accHolderName1 = sc.next();
				accountDao.updateRecord(con, incr, accHolderName1);
				break;

			case 9:
				System.out.println("Deleting  the Account : ");
				System.out.println("Enter AccountNo to be deleted : ");
				int accNo2 = sc.nextInt();
				accountDao.deleteRecord(con, accNo2);
				break;

			case 10:
				System.out.println("!....Thank you for visiting ICICI BANK....!");
				System.out.println("!....HAVE A NICE DAY....!");
				System.exit(0);

			default:
				System.out.println("Invalid Choice...!");
				System.out.println("==================================================================");

				break;
			}
			System.out.println("Do you want to continue Y/y ? : ");
			choice = sc.next();

		} while (choice.equals("Y") || choice.equals("y"));

	}
}