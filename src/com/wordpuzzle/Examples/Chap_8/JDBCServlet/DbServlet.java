import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DbServlet extends HttpServlet
{
	private Statement statement;
	private Connection link;
	private String URL = "jdbc:odbc:HomeDB";

	public void init() throws ServletException
	{
		super.init();

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			link = DriverManager.getConnection(URL,"","");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			System.exit(1);
		}
	}

	public void doPost(HttpServletRequest request,
								HttpServletResponse response)
						throws ServletException,IOException
	{
		String surname,forenames,telNum;

		surname = request.getParameter("Surname");
		forenames = request.getParameter("Forenames");
		telNum = request.getParameter("PhoneNum");

		response.setContentType("text/HTML");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Servlet + JDBC</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");

		String insertion = "INSERT INTO PhoneNums"
						+ " VALUES('" + surname + "','"
						+ forenames + "','" + telNum + "')";

		try
		{
			statement = link.createStatement();
			statement.executeUpdate(insertion);
			statement.close();	//Ensures committal.
		}
		catch (SQLException sqlEx)
		{
			out.println("<BR><CENTER><H2>Unable to execute insertion!</H2></CENTER>");
			out.println("</BODY>");
			out.println("</HTML>");
			out.flush();
			System.exit(1);
		}


		try
		{
			statement = link.createStatement();
			ResultSet results =
				statement.executeQuery("SELECT * FROM PhoneNums");

			out.println("Updated table:");
			out.println("<BR><BR><CENTER>");
			out.println("<TABLE BORDER>");
			out.println("<TR><TH>Surname</TH>");
			out.println("<TH>Forename(s)</TH>");
			out.println("<TH>Phone No.</TH></TR>");

			while (results.next())
			{
				out.println("<TR>");
				out.println("<TD>");
				out.println(results.getString("Surname"));
				out.println("</TD>");
				out.println("<TD>");
				out.println(results.getString("Forenames"));
				out.println("</TD>");
				out.println("<TD>");
				out.println(results.getString("PhoneNum"));
				out.println("</TD>");
				out.println("</TR>");
			}
			out.println("</TABLE>");
		}
		catch(SQLException sqlEx)
		{
			out.println("<BR><H2>Unable to retrieve data!</H2>");
			out.println("</BODY>");
			out.println("</HTML>");
			out.flush();
			System.exit(1);
		}

		out.println("</CENTER>");
		out.println("<BODY>");
		out.println("</HTML>");
		out.flush();
	}

	public void destroy()
	{
		try
		{
			link.close();
		}
		catch(Exception ex)
		{
			System.out.println("Error on closing database!");
			ex.printStackTrace();
			System.exit(1);
		}
	}
}

