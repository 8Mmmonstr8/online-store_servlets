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
    <title>Cart</title>
</head>
<body>

<jsp:include page="../blocks/header.jsp"></jsp:include>


<h3>Cart</h3>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Category</th>
        <th>Description</th>
        <th>Pub Date</th>
        <th>Available Quantity</th>
        <th>Price</th>
        <th>Needed Quantity</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${products}" var="product" >
        <tr>
            <td>${product.getKey().getName()}</td>
            <td>${product.getKey().getCategory().getName()}</td>
            <td>${product.getKey().getDescription()}</td>
            <td>${product.getKey().getPublicationDate()}</td>
            <td>${product.getKey().getQuantity()}</td>
            <td>${product.getKey().getPrice()}</td>
            <td>${product.getValue()}</td>
            <td>
<%--                <a href="addToCart?id=${product.id}">Add to Cart</a>--%>
            </td>

        </tr>
    </c:forEach>
</table>

<br/>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Category</th>
        <th>Description</th>
        <th>Order Id</th>
        <th>Order Date</th>
        <th>Is Order Approved</th>

    </tr>
    <c:forEach items="${notApprovedOrderedProducts}" var="notApprovedOrderedProduct" >
        <tr>
            <td>${notApprovedOrderedProduct.getName()}</td>
            <td>${notApprovedOrderedProduct.getQuantity()}</td>
            <td>${notApprovedOrderedProduct.getPrice()}</td>
            <td>${notApprovedOrderedProduct.getCategory().getName()}</td>
            <td>${notApprovedOrderedProduct.getDescription()}</td>
            <td>${notApprovedOrderedProduct.getOrder().getId()}</td>
            <td>${notApprovedOrderedProduct.getOrder().getOrderDate()}</td>
            <td>${notApprovedOrderedProduct.getOrder().isApproved()}</td>
        </tr>
    </c:forEach>
</table>

<br/>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Category</th>
        <th>Description</th>
        <th>Order Id</th>
        <th>Order Date</th>
        <th>Is Order Approved</th>

    </tr>
    <c:forEach items="${approvedOrderedProducts}" var="approvedOrderedProduct" >
        <tr>
            <td>${approvedOrderedProduct.getName()}</td>
            <td>${approvedOrderedProduct.getQuantity()}</td>
            <td>${approvedOrderedProduct.getPrice()}</td>
            <td>${approvedOrderedProduct.getCategory().getName()}</td>
            <td>${approvedOrderedProduct.getDescription()}</td>
            <td>${approvedOrderedProduct.getOrder().getId()}</td>
            <td>${approvedOrderedProduct.getOrder().getOrderDate()}</td>
            <td>${approvedOrderedProduct.getOrder().isApproved()}</td>
        </tr>
    </c:forEach>
</table>





<jsp:include page="../blocks/footer.jsp"></jsp:include>


</body>
</html>
