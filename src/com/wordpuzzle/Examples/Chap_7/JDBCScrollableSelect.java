import java.sql.*;

public class JDBCScrollableSelect
{
   static private Connection link;
   static private Statement statement;
   static private ResultSet results;

   public static void main(String[] args)
   {
      try
      {
         Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
         link = DriverManager.getConnection(
			 					"jdbc:odbc:Finances","","");
      }
      catch(ClassNotFoundException cnfEx)
      {
         System.out.println("* Unable to load driver! *");
         System.exit(1);
      }
      catch(SQLException sqlEx)
      {
         System.out.println("* Cannot connect to database! *");
         System.exit(1);
      }

      try
      {
         statement =
            link.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
            							ResultSet.CONCUR_READ_ONLY);
         results = statement.executeQuery("SELECT * FROM Accounts");
      }
      catch(SQLException sqlEx)
      {
         System.out.println("* Cannot execute query! *");
         sqlEx.printStackTrace();
         System.exit(1);
      }

      try
      {
         while (results.next())
            showRow();
      }
      catch(SQLException sqlEx)
      {
         System.out.println("* Error retrieving data! *");
         sqlEx.printStackTrace();
         System.exit(1);
      }

      try
      {
         while (results.previous())
            showRow();
      }
      catch(SQLException sqlEx)
      {
         System.out.println("* Error retrieving data! *");
         sqlEx.printStackTrace();
         System.exit(1);
      }

      try
      {
         link.close();
      }
      catch(SQLException sqlEx)
      {
         System.out.println("* Unable to disconnect! *");
         sqlEx.printStackTrace();
      }
   }

   static void showRow() throws SQLException
   {
      System.out.println();
      System.out.println("Account no. " + results.getInt(1));
      System.out.println("Account holder:  "
                          + results.getString(3)
                          + " " + results.getString(2));
	  System.out.printf("Balance: %.2f %n%n",
								     results.getFloat(4));
   }
}
