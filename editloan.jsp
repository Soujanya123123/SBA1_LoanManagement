<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Loan Application</title>
</head>
<!-- <body>
	read the application number to edit from user and send to 
	     user controller to edit info
	
</body> -->


<body onload="populateOnEdit()">

<script>
function populateOnEdit(s, v) {
	setDropdownValue(document.getElementById('bizstructure'),'${editLoan.bstructure}');
	setDropdownValue(document.getElementById('billingindicator'),'${editLoan.bindicator}');
	setDropdownValue(document.getElementById('loantype'),'${editLoan.purpose}');
	setTextValue(document.getElementById('address'),'${editLoan.address}');
	setTextValue(document.getElementById('loanamtreq'),'${editLoan.amtrequest}');
	setTextValue(document.getElementById('email'),'${editLoan.email}');
	setTextValue(document.getElementById('phonenum'),'${editLoan.mobile}');
}

function setDropdownValue(s, v) {
    for ( var i = 0; i < s.options.length; i++ ) {
        if ( s.options[i].text == v ) {
        	console.log(s.options[i]);
            s.options[i].selected = true;
            return;
        }
    }
}

function setTextValue(ele,val){
	ele.innerHTML=val;
}


</script>

	<jsp:include page="header.jsp" />
	<hr />
	<div align=center>
		<h2>eLoan Edit</h2>
		<form action="user?action=editLoanProcess" method="post">
		<%
			Object editLoanInfo = request.getAttribute("editLoan");
			if (editLoanInfo!=null) {
		%>
		
			<div>
				<span><label for="loanid">Application No:</label> </span> <span><input
					type="text" id="loanid" name="loanid" value="${editLoan.applno}" readonly> </span>
			</div>
		
			<div>
				<span><label for="address">Edit Address</label> </span> <span><input
					type="text" id="address" name="address" value="${editLoan.address}"> </span>
			</div>
			
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
				Select Business Structure:<select  id="bizstructure" name="bizstructure" >
					<option value=""></option>
					<option value="Individual">Individual</option>
					<option value="Organization">Organization</option>
				</select>
			</div>

			<div>
				Select Billing Indicator:<select id="billingindicator" name="billingindicator">
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
				<span><label for="phonenum">Enter Phone Number</label> </span> <span><input
					type="number" id="phonenum" name="phonenum"> </span>
			</div>
			<div>
				<span><label for="email">Enter Email Address</label> </span> <span><input
					type="email" id="email" name="email"> </span>
			</div>
			
			
		<%
			}else{
		%>
			<div>
				<span><label for="appid">Enter Application Id to EDIT</label>
				</span>

				<% if (request.getParameter("editloanid") != null) 
					{
				%>
				<input type="text" id="editappid" name="editappid" value="<%=request.getParameter("editloanid")%>">
				<%
					}else{
				%>
				<input type="text" id="editappid" name="editappid">
				<%
					}
				%>
			</div>
		<%
			}
		%>
			<div>
				<input type="submit" value="Edit Loan">
			</div>

		</form>
	</div>
	<hr />
	<jsp:include page="footer.jsp" />
</body>


</html>