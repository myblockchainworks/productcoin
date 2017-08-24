<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Product Coin</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Ubuntu" rel="stylesheet">
<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/productcoin.js" />"></script>
</head>
<body>

	<h1>Product Coin DApp</h1>
	
		<ul class="nav navbar-nav pull-right">
			<li style="padding-top: 15px;">Welcome ${currentUser.fullname}</li><li class=""><a href="#">|</a></li><li class=""><a href="listusers">Users</a></li><li class=""><a href="#">|</a></li><li class=""><a href="logout">Logout</a></li>
		</ul>
		<br><br>
		<div style="float:right;">
	    	<a href="adminhome" class="buttontype"> Back </a>
	  	</div>
	  <br><br>
	  
	  <form action="addNewCoin" method="post">
	 
		<div id="newProductScreen" style="margin-left:25%;margin-right:25%">
		    <h2>Add New Coin</h2>
		    <br><label for="productName">Name:</label><input type="text" id="productName" name="productName" required placeholder="e.g., My Token DAO"></input>
		    <br><label for="nominalValue">Nominal Value:</label><input type="number" id="nominalValue" name="nominalValue" required placeholder="e.g., 1"></input>
		    <br><label for="issueSize">Issue Size:</label><input type="number" id="issueSize" name="issueSize" required placeholder="e.g., 10000"></input>
		    <br><label for="payoutRate">Payout Rate:</label><input type="number" id="payoutRate" name="payoutRate" required placeholder="e.g., 5"></input>
		    <br><label for="productAmount">Amount Symbol:</label><input type="text" id="productSymbol" name="productSymbol" required placeholder="e.g., $"></input>
		    <br><label for="launchDate">Launch Date:</label><input type="date" id="launchDate" name="launchDate" required></input>
		    <br><label for="expiryDate">Expiry Date:</label><input type="date" id="expiryDate" name="expiryDate" required></input>
		    <br><label for="payoutsPerYear">Payouts Per Year:</label><input type="number" id="payoutsPerYear" name="payoutsPerYear" min="1" max="12" placeholder="e.g., 4" required></input>
		
		    <br><br><button id="add" type="submit">Add Coin</button> <button style="background-color: gray;" id="hideAddProductScreen" type="reset">Reset</button> <a href="adminhome" class="buttontype">Back</a>
		  </div>
	   </form>
</body>
</html>