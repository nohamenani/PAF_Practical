<%@page import="com.Buyer" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Buyers Details Here</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Buyer.js"></script>

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-8">

				<h2>Buyers Details</h2>
				
				<form id="formBuyer" name="formBuyer">
				
				
					First Name: 
					<input id="fname" name="fname" type="text"
						class="form-control form-control-sm"> <br> 
						
					Last Name:
					<input id="lname" name="lname" type="text"
						class="form-control form-control-sm"> <br> 
						
					Phone Number: 
					<input id="pnumber" name="pnumber" type="text"
						class="form-control form-control-sm"> <br> 
						
					E-mail: 
					<input id="email" name="email" type="text"
						class="form-control form-control-sm"> <br> 
						
					Password:
					<input id="password" name="password" type="text"
						class="form-control form-control-sm"> <br> 
						
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					
					<input type="hidden" id="hididSave" name="hididSave" value="">
					
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divBuyersGrid">

				<%
					Buyer Obj = new Buyer();
					out.print(Obj.readBuyers());
				%>
				
				</div>
			</div>
		</div>
	</div>
</body>
</html>