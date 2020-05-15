import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ShowSum extends HttpServlet
{
	public void service(HttpServletRequest request,
						HttpServletResponse response)
			throws IOException,ServletException
	{
		HttpSession adderSession = request.getSession();

		String firstTime = (String)adderSession.getValue("firstVisit");
		if (firstTime.equals("Yes"))
			retrieveNewPreferences(request,response,adderSession);

		sendPage(response,adderSession);
	}

	private void sendPage(HttpServletResponse reply,HttpSession session)
													throws IOException
	{
		String userName,foreColour,backColour,sum;

		userName = (String)session.getValue("name");
		foreColour = (String)session.getValue("foreColour");
		backColour = (String)session.getValue("backColour");

		//Value of 'sum' originally saved as instance of class Integer
		//(and saved as instance of class Object in session object),
		//so we cannot typecast into class String as done for three
		//values above. Instead, we use method toString of class Object...
		sum = session.getValue("sum").toString();

		reply.setContentType("text/HTML");

		PrintWriter out = reply.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Result</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY TEXT=" + foreColour +" BGCOLOR=" + backColour + ">");
		out.println("<CENTER>");
		if (!userName.equals(""))
			out.println("<H2>" + userName + "'s " + "Result</H2>");
		out.println("<BR><BR><BR><H3>" + sum + "</H3>");
		out.println("</CENTER>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
	}

	private void retrieveNewPreferences(HttpServletRequest request,
						HttpServletResponse response,HttpSession session)
	{
		final int AGE = 180;	//(180secs = 3mins)

		String forename = request.getParameter("Name");
		if (forename==null) return;	//Should never happen!

		if (!forename.equals(""))
		{
			Cookie nameCookie = new Cookie("name",forename);
			nameCookie.setMaxAge(AGE);
			response.addCookie(nameCookie);
			session.putValue("name",forename);
		}

		String fColour = request.getParameter("ForeColour");
		if (fColour.equals(""))
			fColour = "Black";
		Cookie foreColourCookie = new Cookie("foreColour",fColour);
		foreColourCookie.setMaxAge(AGE);
		response.addCookie(foreColourCookie);
		session.putValue("foreColour",fColour);

		String bColour = request.getParameter("BackColour");
		if (bColour.equals(""))
			bColour = "White";
		Cookie backColourCookie = new Cookie("backColour",bColour);
		backColourCookie.setMaxAge(AGE);
		response.addCookie(backColourCookie);
		session.putValue("backColour",bColour);

		Cookie visitCookie = new Cookie("firstVisit","No");
		visitCookie.setMaxAge(AGE);
		response.addCookie(visitCookie);
		session.putValue("firstVisit","No");
	}
}


