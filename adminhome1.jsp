<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin home</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div align="right"><a href="index.jsp">Logout</a></div>
<h4>Admin Dash Board</h4>
<a href="admin?action=listall">List All</a><br>
<a href="admin?action=process">Process Loan</a><br>
<a href="admin?action=logout">Admin Logout</a><br>
<jsp:include page="footer.jsp"/>
</body>
</html>