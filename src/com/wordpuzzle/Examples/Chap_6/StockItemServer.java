import Sales.*;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

public class StockItemServer
{
	public static void main(String[] args)
	{
		try
		{
			//Create and initialise the ORB...
			ORB orb = ORB.init(args,null);

	      	//Create a StockItemServant object...
	     	StockItemServant stockServant =
	        				new StockItemServant("S0001", 100);

	      	//Register the object  with the ORB...
			orb.connect(stockServant);

	      	//Create a StockItemFactoryServant object....
	     	StockItemFactoryServant factoryServant =
	        					new StockItemFactoryServant();

	      	//Register the object  with the ORB...
			orb.connect(factoryServant);


			//Get a reference to the root naming context...
			org.omg.CORBA.Object objectRef =
					orb.resolve_initial_references("NameService");

			//'Narrow' ('downcast') the context reference...
			NamingContext namingContext =
							NamingContextHelper.narrow(objectRef);

			//Create a NameComponent object
			//for the Stock interface...
			NameComponent nameComp =
								new NameComponent("Stock", "");

			//Specify the path to the interface...
			NameComponent[] stockPath = {nameComp};

			//Bind the servant to the interface path...
			namingContext.rebind(stockPath,stockServant);


			//Create a NameComponent object
			//for the StockFactory interface...
			NameComponent factoryNameComp =
						new NameComponent("StockFactory", "");

			//Specify the path to the interface...
			NameComponent[] factoryPath = {factoryNameComp};

			//Bind the servant to the interface path...
			namingContext.rebind(factoryPath,factoryServant);


			System.out.print("\nServer running...");

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

class StockItemServant extends _StockItemImplBase
{
	private String code = "";
	private int currentLevel = 0;

	public StockItemServant (String newCode, int newLevel)
	{
		code = newCode;
		currentLevel = newLevel;
	}

	public int addStock(int incNumber)
	{
		currentLevel += incNumber;
		return currentLevel;
	}

	public int removeStock(int decNumber)
	{
		currentLevel -= decNumber;
		return currentLevel;
	}

	public String code()
	{
		return code;
	}

	public int currentLevel()
	{
		return currentLevel;
	}

	public void currentLevel(int newLevel)
	{
		currentLevel = newLevel;
	}
}

class StockItemFactoryServant
					extends _StockItemFactoryImplBase
{
	public StockItem createItem(String newCode,
										int newLevel)
	{
		return (new StockItemServant(newCode,newLevel));
	}
}
