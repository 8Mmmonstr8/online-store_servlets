<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
</head>

<body>

<jsp:include page="blocks/header.jsp"></jsp:include>

<h3>Product List Page</h3>

<%--<c:forEach items="products" var="product">--%>
<%--    <ul>--%>
<%--        <li>Name: <c:out value="${product.getName()}"/></li>--%>
<%--    </ul>--%>
<%--</c:forEach>--%>


        <table border="1">
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
                    <td>${product.category}</td>
                    <td>${product.publicationDate}</td>
                    <td>
                        <a href="addToCart?id=${product.id}">Add to Cart</a>
                    </td>
<%--                    <td>--%>
<%--                        <a href="deleteProduct?code=${product.code}">Delete</a>--%>
<%--                    </td>--%>
                </tr>
            </c:forEach>
        </table>


<jsp:include page="blocks/footer.jsp"></jsp:include>


</body>
</html>