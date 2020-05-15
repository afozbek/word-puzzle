import SimpleCORBAExample.*;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

public class HelloServer
{
	public static void main(String[] args)
	{
		try
		{
			ORB orb = ORB.init(args,null);
			HelloServant servant = new HelloServant();
			orb.connect(servant);
			org.omg.CORBA.Object objectRef = orb.resolve_initial_references("NameService");
			NamingContext namingContext = NamingContextHelper.narrow(objectRef);
			NameComponent nameComp= new NameComponent("Hello", "");
			NameComponent[] path = {nameComp};
			namingContext.rebind(path,servant);
			java.lang.Object syncObj = new java.lang.Object();
			synchronized(syncObj)
			{
				syncObj.wait();
			}
		}
		catch (Exception ex)
		{
			System.out.println("*** Server error! ***");
			ex.printStackTrace();
		}
	}
}

class HelloServant extends _HelloImplBase
{
	public String getGreeting()
	{
		return ("Hello there!");
	}
}
