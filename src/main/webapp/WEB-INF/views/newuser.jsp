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
	  
	  <form action="addNewUser" method="post">
	 
		<div id="newProductScreen" style="margin-left:25%;margin-right:25%">
		    <h2>Add New User</h2>
		    <br><label for="productName">Fullname:</label><input type="text" id="fullname" name="fullname" required placeholder="Fullname"></input>
		    <br><label for="productAmount">Username:</label><input type="text" id="username" name="username" required placeholder="Username"></input>
		    <br><label for="productAmount">Password:</label><input type="password" id="password" name="password" required placeholder="Password"></input>
		    <br><label for="launchDate">Confirm Password:</label><input type="password" id="confirmPassword" name="confirmPassword" required placeholder="Confirm Password"></input>
		    <br><label for="expiryDate">Contact Number:</label><input type="text" id="contactNumber" name="contactNumber" required placeholder="Contact Number"></input>
		    <br><label for="payoutsPerYear">Email:</label><input type="text" id="email" name="email" placeholder="Email" required></input>
		    <br><label for="payoutsPerYear">Address:</label><input type="text" id="address" name="address" placeholder="Address" required></input>
		
		    <br><br><button id="add" type="submit">Add User</button> <button style="background-color: gray;" id="hideAddProductScreen" type="reset">Reset</button> <a href="listusers" class="buttontype">Back</a>
		  </div>
	   </form>
</body>
</html>