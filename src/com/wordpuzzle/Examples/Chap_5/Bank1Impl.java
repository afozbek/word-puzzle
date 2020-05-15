//Implementation of RMI interface.
import java.rmi.*;
import java.rmi.server.*;
import java.util.Vector;

public class Bank1Impl extends UnicastRemoteObject
									implements Bank1
{
	private Vector<Account> acctInfo;

	public Bank1Impl(Vector<Account> acctVals)
								throws RemoteException
	{
		acctInfo = acctVals;
	}

	public Vector<Account> getBankAccounts()
								throws RemoteException
	{
		return acctInfo;
	}
}

