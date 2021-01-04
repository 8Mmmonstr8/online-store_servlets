<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Products</title>
</head>
<body>

<jsp:include page="../blocks/header.jsp"></jsp:include>

<br/>


<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Products</h2></caption>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Description</th>
            <th>Category</th>
            <th>Pub Date</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${products}" var="product" >
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.quantity}</td>
                <td>${product.description}</td>
                <td>${product.getCategory().getName()}</td>
                <td>${product.publicationDate}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin_home/products/edit?productId=${product.getId()}">Edit</a>
                    <a href="${pageContext.request.contextPath}/admin_home/products/delete?productId=${product.getId()}">Delete</a>
                </td>
                    <%--                    <td>--%>
                    <%--                        <a href="deleteProduct?code=${product.code}">Delete</a>--%>
                    <%--                    </td>--%>
            </tr>
        </c:forEach>
    </table>
</div>

<a href="${pageContext.request.contextPath}/admin_home/products/categories">Edit categories</a>
<br/>
<a href="${pageContext.request.contextPath}/admin_home/products/create">Create new product</a>
<br/>
<a href="${pageContext.request.contextPath}/admin_home">Back</a>

<jsp:include page="../blocks/footer.jsp"></jsp:include>

</body>
</html>