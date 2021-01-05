<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty param.lang}">
    <fmt:setLocale value="${param.lang}" scope="session"/>
</c:if>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title>Unsupported Page</title>
</head>
<body>

<jsp:include page="blocks/header.jsp"></jsp:include>

<h3><fmt:message key="unsupportedPage.message"/></h3>

<a href="/store/"><fmt:message key="unsupportedPage.button.main"/></a>

<jsp:include page="blocks/footer.jsp"></jsp:include>


</body>
</html>
