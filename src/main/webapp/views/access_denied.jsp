<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:if test="${not empty param.lang}">
    <fmt:setLocale value="${param.lang}" scope="session"/>
</c:if>

<fmt:setBundle basename="message"/>

<html>
<head>
    <title>Access denied</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
</head>

<body>
<%--<c:if test="${not empty param.lang}">--%>
<%--    <fmt:setLocale value="${param.lang}" scope="session"/>--%>
<%--</c:if>--%>

<%--<fmt:setBundle basename="message"/>--%>
<%--<c:if test="${cruiseNotFound}">--%>
<%--    <h1><fmt:message key="alert.wrong.input.data"/></h1>--%>
<%--</c:if>--%>
<%--<c:if test="${notAllData}">--%>
<%--    <h1><fmt:message key="alert.not.enough.data"/></h1>--%>
<%--</c:if>--%>
<h1><fmt:message key="accessDeniedPage.message"/></h1>
<a href="${pageContext.request.contextPath}/" class="button"><fmt:message key="accessDeniedPage.button.main"/></a>
</body>
</html>
