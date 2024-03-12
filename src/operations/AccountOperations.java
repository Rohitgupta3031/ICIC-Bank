package operations;

import entity.Account;
import dao.AccountDao;
import java.sql.Connection;

public class AccountOperations {

	public boolean withdraw(Account account, double amt) {
		if (amt < 0) {
			System.out.println("Amount cannot be -ve.");
			return false;
		} else if (amt > account.getAccBalance()) {
			System.out.println("Insufficient funds...!");
			return false;
		} else {
			double balance = account.getAccBalance() - amt;
			account.setAccBalance(balance);
			return true;
		}
	}

	public boolean deposit(Connection con, AccountDao accountDao, Account account, double amt) {
		if (amt < 0) {
			System.out.println("Amount cannot be -ve.");
			return false;
		} else if (amt > 50000) {
			System.out.println("Please generate documentations first...");
			return false;
		} else {
			double balance = account.getAccBalance() + amt;
			account.setAccBalance(balance);
			accountDao.updateRecord2(con, account);
			return true;
		}
	}

	public boolean transfer(Account account1, Account account2, double amt) {
		if (amt < 0) {
			System.out.println("Amount cannot be -ve.");
			return false;
		} else if (amt > account1.getAccBalance()) {
			System.out.println("Insufficient funds...!");
			return false;
		} else {
			double balance = account1.getAccBalance() - amt;
			account1.setAccBalance(balance);

			double newBalance = account1.getAccBalance() + amt;
			account1.setAccBalance(newBalance);
			return true;
		}
	}
}
