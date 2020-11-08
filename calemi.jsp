<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />
	<div align="right"><a href="index.jsp">Logout</a></div>
	<div align=center>
		<h2>eLoan Process</h2>
<!--
     Read the values from the admin servlet and cal emi and other details and send to
     to the same admin servlet to update the values in the database 
 -->
		<form action="admin?action=callemi" method="post">
			<%
				Object approvedLoanInfo = request.getAttribute("approvedLoan");
				if (approvedLoanInfo != null) {
			%>

			<div>
				<span><label for="loanid">Application No:</label> </span> <span><input
					type="text" id="loanid" name="loanid"
					value="${approvedLoan.applno}" readonly> </span>
			</div>

			<div>
				<span><label for="loan_amount_sanctioned">Loan amount
						sanctioned:</label> </span> <span><input required type="text"
					id="loan_amount_sanctioned" name="loan_amount_sanctioned" value="${approvedLoan.amotsanctioned}">
				</span>
			</div>

			<div>
				<span><label for="loan_duration">Loan Duration:</label> </span> <span><input required
					type="text" id="loan_duration" name="loan_duration" value="${approvedLoan.loanterm}">
				</span>
			</div>

			<div>
				<span><label for="payment_start_date">Payment Start
						Date:</label> </span> <span><input type="date" required id="payment_start_date"
					name="payment_start_date" pattern="YYYY-MM-DD" value="${approvedLoan.psd}"> </span>(DD-MM-YYYY)
			</div>

			<div>
				<span><label for="loan_closure_date">Loan Closure Date is:</label> ${approvedLoan.lcd} </span>
			</div>

			<div>
				<span><label for="monthly_payment">Monthly Payment(EMI):</label> ${approvedLoan.emi} </span>
			</div>

			<div>
				<input type="submit" name = "context" value="Calculate EMI"> 
				<input type="submit" name = "context" value="Approve">
				<input type="submit" name = "context" value="Reject">
			</div>

			<%
				} else {
			%>
			<div>
				<span><label for="appid">Enter Application Id to
						Calculate EMI</label></span> <input type="text" id="appid" name="appid">
			</div>

			<div>
				<input type="submit" value="Fetch Loan">
			</div>
			<%
				}
			%>
		</form>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>