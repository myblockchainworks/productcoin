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

</head>
<body>

	<h1>Product Coin DApp</h1>
	
	<div class="container">

		<div class="welcome" style="padding-top:150px">
		
			  <div class="container" style="padding-left: 25%;">
				<div class="col-md-6">
					<div class=" login">
						<h2>Login</h2>
						<form action="loginUser" method="post">
							
							<div class="form-group">
								<label for="name">User Name</label> <input type="text"
									class="form-control" placeholder="User Name" required id="userName" name="userName">
							</div>
		
							<div class="form-group">
								<label for="pwd">Password</label> <input type="password"
									class="form-control" placeholder="Password" required id="password" name="password">
							</div>
							<div style="padding-top: 10px;padding-bottom:10px;">
								<span style="color: #b32400;">${errormsg}</span>
							</div>
							<div style="margin: auto 45%">
								<button type="submit" class="btn btn-primary">Login</button>
		
							</div>
						</form>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	<!-- <footer id="footer">&nbsp;</footer> -->
</body>
</html>