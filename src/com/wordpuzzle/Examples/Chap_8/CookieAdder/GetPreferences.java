
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class GetPreferences extends HttpServlet
{
	public void doGet(HttpServletRequest request,
						HttpServletResponse response)
			throws IOException,ServletException
	{
		response.setContentType("text/HTML");

		HttpSession adderSession = request.getSession();
		adderSession.putValue("firstVisit","Yes");

		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Preferences</TITLE>");
		out.println("</HEAD>");

		out.println("<BODY>");

		out.println("<BR><BR><BR>");
		out.println("<CENTER>");
		out.println("<FORM  METHOD=POST ACTION='ShowSum'>");
		out.println("<FONT COLOR='Blue' SIZE=5>User Preferences</FONT>");
		out.println("<BR>");
		out.println("<TABLE>");
		out.println("<TR>");
		out.println("<TD>First name</TD>");
		out.println("<TD><INPUT  TYPE='Text'  NAME='Name'  VALUE=''  SIZE=15></TD>");
		out.println("</TR>");
		out.println("<TR>");
		out.println("<TD>Foreground colour</TD>");
		out.println("<TD><INPUT  TYPE='Text' "
					+ "NAME='ForeColour'  VALUE=''  SIZE=10></TD>");
		out.println("</TR>");
		out.println("<TR>");
		out.println("<TD>Background colour</TD>");
		out.println("<TD><INPUT  TYPE='Text' "
					+ "NAME='BackColour'  VALUE=''  SIZE=10></TD>");
		out.println("</TR>");
		out.println("</TABLE>");

		out.println("<BR><BR>");
		out.println("<INPUT  TYPE='Submit'  VALUE = 'Submit'></TD>");
		out.println("<INPUT  TYPE='Reset'  VALUE='Clear'></TD>");
		out.println("</CENTER>");

		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
	}
}
