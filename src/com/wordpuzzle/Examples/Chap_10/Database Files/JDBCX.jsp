<HTML>
	<%@ page language="java" contentType="text/html" 
	errorPage="JDBCXError.jsp" %>
	<jsp:useBean id="data" class="jdbc.JDBCBeanX" />

	<HEAD> 
		<TITLE>JDBC Bean Test 2</TITLE>
	</HEAD>

	<BODY>
		<CENTER>

		<H1>Results</H1>
		<BR><BR><BR>

		<TABLE BGCOLOR="aqua" BORDER=1>
			<TR>
				<TH BGCOLOR="orange">Acct.No.</TH>
				<TH BGCOLOR="orange">Acct.Name</TH>
				<TH BGCOLOR="orange">Balance</TH>
			</TR>


			<% 
				Vector<Object> nums = data.getAcctDetails();
				Integer acctNum;
				String acctName;
				Float balance;
				final int NUM_FIELDS = 3;

				for (int i=0; i<nums.size()/NUM_FIELDS; i++) 
				{
				//Auto-unboxing doesn't work here!
					acctNum = (Integer)nums.elementAt(i*NUM_FIELDS);
					acctName = (String)nums.elementAt(i*NUM_FIELDS + 1);
					balance = (Float)nums.elementAt(i*NUM_FIELDS + 2);	
			%>	
			<TR>

				<TD><%= acctNum %></TD>
				<TD><%= acctName %></TD>
				<TD><%= balance %></TD>
			</TR>

			<%
				 } 
			%>

		</TABLE>
		<BR><BR>
		Number of accounts held:  
		<FONT COLOR="blue">
		<jsp:getProperty name="data" property="numAccounts" /></FONT>
		</CENTER>
	</BODY>

</HTML>




