<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>eLogin Portal</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<hr />
	<div align=center>
		<h2>eLoan Login</h2>
		<form action="user?action=validate" method="post">
		
			<%
				Object userLoginFailed = request.getAttribute("userLoginFailed");
				if (userLoginFailed!=null) {
			%>
				<p>Login failed!! Please try after sometime</p>
			<%
				}
			%>
		
			<div>
				<span><label for="loginid">Enter login Id</label> </span> <span><input
					type="text" id="loginid" name="loginid"> </span>
			</div>
			<div>
				<span><label for="password">Enter password</label> </span> <span><input
					type="password" id="password" name="password"> </span>
			</div>

			<div>
				Select login type:<select UserType="usertype" name="usertype">
					<option value=""></option>
					<option value="User">User</option>
					<option value="Admin">Admin</option>
				</select>
			</div>

			<div>
				<div>
					<input type="submit" value="Login">
				</div>
			</div>
			New User? register <a href="./register.jsp"> here</a>
		</form>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>