<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/include.jsp" %>
<%@ include file="/WEB-INF/jsp/include/header.jsp" %>

<html>
<head>
 <LINK href="index1.css" rel="stylesheet" type="text/css">
 <TITLE>LMS Reports Error Page</TITLE>
</head>
<body>
        <h2>Error page.</h2>
		<p>An error occurred while processing this request.</p> 
		<p><c:out value="${Exception.message}"/></p>

</body>
</html>





