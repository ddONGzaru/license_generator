<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
main page [${msg}]

<h3>Email: <sec:authentication property="name"/></h3> 
<h3> 
	<sec:authorize access="hasRole('ADMIN')">
		<a href="admin">Administration</a>
	</sec:authorize> 
	<sec:authorize access="hasRole('USER')">
		<a href="admin">User</a>
	</sec:authorize> 
</h3>
</body>
</html>

<%-- 
<ul class="nav navbar-nav navbar-right">
    <!-- 회원 권한이 없을 때 -->
    <sec:authorize ifNotGranted="ROLE_USER">
        <li><a href="/user/sign_up">회원가입</a></li>
        <li><a href="/user/login">로그인</a></li>
    </sec:authorize>

    <!-- 회원 권한이 있을 때 -->
    <sec:authorize ifAnyGranted="ROLE_USER">
        <li><a href="/user/logout">로그아웃</a></li>
    </sec:authorize>

    <!-- 여러 권한 체크 -->
    <sec:authorize ifAnyGranted="ROLE_USER, ROLE_ADMIN">
        <li><a href="/user/edit">정보수정</a></li>
    </sec:authorize>
</ul> 
--%>