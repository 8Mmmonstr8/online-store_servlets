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


<h1 align="center">Cart</h1>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Products in cart</h2></caption>
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
<%--                <td>${product.getValue()}</td>--%>
                <td>
                    <form method="POST" action="${pageContext.request.contextPath}/user_home/cart">
                        <div style="display:flex">
                            <input name="neededQuantity"
                                   value="${product.getValue()}"
                                   size="2">
                            <input type="hidden" id="productId" name="productId" value="${product.getKey().getId()}"/>
                            <input type="submit" class="btn btn-sm btn-success" value="Submit">
                        </div>
                    </form>
                </td>

                <td>
    <%--                <a href="addToCart?id=${product.id}">Add to Cart</a>--%>
                    <a href="${pageContext.request.contextPath}/user_home/cart/delete?productId=${product.getKey().getId()}">Delete</a>

                </td>

            </tr>
        </c:forEach>
    </table>
    <p align="center"><b>Total sum: ${totalPrice}</b></p>
</div>

<br/>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Waiting for approval</h2></caption>
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
</div>

<br/>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Ordered products</h2></caption>
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
</div>





<jsp:include page="../blocks/footer.jsp"></jsp:include>


</body>
</html>
