import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class JDBCGUI extends JFrame
{
	static  private Connection link;
	private Statement statement;
	private ResultSet results;
	private JTable table;
	private JScrollPane scroller;
	private final String[] heading =
		{"Account No.","Surname","First Names","Balance"};
	private Vector<String> heads;
	private Vector<Object> row;
	private Vector<Vector<Object>> rows;

	public static void main(String[] args)
	{
		JDBCGUI frame = new JDBCGUI();
		frame.setSize(400,200);
		frame.setVisible(true);

		frame.addWindowListener(
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent winEvent)
				{
					try
					{
						link.close();
						System.exit(0);
					}
					catch(SQLException sqlEx)
					{
						System.out.println(
							"* Error on closing connection! *");
					}
				}
			}
		);
	}

	public JDBCGUI()
	{
		setTitle("Accounts Data");
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			link = DriverManager.getConnection(
									"jdbc:odbc:Finances","","");
			statement = link.createStatement();
			results = statement.executeQuery(
										"SELECT * FROM Accounts");

			heads = new Vector<String>();
			for (int i=0; i<heading.length; i++)
			{
				heads.add(heading[i]);
			}

			rows = new Vector<Vector<Object>>();

			while (results.next())
			{
				row = new Vector<Object>();//Heterogeneous collection.
				row.add(results.getInt(1));
				row.add(results.getString(2));
				row.add(results.getString(3));
				row.add(results.getFloat(4));
				rows.add(row);
			}
			table = new JTable(rows,heads);
			scroller = new JScrollPane(table);
			add(scroller, BorderLayout.CENTER);
		}
		catch(ClassNotFoundException cnfEx)
		{
			System.out.println("* Unable to load driver! *");
			System.exit(1);
		}
		catch(SQLException sqlEx)
		{
			System.out.println("* SQL error! *");
			System.exit(1);
		}
	}
}


