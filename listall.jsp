<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
table, th, td {
  border: 1px solid black;
}
</style>
<meta charset="ISO-8859-1">
<title>Display All Loans</title>
</head>
<body>
	<!-- write code to display all the loan details 
             which are received from the admin controllers' listall method
	--> 
	<form action="user?action=listall" method="post">	
	<table>
	    <tr>
	        <th>List of All Loans</th>
	    </tr>
	    <c:forEach var="AllLoansList" items="${listAllLoansService}">
	        <tr>  
	            <td>${AllLoansList.getApplno().trim()}</td>
	            <td>${AllLoansList.getPurpose()}</td>
	            <td>${AllLoansList.getAmtrequest()}</td>
	            <td>${AllLoansList.getDoa()}</td>
	            <td>${AllLoansList.getBstructure()}</td>
	            <td>${AllLoansList.getMobile()}</td>
	            <td>${AllLoansList.getEmail()}</td>
	        </tr>
	    </c:forEach>
	</table>
	
	<div>
	Navigate to <a href="./adminhome1.jsp"> Admin Home</a>
	</div>
	</form>
</html>