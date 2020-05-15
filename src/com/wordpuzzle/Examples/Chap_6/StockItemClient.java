import Sales.*;

import org.omg.CosNaming.*;
import org.omg.CORBA.*;

public class StockItemClient
{
	public static void main(String[] args)
	{
		try
		{
			//Create and initialise the ORB...
			ORB orb = ORB.init(args,null);

			//Get a reference to the root naming context...
			org.omg.CORBA.Object objectRef =
								orb.resolve_initial_references("NameService");

			//'Narrow' ('downcast') the context reference...
			NamingContext namingContext =
									NamingContextHelper.narrow(objectRef);

			//Create a NameComponent object for the Stock interface...
			NameComponent nameComp = new NameComponent("Stock", "");

			//Specify the path to the interface...
			NameComponent[] stockPath = {nameComp};

			//Get a reference to the interface (reusing existing ref)...
			objectRef = namingContext.resolve(stockPath);

			//'Narrow' ('downcast') the reference...
			StockItem stockRef1 = StockItemHelper.narrow(objectRef);

			//Now use reference to call methods of the Stock object...
			System.out.println("\nStock code: " + stockRef1.code());
			System.out.println("Current level: " + stockRef1.currentLevel());
			stockRef1.addStock(58);
			System.out.println("\nNew level: " + stockRef1.currentLevel());


			//Create a NameComponent object for the StockFactory interface...
			NameComponent factoryNameComp =
									new NameComponent("StockFactory", "");

			//Specify the path to the interface...
			NameComponent[] factoryPath = {factoryNameComp};

			//Get a reference to the interface (reusing existing ref)...
			objectRef = namingContext.resolve(factoryPath);

			//'Narrow' ('downcast') the reference...
			StockItemFactory stockFactoryRef =
									StockItemFactoryHelper.narrow(objectRef);

			//Use factory reference to create a StockItem object on the server
			//and return a reference to this StockItem (using createItem method
			//within StockItemFactory interface)...
			StockItem stockRef2 = stockFactoryRef.createItem("S0002",200);

			//Now use reference to call methods of the StockItem object...
			System.out.println("\nStock code: " + stockRef2.code());
			System.out.println("Current level: " + stockRef2.currentLevel());
		}
		catch (Exception ex)
		{
			System.out.println("*** Client error! ***");
			ex.printStackTrace();
		}
	}
}
