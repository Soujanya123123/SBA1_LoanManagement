<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- write the code to read application number, and send it to admincontrollers
	     callemi method to calculate the emi and other details also provide links
	     to logout and admin home page
	-->
	
<body>
	<jsp:include page="header.jsp" />
	<hr />
	<div align=center>
		<h2>eLoan Process</h2>
		<form action="admin?action=callemi" method="post">
			<div>
				<span><label for="appid">Enter Application Id</label> </span> 
				<span><input type="text" id="appid" name="appid"> </span>
			</div>
			<div>
				<div>
					<input type="submit" value="Process Loan">
				</div>
			</div>
		</form>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />
</body>


</body>
</html>