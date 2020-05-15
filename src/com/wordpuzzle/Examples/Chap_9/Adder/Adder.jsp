<!-- Adder.jsp -->

<%@ page errorPage="NumError.jsp" %>

<%
	String value1 = request.getParameter("Num1");
	String value2 = request.getParameter("Num2");
	int num1 = Integer.parseInt(value1);
	int num2 = Integer.parseInt(value2);
	int sum = num1 + num2;
%>

<HTML>

	<HEAD>
		<TITLE>Result</TITLE>
	</HEAD>

	<BODY>
		<BR><BR><BR>
		<CENTER><H1><FONT COLOR='blue'>
		<%= "Result = " + sum %>
		</FONT></H1></CENTER>
	</BODY>

</HTML>
