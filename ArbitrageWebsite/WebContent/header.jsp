<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>${param.title}</title>

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="bootstrap/css/ie10-viewport-bug-workaround.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="bootstrap/css/sticky-footer-navbar.css" rel="stylesheet">
<link href="bootstrap/css/signin.css" rel="stylesheet">
<link href="bootstrap/css/mystyles.css" rel="stylesheet">



<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../../assets/js/ie-emulation-modes-warning.js"></script>

<script src="javascript/jquery-2.2.3.min.js"></script>
<script src="javascript/tableUpdate.js"></script>

</head>

<body>

	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand"
					href="<c:url value="/Controller?action=home"/>">Arbitrage Trading</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse center-text">
				<ul class="nav navbar-nav">
					<li><a href="<c:url value="/Controller?action=home"/>">Home</a></li>
					<li><a href="<c:url value="/Controller?action=about"/>">About</a></li>
					<c:choose>
						<c:when test='${sessionScope.email != null || sessionScope.email != ""}'>
							<li><a href="<c:url value="/Controller?action=hiddenArbPage"/>">Trades</a></li>
						</c:when>
					</c:choose>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test='${sessionScope.email == null || sessionScope.email == ""}'>
							<li><a href="<c:url value="/Controller?action=login"/>">Log In</a></li>
							<li><a href="<c:url value="/Controller?action=signup"/>">Sign Up</a></li>
						</c:when>
						<c:when test='${sessionScope.email != null || sessionScope.email != ""}'>
							<li class="welcome-user">Welcome <c:out value="${sessionScope.email}"></c:out></li>	
							<li class="logout"><a href="<c:url value="/Controller?action=logout"/>">Logout</a></li>				
						</c:when>
					</c:choose>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<!-- Begin page content -->
	<div class="container">

