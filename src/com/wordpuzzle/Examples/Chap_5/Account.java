//Class from which objects will be created 
//and then passed as arguments.

import java.io.Serializable;

public class Account implements Serializable
{
	private int acctNum;
	private String surname;
	private String firstNames;
	private double balance;

	public Account(int acctNo, String sname, String fnames, double bal)
	{
		acctNum = acctNo;
		surname = sname;
		firstNames = fnames;
		balance = bal;
	}

	public int getAcctNum()
	{
		return acctNum;
	}

	public String getName()
	{
		return (firstNames + " " + surname);
	}

	public double getBalance()
	{
		return balance;
	}

	public double withdraw(double amount)
	{
		if (amount <= balance)
			return amount;
		else 
			return 0;
	}

	public void deposit(double amount)
	{
		if (amount > 0)
			balance += amount;
	}
}
