<!-- NumError.jsp -->

<%@ page isErrorPage="true" %>

<HTML>

	<HEAD>
		<TITLE>Error Page</TITLE>
	</HEAD>

	<BODY>

		<BR><BR><BR>
		<CENTER><H3>Data Entry Error<BR><BR>
		<FONT COLOR="red"><%= exception.toString() %></FONT></H3>
		<BR><BR><BR>

		<FORM METHOD=GET ACTION="SimpleAdderX.html">
			<INPUT TYPE="Submit" VALUE="Try again">
		</FORM>
		<CENTER>

		<!-- Lines below simply for padding (to exceed 512 bytes)! -->
		<BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR>
		<BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR>


	</BODY>

</HTML>

