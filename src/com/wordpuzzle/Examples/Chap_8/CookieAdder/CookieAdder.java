import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CookieAdder extends HttpServlet
{
	public void doPost(HttpServletRequest request,
						HttpServletResponse response)
			throws IOException,ServletException
	{
		int sum=0;

		try
		{
			String value1 = request.getParameter("Num1");
			String value2 = request.getParameter("Num2");

			int num1 = Integer.parseInt(value1);
			int num2 = Integer.parseInt(value2);
			sum = num1 + num2;

		}
		catch(NumberFormatException nfEx)
		{
			sendPage(response,"*** Invalid entry! ***");
			return;
		}

		HttpSession adderSession = request.getSession();
		adderSession.putValue("sum",new Integer(sum));

		Cookie[] cookie = request.getCookies();

		int numCookies = cookie.length;

		for (int i=0; i<numCookies; i++)
			adderSession.putValue(
				cookie[i].getName(),cookie[i].getValue());

		if (adderSession.getValue("firstVisit") == null)
			response.sendRedirect("GetPreferences");
		else
			response.sendRedirect("ShowSum");
	}

	private void sendPage(HttpServletResponse reply,String message)
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
		out.println("<CENTER>" + message + "</CENTER>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
	}
}


