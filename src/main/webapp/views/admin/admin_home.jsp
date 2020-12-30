<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Home</title>
</head>
<body>

<jsp:include page="../blocks/header.jsp"></jsp:include>

<%--<jsp:include page="blocks/header.jsp"></jsp:include>--%>


<h3>Hello: ${user.email}</h3>

User Email: <b>${user.email}</b>
User Role: <b>${role}</b>

<br/>

<a href="${pageContext.request.contextPath}/admin_home/orders" class="button">Orders</a>
<br/>
<a href="${pageContext.request.contextPath}/user_home/users" class="button">Users</a>
<br/>
<a href="${pageContext.request.contextPath}/user_home/products" class="button">Products</a>


<jsp:include page="../blocks/footer.jsp"></jsp:include>

</body>
</html>