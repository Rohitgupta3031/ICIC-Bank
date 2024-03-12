package entity;

public class Account {

	private int accNo;
	private String accHolderName;
	private double accBalance;

	public Account() {
		accNo = 0;
		accHolderName = "";
		accBalance = 0.0;
	}

	public Account(int accNo, String accHolderName, double accBalance) {
		this.accNo = accNo;
		this.accHolderName = accHolderName;
		this.accBalance = accBalance;
	}

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}

	public String getAccHolderName() {
		return accHolderName;
	}

	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}

	public double getAccBalance() {
		return accBalance;
	}

	public void setAccBalance(double accBalance) {
		this.accBalance = accBalance;
	}

	@Override
	public String toString() {
		return "Account [accNo=" + accNo + ", accHolderName=" + accHolderName + ", accBalance=" + accBalance + "]";
	}

}
