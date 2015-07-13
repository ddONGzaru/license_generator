<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie ie9" lang="en" class="no-js"> <![endif]-->
<!--[if !(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->

<head>
	<title>Register | KingAdmin - Admin Dashboard</title>
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
	<div class="wrapper full-page-wrapper page-auth page-register text-center">

		<div class="inner-page">
			<div class="logo">
				<a href="/"><img src="resources/img/kingadmin-logo.png" alt="" /></a>
			</div>
			<button type="button" class="btn btn-auth-facebook"><span>Connect using Facebook</span></button>

			<div class="separator"><span>OR</span></div>

			<div class="register-box center-block">
				<form action="/reg/user" method="post">
					<p class="title">Create Your Account</p>
					<input type="email" placeholder="email" class="form-control" name="username">
					<input type="password" placeholder="password" class="form-control" name="password">
					<input type="password" placeholder="repeat password" class="form-control" name="confirmPwd">
					
					<c:if test="${not empty USERNAME_ERROR_MSG}">
						<font color="red">
			          		<c:out value="${USERNAME_ERROR_MSG}"/>.
			      		</font>
			    	</c:if>
					<c:if test="${not empty PWD_ERROR_MSG}">
						<font color="red">
			          		<c:out value="${PWD_ERROR_MSG}"/>.
			      		</font>
			    	</c:if>
					
					<button class="btn btn-custom-primary btn-lg btn-block btn-auth"><i class="fa fa-check-circle"></i> Create Account</button>
				</form>
			</div>
		</div>
	</div>

	<div class="footer">&copy; 2014-2015 The Develovers</div>

	<!-- Javascript -->
	<script src="resources/js/jquery/jquery-2.1.0.min.js"></script>
	<script src="resources/js/bootstrap/bootstrap.js"></script>
	<script src="resources/js/plugins/modernizr/modernizr.js"></script>

</body>

</html>
