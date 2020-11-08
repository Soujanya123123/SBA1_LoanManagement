<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>eLoan Application</title>
</head>
<body>
	<!-- write the code to display the loan status information 
	     received from usercontrollers' displaystatus method
	-->
	Loan Details:-
	---------------------
	<p> Loan ID: ${fetchedLoan.applno} </p>
	<p> Purpose: ${fetchedLoan.purpose} </p>
	<p> Amount: ${fetchedLoan.amtrequest} </p>
	<p> Date of Application: ${fetchedLoan.doa} </p>
	<p> Your Address: ${fetchedLoan.address} </p>
	<p> Mobile Number: ${fetchedLoan.mobile} </p>
	
	Edit this loan by clicking : <a href="./editloan.jsp?editloanid=${fetchedLoan.applno}"> here </a>
	Navigate to <a href="./userhome.jsp"> User home</a>
	
</body>
</html>