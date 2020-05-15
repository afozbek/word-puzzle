import java.sql.*;

public class JDBCMetaData
{
   static Connection link;
   static Statement statement;
   static ResultSet results;

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
      catch(SQLException sqlEx)
      {
         System.out.println("* Cannot connect to database! *");
         System.exit(1);
      }

      try
      {
         //Step 3...
         statement = link.createStatement();

         String select = "SELECT * FROM Accounts"
                         + " WHERE acctNum = 123456";
         //Step 4...
         results = statement.executeQuery(select);

         //Start of step 5...
         ResultSetMetaData metaData = results.getMetaData();
         int numFields = metaData.getColumnCount();
         boolean found = results.next();

         if (!found)
         {
            System.out.println("\nNot found!");
            link.close();
            return;
		 }

         for (int i=1; i<=numFields; i++)
         {
            System.out.println("\nField name: "
                              + metaData.getColumnName(i));

	        System.out.println("Field type:  "
                              + metaData.getColumnTypeName(i));

            int colType = metaData.getColumnType(i);
            System.out.print("Value: ");
            switch (colType)
            {
				case Types.INTEGER:
				      System.out.println(results.getInt(i));
				      break;
				case Types.VARCHAR:
				      System.out.println(results.getString(i));
				      break;
				case Types.NUMERIC:
                      System.out.printf("%.2f %n%n",
                                    		results.getFloat(i));
				      break;
                default: System.out.println("Unknown");
			}
         }


        //(No further queries, so no Step 6!)

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
}
