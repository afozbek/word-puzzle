//Server.
import java.rmi.*;
import java.util.Vector;

public class Bank1Server
{
	private static final String HOST = "localhost";

	public static void main(String[] args) throws Exception
	{
		Account[] account =
			{new Account(111111,"Smith","Fred James",112.58),
			new Account(222222,"Jones","Sally",507.85),
			new Account(234567,"White","Mary Jane",2345.00),
			new Account(666666,"Satan","Beelzebub",666.00)};

		Vector<Account> acctDetails = new Vector<Account>();

		for (int i=0; i<account.length; i++)
			acctDetails.add(account[i]);

		Bank1Impl temp = new Bank1Impl(acctDetails);

		String rmiObjectName = "rmi://" + HOST + "/Accounts";
		//Could omit host name, since 'localhost' would be
		//assumed by default.

		Naming.rebind(rmiObjectName,temp);
		System.out.println("Binding complete...\n");
	}
}


