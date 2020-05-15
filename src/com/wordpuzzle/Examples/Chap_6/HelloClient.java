import SimpleCORBAExample.*;

import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class HelloClient
{
	public static void main(String[] args)
	{
		try
		{
			ORB orb = ORB.init(args,null);
			org.omg.CORBA.Object objectRef =
								orb.resolve_initial_references("NameService");
			NamingContext namingContext = NamingContextHelper.narrow(objectRef);
			NameComponent nameComp= new NameComponent("Hello", "");
			NameComponent[] path = {nameComp};
			objectRef = namingContext.resolve(path);//Re-use existing obj ref.
			Hello helloRef = HelloHelper.narrow(objectRef);

			String greeting = helloRef.getGreeting();
			System.out.println("Message received: " + greeting);
		}
		catch (Exception ex)
		{
			System.out.println("*** Client error! ***");
			ex.printStackTrace();
		}
	}
}

