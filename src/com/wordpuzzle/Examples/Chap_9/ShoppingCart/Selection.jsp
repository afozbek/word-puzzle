<!-- Selection.jsp -->

<% 
	String currentProduct;

	currentProduct = request.getParameter("Product"); 
	if (currentProduct.equals("Checkout"))
		response.sendRedirect("Checkout.jsp");
	else
		session.setAttribute("currentProd",currentProduct);
%> 

<HTML>

	<HEAD>
		<TITLE><%= currentProduct %></TITLE>
	</HEAD>

	<BODY>
		<CENTER>
		<H1><FONT COLOR=Red><%= currentProduct %></FONT></H1>
		<BR><BR><BR>

		<FORM METHOD=POST ACTION="WeightX">

			<TABLE>

				<TR>
					<TD>Quantity required (kg) 
					<INPUT TYPE='Text' NAME=Qty  VALUE=''  SIZE=5></TD>
				</TR>
			</TABLE>

			<BR><BR><BR>

			<TABLE>

				<TR>
					<TD><INPUT TYPE='Radio' NAME='Option' VALUE='Add' CHECKED>
					<FONT COLOR=blue>Add to cart.</FONT></TD>
				</TR>

				<TR>
					<TD><INPUT TYPE='Radio' NAME='Option' VALUE='Remove'>
					<FONT COLOR=blue>Remove item from cart.</FONT></TD>
				</TR>

				<TR>
					<TD><INPUT TYPE='Radio' NAME='Option' VALUE='Next'>
					<FONT COLOR=blue>	Choose next item.</FONT></TD>
				</TR>

				<TR>
					<TD><INPUT TYPE='Radio' NAME='Option' VALUE='Checkout'>
					<FONT COLOR=blue>Go to checkout.</FONT></TD>
				</TR>

			</TABLE>

			<BR><BR><BR>

			<INPUT TYPE='Submit' VALUE='Submit'>

		</FORM>
		</CENTER>

	</BODY>

</HTML>
