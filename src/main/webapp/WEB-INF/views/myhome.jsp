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
			<li style="padding-top: 15px;">Welcome ${currentUser.fullname}</li><li class=""><a href="#">|</a></li><li class=""><a href="mybalance">Balance</a></li><li class=""><a href="#">|</a></li><li class=""><a href="logout">Logout</a></li>
		</ul>
		<br><br>
		<h2>Coins / Wallets</h2>
		<div style="float:right;">
	    	<!-- <a href="newcoin" class="buttontype"> + New Coin</a> -->
	  	</div>
	
	
	  <br>
	  <table class="table table-bordered table-hover" id="products">
			<tr>
				<th>#</th>
	          	<th>Name</th>
	          	<th>Amount</th>
	          	<th>Amount Symbol</th>
	          	<th>Launch Date</th>
	          	<th>Expiry Date</th>
				<th>Payout Per Year</th>
	          	<th>First Payout</th>
	          	<th>Next Payout</th>
	          	<th>Status</th>
	          	<th>Coin Address</th>
	          	<th>Action</th>
			</tr>
			<c:set var="count" value="0" scope="page" />
			<c:forEach items="${coins}" var="coin">
				<c:set var="count" value="${count + 1}" scope="page"/>
				<tr>
					<td>${count}</td>
					<td>${coin.name}</td>
					<td>${coin.amount}</td>
					<td>${coin.symbol}</td>
					<td class="lanchDate${count}"><script>formatDate('<c:out value='${coin.launchDate}'/>', 'lanchDate' + '<c:out value='${count}'/>');</script></td>
					<td class="expiryDate${count}"><script>formatDate('<c:out value='${coin.expiryDate}'/>', 'expiryDate' + '<c:out value='${count}'/>');</script></td>
					<td>${coin.payoutsPerYear}</td>
					<td class="firstPayoutDate${count}"><script>formatDate('<c:out value='${coin.firstPayoutDate}'/>', 'firstPayoutDate' + '<c:out value='${count}'/>');</script></td>
					<td class="nextPayoutDate${count}"><script>formatDate('<c:out value='${coin.nextPayoutDate}'/>', 'nextPayoutDate' + '<c:out value='${count}'/>');</script></td>
					<td class="status${count}"><script>getStatus('<c:out value='${coin.status}'/>', 'status' + '<c:out value='${count}'/>');</script></td>
					<td>${coin.productAddress}</td>
					<td style="text-align: center;"><a href="sendmoney?currentindex=${count - 1 }">Transfer</a> | <a href="mytransactions?currentindex=${count - 1 }">Transactions</a></td>
				</tr>
			</c:forEach>
		</table>
	  <span>Total Coins : </span> <span id="productCount">${count}</span>
	<!-- <footer id="footer">&nbsp;</footer> -->
</body>
</html>