<!-- Checkout.jsp -->

<%@ page import="java.util.Enumeration" import="java.text.DecimalFormat" %>

<%
	final float APPLES_PRICE = 1.45F;
	final float PEARS_PRICE = 1.75F;
	//In a real application, above prices would be retrieved
	//from a database, of course.
%>
<HTML>
	<HEAD>
		<TITLE>Checkout</TITLE>
	</HEAD>

	<BODY>
		<BR><BR><BR>

		<CENTER>

		<H1><FONT COLOR=Red>Order List</FONT></H1>
		<BR><BR><BR>

		<TABLE BGCOLOR=Aqua BORDER=2>
			<TR>
				<TH>Item</TH>
				<TH>Weight(kg)</TH>
				<TH>Cost(£)</TH>
			</TR>
			<%
				session.removeAttribute("currentProd");
				Enumeration prodNames = session.getAttributeNames();
				float totalCost = 0;
				DecimalFormat costFormat = new DecimalFormat("00.00");


				int numProducts = 0;
				while (prodNames.hasMoreElements())
				{
					float wt=0,cost=0;
					String product = (String)prodNames.nextElement();
					String stringWt = (String)session.getAttribute(product);
					wt = Float.parseFloat(stringWt);
					if (product.equals("Apples"))
						cost = APPLES_PRICE * wt;
					else if (product.equals("Pears"))
						cost = PEARS_PRICE * wt;
			%>
			<TR>
				<TD><%= product %></TD>
				<TD><%= wt %></TD>
				<TD><%= costFormat.format(cost) %></TD>
			</TR>
			<%
					totalCost+=cost;
					numProducts++;
				}
			%>
			<TR BGCOLOR=Yellow>
			<%
				if (numProducts == 0)
				{
			%>
				<TD>*** No orders placed! ***</TD>
			<%
				}
				else
				{
			%>
				<TD></TD>	<!-- Blank cell -->
				<TD>Total cost:</TD>
				<TD><%=  costFormat.format(totalCost) %></TD>
			</TR>
			<%
				}
			%>
		</TABLE>
		</CENTER>

	</BODY>
</HTML>


