<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie ie9" lang="en" class="no-js"> <![endif]-->
<!--[if !(IE)]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->

<head>
	<title>MANASOBI</title>
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
	
	<style>
  		th, td { text-align: center; }
	</style>
</head>

<body class="dashboard2">
	<!-- WRAPPER -->
	<div class="wrapper">
		
		<%@include file="../bar-top.jsp"%>
		
		<!-- BOTTOM: LEFT NAV AND RIGHT MAIN CONTENT -->
		<div class="bottom">
			<div class="container">
				<div class="row">
				
					<%@include file="../bar-side.jsp"%>
					
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
										<li>
											<div class="quick-access-item bg-color-blue">
												<i class="fa fa-table"></i>
												<h5>Application Info</h5><em>ver.1.0.0</em>
											</div>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<!-- main -->
						<div class="content">
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
													<th width="70px;">NO</th>
													<th width="300px">발급 키</th>
													<th>사이트</th>
													<th>호스트네임</th>
													<th width="100px;">타입</th>
													<th width="190px;">유효기간</th>
													<th width="190px;">발급일자</th>
													<th width="100px;">발급완료</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${info}" var="details" varStatus="status">										            
										            <tr>
										            	<%-- <th>${endIndex - status.count}</th> --%>
										            	<%-- <th>${status.count}</th> --%>
										            	<td>${totalRecordCount - (((currentIndex - 1) * recordCountPerPage) + status.index)}</td>
										            	<td>${details.genKey}</td>
										            	<td>${details.license.siteName}</td>
										            	<td>${details.license.hostName}</td>
										            	<td>${details.license.type}</td>
										            	<td>${details.license.expirationDate}</td>
										            	<td>${details.createdDateStr}</td>
										            	<td>${details.generated}</td>										            	
										            </tr>	
										        </c:forEach>
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
									            <c:url var="pageUrl" value="/license/history?page=${i-1}" />
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
</body>

</html>
