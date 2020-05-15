package jdbc;

import java.sql.*;
import java.util.*;

public class JDBCBeanX implements java.io.Serializable
{
	private static Vector<Object> acctDetails;
	private final int NUM_FIELDS = 3;

	public JDBCBeanX() throws SQLException,
										ClassNotFoundException
	{
			Connection link = null;
			Statement statement = null;
			ResultSet results = null;

			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			link = DriverManager.getConnection(
									"jdbc:odbc:Finances","","");
			statement = link.createStatement();
			results = statement.executeQuery(
										"SELECT * FROM Accounts");

			acctDetails = new Vector<Object>();

			while (results.next())
			{
				acctDetails.add(results.getInt(1));
				acctDetails.add(results.getString(3)
									+ " " + results.getString(2));
				acctDetails.add(results.getFloat(4));
			}

			link.close();
	}

	public Vector<Object> getAcctDetails()
	{
		return acctDetails;
	}

	public int getNumAccounts()
	{
		return acctDetails.size()/NUM_FIELDS;
	}
}


