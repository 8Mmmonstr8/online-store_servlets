<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Orders</title>
</head>
<body>

<jsp:include page="../blocks/header.jsp"></jsp:include>

<%--<jsp:include page="blocks/header.jsp"></jsp:include>--%>

<div align="center">
<h3>Hello: ${user.email}</h3>

User Email: <b>${user.email}</b>
User Role: <b>${role}</b>
</div>

<br/>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Waiting for approval</h2></caption>
        <tr>
            <th>Id</th>
            <th>Order Date</th>
            <th>User Id</th>
            <th>Cart Id</th>
            <th>Is Order Approved</th>
            <th>Action</th>

        </tr>
        <c:forEach items="${notApprovedOrders}" var="notApprovedOrder" >
            <tr>
                <td>${notApprovedOrder.getId()}</td>
                <td>${notApprovedOrder.getOrderDate()}</td>
                <td>${notApprovedOrder.getUser().getId()}</td>
                <td>${notApprovedOrder.getCart().getId()}</td>
                <td>${notApprovedOrder.isApproved()}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin_home/orders/approve?orderId=${notApprovedOrder.getId()}">Approve</a>
                    <a href="${pageContext.request.contextPath}/admin_home/orders/decline?orderId=${notApprovedOrder.getId()}">Decline</a>
                    <a href="${pageContext.request.contextPath}/admin_home/orders/details?orderId=${notApprovedOrder.getId()}">Details</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

<br/>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Approved Orders</h2></caption>
        <tr>
            <th>Id</th>
            <th>Order Date</th>
            <th>User Id</th>
            <th>Cart Id</th>
            <th>Is Order Approved</th>
            <th>Action</th>

        </tr>
        <c:forEach items="${approvedOrders}" var="approvedOrder" >
            <tr>
                <td>${approvedOrder.getId()}</td>
                <td>${approvedOrder.getOrderDate()}</td>
                <td>${approvedOrder.getUser().getId()}</td>
                <td>${approvedOrder.getCart().getId()}</td>
                <td>${approvedOrder.isApproved()}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin_home/orders/details?orderId=${approvedOrder.getId()}">Details</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>



<a href="${pageContext.request.contextPath}/admin_home">Back</a>

<jsp:include page="../blocks/footer.jsp"></jsp:include>

</body>
</html>