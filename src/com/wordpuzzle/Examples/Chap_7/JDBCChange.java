import java.sql.*;

public class JDBCChange
{
   static Connection link;
   static Statement statement;
   static ResultSet results;
   //Alternatively, the above 3 variables may
   //be declared non-static within main, but
   //must then be initialised explicitly to null.

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
         System.out.println
                       ("* Cannot connect to database! *");
         System.exit(1);
      }

      try
      {
         //Step 3...
         statement = link.createStatement();

         System.out.println("\nInitial contents of table:");
         //Steps 4 and 5...
         displayTable();

        //Start of step 6...
         String insert = "INSERT INTO Accounts"
                     + " VALUES (112233,'Smith',"
                     + "'John James',752.85)";
         int result = statement.executeUpdate(insert);
         if (result == 0)
            System.out.println("\nUnable to insert record!");

         String change = "UPDATE Accounts"
                      + " SET surname = 'Bloggs',"
                      + "firstNames = 'Fred Joseph'"
                      + " WHERE acctNum = 123456";
         result = statement.executeUpdate(change);
         if (result == 0)
            System.out.println("\nUnable to update record!");

         String remove = "DELETE FROM Accounts"
                      + " WHERE balance < 100";
         result = statement.executeUpdate(remove);
         if (result == 0)
            System.out.println("\nUnable to delete record!");

         System.out.println("\nNew contents of table:");
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
