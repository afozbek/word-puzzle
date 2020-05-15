//Client.
import java.rmi.*;
import java.util.Vector;

public class Bank1Client
{
	private static final String HOST = "localhost";

	public static void main(String[] args)
	{
		try
		{
			Bank1 temp = (Bank1)Naming.lookup(
										"rmi://" + HOST + "/Accounts");

			Vector<Account> acctDetails = temp.getBankAccounts();

			//Simply display all account details...

			for (int i=0; i<acctDetails.size(); i++)
			{
				Account acct = acctDetails.elementAt(i);
				System.out.println("\nAccount number: " + acct.getAcctNum());
				System.out.println("Name: " + acct.getName());
				System.out.println("Balance: " + acct.getBalance());
			}
		}
		catch(ConnectException conEx)
		{
			System.out.println("Unable to connect to server!");
			System.exit(1);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.exit(1);
		}

	}
}

