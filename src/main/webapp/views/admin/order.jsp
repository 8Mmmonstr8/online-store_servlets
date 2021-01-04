<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Details</title>
</head>
<body>

<jsp:include page="../blocks/header.jsp"></jsp:include>

<br/>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Order info</h2></caption>
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
        <c:forEach items="${orderedProducts}" var="orderedProduct" >
            <tr>
                <td>${orderedProduct.getName()}</td>
                <td>${orderedProduct.getQuantity()}</td>
                <td>${orderedProduct.getPrice()}</td>
                <td>${orderedProduct.getCategory().getName()}</td>
                <td>${orderedProduct.getDescription()}</td>
                <td>${orderedProduct.getOrder().getId()}</td>
                <td>${orderedProduct.getOrder().getOrderDate()}</td>
                <td>${orderedProduct.getOrder().isApproved()}</td>
            </tr>
        </c:forEach>
    </table>
    <p align="center"><b>Total sum: ${totalPrice}</b></p>
</div>

<a href="${pageContext.request.contextPath}/admin_home/orders">Back</a>

<jsp:include page="../blocks/footer.jsp"></jsp:include>

</body>
</html>