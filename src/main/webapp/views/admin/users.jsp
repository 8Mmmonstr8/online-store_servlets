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
    <title>Users</title>
</head>
<body>

<jsp:include page="../blocks/header.jsp"></jsp:include>

<br/>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2><fmt:message key="usersPage.title"/></h2></caption>
        <tr>
            <th><fmt:message key="usersPage.table.label.id"/></th>
            <th><fmt:message key="usersPage.table.label.firstName"/></th>
            <th><fmt:message key="usersPage.table.label.lastName"/></th>
            <th>Email</th>
            <th><fmt:message key="usersPage.table.label.role"/></th>
            <th><fmt:message key="usersPage.table.label.isActive"/></th>
            <th><fmt:message key="usersPage.table.label.action"/></th>

        </tr>
        <c:forEach items="${users}" var="user" >
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getFirstName()}</td>
                <td>${user.getLastName()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getRole().name()}</td>
                <td>${user.isNonLocked()}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin_home/users/block?userId=${user.getId()}"><fmt:message key="usersPage.table.button.block"/></a>
                    <a href="${pageContext.request.contextPath}/admin_home/users/unblock?userId=${user.getId()}"><fmt:message key="usersPage.table.button.unblock"/></a>
                    <a href="${pageContext.request.contextPath}/admin_home/users/delete?userId=${user.getId()}"><fmt:message key="usersPage.table.button.delete"/></a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>


<a href="${pageContext.request.contextPath}/admin_home"><fmt:message key="usersPage.button.back"/></a>

<jsp:include page="../blocks/footer.jsp"></jsp:include>

</body>
</html>