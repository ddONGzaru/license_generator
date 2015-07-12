<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie ie9" lang="en" class="no-js"> <![endif]-->
<!--[if !(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->

<head>
	<title>manasobi Appz</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="description" content="KingAdmin Dashboard">
	<meta name="author" content="The Develovers">

	<!-- CSS -->
	<link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="resources/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="resources/css/main.css" rel="stylesheet" type="text/css">

	<!--[if lte IE 9]>
		<link href="resources/css/main-ie.css" rel="stylesheet" type="text/css" />
		<link href="resources/css/main-ie-part2.css" rel="stylesheet" type="text/css" />
	<![endif]-->

	<!-- Fav and touch icons -->
	<link rel="apple-touch-icon-precomposed" sizes="144x144" href="resources/ico/kingadmin-favicon144x144.png">
	<link rel="apple-touch-icon-precomposed" sizes="114x114" href="resources/ico/kingadmin-favicon114x114.png">
	<link rel="apple-touch-icon-precomposed" sizes="72x72" href="resources/ico/kingadmin-favicon72x72.png">
	<link rel="apple-touch-icon-precomposed" sizes="57x57" href="resources/ico/kingadmin-favicon57x57.png">
	<link rel="shortcut icon" href="resources/ico/favicon.png">

</head>

<body>
	<div class="wrapper full-page-wrapper page-auth page-login text-center">
		<div class="inner-page">
			<div class="logo">
				<a href="index.html"><img src="resources/img/kingadmin-logo.png" alt="" /></a>
			</div>
			<button type="button" class="btn btn-auth-facebook"><span>Login via Facebook</span></button>

			<div class="separator"><span>OR</span></div>

			<div class="login-box center-block">
				<form class="form-horizontal" role="form" action="login" method="post">
					<div class="form-group">
						<label for="username" class="control-label sr-only">Username</label>
						<div class="col-sm-12">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user"></i></span>
								<input type="text" placeholder="username" id="username" name="username" class="form-control">
							</div>
						</div>
					</div>
					<label for="password" class="control-label sr-only">Password</label>
					<div class="form-group">
						<div class="col-sm-12">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-lock"></i></span>
								<input type="password" placeholder="password" id="password" name="password" class="form-control">
							</div>
						</div>
					</div>
					
					<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
						<font color="red">
			          		<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.
			      		</font>
			    	</c:if>
			    
					<button class="btn btn-custom-primary btn-lg btn-block btn-auth"><i class="fa fa-arrow-circle-o-right"></i> Login</button>
				</form>
				
				

				<div class="links">
					<p><a href="#">Forgot Username or Password?</a></p>
					<p><a href="/reg/user">Create New Account</a></p>
				</div>
			</div>
		</div>
	</div>

	<footer class="footer">&copy; 2015 The Develovers</footer>

	<!-- Javascript -->
	<script src="resources/js/jquery/jquery-2.1.0.min.js"></script>
	<script src="resources/js/bootstrap/bootstrap.js"></script>
	<script src="resources/js/plugins/modernizr/modernizr.js"></script>
</body>

</html>