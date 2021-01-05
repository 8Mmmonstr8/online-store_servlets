<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty param.lang}">
    <fmt:setLocale value="${param.lang}" scope="session"/>
</c:if>

<fmt:setBundle basename="message"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Home</title>
</head>
<body>

<jsp:include page="../blocks/header.jsp"></jsp:include>

<br/>

<a href="${pageContext.request.contextPath}/admin_home/orders" class="button"><fmt:message key="adminHomePage.button.orders"/></a>
<br/>
<a href="${pageContext.request.contextPath}/admin_home/users" class="button"><fmt:message key="adminHomePage.button.users"/></a>
<br/>
<a href="${pageContext.request.contextPath}/admin_home/products" class="button"><fmt:message key="adminHomePage.button.products"/></a>


<jsp:include page="../blocks/footer.jsp"></jsp:include>

</body>
</html>