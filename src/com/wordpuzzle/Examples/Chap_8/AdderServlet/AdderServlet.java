import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AdderServlet extends HttpServlet
{
	public void doPost(HttpServletRequest request,
						HttpServletResponse response)
			throws IOException,ServletException
	{
		try
		{
			String value1 = request.getParameter("Num1");
			String value2 = request.getParameter("Num2");
			int num1 = Integer.parseInt(value1);
			int num2 = Integer.parseInt(value2);
			int sum = num1 + num2;

			sendPage(response,"Result = " + sum);
		}
		catch(NumberFormatException cnfEx)
		{
			sendPage(response,"*** Invalid entry! ***");
		}
	}

	private void sendPage(HttpServletResponse reply,String result)
												throws IOException
	{
		reply.setContentType("text/HTML");

		PrintWriter out = reply.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Result</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<BR><BR><BR>");
		out.println("<CENTER><H1>" + result + "</H1></CENTER>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
	}
}


