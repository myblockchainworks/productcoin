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
			<li style="padding-top: 15px;">Welcome ${currentUser.fullname}</li><li class=""><a href="#">|</a></li><li class=""><a href="myhome">Home</a></li><li class=""><a href="#">|</a></li><li class=""><a href="logout">Logout</a></li>
		</ul>
		<br><br>
		<h2>My Balance</h2>
		<div style="float:right;">
	    	<!-- <a href="newcoin" class="buttontype"> + New Coin</a> -->
	  	</div>
	
	
	  <br>
	  <table class="table table-bordered table-hover" id="products" style="width: 35%;">
			<tr>
				<th>#</th>
	          	<th>Name</th>
	          	<th>Amount</th>
			</tr>
			<c:set var="count" value="0" scope="page" />
			<c:forEach items="${balances}" var="balance">
				<c:set var="count" value="${count + 1}" scope="page"/>
				<tr>
					<td>${count}</td>
					<td>${balance.name}</td>
					<td>${balance.symbol} ${balance.amount}</td>
				</tr>
			</c:forEach>
		</table>
	  <span>Total Wallet : </span> <span id="productCount">${count}</span>
	<!-- <footer id="footer">&nbsp;</footer> -->
</body>
</html>