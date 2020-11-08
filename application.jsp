<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Application Form</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
</head>
<!-- <body onload="myFunction()"> -->
<!--
	write the html code to accept laon info from user and send to placeloan servlet
-->
</script>
<!-- </body> -->

<body>
	<jsp:include page="header.jsp" />
	<hr />
	<div align=center>
		<h2>eLoan Details</h2>
		<form action="user?action=placeloan" method="post">

			<div>
				Select Loan type:<select name="loantype">
					<option value=""></option>
					<option value="Home">Home</option>
					<option value="Education">Education</option>
					<option value="Property">Property</option>
					<option value="Vehicle">Vehicle</option>
					<option value="Gold">Gold</option>
				</select>
			</div>

			<div>
				Enter Amount
				<input type="text" id="loanamtreq" name="loanamtreq" /> 
			</div>

			<div>
				Enter Loan Application Date: <input type="date" id="dateofapplication" name="dateofapplication" pattern="MM-dd-yyyy" />
			</div>

			<div>
				Select Business Structure:<select name="bizstructure" >
					<option value=""></option>
					<option value="Individual">Individual</option>
					<option value="Organization">Organization</option>
				</select>
			</div>

			<div>
				Select Billing Indicator:<select name="billingindicator">
					<option value=""></option>
					<option value="Salaried">Salaried</option>
					<option value="Non-Salaried">Non-Salaried</option>
				</select>
			</div>

			<div>
				Select Tax Indicator:<select name="taxindicator">
					<option value=""></option>
					<option value="Y">Y</option>
					<option value="N">N</option>
				</select>
			</div>

			<div>
				<span><label for="address">Enter Address</label> </span> <span><input
					type="text" id="address" name="address"> </span>
			</div>

			<div>
				<span><label for="phonenum">Enter Phone Number</label> </span> <span><input
					type="number" id="phonenum" name="phonenum"> </span>
			</div>
			<div>
				<span><label for="email">Enter Email Address</label> </span> <span><input
					type="email" id="email" name="email"> </span>
			</div>

			<div>
				<div>
					<input type="submit" value="Apply">
				</div>
			</div>
		</form>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />
</body>
</html>