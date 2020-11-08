<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
	<!-- read user name and password from user to create account
	     and send to usercontrollers registeruser method
	-->
<body>
	<jsp:include page="header.jsp" />
	<hr />
	<div align=center>
		<h2>eLoan Registration Page</h2>
		<form action="user?action=registernewuser" method="post">
			<%
				Object userNameAlreadyExists = request.getAttribute("userExists");
				if (userNameAlreadyExists!=null) {
			%>
				<p>User already exists!! Try another User Name</p>
			<%
				}
			%>

			<div>
				<div>
					<label for="loginid">Enter login Id</label>
				</div>
				<div>
					<input type="text" id="loginid" name="loginid">
				</div>
			</div>
			<div>
				<div>
					<label for="password">Enter password</label>
				</div>
				<div>
					<input type="password" id="password" name="password">
				</div>
			</div>
			<div>
				<input type="submit" value="Register">
			</div>
		</form>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>
</html>