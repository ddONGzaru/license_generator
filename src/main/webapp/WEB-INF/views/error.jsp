<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie ie9" lang="en" class="no-js"> <![endif]-->
<!--[if !(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->

<head>
	<title>404 Page Not Found | KingAdmin - Admin Dashboard</title>
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
	<div class="wrapper full-page-wrapper page-error text-center">
		<div class="inner-page">
			<h1>
				<span class="clearfix title">
					<span class="number">500</span> <span class="text" style="font-size: 1em; top: 32px;">Oops! <br/>INTERNAL SERVER ERROR</span>
				</span>
			</h1>

			<p style="font-size: 1.5em;">${errMsg}</p>
			<p style="font-size: 1.5em;">${exception}</p>
			
			
			<div>
				<a href="/" class="btn btn-primary"><i class="fa fa-home"></i> Home</a>
			</div>
		</div>
	</div>

	<footer class="footer">&copy; 2014-2015 The Develovers</footer>

	<!-- Javascript -->
	<script src="resources/js/jquery/jquery-2.1.0.min.js"></script>
	<script src="resources/js/bootstrap/bootstrap.js"></script>
	<script src="resources/js/plugins/modernizr/modernizr.js"></script>

</body>

</html>