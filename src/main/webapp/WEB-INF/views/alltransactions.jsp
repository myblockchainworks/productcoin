<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
			<li style="padding-top: 15px;">Welcome ${currentUser.fullname}</li><li class=""><a href="#">|</a></li><li class=""><a href="adminhome">Coins</a></li><li class=""><a href="#">|</a></li><li class=""><a href="listusers">Users</a></li><li class=""><a href="#">|</a></li><li class=""><a href="logout">Logout</a></li>
		</ul>
		<br><br>
		<div style="float:right;">
	    	<a href="adminhome" class="buttontype"> Back </a>
	  	</div>
	
	  <h2>All Transactions</h2>
	  <table class="table table-bordered table-hover" id="products">
			<tr>
				<th>#</th>
	          	<th>Date</th>
	          	<th>Coin</th>
	          	<th>From Address</th>
	          	<th>To Address</th>
	          	<th>Amount</th>
	          	<th>Description</th>
			</tr>
			<c:set var="count" value="0" scope="page" />
			<c:forEach items="${transactions}" var="tran">
				<c:set var="count" value="${count + 1}" scope="page"/>
				<tr>
					<td>${count}</td>
					<td> <fmt:formatDate type = "both" value = "${tran.transactiondate}" /></td>
					<td>${tran.coin.name}</td>
					<td>${tran.fromaddress}</td>
					<td>${tran.toaddress}</td>
					<td>${tran.coin.symbol} ${tran.amount}</td>
					<td>${tran.description}</td>
				</tr>
			</c:forEach>
		</table>
	  <span>Total Transactions : </span> <span id="productCount">${count}</span>
	<!-- <footer id="footer">&nbsp;</footer> -->
</body>
</html>