<!-- JDBCError.jsp -->

<%@ page isErrorPage="true" %>

<HTML>

	<HEAD>
		<TITLE>Error Page</TITLE>
	</HEAD>

	<BODY>

		<BR><BR><BR>
		<CENTER><H3>Data Retrieval Error<BR><BR>
		<FONT COLOR="red"><%= exception.toString() %></FONT></H3>
		<BR><BR><BR>

		<FORM METHOD=GET ACTION="JDBC.jsp">
			<INPUT TYPE="Submit" VALUE="Try again">
		</FORM>
		</CENTER>

	</BODY>

</HTML>

