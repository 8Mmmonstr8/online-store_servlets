<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Home</title>
</head>
<body>

<jsp:include page="../blocks/header.jsp"></jsp:include>

<br/>

<a href="${pageContext.request.contextPath}/admin_home/orders" class="button">Orders</a>
<br/>
<a href="${pageContext.request.contextPath}/admin_home/users" class="button">Users</a>
<br/>
<a href="${pageContext.request.contextPath}/admin_home/products" class="button">Products</a>


<jsp:include page="../blocks/footer.jsp"></jsp:include>

</body>
</html>