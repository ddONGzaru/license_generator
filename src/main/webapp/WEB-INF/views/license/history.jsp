<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie ie9" lang="en" class="no-js"> <![endif]-->
<!--[if !(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->

<head>
	<title>Dashboard | KingAdmin - Admin Dashboard</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="description" content="KingAdmin - Bootstrap Admin Dashboard Theme">
	<meta name="author" content="The Develovers">

	<!-- CSS -->
	<link href="resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="resources/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="resources/css/main.css" rel="stylesheet" type="text/css">
	<link href="resources/css/my-custom-styles.css" rel="stylesheet" type="text/css">

	<link href="resources/css/skins/fulldark.css" rel="stylesheet" type="text/css">
	
	<!--[if lte IE 9]>
		<link href="resources/css/main-ie.css" rel="stylesheet" type="text/css"/>
		<link href="resources/css/main-ie-part2.css" rel="stylesheet" type="text/css"/>
	<![endif]-->

	<!-- Fav and touch icons -->
	<link rel="apple-touch-icon-precomposed" sizes="144x144" href="resources/ico/kingadmin-favicon144x144.png">
	<link rel="apple-touch-icon-precomposed" sizes="114x114" href="resources/ico/kingadmin-favicon114x114.png">
	<link rel="apple-touch-icon-precomposed" sizes="72x72" href="resources/ico/kingadmin-favicon72x72.png">
	<link rel="apple-touch-icon-precomposed" sizes="57x57" href="resources/ico/kingadmin-favicon57x57.png">
	<link rel="shortcut icon" href="resources/ico/favicon.png">
</head>

<body class="dashboard2">
	<!-- WRAPPER -->
	<div class="wrapper">
		<!-- TOP BAR -->
		<div class="top-bar">
			<div class="container">
				<div class="row">
					<!-- logo -->
					<div class="col-md-2 logo">
						<a href="index.html"><img src="resources/img/manasobi.png" alt="KingAdmin - Admin Dashboard" /></a>
<!-- 						<a href="index.html"><img src="resources/img/kingadmin-logo-white.png" alt="KingAdmin - Admin Dashboard" /></a> -->
						<h1 class="sr-only">KingAdmin Admin Dashboard</h1>
					</div>
					<!-- end logo -->
					<div class="col-md-10">
						<div class="row">
							<div class="col-md-3">
							</div>
							<div class="col-md-9">
								<div class="top-bar-right">
									<!-- responsive menu bar icon -->
									<a href="#" class="hidden-md hidden-lg main-nav-toggle"><i class="fa fa-bars"></i></a>
									<!-- end responsive menu bar icon -->
											
									<!-- logged user and the menu -->
									<div class="logged-user">
										<div class="btn-group">
											<a href="#" class="btn btn-link dropdown-toggle" data-toggle="dropdown">
												<img src="resources/img/user-avatar.png" alt="User Avatar" />
												<span class="name">Stacy Rose</span> <span class="caret"></span>
											</a>
											<ul class="dropdown-menu" role="menu">												
												<li>
													<a href="#">
														<i class="fa fa-cog"></i>
														<span class="text">Settings</span>
													</a>
												</li>
												<li>
													<a href="#">
														<i class="fa fa-power-off"></i>
														<span class="text">Logout</span>
													</a>
												</li>
											</ul>
										</div>
									</div>
									<!-- end logged user and the menu -->
								</div>
								<!-- /top-bar-right -->
							</div>
						</div>
						<!-- /row -->
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /top -->
		<!-- BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT -->
		<div class="bottom">
			<div class="container">
				<div class="row">
					<!-- left sidebar -->
					<div class="col-md-2 left-sidebar">
						<!-- main-nav -->
						<nav class="main-nav">
							<ul class="main-menu">
								
								<li><a href="#"></a></li>
								<li>
									<a href="/license/publish">
										<i class="fa fa-dashboard fa-fw"></i><span class="text">라이센스 발급</span>										
									</a>									
								</li>
								<li>
									<a href="/license/history">
										<i class="fa fa-font fa-fw"></i><span class="text">발급 이력</span>
									</a>
								</li>
								<li class="active">
									<a href="#">
										<i class="fa fa-font fa-fw"></i><span class="text">라이센스 상세정보</span>
									</a>
								</li>
								
							</ul>
						</nav>
						<!-- /main-nav -->
						<div class="sidebar-minified js-toggle-minified">
							<i class="fa fa-angle-left"></i>
						</div>
						<!-- sidebar content -->
						<div class="sidebar-content">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h5><i class="fa fa-lightbulb-o"></i> Tips</h5>
								</div>
								<div class="panel-body">
									<p>You can do live search to the widget at search box located at top bar. It's very useful if your dashboard is full of widget.</p>
								</div>
							</div>
							<h5 class="label label-default"><i class="fa fa-info-circle"></i> Server Info</h5>
							<ul class="list-unstyled list-info-sidebar bottom-30px">
								<li class="data-row">
									<div class="data-name">Disk Space Usage</div>
									<div class="data-value">
										274.43 / 2 GB
										<div class="progress progress-xs">
											<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="10" aria-valuemin="0" aria-valuemax="100" style="width: 10%">
												<span class="sr-only">10%</span>
											</div>
										</div>
									</div>
								</li>
								<li class="data-row">
									<div class="data-name">Monthly Bandwidth Transfer</div>
									<div class="data-value">
										230 / 500 GB
										<div class="progress progress-xs">
											<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="46" aria-valuemin="0" aria-valuemax="100" style="width: 46%">
												<span class="sr-only">46%</span>
											</div>
										</div>
									</div>
								</li>
								<li class="data-row">
									<span class="data-name">Database Disk Space</span>
									<span class="data-value">219.45 MB</span>
								</li>
								<li class="data-row">
									<span class="data-name">Operating System</span>
									<span class="data-value">Linux</span>
								</li>
								<li class="data-row">
									<span class="data-name">Apache Version</span>
									<span class="data-value">2.4.6</span>
								</li>
								<li class="data-row">
									<span class="data-name">PHP Version</span>
									<span class="data-value">5.3.27</span>
								</li>
								<li class="data-row">
									<span class="data-name">MySQL Version</span>
									<span class="data-value">5.5.34-cll</span>
								</li>
								<li class="data-row">
									<span class="data-name">Architecture</span>
									<span class="data-value">x86_64</span>
								</li>
							</ul>
						</div>
						<!-- end sidebar content -->
					</div>
					<!-- end left sidebar -->

					<!-- content-wrapper -->
					<div class="col-md-10 content-wrapper">
						<div class="row">
							<div class="col-lg-4 ">
								<ul class="breadcrumb">
									<li><i class="fa fa-home"></i><a href="#">Home</a></li>
									<li class="active">라이센스 발급</li>
								</ul>
							</div>
							<div class="col-lg-8 ">
								<div class="top-content">
									<ul class="list-inline quick-access">
										<!-- <li>
											<a href="charts-statistics-interactive.html">
												<div class="quick-access-item bg-color-green">
													<i class="fa fa-bar-chart-o"></i>
													<h5>CHARTS</h5><em>basic, interactive, real-time</em>
												</div>
											</a>
										</li>
										<li>
											<a href="page-inbox.html">
												<div class="quick-access-item bg-color-blue">
													<i class="fa fa-envelope"></i>
													<h5>INBOX</h5><em>inbox with gmail style</em>
												</div>
											</a>
										</li> -->
										<li>
											<a href="tables-dynamic-table.html">
												<div class="quick-access-item bg-color-orange">
													<i class="fa fa-table"></i>
													<h5>Application Info</h5><em>ver.1.0.0</em>
												</div>
											</a>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<!-- main -->
						<div class="content">
							<!-- <div class="main-header">
								<h2>라이센스 발급</h2>
								<em>라이센스를 발급하는 화면입니다.</em>
							</div> -->
							<div class="main-content">
								
								<!-- SHOW HIDE COLUMNS DATA TABLE -->
								<div class="widget widget-table">
									<div class="widget-header">
										<h3>Widget With Dropdown</h3>										
										<div class="btn-group widget-header-toolbar">								
											<select id="multiselect1" name="multiselect1" class="multiselect">
												<option value="opt1">View: 10</option>
												<option value="opt1">View: 15</option>
												<option value="opt2">View: 20</option>
												<option value="opt3">View: 50</option>
												<option value="opt3">View: 100</option>
											</select>											
										</div>
									</div>
									<div class="widget-content" style="padding-bottom: 0px;">										
										<table id="datatable-column-interactive" class="table table-sorting table-hover table-bordered datatable">
											<thead>
												<tr>
													<th>NO</th>
													<th>발급 키</th>
													<th>사이트</th>
													<th>호스트네임</th>
													<th>타입</th>
													<th>유효기간</th>
													<th>발급일자</th>
													<th>발급완료</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${info}" var="details" varStatus="status">										            
										            <tr>
										            	<%-- <th>${endIndex - status.count}</th> --%>
										            	<%-- <th>${status.count}</th> --%>
										            	<th>${totalRecordCount - (((currentIndex - 1) * recordCountPerPage) + status.index)}</th>
										            	<th>${details.genKey}</th>
										            	<th>${details.license.siteName}</th>
										            	<th>${details.license.hostName}</th>
										            	<th>${details.license.type}</th>
										            	<th>${details.license.expirationDate}</th>
										            	<th>${details.createdDateStr}</th>
										            	<th>${details.generated}</th>										            	
										            </tr>	
										        </c:forEach>
												<%-- <c:forEach items="${info}" var="licenseDetails">										            
										            <tr>
										            	<th>${licenseDetails.id}</th>
										            	<th>${licenseDetails.id}</th>
										            	<th>${licenseDetails.license.siteName}</th>
										            	<th>${licenseDetails.id}</th>
										            	<th>${licenseDetails.id}</th>
										            </tr>	
										        </c:forEach> --%>										        
											</tbody>
										</table>
									</div>
									
									<div class="pull-right" style="margin-right: 20px;">
										<c:url var="firstUrl" value="/pages/1" />
										<c:url var="lastUrl" value="/pages/${pager.totalPages}" />
										<c:url var="prevUrl" value="/pages/${currentIndex - 1}" />
										<c:url var="nextUrl" value="/pages/${currentIndex + 1}" />
										<ul class="pagination borderless">
											 <c:choose>
									            <c:when test="${currentIndex == 1}">
									                <li class="disabled"><a href="#">&lt;&lt;</a></li>
									                <li class="disabled"><a href="#">&lt;</a></li>
									            </c:when>
									            <c:otherwise>
									                <li><a href="${firstUrl}">&lt;&lt;</a></li>
									                <li><a href="${prevUrl}">&lt;</a></li>
									            </c:otherwise>
									        </c:choose>
									        <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
									            <c:url var="pageUrl" value="/license/history?page=${i}" />
									            <c:choose>
									                <c:when test="${i == currentIndex}">
									                    <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
									                </c:when>
									                <c:otherwise>
									                    <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
									                </c:otherwise>
									            </c:choose>
									        </c:forEach>
									        <c:choose>
									            <c:when test="${currentIndex == pager.totalPages}">
									                <li class="disabled"><a href="#">&gt;</a></li>
									                <li class="disabled"><a href="#">&gt;&gt;</a></li>
									            </c:when>
									            <c:otherwise>
									                <li><a href="${nextUrl}">&gt;</a></li>
									                <li><a href="${lastUrl}">&gt;&gt;</a></li>
									            </c:otherwise>
									        </c:choose>
										</ul>
									</div>			
								</div>
								<!-- END SHOW HIDE COLUMNS DATA TABLE -->
							</div>
							<!-- /main-content -->
						</div>
						<!-- /main -->
					</div>
					<!-- /content-wrapper -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- END BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT -->
	</div>
	<!-- /wrapper -->

	<!-- FOOTER -->
	<footer class="footer">
		&copy; 2014-2015 The Develovers
	</footer>
	<!-- END FOOTER -->

	<!-- Javascript -->
	<script src="resources/js/jquery/jquery-2.1.0.min.js"></script>
	<script src="resources/js/bootstrap/bootstrap.js"></script>
	<script src="resources/js/plugins/modernizr/modernizr.js"></script>
	<script src="resources/js/plugins/bootstrap-tour/bootstrap-tour.custom.js"></script>
	<script src="resources/js/king-common.js"></script>
	<script src="resources/js/jquery-ui/jquery-ui-1.10.4.custom.min.js"></script>
	<script src="resources/js/plugins/stat/flot/jquery.flot.min.js"></script>
	<script src="resources/js/plugins/stat/flot/jquery.flot.resize.min.js"></script>
	<script src="resources/js/plugins/stat/flot/jquery.flot.time.min.js"></script>
	<script src="resources/js/plugins/stat/flot/jquery.flot.tooltip.min.js"></script>
	<script src="resources/js/plugins/bootstrap-multiselect/bootstrap-multiselect.js"></script>
	<script src="resources/js/king-page.js"></script>
	
	<script type="text/javascript">
    
    var licneseType;
    
    $(function() {
    	
		licenseType = $(":input:radio[name=type]:checked").val();
    	 
		$("#expirationDateSelect").hide();
		
		$(":input:radio[name=type]").click(function() {
			
			licenseType = $(":input:radio[name=type]:checked").val();
    		 
			if (licenseType == "02") {
				$("#expirationDateSelect").show();    			 
			} else {
				$("#expirationDateSelect").hide();
			}
		});
		
		$("#licenseSubmit").click(function() {
			
			if ($("#siteName").val() == '' || $("#siteName").val() == null) {
				alert('site명을 입력해 주세요');
				$("#siteName").focus();
				return false;
			}
			
			if ($("#hostName").val() == '' || $("#hostName").val() == null) {
				alert('host명를 입력해 주세요');
				$("#hostName").focus();
				return false;
			}
			
			$("#licenseForm")
        		.attr({action:'publish', method:'post'})
        		.submit();
		});
		
     });
    
    </script>
</body>

</html>
