<%--
  Created by IntelliJ IDEA.
  User: Pavel Hubanov
  Date: 26.12.2020
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Create Category</title>
</head>
<body>

<jsp:include page="../blocks/header.jsp"></jsp:include>


<h1 align="center">Create new category</h1>

<div align="center">
    <form method="POST" action="${pageContext.request.contextPath}/admin_home/products/categories/create">
        <div align="center" style="display:flex">
            <a>Name</a>
            <input type="text" name="name" />
            <input type="submit" class="btn btn-sm btn-success" value="Create">
        </div>
    </form>
</div>

<a href="${pageContext.request.contextPath}/admin_home/products/categories">Back</a>

<jsp:include page="../blocks/footer.jsp"></jsp:include>


</body>
</html>
