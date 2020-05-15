//RMI interface.
import java.rmi.*;
import java.util.Vector;

public interface Bank1 extends Remote
{
	public Vector<Account> getBankAccounts() throws RemoteException;
}

