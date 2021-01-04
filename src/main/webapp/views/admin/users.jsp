<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <caption><h2>Users</h2></caption>
        <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Role</th>
            <th>Is Active</th>
            <th>Action</th>

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
                    <a href="${pageContext.request.contextPath}/admin_home/users/block?userId=${user.getId()}">Block</a>
                    <a href="${pageContext.request.contextPath}/admin_home/users/unblock?userId=${user.getId()}">Unblock</a>
                    <a href="${pageContext.request.contextPath}/admin_home/users/delete?userId=${user.getId()}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>


<a href="${pageContext.request.contextPath}/admin_home">Back</a>

<jsp:include page="../blocks/footer.jsp"></jsp:include>

</body>
</html>