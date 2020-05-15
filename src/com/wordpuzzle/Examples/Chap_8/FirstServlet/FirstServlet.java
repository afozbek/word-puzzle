import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FirstServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request,
						HttpServletResponse response)
			throws IOException,ServletException
	{
		response.setContentType("text/HTML");

		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Simple Servlet</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<BR><BR><BR>");
		out.println("<CENTER><H1> A Simple Servlet </H1></CENTER>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
	}
}


