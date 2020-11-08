<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<!-- <body>
	write html code to read the application number and send to usercontrollers'
             displaystatus method for displaying the information
	
</body> -->

<body>
	<jsp:include page="header.jsp" />
	<hr />
	<div align=center>
		<h2>eLoan Login</h2>
		<form action="user?action=displaystatus" method="post">
			<div>
				<span><label for="appid">Enter Application Id</label> </span> 
				<span><input type="text" id="appid" name="appid"> </span>
			</div>

			<div>
				<div>
					<input type="submit" value="Fetch Details">
				</div>
			</div>
		</form>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />
</body>


</html>