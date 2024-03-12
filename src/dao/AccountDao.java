package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Account;

public class AccountDao {

	public void insertRecord(Connection con, Account account) {

		String str = "insert into account values(?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(str);

			ps.setInt(1, account.getAccNo());
			ps.setString(2, account.getAccHolderName());
			ps.setDouble(3, account.getAccBalance());
			int i = ps.executeUpdate();

			if (i > 0) {
				System.out.println("Record is inserted....!");
			} else {
				System.out.println("There is an error...");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void updateRecord(Connection con, int incr, String accHolderName) {

		try {
			String str = "update account set accBal= accBal + ? where accHolderName = ?";

			PreparedStatement ps = con.prepareStatement(str);
			ps.setInt(1, incr);
			ps.setString(2, accHolderName);
			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("Account updated successfully...!");
			} else {
				System.out.println("There is an error...");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void deleteRecord(Connection con, int accNo) {
		try {
			String str = "delete from account where accNo=?";

			PreparedStatement ps = con.prepareStatement(str);

			ps.setInt(1, accNo);
			int i = ps.executeUpdate();

			if (i > 0) {
				System.out.println("Account is deleted successfully...!");
			} else {
				System.out.println("There is an error...");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

//to change on deposit() in database....

	public void updateRecord2(Connection con, Account account) {
		double newBal = account.getAccBalance();
		int accNo = account.getAccNo();

		try {
			String str = "update account set accBal= ? where accNo = ?";

			PreparedStatement ps = con.prepareStatement(str);
			ps.setDouble(1, newBal);
			ps.setInt(2, accNo);
			int i = ps.executeUpdate();
			if (i > 0) {
				System.out.println("Account updated successfully...!");
			} else {
				System.out.println("There is an error...");
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void getAllRecods(Connection con) {
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from account");
			while (rs.next()) {
				System.out.println("Account No : " + rs.getInt("accNo"));
				System.out.println("Account Holder Name : " + rs.getString("accHolderName"));
				System.out.println("Account Balance : " + rs.getDouble("accBal"));
				System.out.println("*************************************************");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

}
