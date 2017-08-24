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
			<li style="padding-top: 15px;">Welcome ${currentUser.fullname}</li><li class=""><a href="#">|</a></li><li class=""><a href="logout">Logout</a></li>
		</ul>
		<br><br>
		<div style="float:right;">
	    	<a href="adminhome" class="buttontype"> Back </a>
	  	</div>
	  
	  <form action="adminTransferAmount" method="post">
	 
		<div id="newProductScreen" style="margin-left:25%;margin-right:25%">
		    	<h2>Transfer Amount</h2> <b style="margin-left: 40px;">Name: </b> ${coinName } <b style="margin-left: 40px;">Current Balance : </b>	${coinAmount }
		        <input type="hidden" id="selectedIndex" value="${currentindex}" name="selectedIndex"/>
    			<br><label for="anotherAccount">User:</label>
    				<select style="width: 400px;font-size: 13px;margin: -2px; height: 28px;" id="userType" name="anotherAccount" required>
						<option value="" label="---Select User---"></option>
						<c:forEach items="${users}" var="user">
							<option value="${user.bcaddress}">${user.fullname}</option>
						</c:forEach>
					</select>
    			<br><label for="amountValue">Amount:</label><input type="number" id="amountValue" placeholder="e.g., 100" name="amountValue"></input>
				<br><label for="desc">Description:</label><input type="text" id="description" placeholder="e.g., desc..." name="description"></input>
		    <br><br><button id="add" type="submit">Send</button> <button style="background-color: gray;" id="hideAddProductScreen" type="reset">Reset</button> <a href="myhome" class="buttontype">Back</a>
		  </div>
	   </form>
</body>
</html>