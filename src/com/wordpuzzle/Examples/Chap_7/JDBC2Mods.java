import java.sql.*;
public class JDBC2Mods
{
	static private Connection link;
	static private Statement statement;
	static private ResultSet results;

    public static void main(String[] args)
    {
		try
		{
         	//Step 1...
         	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

         	//Step 2...
         	link = DriverManager.getConnection(
                                 "jdbc:odbc:Finances","","");
      	}
      	catch(ClassNotFoundException cnfEx)
      	{
		  	System.out.println ("* Unable to load driver! *");
		  	System.exit(1);
	  	}
     	 //For any of a number of reasons, it may not be
      	//possible to establish a connection...
      	catch(SQLException sqlEx)
      	{
         	System.out.println("* Cannot connect to database! *");
            System.exit(1);
        }
        try
        {
         	//Step 3...
         	statement = link.createStatement(
                               ResultSet.TYPE_SCROLL_SENSITIVE,
                               ResultSet.CONCUR_UPDATABLE);

          	System.out.println("\nInitial contents of table:\n");
        	//Steps 4 and 5...
         	displayTable();

         	//Start of step 6...

         	//First the update...
         	results.absolute(2);//Move to row 2 of ResultSet.
         	results.updateFloat("balance", 42.55f);
         	results.updateRow();

         	//Now the insertion...
        	results.moveToInsertRow();
         	results.updateInt("acctNum", 999999);
         	results.updateString("surname", "Harrison");
         	results.updateString("firstNames", "Christine Dawn");
         	results.updateFloat("balance", 2500f);
         	results.insertRow();

         	//Finally, the deletion...
        	results.absolute(3);   //Move to row 3.
        	results.deleteRow();

          	System.out.println("\nNew contents of table:\n");
          	displayTable();
         	//End of step 6.

         	//Step 7...
         	link.close();
      	}
      	catch(SQLException sqlEx)
      	{
         	System.out.println("* SQL or connection error! *");
         	sqlEx.printStackTrace();
         	System.exit(1);
      	}
   	}
   	public static void displayTable() throws SQLException
   	{
      	String select = "SELECT * FROM Accounts";

		results = statement.executeQuery(select);

		System.out.println();

      	while (results.next())
      	{
		  	System.out.println("Account no. "
                           + results.getInt(1));
	     	System.out.println("Account holder:  "
	                       + results.getString(3)
	                       + " " + results.getString(2));
	      	System.out.printf("Balance: %.2f %n%n",
	      							results.getFloat(4));
	  	}
   	}
}
